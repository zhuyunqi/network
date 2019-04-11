package zyq.network.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.subjects.PublishSubject;
import zyq.library.http.AbstractHandleSubscriber;
import zyq.library.http.ActivityLifeCycleEvent;
import zyq.library.http.RetrofitBuilder;
import zyq.library.http.HttpResult;
import zyq.library.http.HttpUtil;
import zyq.network.CallApi;
import zyq.network.HomePageBannerBean;
import zyq.network.R;

public class MainActivity extends AppCompatActivity {
    public final PublishSubject<ActivityLifeCycleEvent> lifecycleSubject = PublishSubject.create();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });

    }


}
