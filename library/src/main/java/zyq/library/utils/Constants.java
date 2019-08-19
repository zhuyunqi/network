package zyq.library.utils;

/**
 * @author: zhuYunQi
 * @mail: zhuyunqi_88@163.com
 * @date: ${date}
 * @describe:常量类
 */
public class Constants {

    public static final String COOKIE = "cookie";

    /**
     * 超时时间设置
     * 默认一分钟
     */
    public static class TimeOut {
        public static int connectTimeout = 60;
        public static int readTimeout = 60;
        public static int writeTimeout = 60;
    }
}
