package com.banno.norbertreader.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import com.banno.norbertreader.R;

import net.dean.jraw.models.Submission;

public class SubmissionDetailView extends FrameLayout {

    private WebView mWebView;
    private Submission mSubmission;

    public SubmissionDetailView(Context context) {
        super(context);

        initialize();
    }

    public SubmissionDetailView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initialize();
    }

    public SubmissionDetailView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initialize();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SubmissionDetailView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        initialize();
    }

    private void initialize() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_submission_detail, this, true);

        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setSupportZoom(true);
        webSettings.setJavaScriptEnabled(true);
    }

    public void setSubmission(Submission submission) {
        if (mSubmission == submission) {
            return;
        }

        mSubmission = submission;

        clear();

        if (submission.isNsfw()) {
            mWebView.loadUrl("http://i.imgur.com/kgfV66r.gif");
        } else {
            mWebView.loadUrl(submission.getUrl());
        }
    }

    private void clear() {
        mWebView.loadUrl("about:blank");
    }
}
