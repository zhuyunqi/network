package zyq.library.http.interceptor;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author: zhuYunQi
 * @mail: zhuyunqi_88@163.com
 * @date: 2018/3/14
 * @describe: 默认不带token
 */
public class DefaultInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl.Builder authorizedUrlBuilder = request.url().newBuilder();
        Request newRequest = request.newBuilder()
                .method(request.method(), request.body())
                .url(authorizedUrlBuilder.build())
                .build();
        return chain.proceed(newRequest);

    }
}
