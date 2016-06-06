package com.kelebro63.intechtest.widgets;


import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import com.kelebro63.intechtest.R;

/**
 * indeterminate circular progress widget
 */
public class CircularProgress extends ProgressBar {
    public CircularProgress(Context context) {
        super(context);
        init();
    }

    public CircularProgress(Context context, AttributeSet attrs) {
        super(context, attrs);init();
    }

    public CircularProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        if (!isInEditMode()) {
            int color = getResources().getColor(R.color.red_bright);
            Drawable indeterminateDrawable = getIndeterminateDrawable();
            Drawable progressDrawable = getProgressDrawable();

            if (indeterminateDrawable != null)
                indeterminateDrawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
            if (progressDrawable != null)
                progressDrawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
        }
    }

    public void setLoading(boolean isLoading) {
        setVisibility(isLoading ? VISIBLE : GONE);
    }

}
