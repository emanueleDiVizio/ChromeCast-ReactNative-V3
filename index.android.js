/*
import { NativeModules } from 'react-native';

const { RNChromeCast } = NativeModules;

export default RNChromeCast;*/

import React, { Component, PropTypes } from 'react';
import {
  requireNativeComponent,
  ViewPropTypes,
  findNodeHandle,
  NativeModules,
  DeviceEventEmitter,
  Text,
  View,
  Dimensions
} from 'react-native';

let NativeChromeCast = NativeModules.RNChromeCast;

function ChromeCastPlayer(available) {
  let isAvailable = available;

  let runIfAvailable = function(func) {
    if (isAvailable) {
      return func().then(() => Promise.resolve(this));
    } else {
      return Promise.reject(new Error('Player not available.'));
    }
  };

  let setUnavailable = function() {
    isAvailable = false;
    return Promise.resolve(this);
  };

  this.start = progress => {
    return runIfAvailable(() => NativeChromeCast.start(progress));
  };

  this.play = () => {
    return runIfAvailable(() => NativeChromeCast.play());
  };

  this.pause = () => {
    return runIfAvailable(() => NativeChromeCast.pause());
  };

  this.stop = () => {
    return runIfAvailable(() =>
      NativeChromeCast.stop().then(() => setUnavailable())
    );
  };
}

function ChromeCastWrapper() {
  this.loadVideo = video => {
    return NativeChromeCast.loadVideo(
      video.url,
      video.title,
      video.subtitle,
      video.image,
      video.duration,
      false,
      video.mimeType
    ).then(() => new ChromeCastPlayer(true));
  };

  this.loadLiveVideo = video => {
    return NativeChromeCast.loadVideo(
      video.url,
      video.title,
      video.subtitle,
      video.image,
      0,
      true,
      video.mimeType
    ).then(() => new ChromeCastPlayer(true));
  };

  this.showChromeCastActivity = cb => {
    NativeChromeCast.showChromeCastActivity();
  };
}

export let ChromeCast = new ChromeCastWrapper();

function ChromeCastScanner() {
  let listenForSessionEvents = cb => {
    return DeviceEventEmitter.addListener('ChromeCastSessionEvent', e => {
      cb({ status: e.SESSION_STATUS, message: e.SESSION_STATUS_MESSAGE });
    });
  };

  let listenForScanEvent = cb => {
    return DeviceEventEmitter.addListener('ChromeCastScanEvent', e => {
      cb({
        deviceConnected: e.DEVICE_CONNECTED,
        deviceConnecting: e.DEVICE_CONNECTING,
        devicesAvailable: e.DEVICES_AVAILABLE
      });
    });
  };

  this.setUp = () => {
    NativeChromeCast.setUpScanner();
  };

  this.startScan = (scanCb, sessionCb) => {
    NativeChromeCast.startScan();
    this.scanListener = listenForScanEvent(scanCb);
    this.sessionListener = listenForSessionEvents(sessionCb);
  };

  this.stopScan = () => {
    this.scanListener.remove();
    this.sessionListener.remove();
    NativeChromeCast.stopScan();
  };
}

let NativeChromeCastButton = requireNativeComponent(
  'RCTChromeCastButton',
  ChromeCastButton
);

export class ChromeCastButton extends Component {
  constructor(props) {
    super(props);
    this.scanner = new ChromeCastScanner();
  }

  render() {
    return <NativeChromeCastButton {...this.props} />;
  }

  componentWillMount() {
    this.scanner.setUp();
    let { onScanEventReceived, onSessionEventReceived } = this.props;
    this.scanner.startScan(onScanEventReceived, onSessionEventReceived);
  }

  componentWillUnmount() {
    this.scanner.stopScan();
  }
}

ChromeCastButton.propTypes = {
  onScanEventReceived: PropTypes.func.isRequired,
  onSessionEventReceived: PropTypes.func.isRequired,
  ...ViewPropTypes
};

ChromeCastButton.defaultProps = {
  onScanEventReceived: () => {},
  onSessionEventReceived: () => {}
};

export const SessionStatus = Object.freeze({
  STARTING: 0,
  STARTED: 1,
  START_FAILED: 2,
  ENDING: 3,
  ENDED: 4,
  RESUMING: 5,
  RESUMED: 6,
  RESUME_FAILED: 7,
  SUSPENDED: 8
});

let NativeChromeCastMiniController = requireNativeComponent(
  'RCTChromeCastMiniController',
  ChromeCastMiniController
);

export class ChromeCastMiniController extends Component {
  render() {
    return (
      <View>
        <NativeChromeCastMiniController {...this.props} />
      </View>
    );
  }
}

ChromeCastMiniController.propTypes = {
  progress: PropTypes.number,
  indeterminate: PropTypes.bool,
  ...ViewPropTypes
};
