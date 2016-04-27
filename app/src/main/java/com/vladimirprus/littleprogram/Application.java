package com.vladimirprus.littleprogram;

import android.util.Log;

import com.vk.sdk.VKSdk;

public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("Appplication", "Initializing");
        VKSdk.initialize(this);
    }
}
