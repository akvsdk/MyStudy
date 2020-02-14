package com.zwl.jyq.mystudy

import com.xuexiang.xui.XUI
import com.zwl.jyq.base.BaseApplication


/**
 * Date:  2019/4/28.
 * version:  V1.0
 * Description:
 * @author Joy
 */
open class App : BaseApplication() {
    override fun onCreate() {
        super.onCreate()

        XUI.init(this)
        XUI.debug(true)

    }
}