package com.banno.norbertreader.api.vo;

import android.os.Parcel;
import android.os.Parcelable;

public class GetResultData implements Parcelable {
    private String title;
    private String permalink;
    private String body;

    public GetResultData() {
    }

    public String getTitle() {
        return title;
    }
    public String getPermalink() {
        return permalink;
    }
    public String getBody() {
        return body;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(title);
    }
    public static final Creator<GetResultData> CREATOR = new Creator<GetResultData>() {
        public GetResultData createFromParcel(Parcel in) {
            return new GetResultData(in);
        }
        public GetResultData[] newArray(int size) {
            return new GetResultData[size];
        }
    };
    private GetResultData(Parcel in) {
        title = in.readString();
    }
}
