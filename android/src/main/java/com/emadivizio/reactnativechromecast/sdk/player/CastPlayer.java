package com.emadivizio.reactnativechromecast.sdk.player;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.emadivizio.reactnativechromecast.sdk.Video;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.common.api.ResultCallbacks;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.images.WebImage;

/**
 * Created by Emanuele on 12/09/2017.
 */

public class CastPlayer {


  private CastSession mCastSession;

  public CastPlayer(CastSession mCastSession) {
    this.mCastSession = mCastSession;
  }


  private MediaMetadata buildMetadata(Video video){
    MediaMetadata movieMetadata = new MediaMetadata(MediaMetadata.MEDIA_TYPE_MOVIE);
    movieMetadata.putString(MediaMetadata.KEY_TITLE, video.getTitle());
    movieMetadata.putString(MediaMetadata.KEY_SUBTITLE, video.getSubtitle());
    movieMetadata.addImage(new WebImage(Uri.parse(video.getImageUri())));
    return movieMetadata;
  }


  private int getStreamType(Video video){
    switch (video.getType()){
      case LIVE:
        return MediaInfo.STREAM_TYPE_LIVE;
      case BUFFER:
        return MediaInfo.STREAM_TYPE_BUFFERED;
      default:
        return MediaInfo.STREAM_TYPE_NONE;
    }
  }


  private MediaInfo buildMediaInfo(Video video){
    return new MediaInfo.Builder(video.getUrl())
          .setStreamType(getStreamType(video))
          .setContentType(video.getMimeType())
          .setMetadata(buildMetadata(video))
          .setStreamDuration(video.getDuration())
          .build();
  }


  public Controls loadVideo(Video video){
    return new Controls(mCastSession.getRemoteMediaClient(), buildMediaInfo(video));
  }


  public class Controls {

    private RemoteMediaClient remoteMediaClient;
    private MediaInfo mediaInfo;

    private Controls(RemoteMediaClient remoteMediaClient, MediaInfo mediaInfo) {
      this.remoteMediaClient = remoteMediaClient;
      this.mediaInfo = mediaInfo;
    }


    public void load(boolean autoplay, long position, final ControlsCallback controlsCallback){
      remoteMediaClient.load(mediaInfo, autoplay, position).setResultCallback(new ResultCallbacks<RemoteMediaClient.MediaChannelResult>() {
        @Override
        public void onSuccess(@NonNull RemoteMediaClient.MediaChannelResult mediaChannelResult) {
          controlsCallback.onSuccess();
        }

        @Override
        public void onFailure(@NonNull Status status) {
          Log.d("ERROR", status.toString());
          controlsCallback.onFailure(status.getStatusMessage());
        }
      });
    }


    public void play(final ControlsCallback controlsCallback){
      remoteMediaClient.play().setResultCallback(new ResultCallbacks<RemoteMediaClient.MediaChannelResult>() {
        @Override
        public void onSuccess(@NonNull RemoteMediaClient.MediaChannelResult mediaChannelResult) {
          controlsCallback.onSuccess();
        }

        @Override
        public void onFailure(@NonNull Status status) {
          controlsCallback.onFailure(status.getStatusMessage());
        }
      });
    }


    public void pause(final ControlsCallback controlsCallback){
      remoteMediaClient.pause().setResultCallback(new ResultCallbacks<RemoteMediaClient.MediaChannelResult>() {
        @Override
        public void onSuccess(@NonNull RemoteMediaClient.MediaChannelResult mediaChannelResult) {
          controlsCallback.onSuccess();
        }

        @Override
        public void onFailure(@NonNull Status status) {
          controlsCallback.onFailure(status.getStatusMessage());
        }
      });
    }


    public void stop(final ControlsCallback controlsCallback){
      remoteMediaClient.stop().setResultCallback(new ResultCallbacks<RemoteMediaClient.MediaChannelResult>() {
        @Override
        public void onSuccess(@NonNull RemoteMediaClient.MediaChannelResult mediaChannelResult) {
          controlsCallback.onSuccess();
        }

        @Override
        public void onFailure(@NonNull Status status) {
          controlsCallback.onFailure(status.getStatusMessage());
        }
      });
    }


    public void seek(long position, final ControlsCallback controlsCallback){
      remoteMediaClient.seek(position).setResultCallback(new ResultCallbacks<RemoteMediaClient.MediaChannelResult>() {
        @Override
        public void onSuccess(@NonNull RemoteMediaClient.MediaChannelResult mediaChannelResult) {
          controlsCallback.onSuccess();
        }

        @Override
        public void onFailure(@NonNull Status status) {
          controlsCallback.onFailure(status.getStatusMessage());
        }
      });
    }



  }

  public interface ControlsCallback{
    void onSuccess();

    void onFailure(String message);
  }

}
