package zyq.library.http;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import zyq.library.utils.Constants;

/**
 * @author: zhuYunQi
 * @mail: zhuyunqi_88@163.com
 * @date: ${date}
 * @describe:
 */
public class OkHttpClientBuilder {

    /**
     * 初始化okhttp
     *
     * @return
     */
    public static OkHttpClient.Builder getClient() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder()
                //设置读取超时时间
                .readTimeout(Constants.TimeOut.readTimeout, TimeUnit.SECONDS)
                //设置请求超时时间
                .connectTimeout(Constants.TimeOut.connectTimeout, TimeUnit.SECONDS)
                //设置写入超时时间
                .writeTimeout(Constants.TimeOut.writeTimeout, TimeUnit.SECONDS)
                //设置出现错误进行重新连接。
                .retryOnConnectionFailure(true);
        return builder;
    }
}
