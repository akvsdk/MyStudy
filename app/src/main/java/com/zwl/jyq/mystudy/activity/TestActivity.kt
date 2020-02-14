package com.zwl.jyq.mystudy.activity

import androidx.lifecycle.Observer
import com.zwl.jyq.mystudy.R
import com.zwl.jyq.mystudy.base.MyActivity
import com.zwl.jyq.mystudy.model.MyModel
import kotlinx.android.synthetic.main.activity_main.*


/**
 * Date:  2019/7/2.
 * version:  V1.0
 * Description:
 * @author Joy
 */

class TestActivity : MyActivity<MyModel>() {

    override fun setLayoutId(): Int = R.layout.activity_main

    override fun initView() {
    }

    override fun initData() {
        tv1.setOnClickListener {
           mViewModel.getLiST()
          //    mViewModel.test3()
        }
    }

    override fun startObserve() {
        mViewModel.mXys.observe(this, Observer {
            tv1.text = it.toString()
        })
    }


}