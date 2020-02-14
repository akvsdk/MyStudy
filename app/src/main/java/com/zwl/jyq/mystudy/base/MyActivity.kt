package com.zwl.jyq.mystudy.base

import com.xuexiang.xui.utils.WidgetUtils
import com.xuexiang.xui.widget.dialog.LoadingDialog
import com.zwl.jyq.base.base.BaseVMActivity
import com.zwl.jyq.base.base.BaseViewModel


/**
 * Date:  2019/8/5.
 * version:  V1.0
 * Description:
 * @author Joy
 */
open abstract class MyActivity<VM : BaseViewModel<*>> : BaseVMActivity<VM>() {

    val mLoadingDialog: LoadingDialog by lazy {
        WidgetUtils.getLoadingDialog(this)
            .setIconScale(0.4F)
            .setLoadingSpeed(8)
    }

    override fun showLoading() {
        mLoadingDialog.show()
    }

    override fun showSuccess() {
        if (mLoadingDialog.isShowing) {
            mLoadingDialog.dismiss()
        }
        super.showSuccess()
    }

    override fun showError(msg: String) {
        if (mLoadingDialog.isShowing) {
            mLoadingDialog.dismiss()
        }
        super.showError(msg)
    }

    override fun hideLoading() {
        if (mLoadingDialog.isShowing) {
            mLoadingDialog.dismiss()
        }
        super.hideLoading()
    }
}