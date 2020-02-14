package com.zwl.jyq.mystudy.api

import com.zwl.jyq.base.bean.Respose
import com.zwl.jyq.base.okhttp.get
import com.zwl.jyq.base.okhttp.http
import com.zwl.jyq.base.okhttp.post
import com.zwl.jyq.base.retrofit.BaseRepository
import com.zwl.jyq.mystudy.bean.HandleBook
import com.zwl.jyq.mystudy.bean.XuenYuanBean

/**
 * Date:  2019/7/2.
 * version:  V1.0
 * Description:
 * @author Joy
 */
class MyRepository : BaseRepository() {

    /**
     *         return "https://nps.zwltech.com/zwltech/api/v1/game/gethandbook".http().params(
    "apiName" to "GAME_HANDLE_BOOK", "difficulty" to 1,
    "init" to false
    ).post<String>().await()!!
     */

    suspend fun getXueYuanList(): Respose<List<HandleBook>> {
        return apiCall {
            MyRetrofitClient.service.getXueYuanList(
                mutableMapOf(
                    "apiName" to "GAME_HANDLE_BOOK", "difficulty" to 1,
                    "init" to false
                )
            )
        }
    }

    suspend fun getXueYuanList2(): Respose<List<XuenYuanBean>> {
        return apiCall { "http://192.168.10.127:8066/xueyuan/index".http().get<Respose<List<XuenYuanBean>>>().await()!! }
    }

    suspend fun test1(): Respose<Map<String, String>> {
        //       return "http://192.168.10.127:8067/main/login".http().params("wxcode" to "061uVrTu1DzYfe0hwOWu1WTITu1uVrTG").post<String>().await()!!
//        return apiCall {
//            // "https://192.168.10.127:9191/main/login".http().params("wxcode" to "001TVHTk2Q9BsD0D8wTk2NrQTk2TVHTf").post<Respose<Map<String, String>>>().await()!!
//            "https://gm.zwltech.com:9192/main/login".http().params("wxcode" to "001TVHTk2Q9BsD0D8wTk2NrQTk2TVHTf").post<Respose<Map<String, String>>>().await()!!
//        }
        return apiCall {
            MyRetrofitClient.service.getLogin(
                mutableMapOf(
                    "wxcode" to "001TVHTk2Q9BsD0D8wTk2NrQTk2TVHTf"
                )
            )
        }
    }

    suspend fun test2(token: String): String {
        return "https://gm.zwltech.com:9192/secret".http().get<String>(token).await()!!
    }

    suspend fun test3(): String {
        return "https://nps.zwltech.com/zwltech/api/v1/game/gethandbook".http().params(
            "apiName" to "GAME_HANDLE_BOOK", "difficulty" to 1,
            "init" to false
        ).post<String>().await()!!
    }
}