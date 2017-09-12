
package com.emadivizio.reactnativechromecast;

import android.support.v4.app.FragmentActivity;

import com.emadivizio.reactnativechromecast.sdk.CastManager;
import com.emadivizio.reactnativechromecast.sdk.player.CastPlayer;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class RNChromeCastModule extends ReactContextBaseJavaModule {

  private CastManager manager;
  private CastPlayer.Controls controls;

  public RNChromeCastModule(ReactApplicationContext reactContext, FragmentActivity activity) {
    super(reactContext);
    this.manager = new CastManager(reactContext);
    this.manager.bindToActivityLifecycle(activity.getApplication());
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