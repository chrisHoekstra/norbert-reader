package com.banno.norbertreader;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.banno.norbertreader.widget.SubmissionsListView;

import net.dean.jraw.models.Listing;
import net.dean.jraw.models.Submission;
import net.dean.jraw.paginators.SubredditPaginator;

import javax.inject.Inject;

public class SubmissionsActivity extends ActionBarActivity {

    @Inject AuthenticatedRedditClient mReddit;

    private SubmissionsListView mSubmissions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listings);

        NorbertReaderApplication.inject(this);

        mSubmissions = (SubmissionsListView) findViewById(R.id.submissions);

        new FrontPageRetrievalTask().execute();
    }

    private class FrontPageRetrievalTask extends AsyncTask<Void, Void, Listing<Submission>> {

        @Override
        protected Listing<Submission> doInBackground(Void... params) {
            try {
                mReddit.authenticate();

                SubredditPaginator frontPage = new SubredditPaginator(mReddit);
                frontPage.setLimit(50);

                return frontPage.next();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(final Listing<Submission> results) {
            if (results != null) {
                mSubmissions.setSubmissions(results);
            }
        }
    }
}
