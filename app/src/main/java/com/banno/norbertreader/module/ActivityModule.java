package com.banno.norbertreader.module;

import android.app.Activity;

import com.banno.norbertreader.AuthenticatedRedditClient;
import com.banno.norbertreader.loader.FrontPageLoader;

import dagger.Module;
import dagger.Provides;

@Module(injects = {FrontPageLoader.class}, complete = false)
public class ActivityModule {

    private final Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    public FrontPageLoader getFrontPageLoader(AuthenticatedRedditClient reddit) {
        return new FrontPageLoader(reddit, mActivity);
    }
}
