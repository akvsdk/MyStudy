package com.zwl.jyq.mystudy.adapter

import androidx.annotation.LayoutRes
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 * Date:  2020/1/21.
 * version:  V1.0
 * Description:
 * @author Joy
 */
abstract class LoadMoreAdapter<T, VH : BaseViewHolder>(@LayoutRes private val layoutRes: Int, data: MutableList<T>? = null) :
    BaseQuickAdapter<T, VH>(layoutRes, data), LoadMoreModule {


}