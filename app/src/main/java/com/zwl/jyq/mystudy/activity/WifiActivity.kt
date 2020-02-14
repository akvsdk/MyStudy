package com.zwl.jyq.mystudy.activity

import com.zwl.jyq.mystudy.adapter.XueYuanAdapter
import com.zwl.jyq.mystudy.base.ListActivity
import com.zwl.jyq.mystudy.bean.XuenYuanBean
import com.zwl.jyq.mystudy.model.MyModel


/**
 * Date:  2019/12/11.
 * version:  V1.0
 * Description:
 * @author Joy
 */
class WifiActivity : ListActivity<MyModel, XuenYuanBean>() {

    override fun bindAdapter() = XueYuanAdapter()

    override fun onRefreshData() {
        adapter.data.clear()
        val data = mutableListOf<XuenYuanBean>()
        for (i in 1..30) {
            data.add(XuenYuanBean(i, "haha"))
        }
        addData(data)
    }

    override fun onLoadMoreData() {
        val data = mutableListOf<XuenYuanBean>()
        if (adapter.data.size < 50) {
            for (i in 1..5) {
                data.add(XuenYuanBean(i, "haha$i"))
            }
        }
        addData(data)
    }


}