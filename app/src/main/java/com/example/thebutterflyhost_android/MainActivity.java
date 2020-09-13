package com.example.thebutterflyhost_android;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylibrary.ButterflyHost;

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
                ButterflyHost butterflyHost = new ButterflyHost();
                boolean success= butterflyHost.OnGrabReportRequested(getApplicationContext(),getSupportFragmentManager(),activity);
                Log.d("Result !!!","from "+this.getClass().getName() +" "+ success);
            }
        });

    }
}
