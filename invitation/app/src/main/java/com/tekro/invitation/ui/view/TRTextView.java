package com.tekro.invitation.ui.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Rocío on 15/02/2015.
 */
public class TRTextView extends TextView {
    public TRTextView(Context context) {
        super(context);
        commonInit();
    }

    public TRTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        commonInit();
    }

    public TRTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        commonInit();
    }

    private void commonInit() {
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "HelveticaNeue.ttf");
        this.setTypeface(font);
    }
}
