package zyq.network;

import java.util.Map;

import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * @author: zhuYunQi
 * @mail: zhuyunqi_88@163.com
 * @date: ${date}
 * @describe:
 */
public interface CallApi {
    @POST("ssc-circle/news/findByPage.json")
    Observable<HomePageBannerBean> getHomePageData(@QueryMap Map<String, String> params);
}
