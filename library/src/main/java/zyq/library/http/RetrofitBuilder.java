package zyq.library.http;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import zyq.library.http.interceptor.AddCookieInterceptor;
import zyq.library.http.interceptor.DefaultInterceptor;
import zyq.library.http.interceptor.ReceivedCookiesInterceptor;

/**
 * @author: zhu yun qi
 * @mail: zhuyunqi_88@163.com
 * @date: 2018/3/14
 * @describe: 此类就是初始化Retrofit，提供一个静态方法初始化Retrofit
 */

public class RetrofitBuilder {

    /**
     * 请求超时时间
     */
    private static final int DEFAULT_TIMEOUT = 10 * 1000;


    /**
     * 默认RetrofitBuilder
     *
     * @return
     */
    public static Retrofit.Builder getDefaultInstance() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.addInterceptor(new DefaultInterceptor());
        return new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Host.ApiHost);
    }


    /**
     * 请求头带有Cookie的RetrofitBuilder
     *
     * @return
     */
    public static Retrofit.Builder getPostCookieInstance() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.addInterceptor(new AddCookieInterceptor());
        return new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Host.ApiHost);
    }

    /**
     * 保存cookie的RetrofitBuilder
     * 一般在发送验证码的时候保存cookie
     *
     * @return
     */
    public static Retrofit.Builder getSaveCookiesInstance() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.addInterceptor(new ReceivedCookiesInterceptor());
        return new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Host.ApiHost);
    }


}
