package com.banno.norbertreader.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.banno.norbertreader.widget.SubmissionListRow;

import net.dean.jraw.models.Listing;
import net.dean.jraw.models.Submission;

public class SubmissionAdapter extends RecyclerView.Adapter<SubmissionAdapter.SubmissionViewHolder> {

    public interface OnSubmissionClickedListener {
        void onSubmissionClicked(Submission submission);
    }

    private final Listing<Submission> mListings;
    private final OnSubmissionClickedListener mListener;

    public SubmissionAdapter(Listing<Submission> submissions, OnSubmissionClickedListener listener) {
        mListings = submissions;
        mListener = listener;
    }

    @Override
    public SubmissionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new SubmissionViewHolder(new SubmissionListRow(viewGroup.getContext()));
    }

    @Override
    public void onBindViewHolder(SubmissionViewHolder submissionViewHolder, int i) {
        submissionViewHolder.setData(mListings.get(i));
    }

    @Override
    public int getItemCount() {
        return mListings.size();
    }

    public class SubmissionViewHolder extends RecyclerView.ViewHolder {

        private SubmissionListRow mView;

        public SubmissionViewHolder(View view) {
            super(view);

            mView = (SubmissionListRow) view;
            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onSubmissionClicked(mListings.get(getAdapterPosition()));
                    }
                }
            });
        }

        public void setData(Submission submission) {
            mView.setSubmission(submission);
        }
    }
}
