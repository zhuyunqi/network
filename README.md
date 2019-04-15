# network
居于RxAndroid、retrofit的网络请求框架
用法
1.添加 Gradle 依赖：

    //这个就是请求工具类
    implementation 'com.zyq.network:networklibrary:1.0.0'
    
    //请求网络需要的依赖包
    implementation 'io.reactivex:rxandroid:1.2.1'
    implementation 'com.squareup.retrofit2:retrofit:2.1.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.1.0'
    implementation 'com.squareup.retrofit2:adapter-rxjava:2.1.0'

2.在重写Application，在onCreate方法里初始化host,context：

~~~java
public class BaseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ContextUtil.context = this;
        Host.ApiHost = "你的服务器地址";
    }
}
~~~

3.写CallApi接口类，定义好接口api
~~~java
public interface CallApi {
    //下面是接口例子，更多使用方法可以参考Retrofit使用说明

    @POST("你的请求接口(不带服务器地址)")
    Observable<HttpResult<HomePageBannerBean>> getHomePageData(@QueryMap Map<String, String> params);
    
    //参数为json
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("你的请求接口(不带服务器地址)")
    Observable<HttpResult<UserInfo>> login(@Body LoginReq param);
}
~~~

4.在Activity的基类设置lifecycleSubject：
~~~java
public  class LifeCycleActivity  {
    public final PublishSubject<ActivityLifeCycleEvent> lifecycleSubject = PublishSubject.create();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        lifecycleSubject.onNext(ActivityLifeCycleEvent.CREATE);
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onStart() {
        lifecycleSubject.onNext(ActivityLifeCycleEvent.START);
        super.onStart();
    }

    @Override
    protected void onResume() {
        lifecycleSubject.onNext(ActivityLifeCycleEvent.RESUME);
        super.onResume();
    }

    @Override
    protected void onPause() {
        lifecycleSubject.onNext(ActivityLifeCycleEvent.PAUSE);
        super.onPause();
    }

    @Override
    protected void onStop() {
        lifecycleSubject.onNext(ActivityLifeCycleEvent.STOP);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        lifecycleSubject.onNext(ActivityLifeCycleEvent.DESTROY);
        super.onDestroy();
    }
}
~~~

5.在需要请求网络的activity里引用即可：
~~~java
Map<String, String> map = new HashMap<>(2);
                map.put("columnId", "12");
                map.put("pageSize", "10");
                CallApi callApi = RetrofitBuilder.getDefaultInstance().build().create(CallApi.class);
                Observable<HttpResult<HomePageBannerBean>> ob = callApi.getHomePageData(map);

                HttpUtil.getInstance().toSubscribe(ob, new AbstractHandleSubscriber<HomePageBannerBean>() {

                    @Override
                    protected void onHandleResponse(HomePageBannerBean homePageBean) {
                        Log.d("homePageBean", homePageBean.getResult().get(0).getContent());
                    }

                    @Override
                    protected void onHandleFailure(String message) {
                        super.onHandleFailure(message);
                    }
                }, lifecycleSubject);
~~~            
                
                
