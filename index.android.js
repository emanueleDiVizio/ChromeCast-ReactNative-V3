/*
import { NativeModules } from 'react-native';

const { RNChromeCast } = NativeModules;

export default RNChromeCast;*/


import React, {Component, PropTypes} from 'react';
import {requireNativeComponent, ViewPropTypes, findNodeHandle, NativeModules, DeviceEventEmitter} from 'react-native';

let NativeChromeCast = NativeModules.RNChromeCast;


export class ChromeCastButton extends Component {

    render() {
        return <NativeChromeCastButton {...this.props} />;
    }
}

ChromeCastButton.propTypes = {
    progress: PropTypes.number,
    indeterminate: PropTypes.bool,
    ...ViewPropTypes
}


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

function CS() {
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

    this.listenForSessionEvents = (cb) => {
        DeviceEventEmitter.addListener("ChromeCastSessionEvent", (e) => {
            cb({status: e.SESSION_STATUS, message: e.SESSION_STATUS_MESSAGE})
        })
    }

    this.listenForScanEvent = (cb) => {
        DeviceEventEmitter.addListener("ChromeCastScanEvent", (e) => {
            cb({
                deviceConnected: e.DEVICE_CONNECTED,
                deviceConnecting: e.DEVICE_CONNECTING,
                devicesAvailable: e.DEVICES_AVAILABLE})
        })
    }


    let successCallback = function (cb) {
        cb({isSuccess: true, error: '', player: new ChromeCastPlayer(true)})
    };

    let errorCallback = function (cb, msg) {
        cb({isSuccess: false, error: msg, player: new ChromeCastPlayer(false)})
    }
}

export let ChromeCast = new CS()

let NativeChromeCastButton = requireNativeComponent('RCTChromeCastButton', ChromeCastButton);