package zyq.library.http;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

/**
 * @author: zhuYunQi
 * @mail: zhuyunqi_88@163.com
 * @date: ${date}
 * @describe:
 */
public class RxHelper {
    /**
     * 添加线程管理并订阅
     *
     * @param ob
     * @param lifecycleSubject
     */
    public static void toSubscribe(Observable ob, final PublishSubject<ActivityLifeCycleEvent> lifecycleSubject, final AbstractHandleSubscriber subscriber) {
        Observable.Transformer<Object, Object> result = bindUntilEvent(lifecycleSubject);
        ob.compose(result)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }

    /**
     * 生命周期管理
     *
     * @param lifecycleSubject
     * @param <T>
     * @return
     */
    public static <T> Observable.Transformer<T, T> bindUntilEvent(final PublishSubject<ActivityLifeCycleEvent> lifecycleSubject) {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> sourceObservable) {
                Observable<ActivityLifeCycleEvent> compareLifecycleObservable =
                        lifecycleSubject.takeFirst(new Func1<ActivityLifeCycleEvent, Boolean>() {
                            @Override
                            public Boolean call(ActivityLifeCycleEvent activityLifeCycleEvent) {
                                return activityLifeCycleEvent.equals(ActivityLifeCycleEvent.DESTROY);
                            }
                        });
                return sourceObservable.takeUntil(compareLifecycleObservable);
            }
        };
    }
}
