package com.zwl.jyq.mystudy

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import com.safframework.log.L
import com.zwl.jyq.base.okhttp.get
import com.zwl.jyq.base.okhttp.http
import com.zwl.jyq.base.utils.toast
import com.zwl.jyq.mystudy.api.MyRetrofitClient
import com.zwl.jyq.mystudy.bean.UpdateBean
import com.zwl.jyq.mystudy.bean.XuenYuanBean
import com.zwl.jyq.mystudy.utils.md5
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private var vm: HttpExtVM? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vm = ViewModelProviders.of(this).get(HttpExtVM::class.java)

//        vm!!.data.observe(this, Observer<UpdateBean> {
//
//            tv1.text = if (it == null) "请求失败" else it.toString()
//        })

        vm!!.xys.observe(this, Observer<List<XuenYuanBean>> {
            toast(it.size.toString())
            tv1.text = if (it == null) "请求失败" else it.toString()
        })
        tv1.setOnClickListener {
            vm!!.doRetrfit()
        }

    }

    class HttpExtVM : ViewModel() {
        //   val data = MutableLiveData<UpdateBean>()
        val xys = MutableLiveData<List<XuenYuanBean>>()

        fun doRetrfit() {
            viewModelScope.launch {
                //                val bean = MyRetrofitClient.service.updateApp("appconfig", createMap())
//                data.postValue(bean)

                //       val bean = MyRetrofitClient.service.getXueYuanList().data
                //       xys.postValue(bean)
                val bean = MyRetrofitClient.service.doPost("15172321248", "0")
                Log.d("123", bean.toString())
            }
        }

        fun doHttp() {
            viewModelScope.launch(Dispatchers.IO) {
                L.e(Thread.currentThread().name)
                val bean = "https://zchat.zwltech.com/interface/sys.ashx".http(this)
                    .params("action" to "appconfig")
                    .params(createMap()).get<UpdateBean>().await()
                //data.postValue(bean)
                //   val bean = MyRetrofitClient.service.doPost("15172321248", "0")
                //  Log.d("123", bean)
            }

        }


        /**
         *  封装固定传参封装
         */
        fun createMap(vararg pairs: Pair<String, Any>): MutableMap<String, Any> {
            val map = mutableMapOf<String, Any>()
            map["appver"] = "1.3.2"
            map["sysver"] = "android," + android.os.Build.VERSION.RELEASE
            map.putAll(pairs.toMap())
            var sign = ""
            val temp = map.filter { it.key != "action" }.toSortedMap()
            for ((key, vaule) in temp) {
                sign += "$key=$vaule&"
            }
            sign = sign.substring(0, sign.length - 1)
            map["sign"] = "zwltech:$sign".trim().md5()
            return map
        }


    }
}
