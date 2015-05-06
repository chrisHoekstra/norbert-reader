package com.banno.norbertreader.module;

import android.app.Activity;

import com.banno.norbertreader.AuthenticatedRedditClient;
import com.banno.norbertreader.loader.SubmissionLoader;

import dagger.Module;
import dagger.Provides;

@Module(injects = {SubmissionLoader.class}, complete = false)
public class ActivityModule {

    private final Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    public SubmissionLoader getSubmissionLoader(AuthenticatedRedditClient reddit) {
        return new SubmissionLoader(reddit, mActivity);
    }
}
