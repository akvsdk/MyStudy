package com.zwl.jyq.base.base

import androidx.annotation.StringRes
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zwl.jyq.base.bean.Respose
import com.zwl.jyq.base.bean.SharedData
import com.zwl.jyq.base.bean.SharedType
import com.zwl.jyq.base.retrofit.BaseRepository
import com.zwl.jyq.base.utils.Clzz
import com.zwl.jyq.base.utils.Setting
import com.zwl.jyq.base.utils.Setting.SUCCESS
import com.zwl.jyq.base.utils.showLog
import com.zwl.jyq.base.utils.tryCatch
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Date:  2019/7/2.
 * version:  V1.0
 * Description:
 * @author Joy
 */
open class BaseViewModel<T : BaseRepository> : ViewModel(),
    BaseContract, LifecycleObserver {

    // 通过反射注入 repository
    val repository: T by lazy { Clzz.getClass<T>(this).newInstance() }

    val sharedData by lazy { MutableLiveData<SharedData>() }

    fun launchUI(
        block: suspend CoroutineScope.() -> Unit,
        error: ((Throwable) -> Unit)? = null
    ): Job {
        return viewModelScope.launch(Dispatchers.Main) {
            tryCatch({
                block()
            }, {
                error?.invoke(it) ?: showException(it.toString())
            })
        }
    }

    fun <R> quickLaunch(block: Execute<R>.() -> Unit) {
        Execute<R>().apply(block)
    }

    override fun showToast(msg: String) {
        sharedData.value = SharedData(msg, type = SharedType.TOAST)
    }

    override fun showError(msg: String) {
        sharedData.value = SharedData(msg, type = SharedType.ERROR)
    }

    override fun showToast(@StringRes strRes: Int) {
        sharedData.value = SharedData(strRes = strRes, type = SharedType.RESOURCE)
    }

    override fun showEmptyView() {
        sharedData.value = SharedData(type = SharedType.EMPTY)
    }

    override fun showLoading() {
        sharedData.value = SharedData(type = SharedType.SHOW_LOADING)
    }

    override fun hideLoading() {
        sharedData.value = SharedData(type = SharedType.HIDE_LOADING)
    }


    private fun showException(exception: String) {
        exception.showLog()
        showError(Setting.UNKNOWN_ERROR)
    }

    inner class Execute<R> {

        private var startBlock: (() -> Unit)? = null
        private var successBlock: ((R?) -> Unit)? = null
        private var failBlock: ((String?) -> Unit)? = null
        private var exceptionBlock: ((Throwable?) -> Unit)? = null

        fun onStart(block: () -> Unit) {
            this.startBlock = block
        }

        fun request(block: suspend CoroutineScope.() -> Respose<R>?) {

            startBlock?.invoke()

            launchUI({ block()?.execute(successBlock, failBlock) }, exceptionBlock)
        }

        fun onSuccess(block: (R?) -> Unit) {
            this.successBlock = block
        }

        fun onFail(block: (String?) -> Unit) {
            this.failBlock = block
        }

        fun onException(block: (Throwable?) -> Unit) {
            this.exceptionBlock = block
        }
    }

    fun <R> Respose<R>.execute(success: ((R?) -> Unit)?, error: ((String) -> Unit)? = null) {
        if (this.code == SUCCESS) {
            success?.invoke(this.data)
            return
        }

        (this.message ?: Setting.MESSAGE_EMPTY).let { error?.invoke(it) ?: showToast(it) }
    }

}