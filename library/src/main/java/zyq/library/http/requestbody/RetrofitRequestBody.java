package zyq.library.http.requestbody;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @author: zhuYunQi
 * @mail: zhuyunqi_88@163.com
 * @date: 2019-09-25
 * @describe:
 */
public class RetrofitRequestBody {

    public static RequestBody toRequestBody(String value) {
        return RequestBody.create(MediaType.parse("multipart/form-data"), value);
    }

    public static RequestBody toRequestBody(File file) {
        return RequestBody.create(MediaType.parse("multipart/form-data"), file);
    }
}
