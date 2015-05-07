package com.banno.norbertreader.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.banno.norbertreader.widget.SubmissionListRow;

import net.dean.jraw.models.Listing;
import net.dean.jraw.models.Submission;

public class SubmissionAdapter extends RecyclerView.Adapter<SubmissionAdapter.SubmissionViewHolder> {

    private Listing<Submission> mSubmissions;

    public SubmissionAdapter() {
        mSubmissions = new Listing<>(Submission.class);
    }

    public void updateSubmissions(Listing<Submission> submissions) {
        mSubmissions = submissions;

        notifyDataSetChanged();
    }

    @Override
    public SubmissionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new SubmissionViewHolder(new SubmissionListRow(viewGroup.getContext()));
    }

    @Override
    public void onBindViewHolder(SubmissionViewHolder submissionViewHolder, int i) {
        submissionViewHolder.setData(mSubmissions.get(i));
    }

    @Override
    public int getItemCount() {
        return mSubmissions.size();
    }

    public class SubmissionViewHolder extends RecyclerView.ViewHolder {

        private SubmissionListRow mView;

        public SubmissionViewHolder(View view) {
            super(view);

            mView = (SubmissionListRow) view;
        }

        public void setData(Submission submission) {
            mView.setSubmission(submission);
        }
    }
}
