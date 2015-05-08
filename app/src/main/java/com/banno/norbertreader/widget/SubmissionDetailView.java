package com.banno.norbertreader.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.banno.norbertreader.R;

import net.dean.jraw.models.Submission;

public class SubmissionDetailView extends LinearLayout {

    private SubmissionListRow mHeader;
    private WebView mWebView;
    private boolean mShowHeader;

    public SubmissionDetailView(Context context) {
        super(context);

        initialize(null);
    }

    public SubmissionDetailView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initialize(attrs);
    }

    public SubmissionDetailView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initialize(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SubmissionDetailView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        initialize(attrs);
    }

    private void initialize(AttributeSet attrs) {
        LayoutInflater.from(getContext()).inflate(R.layout.view_submission_detail, this, true);

        if (attrs != null) {
            TypedArray attributes = getContext().obtainStyledAttributes(attrs, R.styleable.SubmissionDetailView);
            mShowHeader = attributes.getBoolean(R.styleable.SubmissionDetailView_showHeader, true);
            attributes.recycle();
        } else {
            mShowHeader = true;
        }

        setOrientation(VERTICAL);

        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setSupportZoom(true);
        webSettings.setJavaScriptEnabled(true);

        mHeader = (SubmissionListRow) findViewById(R.id.header);

        clear();
    }

    public void setSubmission(Submission submission) {
        clear();

        if (submission.isNsfw()) {
            mWebView.loadUrl("http://i.imgur.com/kgfV66r.gif");
        } else {
            mWebView.loadUrl(submission.getUrl());
        }

        mHeader.setSubmission(submission);

        if (mShowHeader) {
            mHeader.setVisibility(VISIBLE);
        }
    }

    private void clear() {
        mWebView.loadUrl("about:blank");
        mHeader.setVisibility(GONE);
    }
}
