package com.banno.norbertreader;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.banno.norbertreader.api.vo.GetResult;

import java.util.List;

public class ListingAdapter extends RecyclerView.Adapter<ListingAdapter.SubmissionViewHolder> {

    private final List<GetResult> mListings;

    public ListingAdapter(List<GetResult> submissions) {
        mListings = submissions;
    }

    @Override
    public SubmissionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View row = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_submission_row, viewGroup, false);

        return new SubmissionViewHolder(row);
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

        private TextView mTitle;

        public SubmissionViewHolder(View view) {
            super(view);

            mTitle = (TextView) view.findViewById(R.id.title);
        }

        public void setData(GetResult submission) {
            mTitle.setText(submission.getData().getTitle());
        }
    }
}
