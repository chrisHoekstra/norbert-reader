package com.banno.norbertreader.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBarActivity;

import com.banno.norbertreader.NorbertReaderApplication;
import com.banno.norbertreader.R;
import com.banno.norbertreader.loader.SubmissionLoader;
import com.banno.norbertreader.module.ActivityModule;
import com.banno.norbertreader.widget.SubmissionDetailView;

import net.dean.jraw.models.Submission;

public class SubmissionDetailActivity extends ActionBarActivity implements LoaderManager.LoaderCallbacks<Submission> {

    private static final String KEY_SUBMISSION_ID = "keySubmissionId";

    private SubmissionDetailView mSubmissionDetail;
    private String mId;

    public static void startActivity(Activity activity, Submission submission) {
        Intent intent = new Intent(activity, SubmissionDetailActivity.class);
        intent.putExtra(KEY_SUBMISSION_ID, submission.getId());

        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission_detail);

        getBundleExtras(savedInstanceState != null ? savedInstanceState : getIntent().getExtras());

        mSubmissionDetail = (SubmissionDetailView) findViewById(R.id.submission);

        Bundle bundle = new Bundle();
        bundle.putString(KEY_SUBMISSION_ID, mId);
        getSupportLoaderManager().initLoader(SubmissionLoader.LOADER_ID, bundle, this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(KEY_SUBMISSION_ID, mId);
    }

    private void getBundleExtras(Bundle bundle) {
        if (bundle != null) {
            mId = bundle.getString(KEY_SUBMISSION_ID);
        }
    }

    @Override
    public Loader<Submission> onCreateLoader(int id, Bundle args) {
        SubmissionLoader loader = NorbertReaderApplication.withObjectGraph(new ActivityModule(this))
                .get(SubmissionLoader.class);
        loader.setSubmissionId(args.getString(KEY_SUBMISSION_ID));

        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Submission> loader, Submission data) {
        mSubmissionDetail.setSubmission(data);
    }

    @Override
    public void onLoaderReset(Loader<Submission> loader) {}
}
