package com.butterfly.thebutterflyhost_android;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.butterfly.sdk.ButterflyHost;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.generate_button);
        textView = findViewById(R.id.text_view);

        final Activity activity = this;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ButterflyHost butterflyHost = ButterflyHost.getInstance();
                boolean success= butterflyHost.OnGrabReportRequested(activity,"key1");
                Log.d("Result !!!","from "+this.getClass().getName() +" "+ success);
            }
        });

    }
}
