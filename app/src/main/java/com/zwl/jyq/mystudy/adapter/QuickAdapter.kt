package com.zwl.jyq.mystudy.adapter

import android.net.wifi.ScanResult
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zwl.jyq.mystudy.R

/**
 * Date:  2019/7/3.
 * version:  V1.0
 * Description:
 * @author Joy
 */
class QuickAdapter : BaseQuickAdapter<ScanResult, BaseViewHolder>(R.layout.item_wifi) {


    override fun convert(helper: BaseViewHolder, item: ScanResult?) {
        helper.setText(R.id.wifi_name, item?.SSID)
     //   helper.setText(R.id.wifi_address, item.venueName.toString())
        helper.setText(R.id.wifi_way, item?.capabilities)
        item?.level?.let { helper.setText(R.id.wifi_strength, it) }
    }



}