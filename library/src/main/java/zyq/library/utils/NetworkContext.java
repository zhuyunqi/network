package zyq.library.utils;

import android.content.Context;
import android.util.Log;

/**
 * @author: zhuYunQi
 * @mail: zhuyunqi_88@163.com
 * @date: ${date}
 * @describe:
 */
public class NetworkContext {
    public static Context context;

    public static Context getContext() {
        if (context == null) {
            Log.d("zyq-network", "请在 BaseApp 里设置 NetworkContext.context = this");
        }
        return context;
    }
}
