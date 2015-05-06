package com.banno.norbertreader.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

public abstract class BaseAsyncTaskLoader<T> extends AsyncTaskLoader<T> {

    protected T mData;

    public BaseAsyncTaskLoader(Context context) {
        super(context);
    }

    public abstract T loadInBackground();

    @Override
    public void onStartLoading() {
        if (mData != null) {
            deliverResult(mData);
        }

        if (takeContentChanged() || mData == null) {
            forceLoad();
        }
    }

    @Override
    public void deliverResult(T data) {
        mData = data;

        if (isStarted()) {
            super.deliverResult(mData);
        }
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    protected void onReset() {
        super.onReset();

        onStopLoading();

        if (mData != null) {
            resetData();
        }
    }

    protected void resetData() {
        mData = null;
    }
}
