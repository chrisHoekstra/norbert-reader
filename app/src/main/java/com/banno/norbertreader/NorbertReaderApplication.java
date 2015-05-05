package com.banno.norbertreader;

import android.app.Application;

import com.banno.norbertreader.module.NorbertReaderModule;

import dagger.ObjectGraph;

public class NorbertReaderApplication extends Application {

    private static ObjectGraph sObjectGraph;

    @Override
    public void onCreate() {
        super.onCreate();

        sObjectGraph = ObjectGraph.create(new NorbertReaderModule());
    }

    public static void inject(Object object) {
        sObjectGraph.inject(object);
    }

}
