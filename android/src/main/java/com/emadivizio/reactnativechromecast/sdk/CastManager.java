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
    private CastPlayer castPlayer;

    private void onCreate(Context context){
        castDeviceScanner = new CastDeviceScanner();
        castDeviceScanner.setUp(context);

    }


    private void onResume(Context context){
        castDeviceScanner.startScanning(context, new CastDeviceScanner.CastScanListener() {
            @Override
            public void onNoDevicesAvailable() {
            }

            @Override
            public void onDeviceConnecting() {
//                showIntroductoryOverlay(activity);
            }

            @Override
            public void onDeviceConnected() {
//                showIntroductoryOverlay(activity);
            }

            @Override
            public void onDeviceNotConnected() {
//                showIntroductoryOverlay(activity);
            }
        },
                new CastDeviceScanner.SessionStateListener() {
            @Override
            public void onSessionStarting() {

            }

            @Override
            public void onSessionStarted(String var2) {

            }

            @Override
            public void onSessionStartFailed(int var2) {

            }

            @Override
            public void onSessionEnding() {

            }

            @Override
            public void onSessionEnded(int var2) {

            }

            @Override
            public void onSessionResuming(String var2) {

            }

            @Override
            public void onSessionResumed(boolean var2) {

            }

            @Override
            public void onSessionResumeFailed(int var2) {

            }

            @Override
            public void onSessionSuspended(int var2) {

            }
        });
    }

    private void onPause(){
        castDeviceScanner.stopScanning();
    }


    public void bindToActivityLifecycle(Application application){
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {
                onCreate(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
                onResume(activity);
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


    public CastPlayer.Controls loadVideo(String url, String title, String subtitle, String imageUri, int duration, boolean isLive){
        return castPlayer.loadVideo(new Video(url, title, subtitle, imageUri, duration, isLive ? Video.StreamType.LIVE : Video.StreamType.BUFFER));
    }







//    private void showIntroductoryOverlay(final Activity activity) {
//        if (mIntroductoryOverlay != null) {
//            mIntroductoryOverlay.remove();
//        }
//        if ((mediaRouteMenuItem != null) && mediaRouteMenuItem.isVisible()) {
//            new Handler().post(new Runnable() {
//                @Override
//                public void run() {
//                    mIntroductoryOverlay = new IntroductoryOverlay.Builder(
//                            activity, mediaRouteMenuItem)
//                            .setTitleText(activity.getString(R.string.introducing_cast))
//                            .setOverlayColor(R.color.primary)
//                            .setSingleTime()
//                            .setOnOverlayDismissedListener(
//                                    new IntroductoryOverlay.OnOverlayDismissedListener() {
//                                        @Override
//                                        public void onOverlayDismissed() {
//                                            mIntroductoryOverlay = null;
//                                        }
//                                    })
//                            .build();
//                    mIntroductoryOverlay.show();
//                }
//            });
//        }
//    }


}
