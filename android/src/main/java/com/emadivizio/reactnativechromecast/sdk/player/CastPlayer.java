package com.emadivizio.reactnativechromecast.sdk.player;

import android.net.Uri;

import com.emadivizio.reactnativechromecast.sdk.Video;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.common.images.WebImage;

/**
 * Created by Emanuele on 12/09/2017.
 */

public class CastPlayer {


  private CastSession mCastSession;
  private RemoteMediaClient remoteMediaClient;


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
          .setContentType("videos/mp4")
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


    public void load(boolean autoplay, long position){
      remoteMediaClient.load(mediaInfo, autoplay, position);
    }


    public void play(){
      remoteMediaClient.play();
    }


    public void pause(){
      remoteMediaClient.pause();
    }


    public void stop(){
      remoteMediaClient.stop();
    }


    public void seek(long position){
      remoteMediaClient.seek(position);
    }
  }

}
