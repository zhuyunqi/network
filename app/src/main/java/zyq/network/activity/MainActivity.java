package zyq.network.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.subjects.PublishSubject;
import zyq.library.http.AbstractHandleSubscriber;
import zyq.library.http.ActivityLifeCycleEvent;
import zyq.library.http.ApiService;
import zyq.library.http.RxHelper;
import zyq.network.CallApi;
import zyq.network.HomePageBannerBean;
import zyq.network.R;

public class MainActivity extends AppCompatActivity {
    public final PublishSubject<ActivityLifeCycleEvent> lifecycleSubject = PublishSubject.create();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lifecycleSubject.onNext(ActivityLifeCycleEvent.CREATE);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test();
            }
        });

    }

    private void test() {
        Map<String, String> map = new HashMap<>(2);
        map.put("columnId", "12");
        map.put("pageSize", "10");
        Observable ob = ApiService.getInstance().getDefaultService(CallApi.class, " http://www.hn-ssc.com/").getHomePageData(map);
        RxHelper.toSubscribe(ob, lifecycleSubject, new AbstractHandleSubscriber<HomePageBannerBean>() {
            @Override
            protected void onHandleResponse(HomePageBannerBean homePageBean) {
                TextView tv = ((TextView) findViewById(R.id.tv));
                tv.setText(homePageBean.getData().getResult().get(0).getContent());
                Log.d("homePageBean", homePageBean.getData().getResult().get(0).getContent());
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("zyq", "onDestroy");
        lifecycleSubject.onNext(ActivityLifeCycleEvent.DESTROY);
    }
}
