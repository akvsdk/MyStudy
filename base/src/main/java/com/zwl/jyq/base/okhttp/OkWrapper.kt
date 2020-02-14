package com.zwl.jyq.base.okhttp

import me.jessyan.progressmanager.ProgressManager
import okhttp3.Call
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

/**
 * Date:  2019/6/24.
 * version:  V1.0
 * Description:
 * @author Joy
 */
object OkWrapper {
    const val httpTimeout = 15000L
    val globalHeaders = arrayListOf<Pair<String, String>>()
    val requestCache = hashMapOf<Any, Call>()
    var baseUrl = ""
    var okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .writeTimeout(httpTimeout, TimeUnit.MILLISECONDS)
        .readTimeout(httpTimeout, TimeUnit.MILLISECONDS)
        .connectTimeout(httpTimeout, TimeUnit.MILLISECONDS)
        .addNetworkInterceptor(HttpLogInterceptor())
        //.cookieJar(PersistentCookieStore())
        .sslSocketFactory(
            HttpsUtils.getSslSocketFactory().sSLSocketFactory,
            HttpsUtils.getSslSocketFactory().trustManager
        )
        .build()

    init {
        okHttpClient = ProgressManager.getInstance().with(okHttpClient.newBuilder()).build()
    }

    fun headers(vararg header: Pair<String, String>): OkWrapper {
        header.forEach {
            globalHeaders.add(it)
        }
        return this
    }

    fun interceptors(vararg interceptor: Interceptor): OkWrapper {
        val builder = okHttpClient.newBuilder()
        interceptor.forEach {
            builder.addInterceptor(it)
        }
        okHttpClient = builder.build()
        return this
    }

    fun baseUrl(url: String): OkWrapper {
        this.baseUrl = url
        return this
    }

    fun setClient(client: OkHttpClient): OkWrapper {
        this.okHttpClient = client
        return this
    }


    fun cancel(tag: Any){
        requestCache[tag]?.cancel()
        requestCache.remove(tag)
    }
}