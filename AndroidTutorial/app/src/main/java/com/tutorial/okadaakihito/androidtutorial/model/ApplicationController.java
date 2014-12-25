package com.tutorial.okadaakihito.androidtutorial.model;

import android.app.Application;

/**
 * Created by okadaakihito on 2014/12/25.
 */
public class ApplicationController extends Application {
    private static ApplicationController sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static synchronized ApplicationController getInstance() {
        return sInstance;
    }
}
