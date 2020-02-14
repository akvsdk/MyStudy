package com.zwl.jyq.base.retrofit

import com.zwl.jyq.base.okhttp.HttpLogInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Date:  2019/6/24.
 * version:  V1.0
 * Description:
 * @author Joy
 */
abstract class BaseRetrofitClient {
    companion object {
        private const val TIME_OUT = 5
    }

    private val client: OkHttpClient
        get() {
            val builder = OkHttpClient.Builder()
//            val logging = HttpLoggingInterceptor(HttpLogger())
//            if (BuildConfig.DEBUG) {
//                logging.level = HttpLoggingInterceptor.Level.BODY
//            } else {
//                logging.level = HttpLoggingInterceptor.Level.BASIC
//            }
            val logging = HttpLogInterceptor(false)
            builder.addInterceptor(logging)
                .connectTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)

            handleBuilder(builder)

            return builder.build()
        }

    protected abstract fun handleBuilder(builder: OkHttpClient.Builder)

    fun <S> getService(serviceClass: Class<S>, baseUrl: String): S {
        return Retrofit.Builder()
            .client(client)

//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .addConverterFactory(CustomCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build().create(serviceClass)
    }
}