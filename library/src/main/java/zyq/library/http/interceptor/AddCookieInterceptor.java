package zyq.library.http.interceptor;

import android.util.Log;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import zyq.library.utils.Constants;
import zyq.library.utils.NetworkContext;
import zyq.library.utils.SharedPreferencesUtil;

/**
 * @author: zhuYunQi
 * @mail: zhuyunqi_88@163.com
 * @date: 2018/3/14
 * @describe:添加请求头
 */
public class AddCookieInterceptor implements Interceptor {

    private SharedPreferencesUtil sp = SharedPreferencesUtil.getInstance(NetworkContext.getContext());

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl.Builder authorizedUrlBuilder = request.url().newBuilder();
        Request newRequest = request.newBuilder()
                .addHeader("Cookie", sp.getStirng(Constants.COOKIE))
                .method(request.method(), request.body())
                .url(authorizedUrlBuilder.build())
                .build();
        Log.d("zyq-network:url:", request.url().toString());
        return chain.proceed(newRequest);

    }
}
