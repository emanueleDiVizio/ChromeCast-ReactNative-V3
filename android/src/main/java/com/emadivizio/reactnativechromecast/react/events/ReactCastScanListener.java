package com.emadivizio.reactnativechromecast.react.events;

import com.emadivizio.reactnativechromecast.sdk.cast.CastDeviceScanner;
import com.emadivizio.reactnativechromecast.util.Constants;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

/**
 * Created by Emanuele on 12/09/2017.
 */
public class ReactCastScanListener implements CastDeviceScanner.CastScanListener {

  private ReactContext mReactContext;

  public ReactCastScanListener(ReactContext mReactContext) {
    this.mReactContext = mReactContext;
  }

  @Override
  public void onNoDevicesAvailable() {
    WritableMap map = Arguments.createMap();
    map.putBoolean(Constants.DEVICES_AVAILABLE_STRING, false);
    map.putBoolean(Constants.DEVICE_CONNECTED_STRING, false);
    map.putBoolean(Constants.DEVICE_CONNECTING_STRING, false);
    mReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
          .emit("ChromeCastScanEvent", map);
  }

  @Override
  public void onDeviceConnecting() {
    WritableMap map = Arguments.createMap();
    map.putBoolean(Constants.DEVICES_AVAILABLE_STRING, true);
    map.putBoolean(Constants.DEVICE_CONNECTED_STRING, false);
    map.putBoolean(Constants.DEVICE_CONNECTING_STRING, true);
    mReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
          .emit("ChromeCastScanEvent", map);
  }

  @Override
  public void onDeviceConnected() {
    WritableMap map = Arguments.createMap();
    map.putBoolean(Constants.DEVICES_AVAILABLE_STRING, true);
    map.putBoolean(Constants.DEVICE_CONNECTED_STRING, true);
    map.putBoolean(Constants.DEVICE_CONNECTING_STRING, false);
    mReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
          .emit("ChromeCastScanEvent", map);
  }

  @Override
  public void onDeviceNotConnected() {
    WritableMap map = Arguments.createMap();
    map.putBoolean(Constants.DEVICES_AVAILABLE_STRING, true);
    map.putBoolean(Constants.DEVICE_CONNECTED_STRING, false);
    map.putBoolean(Constants.DEVICE_CONNECTING_STRING, false);
    mReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
          .emit("ChromeCastScanEvent", map);
  }
}
