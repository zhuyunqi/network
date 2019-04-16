package zyq.network;

import android.app.Application;

import zyq.library.utils.NetworkContext;


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
        NetworkContext.context = this;
    }


}
