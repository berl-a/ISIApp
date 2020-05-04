package com.example.taxiapp.Utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;

public class MyBoldTextView extends androidx.appcompat.widget.AppCompatTextView {
    public MyBoldTextView(Context context) {
        super(context);
        applyCustomFont(context);
    }

    public MyBoldTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context);
    }

    public MyBoldTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustomFont(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyBoldTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr);
        applyCustomFont(context);
    }

    private void applyCustomFont(Context context) {
        Typeface customFont = FontCache.getTypeface("fonts/ClanPro-NarrNews.otf", context);
        setTypeface(customFont);
    }
}
