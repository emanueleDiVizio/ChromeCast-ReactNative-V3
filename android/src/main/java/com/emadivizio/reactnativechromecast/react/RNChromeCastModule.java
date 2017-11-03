
package com.emadivizio.reactnativechromecast.react;

import android.content.Intent;
import android.os.Handler;

import com.emadivizio.reactnativechromecast.react.events.ReactCastScanListener;
import com.emadivizio.reactnativechromecast.react.events.ReactCastSessionStateListener;
import com.emadivizio.reactnativechromecast.sdk.cast.CastControls;
import com.emadivizio.reactnativechromecast.sdk.cast.CastManager;
import com.emadivizio.reactnativechromecast.sdk.ui.ExpandedControlsActivity;
import com.emadivizio.reactnativechromecast.util.Constants;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.util.HashMap;
import java.util.Map;

public class RNChromeCastModule extends ReactContextBaseJavaModule {


  private CastManager manager;
  private ReactApplicationContext reactContext;
  private CastControls castControls;


  public RNChromeCastModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.manager = new CastManager(reactContext, new ReactCastSessionStateListener(reactContext), new ReactCastScanListener(reactContext));
    this.reactContext = reactContext;
  }

  @Override
  public Map<String, Object> getConstants() {
    final Map<String, Object> constants = new HashMap<>();
    constants.put(Constants.SESSION_STARTING_STRING, Constants.SESSION_STARTING);
    constants.put(Constants.SESSION_STARTED_STRING, Constants.SESSION_STARTED);
    constants.put(Constants.SESSION_START_FAILED_STRING, Constants.SESSION_START_FAILED);
    constants.put(Constants.SESSION_ENDING_STRING, Constants.SESSION_ENDING);
    constants.put(Constants.SESSION_ENDED_STRING, Constants.SESSION_ENDED);
    constants.put(Constants.SESSION_RESUMING_STRING, Constants.SESSION_RESUMING);
    constants.put(Constants.SESSION_RESUMED_STRING, Constants.SESSION_RESUMED);
    constants.put(Constants.SESSION_RESUME_FAILED_STRING, Constants.SESSION_RESUME_FAILED);
    constants.put(Constants.SESSION_SUSPENDED_STRING, Constants.SESSION_SUSPENDED);
    constants.put(Constants.SESSION_STATUS_STRING, Constants.SESSION_STATUS_STRING);
    constants.put(Constants.DEVICES_AVAILABLE_STRING, Constants.DEVICES_AVAILABLE_STRING);
    constants.put(Constants.DEVICE_CONNECTED_STRING, Constants.DEVICE_CONNECTED_STRING);
    constants.put(Constants.DEVICE_CONNECTING_STRING, Constants.DEVICE_CONNECTING_STRING);
    return constants;
  }

  @Override
  public String getName() {
    return "RNChromeCast";
  }

  private void runOnUiThread(Runnable runnable){
    Handler mainHandler = new Handler(reactContext.getMainLooper());
    mainHandler.post(runnable);
  }

  @ReactMethod
  public void setUpScanner(){
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        manager.setUp();
      }
    });
  }
  @ReactMethod
  public void startScan(){
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        manager.startScan();
      }
    });
  }

  @ReactMethod
  public void stopScan(){
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        manager.stopScan();
      }
    });
  }


  @ReactMethod
  public void showChromeCastActivity(){
    Intent intent = new Intent(reactContext, ExpandedControlsActivity.class);
    reactContext.startActivity(intent);
  }


  @ReactMethod
  public void loadVideo(final String url, final String title, final String subtitle, final String imageUri, final Integer duration, final Boolean isLive, final String mimeType, final Promise promise) {
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        try {
          castControls = manager.loadVideo(url, title, subtitle, imageUri, duration, isLive, mimeType);
          promise.resolve(null);
        } catch (Exception e) {
          promise.reject(e);
        }
      }
    });

  }

  @ReactMethod
  public void start(final Integer position, final Promise promise) {
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        castControls.load(true, position, new ControlsCallback(promise));
      }
    });
  }


  @ReactMethod
  public void play(final Promise promise) {
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        castControls.play(new ControlsCallback(promise));
      }
    });
  }


  @ReactMethod
  public void pause(final Promise promise) {
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        castControls.pause(new ControlsCallback(promise));
      }
    });
  }


  @ReactMethod
  public void stop(final Promise promise) {
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        castControls.stop(new ControlsCallback(promise));
      }
    });
  }

  @ReactMethod
  public void seek(final Integer position, final Promise promise) {
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        castControls.seek((long) position, new ControlsCallback(promise));
      }
    });
  }


  private class ControlsCallback implements CastControls.ControlsCallback {
    private Promise promise;

    public ControlsCallback(Promise promise) {
      this.promise = promise;
    }

    @Override
    public void onSuccess() {
      promise.resolve(null);
    }

    @Override
    public void onFailure(CastControls.ResultError error) {
      promise.reject(String.valueOf(error.getCode()), error.getMessage());
    }
  }




}