package com.banno.norbertreader.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.banno.norbertreader.NorbertReaderApplication;
import com.banno.norbertreader.R;
import com.banno.norbertreader.RedditUtil;

import net.dean.jraw.models.Submission;

import javax.inject.Inject;

import static com.banno.norbertreader.ViewUtil.dipToPixels;

public class SubmissionListRow extends RelativeLayout {

    public static final String TRANSITION_NAME = "submissionListRow";

    private static final int[] STATE_IS_READ = {
            R.attr.state_is_read
    };

    private TextView mTitle;
    private TextView mScore;
    private TextView mAuthor;
    private TextView mSubreddit;
    private boolean mIsRead = false;

    @Inject RedditUtil mRedditUtil;

    public SubmissionListRow(Context context) {
        super(context);

        initialize();
    }

    public SubmissionListRow(Context context, AttributeSet attrs) {
        super(context, attrs);

        initialize();
    }

    public SubmissionListRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initialize();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SubmissionListRow(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        initialize();
    }

    private void initialize() {
        NorbertReaderApplication.inject(this);

        LayoutInflater.from(getContext()).inflate(R.layout.view_submission_row, this, true);

        if (getBackground() == null) {
            setBackgroundResource(R.drawable.list_row_background);
        }

        int padding = (int) dipToPixels(getContext(), 10.0f);
        setPadding(padding, padding, padding, padding);
        setMinimumHeight((int) dipToPixels(getContext(), 70.0f));
        setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setTransitionName(TRANSITION_NAME);
        }

        mTitle = (TextView) findViewById(R.id.title);
        mScore = (TextView) findViewById(R.id.score);
        mAuthor = (TextView) findViewById(R.id.author);
        mSubreddit = (TextView) findViewById(R.id.subreddit);
    }

    public void setSubmission(Submission submission) {
        if (submission.isNsfw()) {
            mTitle.setText(R.string.not_safe_for_presentation);
        } else {
            mTitle.setText(Html.fromHtml(submission.getTitle()));
        }
        mScore.setText(submission.getScore().toString());
        mAuthor.setText(submission.getAuthor());
        mSubreddit.setText(submission.getSubredditName());
        mIsRead = mRedditUtil.isRead(submission);

        refreshDrawableState();
    }

    @Override
    public int[] onCreateDrawableState(int extraSpace) {
        final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);

        if (mIsRead) {
            mergeDrawableStates(drawableState, STATE_IS_READ);
        }

        return drawableState;
    }
}
