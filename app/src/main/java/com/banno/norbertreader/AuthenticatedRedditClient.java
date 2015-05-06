package com.banno.norbertreader;

import net.dean.jraw.RedditClient;
import net.dean.jraw.http.NetworkException;
import net.dean.jraw.http.UserAgent;
import net.dean.jraw.http.oauth.Credentials;
import net.dean.jraw.http.oauth.OAuthData;
import net.dean.jraw.http.oauth.OAuthException;
import net.dean.jraw.http.oauth.OAuthHelper;

public class AuthenticatedRedditClient extends RedditClient {

    private Credentials mCredentials;

    public AuthenticatedRedditClient(UserAgent userAgent, Credentials credentials) {
        super(userAgent);

        mCredentials = credentials;
    }

    public void authenticate() throws NetworkException, OAuthException {
        if (!isAuthenticated()) {
            OAuthHelper helper = getOAuthHelper();
            OAuthData data = helper.easyAuth(mCredentials);
            authenticate(data);
        }
    }
}
