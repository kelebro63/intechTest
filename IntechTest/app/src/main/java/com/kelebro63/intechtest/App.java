package com.kelebro63.intechtest;

import android.app.Application;
import android.content.Context;

import com.kelebro63.intechtest.di.components.AppComponent;
import com.kelebro63.intechtest.di.components.DaggerAppComponent;
import com.kelebro63.intechtest.di.modules.AppModule;


/**
 * Created by kelebro63 on 02.06.2016
 */
public class App extends Application {

    private AppComponent appComponent;

    public static AppComponent getAppComponent(Context context) {
        return ((App) context.getApplicationContext()).appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        }
    }
}
