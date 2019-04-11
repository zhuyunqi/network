package zyq.library.http.interceptor;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import zyq.library.utils.Constants;
import zyq.library.utils.ContextUtil;
import zyq.library.utils.SharedPreferencesUtil;

/**
 * @author: zhuYunQi
 * @mail: zhuyunqi_88@163.com
 * @date: 2018/3/14
 * @describe:添加请求头
 */
public class AddCookieInterceptor implements Interceptor {

    private SharedPreferencesUtil sp = SharedPreferencesUtil.getInstance(ContextUtil.getContext());

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl.Builder authorizedUrlBuilder = request.url().newBuilder();
        Request newRequest = request.newBuilder()
                .addHeader("Cookie", sp.getStirng(Constants.COOKIE))
                .method(request.method(), request.body())
                .url(authorizedUrlBuilder.build())
                .build();
        return chain.proceed(newRequest);

    }
}
