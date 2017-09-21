package com.emadivizio.reactnativechromecast.sdk.cast;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.MediaRouteButton;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.emadivizio.reactnativechromecast.R;
import com.emadivizio.reactnativechromecast.sdk.cast.util.Video;
import com.google.android.gms.cast.framework.CastButtonFactory;


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


    public void setUp() {
        castDeviceScanner.setUp();
    }

    public void startScan() {
        castDeviceScanner.startScanning(castScanListener,
            sessionStateListener);
    }


    public void stopScan() {
        castDeviceScanner.stopScanning();
    }


    public boolean dispatchKeyEvent(@NonNull KeyEvent event) {
        return castDeviceScanner.dispatchKeyEvent(event);
    }


    public boolean onCreateOptionsMenu(Activity activity, Menu menu) {
        activity.getMenuInflater().inflate(R.menu.menu, menu);
        setUpCastButton(activity, menu, R.id.media_route_menu_item);
        return true;
    }


    /**
     * Set up cast button to show cast dialog.
     *
     * @param context          Android application context.
     * @param mediaRouteButton Button to wrap.
     */
    public static void setUpCastButton(Context context, MediaRouteButton mediaRouteButton) {
        CastButtonFactory.setUpMediaRouteButton(context, mediaRouteButton);
    }

    public CastPlayer.Controls loadVideo(String url, String title, String subtitle, String imageUri, int duration, boolean isLive, String mimeType) {
        return new CastPlayer(castDeviceScanner.getCurrentCastSession()).loadVideo(new Video(url, title, subtitle, imageUri, duration, isLive ? Video.StreamType.LIVE : Video.StreamType.BUFFER, mimeType));
    }


    /**
     * Set up cast button to show cast dialog.
     *
     * @param context  Android application context.
     * @param menu     Menu.
     * @param buttonId Ref id.
     */
    private MenuItem setUpCastButton(Context context, Menu menu, int buttonId) {
        return CastButtonFactory.setUpMediaRouteButton(context, menu, buttonId);
    }

}
