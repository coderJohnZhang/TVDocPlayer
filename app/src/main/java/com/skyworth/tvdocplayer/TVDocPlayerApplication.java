package com.skyworth.tvdocplayer;

import android.app.Application;
import android.util.Log;

public class TVDocPlayerApplication extends Application {
    private static final String TAG = TVDocPlayerApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }
}
