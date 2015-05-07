package com.banno.norbertreader.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.banno.norbertreader.adapter.SubmissionAdapter;

import net.dean.jraw.models.Listing;
import net.dean.jraw.models.Submission;

public class SubmissionsListView extends RecyclerView {

    public SubmissionsListView(Context context) {
        super(context);

        initialize();
    }

    public SubmissionsListView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initialize();
    }

    public SubmissionsListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        initialize();
    }

    private void initialize() {
        addItemDecoration(new SimpleDividerItemDecoration(getContext()));
        setLayoutManager(new LinearLayoutManager(getContext()));
        setAdapter(new SubmissionAdapter());
    }

    public void setOnSubmissionClicked(SubmissionAdapter.OnSubmissionClickedListener listener) {
        ((SubmissionAdapter) getAdapter()).setOnSubmissionClickedListener(listener);
    }

    public void setSubmissions(Listing<Submission> submissions) {
        ((SubmissionAdapter) getAdapter()).updateSubmissions(submissions);
    }
}
