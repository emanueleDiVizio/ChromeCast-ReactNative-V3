package com.emadivizio.reactnativechromecast.sdk;

/**
 * Created by Emanuele on 12/09/2017.
 */
public class Video {
  private String url, title, subtitle, imageUri;
  private int duration;
  private StreamType type;

  public Video(String url, String title, String subtitle, String imageUri, int duration, StreamType type) {
    this.url = url;
    this.title = title;
    this.subtitle = subtitle;
    this.imageUri = imageUri;
    this.duration = duration;
    this.type = type;
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

  public enum StreamType {
    LIVE, BUFFER
  }
}
