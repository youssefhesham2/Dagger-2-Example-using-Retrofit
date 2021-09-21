package com.example.dagger2;

import javax.inject.Singleton;

import dagger.Component;
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    ApiComponent getApiComponent(ApiModule apiModule);
}
