package zyq.network;

import android.app.Application;

import okhttp3.OkHttpClient;
import zyq.library.http.Host;
import zyq.library.utils.ContextUtil;


/**
 * @author: zhu yun qi
 * @mail: zhuyunqi_88@163.com
 * @date: 2018/3/14
 * @describe:
 */

public class BaseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //ContextUtil.context = this;
        Host.ApiHost = "http://www.hn-ssc.com/";
    }


}
