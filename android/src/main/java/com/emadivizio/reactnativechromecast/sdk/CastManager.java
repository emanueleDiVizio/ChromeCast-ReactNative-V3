package com.emadivizio.reactnativechromecast.sdk;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.MediaRouteButton;
import android.view.KeyEvent;
import android.view.Menu;

import com.emadivizio.reactnativechromecast.R;
import com.emadivizio.reactnativechromecast.sdk.player.CastPlayer;


/**
 * Created by Emanuele on 07/09/2017.
 */

public class CastManager {
    private CastDeviceScanner castDeviceScanner;
    private Context context;
    private CastDeviceScanner.SessionStateListener sessionStateListener;
    private CastDeviceScanner.CastScanListener castScanListener;

    public CastManager(Context context, CastDeviceScanner.SessionStateListener listener, CastDeviceScanner.CastScanListener castScanListener) {
        castDeviceScanner = new CastDeviceScanner(context);
        this.context = context;
        this.sessionStateListener = listener;
        this.castScanListener = castScanListener;
    }


    private void onCreate(){
      castDeviceScanner.setUp();
      onResume();
    }

    private void onResume(){
        castDeviceScanner.startScanning(castScanListener,
             sessionStateListener);
    }


    private void onPause(){
        castDeviceScanner.stopScanning();
    }




    public void bindToActivityLifecycle(Application application){
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {
              onCreate();
            }

            @Override
            public void onActivityStarted(Activity activity) {
//              onResume();
            }

            @Override
            public void onActivityResumed(Activity activity) {
                onResume();
            }

            @Override
            public void onActivityPaused(Activity activity) {
                onPause();
            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }


    public boolean dispatchKeyEvent(@NonNull KeyEvent event){
        return castDeviceScanner.dispatchKeyEvent(event);
    }


    public boolean onCreateOptionsMenu(Activity activity, Menu menu) {
        activity.getMenuInflater().inflate(R.menu.menu, menu);
        CastDeviceScanner.registerMenu(activity, menu,  R.id.media_route_menu_item);
        return true;
    }


    public static void registerButton(Context context, MediaRouteButton button){
        CastDeviceScanner.registerButton(context, button);
    }


    public CastPlayer.Controls loadVideo(String url, String title, String subtitle, String imageUri, int duration, boolean isLive, String mimeType){
        return new CastPlayer(castDeviceScanner.getCurrentCastSession()).loadVideo(new Video(url, title, subtitle, imageUri, duration, isLive ? Video.StreamType.LIVE : Video.StreamType.BUFFER, mimeType));
    }

}
