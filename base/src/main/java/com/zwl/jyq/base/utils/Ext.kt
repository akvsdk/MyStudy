package com.zwl.jyq.base.utils

import android.content.Context
import android.util.Log
import android.widget.Toast


/**
 * Date:  2019/7/2.
 * version:  V1.0
 * Description:
 * @author Joy
 */

/**
 * 通用toast
 */
fun Context.toast(value: String) = toast { value }

inline fun Context.toast(value: () -> String) =
    Toast.makeText(this, value(), Toast.LENGTH_SHORT).show()


/**
 * 判断是否存在该应用
 *
 * @param context 上下文
 * @param appName APP B 的包名
 * @return 如果存在该应用，则返回true；否则返回false
 */
fun isClientAvailable(context: Context, appName: String): Boolean {
    val packageManager = context.packageManager
    val pinfo = packageManager.getInstalledPackages(0)
    if (pinfo != null) {
        for (i in pinfo.indices) {
            val pn = pinfo[i].packageName
            if (pn.equals(appName, ignoreCase = true)) {
                return true
            }
        }
    }
    return false
}



fun String.showLog() {
    Log.d(this.javaClass.name, "<------------------------------")
    Log.d(this.javaClass.name, "[${this.javaClass.name}]:  $this")
    Log.d(this.javaClass.name, "------------------------------->")
}


inline fun tryCatch(tryBlock: () -> Unit, catchBlock: (Throwable) -> Unit = {}) {
    try {
        tryBlock()
    } catch (t: Throwable) {
        t.printStackTrace()
        catchBlock(t)
    }
}
