package com.banno.norbertreader.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.banno.norbertreader.RedditUtil;
import com.banno.norbertreader.widget.SubmissionListRow;

import net.dean.jraw.models.Listing;
import net.dean.jraw.models.Submission;

public class SubmissionAdapter extends RecyclerView.Adapter<SubmissionAdapter.SubmissionViewHolder> {

    public interface OnSubmissionClickedListener {
        void onSubmissionClicked(SubmissionListRow row, Submission submission);
    }

    private final Listing<Submission> mListings;
    private final OnSubmissionClickedListener mListener;
    private long mSelectedId;

    public SubmissionAdapter(Listing<Submission> submissions, OnSubmissionClickedListener listener) {
        setHasStableIds(true);

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

    @Override
    public long getItemId(int position) {
        return RedditUtil.getId(mListings.get(position).getId());
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
                        mSelectedId = getItemId();
                        mView.setSelected(true);

                        mListener.onSubmissionClicked((SubmissionListRow) v, mListings.get(getAdapterPosition()));

                        notifyDataSetChanged();
                    }
                }
            });
        }

        public void setData(Submission submission) {
            mView.setSelected(getItemId() == mSelectedId);

            mView.setSubmission(submission);
        }
    }
}
