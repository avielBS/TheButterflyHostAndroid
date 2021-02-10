# TheButterflyHost Android SDK
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT) [![](https://img.shields.io/cocoapods/p/CrashOps.svg?style=flat)](https://cocoapods.org/pods/CrashOps)

TheButterflyHost help you app to take part in the fight against domestic violent.


## Installation
### 🔌 & ▶️

### Install via gradle

#### Using the plain and common maven

#### Using "jitpack.io"

In your root-level "build.gradle" file, put:
```
    allprojects {
        repositories {
            jcenter()
            maven { url "https://jitpack.io" }
        }
   }
```

In your app-level "build.gradle" file, put:
```
   dependencies {
    implementation 'com.github.avielBS:TheButterflyHostAndroid:0.1.5'
   }
```

## Usage

To recognize your app in ButterflyHostSDK servers you need an application key, you can set it via code.
In order to present the view, ButterflyHostSDK require an the current Activitiy.

#### Example

```Java
// Java (pretty much like Kotlin 🙂)
ButterflyHost butterflyHost = ButterflyHost.getInstance();
        butterflyHost.OnGrabReportRequested(activity, YOUR_API_KEY);
```
