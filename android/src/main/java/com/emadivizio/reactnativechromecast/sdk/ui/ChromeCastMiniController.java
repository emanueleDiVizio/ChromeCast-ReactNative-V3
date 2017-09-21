package com.emadivizio.reactnativechromecast.sdk.ui;

import android.content.Context;
import android.support.v7.app.MediaRouteButton;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.emadivizio.reactnativechromecast.R;


public class ChromeCastMiniController extends LinearLayout {
    private MediaRouteButton mButton;
    private TextView routes;

    public ChromeCastMiniController(Context context) {
        super(context);
        init(context);
    }

    public ChromeCastMiniController(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ChromeCastMiniController(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.chrome_cast_mini_player, this);
    }


}