package com.banno.norbertreader.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.banno.norbertreader.NorbertReaderApplication;
import com.banno.norbertreader.RedditUtil;
import com.banno.norbertreader.widget.SubmissionListRow;

import net.dean.jraw.models.Listing;
import net.dean.jraw.models.Submission;

import javax.inject.Inject;

public class SubmissionAdapter extends RecyclerView.Adapter<SubmissionAdapter.SubmissionViewHolder> {

    private static final long NO_SELECTION = -1;

    public interface OnSubmissionClickedListener {
        void onSubmissionClicked(Submission submission);
    }

    private Listing<Submission> mSubmissions;
    private OnSubmissionClickedListener mListener;
    private long mSelectedId = NO_SELECTION;

    @Inject RedditUtil mRedditUtil;

    public SubmissionAdapter() {
        NorbertReaderApplication.inject(this);

        mSubmissions = new Listing<>(Submission.class);
        setHasStableIds(true);
    }

    private void notifyOfSelectedSubmission() {
        if (mSelectedId != NO_SELECTION && mListener != null) {
            mListener.onSubmissionClicked(getSubmission(mSelectedId));
        }
    }

    public void updateSubmissions(Listing<Submission> submissions) {
        mSubmissions = submissions;

        notifyDataSetChanged();
        notifyOfSelectedSubmission();
    }
    
    public void setOnSubmissionClickedListener(OnSubmissionClickedListener listener) {
        mListener = listener;

        notifyOfSelectedSubmission();
    }

    public long getSelectedId() {
        return mSelectedId;
    }

    public void setSelectedId(long selectedId) {
        mSelectedId = selectedId;

        notifyDataSetChanged();
        notifyOfSelectedSubmission();
    }

    public void clearSelectedId() {
        mSelectedId = NO_SELECTION;

        notifyDataSetChanged();
    }

    public Submission getSubmission(long id) {
        for (Submission submission : mSubmissions) {
            if (id == getItemId(submission)) {
                return submission;
            }
        }

        return null;
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

    @Override
    public long getItemId(int position) {
        return getItemId(mSubmissions.get(position));
    }

    public long getItemId(Submission submission) {
        return mRedditUtil.getId(submission.getId());
    }

    public class SubmissionViewHolder extends RecyclerView.ViewHolder {

        private SubmissionListRow mView;

        public SubmissionViewHolder(View view) {
            super(view);

            mView = (SubmissionListRow) view;
            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSelectedId = getItemId();

                    Submission submission = mSubmissions.get(getAdapterPosition());

                    mRedditUtil.markAsRead(submission);
                    
                    if (mListener != null) {
                        mListener.onSubmissionClicked(submission);
                    }

                    notifyDataSetChanged();
                }
            });
        }

        public void setData(Submission submission) {
            mView.setSelected(getItemId() == mSelectedId);

            mView.setSubmission(submission);
        }
    }
}
