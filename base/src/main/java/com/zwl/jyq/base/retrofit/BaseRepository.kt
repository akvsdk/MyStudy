package com.zwl.jyq.base.retrofit

import com.zwl.jyq.base.bean.Respose

/**
 * Created by luyao
 * on 2019/4/10 9:41
 */
open class BaseRepository {


    suspend fun <T : Any> apiCall(call: suspend () -> Respose<T>): Respose<T> {
        return call.invoke()
    }

}