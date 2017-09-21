package com.emadivizio.reactnativechromecast.sdk.cast;

import android.net.Uri;

import com.emadivizio.reactnativechromecast.sdk.cast.util.Video;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.framework.CastSession;
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


  public CastControls loadVideo(Video video){
    return new CastControls(mCastSession.getRemoteMediaClient(), buildMediaInfo(video));
  }


}
