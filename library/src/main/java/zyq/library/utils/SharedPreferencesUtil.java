package zyq.library.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author: zhuYunQi
 * @mail: zhuyunqi_88@163.com
 * @date: ${date}
 * @describe:
 */
public class SharedPreferencesUtil {
    /**
     * app存储sp的文件名
     */
    public static final String NAME = "zyq_token";

    /**
     * 创建一个写入器
     */
    private static SharedPreferences mPreferences;
    private static SharedPreferences.Editor mEditor;
    private static SharedPreferencesUtil spUtil;


    public SharedPreferencesUtil(Context context) {
        mPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        mEditor = mPreferences.edit();
    }

    public static SharedPreferencesUtil getInstance(Context context) {
        if (spUtil == null) {
            synchronized (SharedPreferencesUtil.class) {
                if (spUtil == null) {
                    spUtil = new SharedPreferencesUtil(context);
                }
            }
        }
        return spUtil;
    }

    /**
     * 存入数据 Stirng
     */
    public void putSP(String key, String value) {
        mEditor.putString(key, value);
        mEditor.commit();
    }

    /**
     * 获取数据 Stirng
     */
    public String getStirng(String key) {
        return mPreferences.getString(key, "");
    }


    /**
     * 存入数据 boolean
     */
    public void putSP(String key, boolean value) {
        mEditor.putBoolean(key, value);
        mEditor.commit();
    }


    /**
     * 获取数据 boolean
     */
    public boolean getBoolean(String key, boolean defValue) {
        return mPreferences.getBoolean(key, defValue);
    }


    /**
     * 移除数据
     */
    public void removeSP(String key) {
        mEditor.remove(key);
        mEditor.commit();
    }


}
