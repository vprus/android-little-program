package com.vladimirprus.littleprogram;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

    public SpacesItemDecoration(RecyclerView parent, int dip)
    {
        this.spacing = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, parent.getResources().getDisplayMetrics());
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        int p = parent.getChildAdapterPosition(view);

        outRect.bottom = spacing;

        if (p == 0 || p == 1) {
            outRect.top = spacing;
        }

        if (p % 2 == 0) {
            outRect.left = spacing;
            outRect.right = spacing /2;
        } else {
            outRect.left = spacing /2;
            outRect.right = spacing;
        }
    }

    private int spacing;
}