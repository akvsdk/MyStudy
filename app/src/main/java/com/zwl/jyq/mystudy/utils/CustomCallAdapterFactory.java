package com.zwl.jyq.mystudy.utils;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * Date:  2019/6/24.
 * version:  V1.0
 * Description:
 *
 * @author Joy
 */
public class CustomCallAdapterFactory extends Converter.Factory {
    public static final CustomCallAdapterFactory INSTANCE = new CustomCallAdapterFactory();

    public static CustomCallAdapterFactory create() {
        return INSTANCE;
    }

    // 我们只关实现从ResponseBody 到 String 的转换，所以其它方法可不覆盖
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        if (type == String.class) {
            return StringConverter.INSTANCE;
        }
        //其它类型我们不处理，返回null就行
        return null;
    }

    /**
     * 自定义Converter实现RequestBody到String的转换
     */
    public static class StringConverter implements Converter<ResponseBody, String> {

        public static final StringConverter INSTANCE = new StringConverter();

        @Override
        public String convert(ResponseBody value) throws IOException {
            return value.string();
        }
    }
}
