package com.banno.norbertreader.module;

import com.banno.norbertreader.ListingsActivity;
import com.banno.norbertreader.api.RedditApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;

@Module(injects = {RedditApi.class, ListingsActivity.class})
public class NorbertReaderModule {

    @Provides
    @Singleton
    public RestAdapter provideRestAdapter() {
        return new RestAdapter.Builder().setEndpoint("http://www.reddit.com").build();
    }

    @Provides
    public RedditApi provideBannoApi(RestAdapter restAdapter) {
        return restAdapter.create(RedditApi.class);
    }
}
