package com.zwl.jyq.base.base

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.zwl.jyq.base.bean.SharedData
import com.zwl.jyq.base.bean.SharedType
import com.zwl.jyq.base.utils.Clzz
import com.zwl.jyq.base.utils.showLog
import com.zwl.jyq.base.utils.toast


/**
 * Date:  2019/7/2.
 * version:  V1.0
 * Description:
 * @author Joy
 */
abstract class BaseVMActivity<VM : BaseViewModel<*>> : BaseActivity(), LifecycleObserver {

    lateinit var mViewModel: VM

    private fun initVM() {
        mViewModel = ViewModelProviders.of(this).get(Clzz.getClass(this))
        mViewModel.run { let(lifecycle::addObserver) }
        mViewModel.sharedData.observe(this, observer)
        startObserve()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        initVM()
        super.onCreate(savedInstanceState)
    }

    open fun startObserve() {}

    override fun onDestroy() {
        mViewModel.let {
            lifecycle.removeObserver(it)
        }
        super.onDestroy()
    }


    open fun showSuccess() {
        "showSuccess".showLog()
    }

    open fun showError(msg: String) {
        toast(msg)
        "msg".showLog()
    }

    open fun showToast(msg: String) {
        toast(msg)
        "msg".showLog()
    }

    open fun showLoading() {
        "showLoading".showLog()
    }

    open fun hideLoading() {
        "showLoading".showLog()
    }

    open fun showEmptyView() {}

    open fun showTips(@StringRes strRes: Int) {
        toast(getString(strRes))
    }


    // 分发状态
    private val observer by lazy {
        Observer<SharedData> { sharedData ->
            sharedData?.run {
                when (type) {
                    SharedType.RESOURCE -> showTips(strRes)
                    SharedType.ERROR -> showError(msg)
                    SharedType.SHOW_LOADING -> showLoading()
                    SharedType.HIDE_LOADING -> hideLoading()
                    SharedType.TOAST -> showToast(msg)
                    SharedType.EMPTY -> showEmptyView()
                }
            }
        }
    }


}