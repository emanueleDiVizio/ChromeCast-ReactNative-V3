
package com.emadivizio.reactnativechromecast;

import android.app.Application;
import android.os.Handler;

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


  public RNChromeCastModule(ReactApplicationContext reactContext, Application application) {
    super(reactContext);
    this.manager = new CastManager(reactContext, new ReactCastSessionStateListener(reactContext), new ReactCastScanListener(reactContext));
    manager.bindToActivityLifecycle(application);
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
  public void loadVideo(final String url, final String title, final String subtitle, final String imageUri, final int duration, final boolean isLive, final String mimeType, final Callback errorCallback, final Callback successCallback) {
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        try {
          controls = manager.loadVideo(url, title, subtitle, imageUri, duration, isLive, mimeType);
          successCallback.invoke();
        } catch (Exception e) {
          errorCallback.invoke(e.getMessage());
        }
      }
    });

  }

  @ReactMethod
  public void start(final Callback errorCallback, final Callback successCallback) {
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        controls.load(true, 0, new CastPlayer.ControlsCallback() {
          @Override
          public void onSuccess() {
            successCallback.invoke();
          }

          @Override
          public void onFailure(String message) {
            errorCallback.invoke(message);
          }
        });
      }
    });
  }


  @ReactMethod
  public void play(final Callback errorCallback, final Callback successCallback) {
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        controls.play(new CastPlayer.ControlsCallback() {
          @Override
          public void onSuccess() {
            successCallback.invoke();
          }

          @Override
          public void onFailure(String message) {
            errorCallback.invoke(message);
          }
        });
      }
    });
  }


  @ReactMethod
  public void pause(final Callback errorCallback, final Callback successCallback) {
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        controls.pause(new CastPlayer.ControlsCallback() {
          @Override
          public void onSuccess() {
            successCallback.invoke();
          }

          @Override
          public void onFailure(String message) {
            errorCallback.invoke(message);
          }
        });
      }
    });
  }


  @ReactMethod
  public void stop(final Callback errorCallback, final Callback successCallback) {
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        controls.stop(new CastPlayer.ControlsCallback() {
          @Override
          public void onSuccess() {
            successCallback.invoke();
          }

          @Override
          public void onFailure(String message) {
            errorCallback.invoke(message);
          }
        });
      }
    });
  }

  @ReactMethod
  public void seek(final double position, final Callback errorCallback, final Callback successCallback) {
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        controls.seek((long) position, new CastPlayer.ControlsCallback() {
          @Override
          public void onSuccess() {
            successCallback.invoke();
          }

          @Override
          public void onFailure(String message) {
            errorCallback.invoke(message);
          }
        });
      }
    });
  }


}