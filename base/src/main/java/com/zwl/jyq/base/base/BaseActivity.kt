package com.zwl.jyq.base.base

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Date:  2019/7/2.
 * version:  V1.0
 * Description:
 * @author Joy
 */
abstract class BaseActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setLayoutId())
        initView()
        initData()
        setListener()
    }

    abstract fun setLayoutId(): Int
    abstract fun initView()
    open fun initData() {}
    open fun setListener() {}

    protected fun startActivity(z: Class<*>) {
        startActivity(Intent(this, z))
    }

    protected fun startActivity(z: Class<*>, name: String, value: Boolean) {
        val intent = Intent(this, z).putExtra(name, value)
        startActivity(intent)
    }
}