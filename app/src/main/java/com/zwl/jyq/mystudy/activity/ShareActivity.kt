//package com.zwl.jyq.mystudy.activity
//
//import android.content.Intent
//import android.net.Uri
//import android.widget.Toast
//import com.safframework.log.L
//import com.zwl.jyq.base.base.BaseActivity
//import com.zwl.jyq.mystudy.R
//import com.zwl.jyq.mystudy.utils.sxapi.OnResponseListener
//import com.zwl.jyq.mystudy.utils.sxapi.SXShare
//import kotlinx.android.synthetic.main.activity_main.*
//
///**
// * Date:  2019/7/4.
// * version:  V1.0
// * Description:
// * @author Joy
// */
//class ShareActivity : BaseActivity() {
//    private val sxShare: SXShare by lazy {
//        SXShare(this).register()
//    }
//    private lateinit var haha: String
//
//    override fun setLayoutId(): Int = R.layout.activity_main
//
//
//    override fun initView() {
//        sxShare.setListener(object : OnResponseListener {
//            override fun onSuccess() {
//                L.d("分享成功")
//            }
//
//            override fun onCancel() {
//                L.d("分享成功")
//            }
//
//            override fun onFail(message: String?) {
//                L.d(message)
//            }
//
//        })
//
//    }
//
//    override fun initData() {
//
//        tv1.setOnClickListener {
//
//            val data = Uri.parse("zwltech://com.zwltech.chat/profile?key=mykey&data=123")
//            val intent = Intent(Intent.ACTION_VIEW, data)
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//            try {
//                startActivityForResult(intent, RESULT_OK)
//            } catch (e: Exception) {
//                e.printStackTrace()
//                Toast.makeText(this, "没有匹配的APP，请下载安装", Toast.LENGTH_SHORT).show()
//            }
//
//        }
//    }
//
//}