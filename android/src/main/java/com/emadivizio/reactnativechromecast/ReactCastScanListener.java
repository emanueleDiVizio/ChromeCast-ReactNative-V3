package com.emadivizio.reactnativechromecast;

import com.emadivizio.reactnativechromecast.sdk.CastDeviceScanner;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

/**
 * Created by Emanuele on 12/09/2017.
 */
class ReactCastScanListener implements CastDeviceScanner.CastScanListener {

  private ReactContext mReactContext;

  public ReactCastScanListener(ReactContext mReactContext) {
    this.mReactContext = mReactContext;
  }

  @Override
  public void onNoDevicesAvailable() {
    WritableMap map = Arguments.createMap();
    map.putBoolean(RNChromeCastModule.DEVICES_AVAILABLE_STRING, false);
    map.putBoolean(RNChromeCastModule.DEVICE_CONNECTED_STRING, false);
    map.putBoolean(RNChromeCastModule.DEVICE_CONNECTING_STRING, false);
    mReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
          .emit("ChromeCastScanEvent", map);
  }

  @Override
  public void onDeviceConnecting() {
    WritableMap map = Arguments.createMap();
    map.putBoolean(RNChromeCastModule.DEVICES_AVAILABLE_STRING, true);
    map.putBoolean(RNChromeCastModule.DEVICE_CONNECTED_STRING, false);
    map.putBoolean(RNChromeCastModule.DEVICE_CONNECTING_STRING, true);
    mReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
          .emit("ChromeCastScanEvent", map);
  }

  @Override
  public void onDeviceConnected() {
    WritableMap map = Arguments.createMap();
    map.putBoolean(RNChromeCastModule.DEVICES_AVAILABLE_STRING, true);
    map.putBoolean(RNChromeCastModule.DEVICE_CONNECTED_STRING, true);
    map.putBoolean(RNChromeCastModule.DEVICE_CONNECTING_STRING, false);
    mReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
          .emit("ChromeCastScanEvent", map);
  }

  @Override
  public void onDeviceNotConnected() {
    WritableMap map = Arguments.createMap();
    map.putBoolean(RNChromeCastModule.DEVICES_AVAILABLE_STRING, true);
    map.putBoolean(RNChromeCastModule.DEVICE_CONNECTED_STRING, false);
    map.putBoolean(RNChromeCastModule.DEVICE_CONNECTING_STRING, false);
    mReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
          .emit("ChromeCastScanEvent", map);
  }
}
