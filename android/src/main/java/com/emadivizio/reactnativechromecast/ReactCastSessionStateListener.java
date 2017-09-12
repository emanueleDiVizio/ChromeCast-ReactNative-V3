package com.emadivizio.reactnativechromecast;

import android.util.Log;

import com.emadivizio.reactnativechromecast.sdk.CastDeviceScanner;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

/**
 * Created by Emanuele on 12/09/2017.
 */
class ReactCastSessionStateListener implements CastDeviceScanner.SessionStateListener {

  private ReactContext mReactContext;

  public ReactCastSessionStateListener(ReactContext mReactContext) {
    this.mReactContext = mReactContext;
  }

  private void sendEvent(int status) {
    Log.d("EVENT", String.valueOf(status));
    WritableMap map = Arguments.createMap();
    map.putInt(RNChromeCastModule.SESSION_STATUS_STRING, status);
    mReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
          .emit("ChromeCastSessionEvent", map);
  }

  @Override
  public void onSessionStarting() {
    sendEvent(RNChromeCastModule.SESSION_STARTING);
  }

  @Override
  public void onSessionStarted(String var2) {
    sendEvent(RNChromeCastModule.SESSION_STARTED);
  }

  @Override
  public void onSessionStartFailed(int var2) {
    sendEvent(RNChromeCastModule.SESSION_START_FAILED);
  }

  @Override
  public void onSessionEnding() {
    sendEvent(RNChromeCastModule.SESSION_ENDING);
  }

  @Override
  public void onSessionEnded(int var2) {
    sendEvent(RNChromeCastModule.SESSION_ENDED);
  }

  @Override
  public void onSessionResuming(String var2) {
    sendEvent(RNChromeCastModule.SESSION_RESUMING);
  }

  @Override
  public void onSessionResumed(boolean var2) {
    sendEvent(RNChromeCastModule.SESSION_RESUMED);
  }

  @Override
  public void onSessionResumeFailed(int var2) {
    sendEvent(RNChromeCastModule.SESSION_RESUME_FAILED);
  }

  @Override
  public void onSessionSuspended(int var2) {
    sendEvent(RNChromeCastModule.SESSION_SUSPENDED);
  }
}
