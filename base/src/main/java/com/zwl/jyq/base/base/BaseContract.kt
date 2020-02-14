package com.zwl.jyq.base.base

import androidx.annotation.StringRes

/**
 * Date:  2020/1/22.
 * version:  V1.0
 * Description:
 * @author Joy
 */
interface BaseContract {

    fun showError(msg: String)

    fun showToast(msg: String)

    fun showToast(@StringRes strRes: Int)

    fun showEmptyView()

    fun showLoading()

    fun hideLoading()
}