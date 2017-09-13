
# react-native-chrome-cast

## Getting started

`$ npm install react-native-chrome-cast --save`

### Mostly automatic installation

`$ react-native link react-native-chrome-cast`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-chrome-cast` and add `RNChromeCast.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNChromeCast.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.emadivizio.reactnativechromecast.RNChromeCastPackage;` to the imports at the top of the file
  - Add `new RNChromeCastPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-chrome-cast'
  	project(':react-native-chrome-cast').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-chrome-cast/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-chrome-cast')
  	```

#### Windows
[Read it! :D](https://github.com/ReactWindows/react-native)

1. In Visual Studio add the `RNChromeCast.sln` in `node_modules/react-native-chrome-cast/windows/RNChromeCast.sln` folder to their solution, reference from their app.
2. Open up your `MainPage.cs` app
  - Add `using Chrome.Cast.RNChromeCast;` to the usings at the top of the file
  - Add `new RNChromeCastPackage()` to the `List<IReactPackage>` returned by the `Packages` method


## Usage
```javascript
import RNChromeCast from 'react-native-chrome-cast';

// TODO: What to do with the module?
RNChromeCast;
```



## Constants
```
SESSION_STARTING = 0;
SESSION_STARTED = 1;
SESSION_START_FAILED = 2;
SESSION_ENDING = 3;
SESSION_ENDED = 4;
SESSION_RESUMING = 5;
SESSION_RESUMED = 6;
SESSION_RESUME_FAILED = 7;
SESSION_SUSPENDED = 8;
```
  
