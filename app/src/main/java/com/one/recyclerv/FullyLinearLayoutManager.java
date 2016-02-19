package com.one.recyclerv;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

/**
 * Created by buke on 15/12/8.
 */
public class FullyLinearLayoutManager extends LinearLayoutManager {

    private static final String TAG = "FullyLinearLayoutManager";

    private int[] mMeasuredDimension = new int[2];

    public FullyLinearLayoutManager(Context context) {
        super(context);
    }

    public FullyLinearLayoutManager(Context context, int orientation,
            boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }
}
