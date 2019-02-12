package com.saif.jakerepo;

import android.app.Application;


public class RepoApplication extends Application {

    private static RepoApplication instance;
    private Thread.UncaughtExceptionHandler defaultUEH;

    public static RepoApplication get() {
        if (instance == null)
            throw new IllegalStateException("Application instance has not been initialized!");
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }



}
