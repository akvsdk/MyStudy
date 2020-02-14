package com.zwl.jyq.base.okhttp

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Date:  2019/8/20.
 * version:  V1.0
 * Description:
 * @author Joy
 */
class JwtAuthenticationInterceptor : Interceptor {
    private var jwtToken: String? = null

    fun setJwtToken(jwtToken: String): Interceptor {
        this.jwtToken = jwtToken
        return this
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val builder = original.newBuilder()
            .header("Authorization", "Bearer $jwtToken")
        //String.format("Bearer %s", jwtToken));
        val request = builder.build()
        return chain.proceed(request)
    }
}