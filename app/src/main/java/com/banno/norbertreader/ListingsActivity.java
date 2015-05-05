package com.banno.norbertreader;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.banno.norbertreader.api.RedditApi;
import com.banno.norbertreader.api.vo.GetResult;
import com.banno.norbertreader.api.vo.ListingItem;

import java.util.ArrayList;

import javax.inject.Inject;

public class ListingsActivity extends ActionBarActivity {

    @Inject RedditApi mRedditApi;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_listings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class ListingRetrievalTask extends AsyncTask<Void, Void, ListingItem> {

        @Override
        protected ListingItem doInBackground(Void... params) {
            try {
                return mRedditApi.getListings();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(final ListingItem results) {
            if (results != null) {
                mSubmissions.setAdapter(new ListingAdapter(results.getData().getChildren()));
            }
        }

        private ArrayList<GetResult> getHomepageItems(ListingItem results) {
            return results.getData().getChildren();
        }

    }
}
