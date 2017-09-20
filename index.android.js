/*
import { NativeModules } from 'react-native';

const { RNChromeCast } = NativeModules;

export default RNChromeCast;*/


import React, {Component, PropTypes} from 'react';
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

    let runIfAvailable = function (func) {
        if (isAvailable) {
            func()
        } else {
            console.log('Player not available.')
        }
    };

    let setUnavailable = function () {
        isAvailable = false;
    };

    let successCallback = function (cb) {
        cb({isSuccess: true, error: ''})
    };

    let errorCallback = function (cb, msg) {
        cb({isSuccess: false, error: msg})
    };


    this.play = (cb) => {
        runIfAvailable(() => NativeChromeCast.play((msg) => errorCallback(), () => successCallback(cb)))
    };

    this.pause = (cb) => {
        runIfAvailable(() => NativeChromeCast.pause((msg) => errorCallback(cb, msg), () => successCallback(cb)))
    };

    this.stop = (cb) => {
        runIfAvailable(() => NativeChromeCast.stop((msg) => errorCallback(cb, msg), () => {
            setUnavailable()
            successCallback(cb)
        }))
    }


}

function ChromeCastWrapper() {
    this.loadVideo = (video, cb) => {
        NativeChromeCast.loadVideo(video.url, video.title, video.subtitle,
            video.image, video.duration, video.isLive, video.mimeType,
            (msg) => errorCallback(cb, msg),
            () => {
                if (video.autoplay) {
                    NativeChromeCast.start(
                        (msg) => errorCallback(cb, msg),
                        () => successCallback(cb))
                }
                else {
                    successCallback(cb)
                }
            })
    };

    this.showChromeCastActivity = (cb) => {
        NativeChromeCast.showChromeCastActivity()
    }


    let successCallback = function (cb) {
        cb({isSuccess: true, error: '', player: new ChromeCastPlayer(true)})
    };

    let errorCallback = function (cb, msg) {
        cb({isSuccess: false, error: msg, player: new ChromeCastPlayer(false)})
    }
}

export let ChromeCast = new ChromeCastWrapper()


function ChromeCastScanner() {
    let listenForSessionEvents = (cb) => {
        DeviceEventEmitter.addListener("ChromeCastSessionEvent", (e) => {
            cb({status: e.SESSION_STATUS, message: e.SESSION_STATUS_MESSAGE})
        })
    }

    let listenForScanEvent = (cb) => {
        DeviceEventEmitter.addListener("ChromeCastScanEvent", (e) => {
            cb({
                deviceConnected: e.DEVICE_CONNECTED,
                deviceConnecting: e.DEVICE_CONNECTING,
                devicesAvailable: e.DEVICES_AVAILABLE
            })
        })
    }

    this.setUp = () => {
        NativeChromeCast.setUpScanner();
    }

    this.startScan = (scanCb, sessionCb) => {
        NativeChromeCast.startScan();
        this.scanListener = listenForScanEvent(scanCb);
        this.sessionListener = listenForSessionEvents(sessionCb)
    }

    this.stopScan = () => {
        NativeChromeCast.stopScan();
        this.scanListener.remove();
        this.sessionListener.remove()
    }
}

let NativeChromeCastButton = requireNativeComponent('RCTChromeCastButton', ChromeCastButton);

export class ChromeCastButton extends Component {

    constructor(props) {
        super(props);
        this.scanner = new ChromeCastScanner();
    }

    render() {
        return <NativeChromeCastButton {...this.props} />;
    }

    componentWillMount() {
        this.scanner.setUp()
        let {onScanEventReceived, onSessionEventReceived} = this.props;
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
}

ChromeCastButton.defaultProps = {
    onScanEventReceived: () => {
    },
    onSessionEventReceived: () => {
    },
}

export let SessionStatus = Object.freeze({
    STARTING: 0,
    STARTED: 1,
    START_FAILED: 2,
    ENDING: 3,
    ENDED: 4,
    RESUMING: 5,
    RESUMED: 6,
    RESUME_FAILED: 7,
    SUSPENDED: 8,
})


let NativeChromeCastMiniController = requireNativeComponent('RCTChromeCastMiniController', ChromeCastMiniController);

export class ChromeCastMiniController extends Component {

    render() {
        return <View>
            <NativeChromeCastMiniController {...this.props} />
        </View>;
    }
}

ChromeCastMiniController.propTypes = {
    progress: PropTypes.number,
    indeterminate: PropTypes.bool,
    ...ViewPropTypes
}
