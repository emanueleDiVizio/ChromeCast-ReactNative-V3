package com.emadivizio.reactnativechromecast.sdk.cast;

import android.support.annotation.NonNull;

import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.common.api.ResultCallbacks;
import com.google.android.gms.common.api.Status;

/**
 * Created by Emanuele on 21/09/2017.
 */
public class CastControls {

  private RemoteMediaClient remoteMediaClient;
  private MediaInfo mediaInfo;


  CastControls(RemoteMediaClient remoteMediaClient, MediaInfo mediaInfo) {
    this.remoteMediaClient = remoteMediaClient;
    this.mediaInfo = mediaInfo;
  }


  public void load(boolean autoplay, long position, final ControlsCallback controlsCallback) {
    remoteMediaClient.load(mediaInfo, autoplay, position).setResultCallback(new ResultCallback(controlsCallback));
  }


  public void play(final ControlsCallback controlsCallback) {
    remoteMediaClient.play().setResultCallback(new ResultCallback(controlsCallback));
  }


  public void pause(final ControlsCallback controlsCallback) {
    remoteMediaClient.pause().setResultCallback(new ResultCallback(controlsCallback));
  }


  public void stop(final ControlsCallback controlsCallback) {
    remoteMediaClient.stop().setResultCallback(new ResultCallback(controlsCallback));
  }


  public void seek(long position, final ControlsCallback controlsCallback) {
    remoteMediaClient.seek(position).setResultCallback(new ResultCallback(controlsCallback));
  }




  private class ResultCallback extends ResultCallbacks<RemoteMediaClient.MediaChannelResult> {
    private ControlsCallback controlsCallback;

    public ResultCallback(ControlsCallback controlsCallback) {
      this.controlsCallback = controlsCallback;
    }

    @Override
    public void onSuccess(@NonNull RemoteMediaClient.MediaChannelResult mediaChannelResult) {
      controlsCallback.onSuccess();
    }

    @Override
    public void onFailure(@NonNull Status status) {
      controlsCallback.onFailure(new ResultError(status));
    }
  }

  public class ResultError {
    private Status status;

    public ResultError(Status status) {
      this.status = status;
    }


      public int getCode() {
      return status.getStatusCode();
    }

      public String getMessage() {
      return status.getStatusMessage();
    }
  }

  public interface ControlsCallback{
    void onSuccess();

    void onFailure(ResultError resultError);
  }
}
