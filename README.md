[![Kotlin](https://img.shields.io/badge/version-1.0.0-orange.svg)]()
[![Kotlin](https://img.shields.io/badge/kotlin-powered-green.svg)]()
[![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg)](https://ktlint.github.io/)
[![Build](https://github.com/giovannicmelo/marvel-android/actions/workflows/build.yml/badge.svg?branch=develop)](https://github.com/giovannicmelo/marvel-android/actions/workflows/build.yml)

# Marvel Characters
A sample of a consumer application of the Marvel API, made in Android

# Screenshots
![Splash](/screenshots/splash.png)
![Main](/screenshots/main.png)
![Details](/screenshots/details.png)

## Project Structure
This project was separated in 3 modules:
- app: Main module, containing specific files from application
- data: All remote server files, with their configuration files 
- ui: User interface files, as images, drawables, fonts in commom

## Compatibility
This application project was developed to run on Android versions up to API 23 (Android 6.0)

## Libraries used on this project:

### Coroutines:
- Version: 1.2.2
- Used: To run tasks in background

### Glide:
- Version: 4.9.0
- Used: To load images from URI

### Koin:
- Version: 2.0.1
- Used: Dependence injection

### Ktlint:
- Version: 0.30.0
- Used: To format and clean code

### Lottie:
- Version: 3.0.7
- Used: To animate loaders

### Mockito:
- Version: 2.1.0
- Used: To mock objects in unit tests

### OkHttp:
- Version: 4.0.1
- Used: With Retrofit to intercept Logs and create requests clients

### Retrofit:
- Version: 2.6.0
- Used: To make requests on server API

## Code coverage:

### data
- Class: 18%
- Method: 24%
- Line: 10%

### app
- Class: 8%
- Method: 7%
- Line: 1%
