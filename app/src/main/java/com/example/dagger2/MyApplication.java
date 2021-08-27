package com.example.dagger2;

import com.example.dagger2.ApiComponent;
import com.google.gson.Gson;

import android.app.Application;

import javax.inject.Inject;

import okhttp3.OkHttpClient;

public class MyApplication extends Application {
    public ApiComponent mApiComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mApiComponent = DaggerApiComponent.builder()
                .appModule(new AppModule(this))
                .apiModule(new ApiModule("https://restcountries.eu/rest/v2/"))
                .build();
    }

    public ApiComponent getmApiComponent() {
        return mApiComponent;
    }
}
