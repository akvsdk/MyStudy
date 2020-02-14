package com.zwl.jyq.base.bean

/**
 * Date:  2019/7/2.
 * version:  V1.0
 * Description:
 * @author Joy
 */
data class Respose<out T>(val code: Int, val message: String?, val data: T)