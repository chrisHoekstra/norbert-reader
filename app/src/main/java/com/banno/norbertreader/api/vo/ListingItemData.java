package com.banno.norbertreader.api.vo;

import java.util.ArrayList;

public class ListingItemData {

    private ArrayList<GetResult> children;
    private String after;
    private String before;

    public ListingItemData() {
    }

    public ArrayList<GetResult> getChildren() {
        return children;
    }
}
