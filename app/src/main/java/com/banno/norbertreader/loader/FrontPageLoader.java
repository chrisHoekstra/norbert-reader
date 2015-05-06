package com.banno.norbertreader.loader;

import android.content.Context;

import com.banno.norbertreader.AuthenticatedRedditClient;

import net.dean.jraw.http.oauth.OAuthException;
import net.dean.jraw.models.Listing;
import net.dean.jraw.models.Submission;
import net.dean.jraw.paginators.SubredditPaginator;

import javax.inject.Inject;

public class FrontPageLoader extends BaseAsyncTaskLoader<Listing<Submission>> {

    public static final int LOADER_ID = 1;

    private AuthenticatedRedditClient mReddit;

    @Inject
    public FrontPageLoader(AuthenticatedRedditClient reddit, Context context) {
        super(context);

        mReddit = reddit;
    }

    @Override
    public Listing<Submission> loadInBackground() {
        try {
            mReddit.authenticate();
        } catch (OAuthException e) {
            e.printStackTrace();
        }

        return new SubredditPaginator(mReddit).next();
    }
}
