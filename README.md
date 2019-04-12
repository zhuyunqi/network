# network
居于RxAndroid、retrofit的网络请求框架
用法
1.在baseApp里初始化host,content
public class BaseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ContextUtil.context = this;
        Host.ApiHost = "http://www.hn-ssc.com/";
    }


}
