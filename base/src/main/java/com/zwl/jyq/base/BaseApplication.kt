package com.zwl.jyq.base

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.safframework.log.L

/**
 * Date:  2019/8/5.
 * version:  V1.0
 * Description:
 * @author Joy
 */
abstract class BaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        L.init("J1ang")
    }

    companion object {
        lateinit var INSTANCE: BaseApplication
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        //解决4.x运行崩溃的问题
        MultiDex.install(this)
    }
}