/*
import { NativeModules } from 'react-native';

const { RNChromeCast } = NativeModules;

export default RNChromeCast;*/


import React, { Component, PropTypes } from 'react';
import { requireNativeComponent, ViewPropTypes, findNodeHandle} from 'react-native';


export default class ChromeButton extends Component {

    render() {
        return  <NativeChromeCastButton {...this.props} />;
    }
}

ChromeButton.propTypes = {
    progress: PropTypes.number,
    indeterminate: PropTypes.bool,
    ...ViewPropTypes
}

let NativeChromeCastButton = requireNativeComponent('RCTChromeCastButton', ChromeButton);