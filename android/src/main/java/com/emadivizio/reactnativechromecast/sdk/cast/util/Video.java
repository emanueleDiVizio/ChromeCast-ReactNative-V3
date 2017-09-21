package com.emadivizio.reactnativechromecast.sdk.cast.util;

/**
 * Created by Emanuele on 12/09/2017.
 */
public class Video {
  private String url, title, subtitle, imageUri;
  private int duration;
  private StreamType type;
  private String mimeType;

  public Video(String url, String title, String subtitle, String imageUri, int duration, StreamType type, String mimeType) {
    this.url = url;
    this.title = title;
    this.subtitle = subtitle;
    this.imageUri = imageUri;
    this.duration = duration;
    this.type = type;
    this.mimeType = mimeType;
  }


  public String getUrl() {
    return url;
  }

  public String getTitle() {
    return title;
  }

  public String getSubtitle() {
    return subtitle;
  }

  public String getImageUri() {
    return imageUri;
  }

  public int getDuration() {
    return duration;
  }

  public StreamType getType() {
    return type;
  }

  public String getMimeType() {
    return mimeType;
  }

  public enum StreamType {
    LIVE, BUFFER
  }
}
