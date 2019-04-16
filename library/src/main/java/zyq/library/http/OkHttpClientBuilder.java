package zyq.library.http;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * @author: zhuYunQi
 * @mail: zhuyunqi_88@163.com
 * @date: ${date}
 * @describe:
 */
public class OkHttpClientBuilder {

    private static final int DEFAULT_TIME = 10;

    /**
     * 初始化okhttp
     *
     * @return
     */
    public static OkHttpClient.Builder getClient() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder()
                //设置读取超时时间
                .readTimeout(DEFAULT_TIME, TimeUnit.SECONDS)
                //设置请求超时时间
                .connectTimeout(DEFAULT_TIME, TimeUnit.SECONDS)
                //设置写入超时时间
                .writeTimeout(DEFAULT_TIME, TimeUnit.SECONDS)
                //设置出现错误进行重新连接。
                .retryOnConnectionFailure(true);
        return builder;
    }
}
