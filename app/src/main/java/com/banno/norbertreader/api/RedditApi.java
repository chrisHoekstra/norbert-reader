package com.banno.norbertreader.api;

import com.banno.norbertreader.api.vo.ListingItem;

import retrofit.http.GET;

public interface RedditApi {

    @GET("/top")
    ListingItem getListings();
}
