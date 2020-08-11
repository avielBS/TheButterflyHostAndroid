package com.example.mylibrary;

import android.util.Log;

public class MyLibrary1 {

    public MyLibrary1(){

    }


    public static void loopForIndex(int index){
        for (int i = 0; i < index; i++) {
            Log.d("MyLibrary",i+"");
        }
    }
}
