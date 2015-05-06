package com.banno.norbertreader.activity;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.banno.norbertreader.NorbertReaderApplication;
import com.banno.norbertreader.R;
import com.banno.norbertreader.loader.FrontPageLoader;
import com.banno.norbertreader.module.ActivityModule;
import com.banno.norbertreader.widget.SubmissionsListView;

import net.dean.jraw.models.Listing;
import net.dean.jraw.models.Submission;

public class SubmissionsActivity extends ActionBarActivity implements LoaderManager.LoaderCallbacks<Listing<Submission>> {

    private SubmissionsListView mSubmissions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listings);

        NorbertReaderApplication.inject(this);

        mSubmissions = (SubmissionsListView) findViewById(R.id.submissions);

        getSupportLoaderManager().initLoader(FrontPageLoader.LOADER_ID, null, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.submission_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.refresh) {
            getSupportLoaderManager().restartLoader(FrontPageLoader.LOADER_ID, null, this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public Loader<Listing<Submission>> onCreateLoader(int id, Bundle args) {
        return NorbertReaderApplication.withObjectGraph(new ActivityModule(this))
                .get(FrontPageLoader.class);
    }

    @Override
    public void onLoadFinished(Loader<Listing<Submission>> loader, Listing<Submission> data) {
        mSubmissions.setSubmissions(data);
    }

    @Override
    public void onLoaderReset(Loader<Listing<Submission>> loader) {}
}
