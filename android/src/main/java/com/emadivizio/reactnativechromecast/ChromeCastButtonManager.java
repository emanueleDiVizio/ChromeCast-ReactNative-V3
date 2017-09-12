package com.emadivizio.reactnativechromecast;

import com.emadivizio.reactnativechromecast.sdk.ChromeCastButton;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

/**
 * Created by Emanuele on 11/09/2017.
 */

public class ChromeCastButtonManager extends SimpleViewManager<ChromeCastButton> {

    public static final String REACT_CLASS = "RCTChromeCastButton";

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    protected ChromeCastButton createViewInstance(ThemedReactContext reactContext) {
        return new ChromeCastButton(reactContext);
    }


    @ReactProp(name = "progress", defaultInt = 0)
    public void setProgress(ChromeCastButton view, int progress) {

//      view.setProgress(progress);
    }

}
