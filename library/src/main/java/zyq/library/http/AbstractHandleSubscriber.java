package zyq.library.http;


import android.util.Log;

import rx.Subscriber;
import zyq.library.utils.ContextUtil;

/**
 * @author zhuyunqi
 * @date 2017/4/1 14:16
 */
public abstract class AbstractHandleSubscriber<T> extends Subscriber<T> {

    @Override
    public void onCompleted() {
        Log.d("onCompleted", "请求完成");
    }

    @Override
    public void onNext(T t) {
        onHandleResponse(t);
    }

    @Override
    public void onError(Throwable e) {
        //判断网络不可以
        if (!NetworkUtils.isNetworkAvailable(ContextUtil.getContext())) {
            onHandleFailure("网络不可用");
        } else {
            onHandleFailure(e.getMessage());
        }
    }

    /**
     * 请求成功后回调该方法
     *
     * @param t
     */
    protected abstract void onHandleResponse(T t);

    /**
     * 请求失败后回调该方法
     *
     * @param message
     */
    protected void onHandleFailure(String message) {
        Log.d("onHandleFailure", message);
    }


}
