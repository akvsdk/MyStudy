package com.zwl.jyq.mystudy.model

import androidx.lifecycle.MutableLiveData
import com.safframework.log.L
import com.zwl.jyq.base.base.BaseViewModel
import com.zwl.jyq.mystudy.api.MyRepository
import com.zwl.jyq.mystudy.bean.HandleBook
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Date:  2019/7/2.
 * version:  V1.0
 * Description:
 * @author Joy
 */
class MyModel : BaseViewModel<MyRepository>() {
    val mXys: MutableLiveData<List<HandleBook>> = MutableLiveData()

    fun getLiST() {
        quickLaunch<List<HandleBook>> {
            onStart { showLoading() }

            request { repository.getXueYuanList() }

            onSuccess {
                hideLoading()
                mXys.postValue(it)
            }

        }

    }

    fun testKtor() {

        launchUI({
            withContext(Dispatchers.IO) {
                val token = repository.test1()
                L.d(token.data.toString())
                token.data["token"]?.let { repository.test2(it) }
            }
        })
    }

    fun test3() {
        launchUI({
            withContext(Dispatchers.IO) {
                val res = repository.test3()
                L.d(res)
            }
        })

    }

}