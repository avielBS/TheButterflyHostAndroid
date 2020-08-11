package com.example.thebutterflyhost_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.mylibrary.MyLibrary1;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyLibrary1.loopForIndex(6);
        setContentView(R.layout.activity_main);
    }
}
