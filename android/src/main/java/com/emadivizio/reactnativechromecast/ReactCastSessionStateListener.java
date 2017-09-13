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
    map.putInt(Constants.SESSION_STATUS_STRING, status);
    map.putString(Constants.SESSION_STATUS_MESSAGE_STRING, Constants.intToString(status));
    mReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
          .emit("ChromeCastSessionEvent", map);
  }

  @Override
  public void onSessionStarting() {
    sendEvent(Constants.SESSION_STARTING);
  }

  @Override
  public void onSessionStarted(String var2) {
    sendEvent(Constants.SESSION_STARTED);
  }

  @Override
  public void onSessionStartFailed(int var2) {
    sendEvent(Constants.SESSION_START_FAILED);
  }

  @Override
  public void onSessionEnding() {
    sendEvent(Constants.SESSION_ENDING);
  }

  @Override
  public void onSessionEnded(int var2) {
    sendEvent(Constants.SESSION_ENDED);
  }

  @Override
  public void onSessionResuming(String var2) {
    sendEvent(Constants.SESSION_RESUMING);
  }

  @Override
  public void onSessionResumed(boolean var2) {
    sendEvent(Constants.SESSION_RESUMED);
  }

  @Override
  public void onSessionResumeFailed(int var2) {
    sendEvent(Constants.SESSION_RESUME_FAILED);
  }

  @Override
  public void onSessionSuspended(int var2) {
    sendEvent(Constants.SESSION_SUSPENDED);
  }
}
