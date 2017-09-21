package com.emadivizio.reactnativechromecast.sdk.ui;

import android.content.Context;
import android.support.v7.app.MediaRouteButton;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.emadivizio.reactnativechromecast.R;
import com.emadivizio.reactnativechromecast.sdk.cast.CastManager;


public class ChromeCastButton extends LinearLayout {
    private MediaRouteButton mButton;
    private TextView routes;

    public ChromeCastButton(Context context) {
        super(context);
        init(context);
    }

    public ChromeCastButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ChromeCastButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.sample_chrome_cast_button, this);
        this.mButton = (MediaRouteButton) findViewById(R.id.media_route_button);
        CastManager.registerButton(context, mButton);
    }


}