package com.zwl.jyq.mystudy.activity

import android.content.Context
import android.net.wifi.ScanResult
import android.net.wifi.WifiManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.zwl.jyq.base.base.BaseActivity
import com.zwl.jyq.mystudy.R
import com.zwl.jyq.mystudy.adapter.QuickAdapter
import kotlinx.android.synthetic.main.activity_wifi.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ticker


/**
 * Date:  2019/7/2.
 * version:  V1.0
 * Description:
 * @author Joy
 */

class WifiTestActivity : BaseActivity() {
    lateinit var wifiManager: WifiManager

    private var mWifiList: MutableList<ScanResult> = mutableListOf()
    lateinit var job: Job

    var adapter: QuickAdapter = QuickAdapter()
    override fun setLayoutId(): Int = R.layout.activity_wifi

    override fun initView() {
        recy.setHasFixedSize(true)
        recy.layoutManager = LinearLayoutManager(this)
        recy.adapter = adapter
    }

    override fun initData() {
        // scanInfo()
        // WifiTestActivity.this.registerReceiver(receiverWifi, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        job = GlobalScope.launch {
            scanWifi()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    private suspend fun scanWifi() = withContext(Dispatchers.Main) {
        val tickerChannel = ticker(delayMillis = 2000, initialDelayMillis = 0)
        coroutineScope {
            for (event in tickerChannel) {
                scanInfo()
                //L.e(Date().time.toString())
                //     delay(100)
            }
        }
    }

    fun scanInfo() {
        val wserviceName = Context.WIFI_SERVICE
        wifiManager = getSystemService(wserviceName) as WifiManager
        wifiManager.isWifiEnabled = true
        adapter.setNewData(wifiManager.scanResults)
    }


}