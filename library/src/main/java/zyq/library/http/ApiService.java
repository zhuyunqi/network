package zyq.library.http;


/**
 * @author: zhuYunQi
 * @mail: zhuyunqi_88@163.com
 * @date: ${date}
 * @describe:
 */
public class ApiService {

    /**
     * 私有构造方法
     */
    private ApiService() {
    }

    /**
     * 静态内部类
     */
    private static class SingletonHolder {
        private static final ApiService INSTANCE = new ApiService();
    }

    /**
     * 获取单例
     */
    public static ApiService getInstance() {
        return SingletonHolder.INSTANCE;
    }


    public <T> T getDefaultService(Class<T> service, String url) {
        return RetrofitUtil.getDefaultRetrofit(service, url);
    }

    public <T> T getReceivedCookiesService(Class<T> service, String url) {
        return RetrofitUtil.getReceivedCookiesRetrofit(service, url);
    }

    public <T> T getAddCookieService(Class<T> service, String url) {
        return RetrofitUtil.getAddCookieRetrofit(service, url);
    }


}
