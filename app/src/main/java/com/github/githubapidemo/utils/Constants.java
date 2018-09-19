package com.github.githubapidemo.utils;

import android.util.Log;

public class Constants {

    public static final String BASE_URL = "https://api.github.com/";

    public static final boolean DEBUG = true;
    public static void debug(String str){
        if (DEBUG)
            Log.d("fengjw", str);
    }

}
