package zyq.library.http;

import okhttp3.OkHttpClient;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import zyq.library.http.interceptor.AddCookieInterceptor;
import zyq.library.http.interceptor.DefaultInterceptor;
import zyq.library.http.interceptor.ReceivedCookiesInterceptor;

/**
 * @author: zhuYunQi
 * @mail: zhuyunqi_88@163.com
 * @date: ${date}
 * @describe:
 */
public class RetrofitUtil {
    public static <T> T getDefaultRetrofit(Class<T> service, String url) {
        OkHttpClient client = OkHttpClientBuilder.getClient().addInterceptor(new DefaultInterceptor()).build();
        return new retrofit2.Retrofit.Builder()
                .client(client)
                .baseUrl(url)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(service);
    }

    public static <T> T getReceivedCookiesRetrofit(Class<T> service, String url) {
        OkHttpClient client = OkHttpClientBuilder.getClient().addInterceptor(new ReceivedCookiesInterceptor()).build();
        return new retrofit2.Retrofit.Builder()
                .client(client)
                .baseUrl(url)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(service);
    }

    public static <T> T getAddCookieRetrofit(Class<T> service, String url) {
        OkHttpClient client = OkHttpClientBuilder.getClient().addInterceptor(new AddCookieInterceptor()).build();
        return new retrofit2.Retrofit.Builder()
                .client(client)
                .baseUrl(url)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(service);
    }
}
