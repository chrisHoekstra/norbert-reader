package com.banno.norbertreader.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.banno.norbertreader.R;

import net.dean.jraw.models.Listing;
import net.dean.jraw.models.Submission;

public class SubmissionAdapter extends RecyclerView.Adapter<SubmissionAdapter.SubmissionViewHolder> {

    private Listing<Submission> mSubmissions;

    public SubmissionAdapter() {
        mSubmissions = new Listing<>(Submission.class);
    }

    public void updateSubmissions(Listing<Submission> submissions) {
        mSubmissions = submissions;
    }

    @Override
    public SubmissionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View row = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_submission_row, viewGroup, false);

        return new SubmissionViewHolder(row);
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

        private TextView mTitle;
        private TextView mScore;
        private TextView mAuthor;
        private TextView mSubreddit;

        public SubmissionViewHolder(View view) {
            super(view);

            mTitle = (TextView) view.findViewById(R.id.title);
            mScore = (TextView) view.findViewById(R.id.score);
            mAuthor = (TextView) view.findViewById(R.id.author);
            mSubreddit = (TextView) view.findViewById(R.id.subreddit);
        }

        public void setData(Submission submission) {
            if (submission.isNsfw()) {
                mTitle.setText(R.string.not_safe_for_presentation);
            } else {
                mTitle.setText(Html.fromHtml(submission.getTitle()));
            }
            mScore.setText(submission.getScore().toString());
            mAuthor.setText(submission.getAuthor());
            mSubreddit.setText(submission.getSubredditName());
        }
    }
}
