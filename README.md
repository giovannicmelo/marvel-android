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
This project was developed with Clean Architecture with MVVM, making use of SOLID principles so that each layer has its responsibility and is testable, with the following layers:

![https://blog.cleancoder.com/uncle-bob/images/2012-08-13-the-clean-architecture/CleanArchitecture.jpg "CleanArchitecture.jpg")](https://blog.cleancoder.com/uncle-bob/images/2012-08-13-the-clean-architecture/CleanArchitecture.jpg "CleanArchitecture.jpg")

- **app**: Application module
  - **mappers**: Models and DTOs adapters 
  - **frameworks**: Storage database and API services libraries
  - **presentation**: Views and view models
- **core**: Business Logic module
  - data: business contracts and commom classes
  - domain: business classes and objects
  - usecases: User interactors classes

This project also uses Cached Data First Strategy, improvement a better user experience. In this case, if we have some data storaged in app, we should load it at first instead fetch it from server. If we don't, we fetch it from server and then storaged in app.

## Compatibility
This application project was developed to run on Android versions up to API 23 (Android 6.0)

## Libraries used on this project:

### Coroutines:
- Version: 1.4.1
- Used: To run tasks in background

### Glide:
- Version: 4.9.0
- Used: To load images from URI

### Koin:
- Version: 2.1.3
- Used: Dependence injection

### Ktlint:
- Version: 0.30.0
- Used: To format and clean code

### Lottie:
- Version: 3.0.7
- Used: To animate loaders

### Mockito:
- Version: 2.2.0
- Used: To mock objects in unit tests

### OkHttp:
- Version: 4.0.1
- Used: With Retrofit to intercept Logs and create requests clients

### Retrofit:
- Version: 2.9.0
- Used: To make requests on server API

### PaperDb:
- Version: 2.7.1
- Used: To storage data in app

## Code coverage:
*In progress...*
