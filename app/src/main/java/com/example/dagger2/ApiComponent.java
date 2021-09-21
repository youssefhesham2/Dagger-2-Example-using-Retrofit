package com.example.dagger2;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Subcomponent;

@MainActivityScope
@Subcomponent(modules = {ApiModule.class})
public interface ApiComponent {
    void inject(MainActivity activity);
}
