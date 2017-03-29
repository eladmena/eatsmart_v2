package org.hackathon.eatsmart;

import android.app.Application;
import android.content.res.Resources;

public class MyApplication extends Application {
    protected static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static Resources getMyResources() {
        return instance.getResources();
    }
}