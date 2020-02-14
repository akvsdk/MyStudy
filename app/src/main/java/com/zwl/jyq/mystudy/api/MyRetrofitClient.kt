package com.zwl.jyq.mystudy.api
import com.zwl.jyq.base.BaseApplication
import com.zwl.jyq.base.okhttp.HttpsUtils
import com.zwl.jyq.base.retrofit.BaseRetrofitClient
import com.zwl.jyq.base.utils.NetWorkUtils
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.OkHttpClient

import java.io.File


/**
 * Date:  2019/6/24.
 * version:  V1.0
 * Description:
 * @author Joy
 */
object MyRetrofitClient : BaseRetrofitClient() {

    val service by lazy { getService(Service::class.java, "http://192.168.10.127:8066/") }

    //private val cookieJar by lazy { PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(App.CONTEXT)) }

    override fun handleBuilder(builder: OkHttpClient.Builder) {
        val httpCacheDirectory = File(BaseApplication.INSTANCE.cacheDir, "responses")
        val cacheSize = 10 * 1024 * 1024L // 10 MiB
        val cache = Cache(httpCacheDirectory, cacheSize)
        builder.cache(cache)
            // .cookieJar(cookieJar)
            .addInterceptor { chain ->
                var request = chain.request()
                if (!NetWorkUtils.isNetworkAvailable(BaseApplication.INSTANCE)) {
                    request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build()
                }
                val response = chain.proceed(request)
                if (!NetWorkUtils.isNetworkAvailable(BaseApplication.INSTANCE)) {
                    val maxAge = 60 * 60
                    response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, max-age=$maxAge")
                        .build()
                } else {
                    val maxStale = 60 * 60 * 24 * 28 // tolerate 4-weeks stale
                    response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                        .build()
                }

                response
            }
            .sslSocketFactory(
                HttpsUtils.getSslSocketFactory().sSLSocketFactory,
                HttpsUtils.getSslSocketFactory().trustManager
            )
    }
}