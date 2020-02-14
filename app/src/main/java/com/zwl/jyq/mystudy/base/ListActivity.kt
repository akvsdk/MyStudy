package com.zwl.jyq.mystudy.base

import androidx.recyclerview.widget.LinearLayoutManager
import com.zwl.jyq.base.base.BaseVMActivity
import com.zwl.jyq.base.base.BaseViewModel
import com.zwl.jyq.mystudy.R
import com.zwl.jyq.mystudy.adapter.LoadMoreAdapter
import kotlinx.android.synthetic.main.activity_list.*

/**
 * Date:  2019/8/5.
 * version:  V1.0
 * Description:
 * @author Joy
 */
abstract class ListActivity<VM : BaseViewModel<*>, T> : BaseVMActivity<VM>() {


    // 当前页
    var page = 0

    var total = 0

    var refreshColor = R.color.colorPrimary

    val adapter by lazy { bindAdapter() }

    abstract fun bindAdapter(): LoadMoreAdapter<T, *>


    /**
     *  下拉刷新
     */
    abstract fun onRefreshData()

    /**
     *  上拉加载更多
     */
    abstract fun onLoadMoreData()


    override fun setLayoutId(): Int = R.layout.activity_list

    // 判断是否有更多数据
    fun hasMore() = adapter.data.size < this.total

    fun addData(newData: MutableList<T>) {
        if (newData.isEmpty()) {
            adapter.loadMoreModule?.loadMoreComplete()
            return
        }

        if (srlRefresh.isRefreshing) {
            srlRefresh.isRefreshing = false
            adapter.setNewData(newData)
            return
        }

        adapter.addData(newData)
        adapter.loadMoreModule?.loadMoreComplete()
    }

    override fun initData() {
        super.initData()
        onRefreshData()
    }


    override fun initView() {

        // 初始化 SwipeRefreshLayout
        initRefresh()

        // 初始化 article
        initRecyclerView()
    }


    private fun initRefresh() {
        // 设置 下拉刷新 loading 颜色
        srlRefresh.setColorSchemeResources(refreshColor)
        srlRefresh.setOnRefreshListener {
            page = 0
            onRefreshData()
        }
    }

    private fun initRecyclerView() {

        rvContent.layoutManager = LinearLayoutManager(this)
        rvContent.adapter = adapter

        // 上拉加载更多
        adapter.loadMoreModule?.setOnLoadMoreListener {
            ++page
            onLoadMoreData()
        }
    }


}