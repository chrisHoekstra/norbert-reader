package com.banno.norbertreader.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.banno.norbertreader.R;
import com.banno.norbertreader.widget.SubmissionDetailView;

import net.dean.jraw.JrawUtils;
import net.dean.jraw.models.Submission;

public class SubmissionDetailActivity extends ActionBarActivity {

    private static final String KEY_SUBMISSION = "keySubmission";

    private SubmissionDetailView mSubmissionDetail;
    private Submission mSubmission;

    public static void startActivity(Activity activity, Submission submission) {
        Intent intent = new Intent(activity, SubmissionDetailActivity.class);
        intent.putExtra(KEY_SUBMISSION, JrawUtils.toJson(submission));

        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission_detail);

        getBundleExtras(savedInstanceState != null ? savedInstanceState : getIntent().getExtras());

        mSubmissionDetail = (SubmissionDetailView) findViewById(R.id.submission);
        mSubmissionDetail.setSubmission(mSubmission);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(KEY_SUBMISSION, JrawUtils.toJson(mSubmission));
    }

    private void getBundleExtras(Bundle bundle) {
        if (bundle != null) {
            mSubmission = new Submission(JrawUtils.fromString(bundle.getString(KEY_SUBMISSION)));
        }
    }
}