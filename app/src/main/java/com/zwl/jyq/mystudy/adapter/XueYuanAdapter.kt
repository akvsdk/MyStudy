package com.zwl.jyq.mystudy.adapter

import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.zwl.jyq.mystudy.R
import com.zwl.jyq.mystudy.bean.XuenYuanBean

/**
 * Date:  2019/7/3.
 * version:  V1.0
 * Description:
 * @author Joy
 */
class XueYuanAdapter : LoadMoreAdapter<XuenYuanBean, BaseViewHolder>(R.layout.item_wifi) {


    override fun convert(helper: BaseViewHolder, item: XuenYuanBean?) {
        //   helper.setText(R.id.wifi_address, item.venueName.toString())
        //     helper.setText(R.id.wifi_way, item.capabilities)
        helper.setText(R.id.wifi_strength, item?.name)
    }


}