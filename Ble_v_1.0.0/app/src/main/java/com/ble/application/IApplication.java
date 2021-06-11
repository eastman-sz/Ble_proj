package com.ble.application;

import android.app.Application;
import android.content.Context;
import com.sdk.application.SdkApplication;
/**
 * Created by E on 2017/6/7.
 */
public class IApplication extends Application {

    private static Context context = null;

    @Override
    public void onCreate() {
        super.onCreate();

        context = this;

        SdkApplication.init(this);
    }

    public static Context getContext(){
        return context;
    }
}
