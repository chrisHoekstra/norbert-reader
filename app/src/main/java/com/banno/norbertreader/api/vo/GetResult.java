package com.banno.norbertreader.api.vo;

import android.os.Parcel;
import android.os.Parcelable;

public class GetResult extends RedditItem<GetResultData> implements Parcelable {

    public GetResult() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(data, flags);
    }
    public static final Creator<GetResult> CREATOR = new Creator<GetResult>() {
        public GetResult createFromParcel(Parcel in) {
            return new GetResult(in);
        }
        public GetResult[] newArray(int size) {
            return new GetResult[size];
        }
    };
    private GetResult(Parcel in) {
        data = in.readParcelable(null);
    }

}
