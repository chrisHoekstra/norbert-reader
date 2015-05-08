package com.banno.norbertreader.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.banno.norbertreader.AuthenticatedRedditClient;
import com.banno.norbertreader.RedditUtil;
import com.banno.norbertreader.activity.SubmissionsActivity;
import com.banno.norbertreader.adapter.SubmissionAdapter;

import net.dean.jraw.http.UserAgent;
import net.dean.jraw.http.oauth.Credentials;

import java.util.UUID;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(injects = {SubmissionsActivity.class, SubmissionAdapter.class}, library = true)
public class NorbertReaderModule {

    private static final String KEY_DEVICE_UUID = "keyDeviceUuid";

    private final Context mContext;

    public NorbertReaderModule(Context context) {
        mContext = context;
    }

    @Provides
    public SharedPreferences provideSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(mContext);
    }

    @Provides
    @Singleton
    public AuthenticatedRedditClient provideAuthenticatedRedditClient(UserAgent userAgent, Credentials credentials) {
        return new AuthenticatedRedditClient(userAgent, credentials);
    }

    @Provides
    public Credentials provideCredentials(SharedPreferences sharedPreferences) {
        return Credentials.userlessApp("wuWkh9E_fnp3Sg", getUuid(sharedPreferences));
    }

    @Provides
    public UserAgent provideUserAgent() {
        return UserAgent.of("android", "com.banno.norbertreader", "0.1", "xXx_smaug_xXx");
    }

    @Provides
    @Singleton
    public RedditUtil provideRedditUtil() {
        return new RedditUtil();
    }

    private UUID getUuid(SharedPreferences sharedPreferences) {
        UUID uuid;
        String uuidString = sharedPreferences.getString(KEY_DEVICE_UUID, null);

        if (uuidString == null) {
            uuid = UUID.randomUUID();

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(KEY_DEVICE_UUID, uuid.toString());
            editor.apply();
        } else {
            uuid = UUID.fromString(uuidString);
        }

        return uuid;
    }
}
