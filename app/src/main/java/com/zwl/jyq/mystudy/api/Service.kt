package com.zwl.jyq.mystudy.api

import android.provider.ContactsContract
import com.zwl.jyq.base.bean.Respose
import com.zwl.jyq.mystudy.bean.HandleBook
import com.zwl.jyq.mystudy.bean.UpdateBean
import retrofit2.http.*


/**
 * Date:  2019/6/24.
 * version:  V1.0
 * Description:
 * @author Joy
 */
interface Service {

    @POST("https://zchat.zwltech.com/interface/sys.ashx")
    @FormUrlEncoded
    suspend fun updateApp(@Query("action") action: String, @FieldMap param: MutableMap<String, Any>): UpdateBean

    @POST("http://39.107.78.243:8095/captcha")
    @FormUrlEncoded
    suspend fun doPost(
        @Field("phone") userName: String,
        @Field("type") passWord: String
    ): ContactsContract.CommonDataKinds.Phone


    @POST("https://nps.zwltech.com/zwltech/api/v1/game/gethandbook")
    @FormUrlEncoded
    suspend fun getXueYuanList(@FieldMap param: MutableMap<String, Any>): Respose<List<HandleBook>>


    @POST("https://gm.zwltech.com:9192/main/login")
    @FormUrlEncoded
    suspend fun getLogin(@FieldMap param: MutableMap<String, Any>): Respose<Map<String, String>>

}