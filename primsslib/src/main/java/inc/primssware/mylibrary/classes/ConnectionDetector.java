package inc.primssware.mylibrary.classes;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectionDetector {

    private final String TAG = ConnectionDetector.class.getSimpleName();

    private static Context context;
    private static Callback callback;
    private static boolean detecting = false;

    private static ConnectionDetector mInstance;
    public static synchronized ConnectionDetector getInstance(Context ctx) {
        if (mInstance == null)
            mInstance = new ConnectionDetector();

        initialize();
        context = ctx;
        return mInstance;
    }

    public ConnectionDetector setCallback(Callback mCallback){
        if (detecting){
            throw new IllegalStateException("You cannot set callback when detection has already been started.");
        }
        callback = mCallback;
        return this;
    }

    private static void initialize(){
        callback = null;
        detecting = false;
    }

    public void detectInternet() {
        if (detecting){
            return;
        }

        if (isNetworkAvailable()) {
            new AsyncTask<Void, Void, Boolean>(){

                @Override
                protected void onPreExecute(){}

                @Override
                protected Boolean doInBackground(Void... voids) {
                    try {
                        HttpURLConnection urlc = (HttpURLConnection)
                                (new URL("http://clients3.google.com/generate_204")
                                        .openConnection());
                        urlc.setRequestProperty("User-Agent", "Android");
                        urlc.setRequestProperty("Connection", "close");
                        urlc.setConnectTimeout(3000);
                        urlc.connect();
                        return  (urlc.getResponseCode() == 204 &&
                                urlc.getContentLength() == 0);
                    } catch (IOException e) {
                        Log.e(TAG, "Error checking internet connection", e);
                        return false;
                    }
                }

                @Override
                public void onPostExecute(Boolean result){
                    if (callback != null)
                        callback.onDetect(result);
                }
            }.execute();
        } else {
            if (callback != null)
                callback.onDetect(false);
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null&& activeNetworkInfo.isConnected();
    }

    public interface Callback{
        void onDetect(boolean isConnected);
    }
}