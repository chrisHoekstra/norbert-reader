package com.banno.norbertreader.widget;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
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

    public void clearSelected() {
        ((SubmissionAdapter) getAdapter()).clearSelectedId();
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.selectedId = ((SubmissionAdapter) getAdapter()).getSelectedId();

        return savedState;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        SavedState savedState = (SavedState) state;

        super.onRestoreInstanceState(savedState.getSuperState());

        ((SubmissionAdapter) getAdapter()).setSelectedId(savedState.selectedId);
    }

    public static class SavedState extends BaseSavedState {

        public long selectedId;

        public SavedState(Parcel source) {
            super(source);

            selectedId = source.readLong();
        }

        public SavedState(Parcelable superState) {
            super(superState);
        }

        @Override
        public void writeToParcel(@NonNull Parcel dest, int flags) {
            super.writeToParcel(dest, flags);

            dest.writeLong(selectedId);
        }

        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            @Override
            public SavedState createFromParcel(Parcel source) {
                return new SavedState(source);
            }

            @Override
            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };

    }
}
