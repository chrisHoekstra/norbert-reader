package com.banno.norbertreader;

import android.content.Context;
import android.util.TypedValue;

public class ViewUtil {
    public static float dipToPixels(Context context, float dipValue) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dipValue,
                context.getResources().getDisplayMetrics());
    }
}
