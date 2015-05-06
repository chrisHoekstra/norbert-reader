package com.banno.norbertreader.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.banno.norbertreader.R;
import com.banno.norbertreader.widget.SubmissionDetailView;

public class SubmissionDetailActivity extends ActionBarActivity {

    private SubmissionDetailView mSubmissionDetailView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission_detail);

        mSubmissionDetailView = (SubmissionDetailView) findViewById(R.id.submission);
    }
}
