package zyq.library.http.interceptor;

import android.util.Log;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import zyq.library.utils.Constants;
import zyq.library.utils.NetworkContext;
import zyq.library.utils.SharedPreferencesUtil;

/**
 * @author: zhuYunQi
 * @mail: zhuyunqi_88@163.com
 * @date: ${date}
 * @describe: 解析Cookie并保存
 */
public class ReceivedCookiesInterceptor implements Interceptor {
    private SharedPreferencesUtil sp = SharedPreferencesUtil.getInstance(NetworkContext.getContext());

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        Request request = chain.request();
        List<String> cookies = response.headers("Set-Cookie");
        String cookieStr = "";
        if (cookies != null && cookies.size() > 0) {
            for (int i = 0; i < cookies.size(); i++) {
                cookieStr += cookies.get(i);
            }
            sp.putSP(Constants.COOKIE, cookieStr);
        }
        Log.d("zyq-network:url:", request.url().toString());
        return response;
    }

}
