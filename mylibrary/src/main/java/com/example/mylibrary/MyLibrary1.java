package com.example.mylibrary;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MyLibrary1 implements ReporterDialogData {

    private String res = "";
    private Context context;
    private FragmentManager fragmentManager;

    public MyLibrary1() {
    }


    public String loopForIndex(Context context, FragmentManager supportManager) {
        this.context = context;
        this.fragmentManager = supportManager;
        openDialog(fragmentManager);
        return res;
    }

    private void openDialog(FragmentManager supportManager) {
        ReporterDialog reporterDialog = new ReporterDialog(this);
        reporterDialog.show(supportManager, "");
    }

    private String makeHttp() {
        try {
            return run("https://localhost:12345/sendReport");
        } catch (IOException e) {
            e.printStackTrace();
            return "not successed";
        }
    }

    private String run(String url) throws IOException {

        final MediaType JSON
                = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.body() != null) {
                showToast("sent");
                return response.body().string();
            } else {
                Log.d("err", "http fail");
                showToast("failed, try again");
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void showToast(final String msg) {
        Handler handler = new Handler(context.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public Report WhenGetDialogReporterData(String name, String way, String date) {
        Report report = new Report(name, way, date,"");
        Gson gson = new Gson();
        final String jsonReport = gson.toJson(report);
        Log.d("report as json ", jsonReport);

        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
//                    res = post("https://butterfly-host-server.herokuapp.com/sendReport",jsonReport);
                    res = post("http://10.0.2.2:12345/sendReport",jsonReport);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.d("tag", res);
            }
        });
        thread.start();
        return report;
    }

    private String post(String url, String json) throws IOException{
            MediaType JSON = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(json, JSON);
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            try (Response response = client.newCall(request).execute()) {

                if (response.code() == 200) {
                    showToast("sent");
                    return response.body().string();
                } else {
                    Log.d("err", "http fail");
                    showToast("failed, try again");
                    return "";
                }

            }

    }
}
