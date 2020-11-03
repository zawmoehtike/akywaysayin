package com.zawmoehtike.akywaysayin;

import android.app.Application;

import me.myatminsoe.mdetect.MDetect;

public class AKywaySaYinApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MDetect.INSTANCE.init(this);
    }
}
