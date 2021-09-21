package com.example.dagger2;

import com.example.dagger2.ApiComponent;
import com.google.gson.Gson;

import android.app.Application;

import javax.inject.Inject;

import okhttp3.OkHttpClient;

public class MyApplication extends Application {
    private AppComponent mAppComponent;
    private ApiComponent mApiComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        mApiComponent=mAppComponent.getApiComponent(new ApiModule("https://restcountries.eu/rest/v2/"));
    }

    public ApiComponent getmApiComponent() {
        return mApiComponent;
    }
}
