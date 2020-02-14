package com.zwl.jyq.mystudy.utils

import android.content.Context
import android.net.ConnectivityManager

/**
 * Date:  2019/6/24.
 * version:  V1.0
 * Description:
 * @author Joy
 */
class NetWorkUtils {
    companion object {
        fun isNetworkAvailable(context: Context): Boolean {
            val manager = context.applicationContext.getSystemService(
                Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val info = manager.activeNetworkInfo
            return !(null == info || !info.isAvailable)
        }
    }
}