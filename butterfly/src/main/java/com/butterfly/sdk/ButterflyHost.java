package com.butterfly.sdk;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class ButterflyHost implements ReporterDialogData {

    private final Handler handler;
    private String res = "";
    private Integer responseCode;
    private Activity activity;
    private Context context;
    private String key = "";
    private static ButterflyHost butterflyHost = null;

    public static ButterflyHost getInstance() {
        if (butterflyHost == null) {
            butterflyHost = new ButterflyHost();
        }
        return butterflyHost;
    }

    private  ButterflyHost() {
        responseCode = 0;
        HandlerThread handlerThread = new HandlerThread("butterflyHost");
        handlerThread.start();
        handler = new Handler(handlerThread.getLooper());
    }

    public void OnGrabReportRequested(Activity activity,String key) {
        this.activity = activity;
        this.context = activity.getApplicationContext();
        this.key = key;
        synchronized (this.responseCode) {
            openDialog();
        }
    }

    private void openDialog() {
        ButterflyUtils.Companion.getUserInput(activity, new Function3<String, String, String, Unit>() {
            @Override
            public Unit invoke(String name, String wayToContactAndWhen, String additionalInfo) {
                onDialogComplete(name,wayToContactAndWhen,additionalInfo);
                return Unit.INSTANCE;
            }
        });
    }

    @Override
    public void onDialogComplete(String name, String way, String date ) {
        final Report report = new Report(name, way, date,"");
        final Gson gson = new Gson();
        final String jsonReport = gson.toJson(report);
        Log.d("report as json ", jsonReport);

        if (isNetworkAvailable()) {

            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    responseCode = post("https://us-central1-butterfly-host.cloudfunctions.net/sendReport", gson.toJson(report));
                    //responseCode = post("http://192.168.56.1:12345/sendReport", gson.toJson(report)); //for local running
                    if (responseCode == 200)
                        showToast(context.getString(R.string.butterfly_sent_reply));
                    else if (responseCode == 403)
                        showToast(context.getString(R.string.butterfly_not_valid_api_key));
                    else
                        showToast(context.getString(R.string.butterfly_failed_reply));
                    Log.d("tag", res);
                }
            };
            handler.post(runnable);
        } else {
            showToast(activity.getString(R.string.butterfly_no_connection_message));
        }

    }

    private void showToast(final String s) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, s, Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /**
     * this function make POST request to given URL and json
     * @param url
     * @param json
     * @return
     */
    private int post(String url, String json) {
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(json, JSON);
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .addHeader("BUTTERFLY_HOST_API_KEY", this.key)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                if (response.code() == 400) {
                    Log.d("err", "http fail");
                }
                return response.code();
            }
            catch (Exception e){
                Log.e("error",e.getMessage());
                return 400;
            }
    }
}
