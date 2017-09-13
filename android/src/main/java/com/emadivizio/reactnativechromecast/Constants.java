package com.emadivizio.reactnativechromecast;

/**
 * Created by Emanuele on 13/09/2017.
 */

public class Constants {

  public static final int SESSION_STARTING = 0;
  public static final int SESSION_STARTED = 1;
  public static final int SESSION_START_FAILED = 2;
  public static final int SESSION_ENDING = 3;
  public static final int SESSION_ENDED = 4;
  public static final int SESSION_RESUMING = 5;
  public static final int SESSION_RESUMED = 6;
  public static final int SESSION_RESUME_FAILED = 7;
  public static final int SESSION_SUSPENDED = 8;

  public static final String SESSION_STARTING_STRING = "SESSION_STARTING";
  public static final String SESSION_STARTED_STRING = "SESSION_STARTED";
  public static final String SESSION_START_FAILED_STRING = "SESSION_START_FAILED";
  public static final String SESSION_ENDING_STRING = "SESSION_ENDING";
  public static final String SESSION_ENDED_STRING = "SESSION_ENDED";
  public static final String SESSION_RESUMING_STRING = "SESSION_RESUMING";
  public static final String SESSION_RESUMED_STRING = "SESSION_RESUMED";
  public static final String SESSION_RESUME_FAILED_STRING = "SESSION_RESUME_FAILED";
  public static final String SESSION_SUSPENDED_STRING = "SESSION_SUSPENDED";
  public static final String SESSION_STATUS_STRING = "SESSION_STATUS";
  public static final String DEVICES_AVAILABLE_STRING = "DEVICES_AVAILABLE";
  public static final String DEVICE_CONNECTED_STRING = "DEVICE_CONNECTED";
  public static final String DEVICE_CONNECTING_STRING = "DEVICE_CONNECTING";
  public static final String SESSION_STATUS_MESSAGE_STRING = "SESSION_STATUS_MESSAGE";


  public static String intToString(int status){
    switch(status){
      case SESSION_STARTING:
        return SESSION_STARTING_STRING;
      case SESSION_STARTED:
        return SESSION_STARTED_STRING;
      case SESSION_START_FAILED:
        return SESSION_START_FAILED_STRING;
      case SESSION_ENDING:
        return SESSION_ENDING_STRING;
      case SESSION_ENDED:
        return SESSION_ENDED_STRING;
      case SESSION_RESUMING:
        return SESSION_RESUMING_STRING;
      case SESSION_RESUMED:
        return SESSION_RESUMED_STRING;
      case SESSION_RESUME_FAILED:
        return SESSION_RESUME_FAILED_STRING;
      case SESSION_SUSPENDED:
        return SESSION_SUSPENDED_STRING;
      default:
        return "";
    }
  }
}
