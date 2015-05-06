package com.banno.norbertreader.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.banno.norbertreader.SimpleDividerItemDecoration;
import com.banno.norbertreader.SubmissionAdapter;

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
    }

    public void setSubmissions(Listing<Submission> submissions) {
        setAdapter(new SubmissionAdapter(submissions));
    }
}
