package zyq.library.http.requestbody;

import android.os.Handler;
import android.os.Looper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

/**
 * @author: zhuYunQi
 * @mail: zhuyunqi_88@163.com
 * @date: 2019-11-20
 * @describe:
 */
public class ProgressRequestBody extends RequestBody {
    private File mFile;
    private String mMediaType;
    private UploadCallbacks mListener;

    private int mEachBufferSize = 1024;

    public interface UploadCallbacks {
        /**
         *
         * @param percentage
         */
        void onProgressUpdate(int percentage);

        /**
         *
         */
        void onError();

        /**
         *
         */
        void onFinish();
    }

    public ProgressRequestBody(final File file, String mediaType, final UploadCallbacks listener) {
        mFile = file;
        mMediaType = mediaType;
        mListener = listener;
    }

    public ProgressRequestBody(final File file, String mediaType, int eachBufferSize, final UploadCallbacks listener) {
        mFile = file;
        mMediaType = mediaType;
        mEachBufferSize = eachBufferSize;
        mListener = listener;
    }

    @Override
    public MediaType contentType() {
        // i want to upload only images
        return MediaType.parse(mMediaType);
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        long fileLength = mFile.length();
        byte[] buffer = new byte[mEachBufferSize];
        FileInputStream in = new FileInputStream(mFile);
        long uploaded = 0;

        try {
            int read;
            Handler handler = new Handler(Looper.getMainLooper());
            while ((read = in.read(buffer)) != -1) {
                // update progress on UI thread
                uploaded += read;
                sink.write(buffer, 0, read);
                handler.post(new ProgressUpdater(uploaded, fileLength));
            }
        } finally {
            in.close();
        }
    }

    private class ProgressUpdater implements Runnable {
        private long mUploaded;
        private long mTotal;

        public ProgressUpdater(long uploaded, long total) {
            mUploaded = uploaded;
            mTotal = total;
        }

        @Override
        public void run() {
            mListener.onProgressUpdate((int) (100 * mUploaded / mTotal));
        }
    }
}
