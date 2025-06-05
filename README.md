# Kotlin FHIR Multiplatform Demo App

[![License](https://img.shields.io/badge/License-Apache_2.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

This is a demo app for the [Kotlin FHIR](https://github.com/google/kotlin-fhir/) Multiplatform
library targeting Android, iOS, and Web. 

## User guide

* To run the Android app, open the project in Android Studio and set up an Android Run configuration
  for the `:composeApp` module. You can also run the `:composeApp:installDebug` Gradle task to
  install the Android app on your connected physical device or emulator. 
* `/iosApp` contains the iOS application. You can run it in Xcode, or in Android Studio if you have
  the [Kotlin Multiplatform](https://plugins.jetbrains.com/plugin/14936-kotlin-multiplatform)
  plugin.
* To open the web application, run the `:composeApp:wasmJsBrowserDevelopmentRun` Gradle task.

## Acknowledgements

This app is created using the [Kotlin Multiplatform Wizard](https://kmp.jetbrains.com) by JetBrains.
The data displayed in this app is from
[Sample FHIR Bulk Export Datasets](https://github.com/smart-on-fhir/sample-bulk-fhir-datasets).