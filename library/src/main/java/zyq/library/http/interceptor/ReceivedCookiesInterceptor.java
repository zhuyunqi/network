package zyq.library.http.interceptor;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.Response;
import zyq.library.utils.Constants;
import zyq.library.utils.ContextUtil;
import zyq.library.utils.SharedPreferencesUtil;

/**
 * @author: zhuYunQi
 * @mail: zhuyunqi_88@163.com
 * @date: ${date}
 * @describe: 解析Cookie并保存
 */
public class ReceivedCookiesInterceptor implements Interceptor {
    private SharedPreferencesUtil sp = SharedPreferencesUtil.getInstance(ContextUtil.getContext());

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response resp = chain.proceed(chain.request());
        List<String> cookies = resp.headers("Set-Cookie");
        String cookieStr = "";
        if (cookies != null && cookies.size() > 0) {
            for (int i = 0; i < cookies.size(); i++) {
                cookieStr += cookies.get(i);
            }
            sp.putSP(Constants.COOKIE, cookieStr);
        }
        return resp;
    }

}
