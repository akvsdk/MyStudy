package com.zwl.jyq.base.utils

import java.lang.reflect.ParameterizedType

/**
 * Date:  2019/8/1.
 * version:  V1.0
 * Description:
 * @author Joy
 */
object Clzz {

    @Suppress("UNCHECKED_CAST")
    fun <T> getClass(t: Any): Class<T> {
        // 通过反射 获取父类泛型 (T) 对应 Class类
        return (t.javaClass.genericSuperclass as ParameterizedType)
            .actualTypeArguments[0]
                as Class<T>
    }
}