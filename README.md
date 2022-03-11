# Marvel - My Hero Team

App developed for Alma & Bryo partnership purpose.
Author : Fabrice Rakotonarivo from Bryo
The application can be used with Android 5+.
It is developed in full-kotlin (guess it is obvious😅).
Choice was made not to develop the app for Tablet, but it could easily be developed with the List-Detail Fragments architecture (see below).

## Setup

The Public and Private keys for the Marvel API are stored in locale.
In order to compile and launch the app : 

- add in your `~/.bash_profile` or `~/.profile` : 
```
export MARVEL_API_PUBLIC_KEY="your_public_key"
export MARVEL_API_PRIVATE_KEY="your_private_key"
```

- run in terminal :
`source ~/.profile` OR `source ~/.bash_profile`

- invalidate/cache Android Studio (not necessarily needed)

## Architecture

App developed with 2 modules in MVVM : 
- core : ApiService, mappers, API-models, repository : Repository used to retrieve through the Api Service the response parsed as Api-Models from the distant Server. The mappers allows to map the Api-Models in UI-Models
- app : Activities & Fragments, ViewModels, UI-Models, UseCases : Screens to be displayed in the App-lifecycle. The UseCases allow the ViewModels to do work such as retrieving data from the distant Server or to save in DB the current Hero Team (through the repositories).
Each Module has tests (the app Module also has androidTest).

## Tech choices

The application is developed with the libraries : 
- ViewBinding for the Activity/Fragment <-> xml files
- Material and AndroidX libraries for views and Lifecycle
- Coroutines for the data flow and the ViewModel Scopes
- Livedata for the implementation ViewModel -> View
- Glide for the images
- Koin for the Dependency Injection
- SquareUp Retrofit & OkHttp3 for the calls to the distant Server
- Room for the Database implementation
- Junit, Mockito, Koin-Test and Espresso for the Tests

## What is not in the app but should for Industry purpose :
- Firebase Crashlytics
- Firebase RemoteConfig could be but is not needed at this stage
- Firebase Analytics could be a + for Metrics purpose
- Keys for Store

## TO DO
- UI Tests missing !!!
- Update-merge in base instead of erasing in order not to lose the current Squad on refresh from server
- More ViewModel, Functional and Unit Tests
- Implement UI for Tablet
- Update launcher icon
- Multi-language with strings-fr
- DarkMode ? not really necessary as background is always Dark
- Accessibility
- Improve Design System with textStyles and dimens
- Improve UI

## Notes
Because of the limited number of calls allowed to the distant Server, I choose to implement a Cache (with Room) of 24h (as advised in the Server documentation). 
So on start of the main screen, the app should fetch a data list only if :
- the cache is empty
- the cache is filled with data older than 24h

The user can force-refresh the data with a pull-to-refresh from the main application.
Distant Server test page : https://developer.marvel.com/docs


https://user-images.githubusercontent.com/45197000/157774785-aad3fe69-ffb4-4c8f-ad89-fe36fc3e7140.mov

https://user-images.githubusercontent.com/45197000/157821911-a3f35fc3-4e45-438c-8799-a2824b6124b4.mov


