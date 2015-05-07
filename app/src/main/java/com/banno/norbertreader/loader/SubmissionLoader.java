package com.banno.norbertreader.loader;

import android.content.Context;

import com.banno.norbertreader.AuthenticatedRedditClient;

import net.dean.jraw.http.oauth.OAuthException;
import net.dean.jraw.models.Submission;

import javax.inject.Inject;

public class SubmissionLoader extends BaseAsyncTaskLoader<Submission> {

    public static final int LOADER_ID = 2;

    private AuthenticatedRedditClient mReddit;
    private String mId;

    @Inject
    public SubmissionLoader(AuthenticatedRedditClient reddit, Context context) {
        super(context);

        mReddit = reddit;
    }

    @Override
    public Submission loadInBackground() {
        try {
            mReddit.authenticate();
        } catch (OAuthException e) {
            e.printStackTrace();
        }

        return mReddit.getSubmission(mId);
    }

    public void setSubmissionId(String id) {
        mId = id;
    }
}
