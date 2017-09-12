
package com.emadivizio.reactnativechromecast;

import android.support.v4.app.FragmentActivity;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RNChromeCastPackage implements ReactPackage {
  public RNChromeCastPackage(FragmentActivity activity) {
    this.activity = activity;
  }

  private FragmentActivity activity;

  @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
      return Arrays.<NativeModule>asList(new RNChromeCastModule(reactContext, activity));
    }

    // Deprecated from RN 0.47
    public List<Class<? extends JavaScriptModule>> createJSModules() {
      return Collections.emptyList();
    }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
      return Arrays.<ViewManager>asList(new ChromeCastButtonManager());
    }
}