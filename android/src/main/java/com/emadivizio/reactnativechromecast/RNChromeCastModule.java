
package com.emadivizio.reactnativechromecast;

import android.support.v4.app.FragmentActivity;

import com.emadivizio.reactnativechromecast.sdk.CastManager;
import com.emadivizio.reactnativechromecast.sdk.player.CastPlayer;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.util.HashMap;
import java.util.Map;

public class RNChromeCastModule extends ReactContextBaseJavaModule {

  private CastManager manager;
  private ReactApplicationContext reactContext;
  private CastPlayer.Controls controls;


  public static final int SESSION_STARTING = 0;
  public static final int SESSION_STARTED = 1;
  public static final int SESSION_START_FAILED = 2;
  public static final int SESSION_ENDING = 3;
  public static final int SESSION_ENDED = 4;
  public static final int SESSION_RESUMING = 5;
  public static final int SESSION_RESUMED = 6;
  public static final int SESSION_RESUME_FAILED = 7;
  public static final int SESSION_SUSPENDED = 8;


  public static final String SESSION_STATUS_STRING = "SESSION_STATUS";
  public static final String DEVICES_AVAILABLE_STRING = "DEVICES_AVAILABLE";
  public static final String DEVICE_CONNECTED_STRING = "DEVICE_CONNECTED";
  public static final String DEVICE_CONNECTING_STRING = "DEVICE_CONNECTING";

  public RNChromeCastModule(ReactApplicationContext reactContext, FragmentActivity activity) {
    super(reactContext);
    this.manager = new CastManager(reactContext, new ReactCastSessionStateListener(reactContext), new ReactCastScanListener(reactContext));
    manager.bindToActivityLifecycle(activity.getApplication());
    this.reactContext = reactContext;
  }

  @Override public Map<String, Object> getConstants() {
    final Map<String, Object> constants = new HashMap<>();
    constants.put("SESSION_STARTING", SESSION_STARTING);
    constants.put("SESSION_STARTED", SESSION_STARTED);
    constants.put("SESSION_START_FAILED", SESSION_START_FAILED);
    constants.put("SESSION_ENDING", SESSION_ENDING);
    constants.put("SESSION_ENDED", SESSION_ENDED);
    constants.put("SESSION_RESUMING", SESSION_RESUMING);
    constants.put("SESSION_RESUMED", SESSION_RESUMED);
    constants.put("SESSION_RESUME_FAILED", SESSION_RESUME_FAILED);
    constants.put("SESSION_SUSPENDED", SESSION_SUSPENDED);
    constants.put(SESSION_STATUS_STRING, SESSION_STATUS_STRING);
    constants.put(DEVICES_AVAILABLE_STRING, DEVICES_AVAILABLE_STRING);
    constants.put(DEVICE_CONNECTED_STRING, DEVICE_CONNECTED_STRING);
    constants.put(DEVICE_CONNECTING_STRING, DEVICE_CONNECTING_STRING);
    return constants;
  }

  @Override
  public String getName() {
    return "RNChromeCast";
  }


  @ReactMethod
  public void loadVideo(String url, String title, String subtitle, String imageUri, int duration, boolean isLive, Callback errorCallback, Callback successCallback) {
    try{
      controls = manager.loadVideo(url, title, subtitle, imageUri, duration, isLive);
      successCallback.invoke(true);
    } catch (Exception e){
      errorCallback.invoke(e.getMessage());
    }
  }

  @ReactMethod
  public void start(){
    controls.load(true, 0);
  }


  @ReactMethod
  public void play(){
    controls.play();
  }


  @ReactMethod
  public void pause(){
    controls.pause();
  }


  @ReactMethod
  public void stop(){
    controls.stop();
  }

  @ReactMethod
  public void seek(double position){
    controls.seek((long) position);
  }




}