package com.banno.norbertreader;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import net.dean.jraw.models.Listing;
import net.dean.jraw.models.Submission;
import net.dean.jraw.paginators.Sorting;
import net.dean.jraw.paginators.SubredditPaginator;
import net.dean.jraw.paginators.TimePeriod;

import javax.inject.Inject;

public class ListingsActivity extends ActionBarActivity {

    @Inject AuthenticatedRedditClient mReddit;

    private RecyclerView mSubmissions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listings);

        NorbertReaderApplication.inject(this);

        mSubmissions = (RecyclerView) findViewById(R.id.submissions);
        mSubmissions.setLayoutManager(new LinearLayoutManager(this));

        new ListingRetrievalTask().execute();
    }

    private class ListingRetrievalTask extends AsyncTask<Void, Void, Listing<Submission>> {

        @Override
        protected Listing<Submission> doInBackground(Void... params) {
            try {
                mReddit.authenticate();

                SubredditPaginator frontPage = new SubredditPaginator(mReddit);
                frontPage.setLimit(50);
                frontPage.setTimePeriod(TimePeriod.HOUR);
                frontPage.setSorting(Sorting.TOP);
                return frontPage.next();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(final Listing<Submission> results) {
            if (results != null) {
                mSubmissions.setAdapter(new ListingAdapter(results));
            }
        }
    }
}
