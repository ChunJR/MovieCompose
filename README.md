# MovieCompose
Movie App is a Single Activity App with Jetpack Compose, built-in Clean Architecture, movie information with the backend api [theMovieDb](https://developer.themoviedb.org/reference/intro/getting-started).
There are 2 main screen to showing multiple movie list and the movie details

## Features
- Movies screen: 
  - Dislay all Movies result with Paging3.
  - Favourite function ( NOT done yet ).
- Search Movies screen: 
  - Dislay all Movies result with Paging3.
  - Search function.

## Technical

- **Android Jetpack Component**:
  - **[Compose]** - Android’s recommended modern toolkit for building native UI.
  - **[ViewModel]** - a class designed to store and manage UI-related data in a lifecycle-conscious way.
  - **[StateFlow]** - an observable data holder class.
    **[Paging3]** - The Paging library provides powerful capabilities for loading and displaying paged data from a larger dataset.

- **Design Pattern**:
  - **[MVVM]** a Model-View-ViewModel architecture that removes the tight coupling between each component.
    
- **Dependency Injection**:
  - **[Hilt]** - a dependency injection library for Android that reduces the boilerplate of doing manual dependency injection in your project.
 
- **Networking**:  
  - **[Retrofit]** - a type-safe REST client for Android that aims to make it easier to consume RESTful web services.
  - **[Coil]** - an image-loading library backed by Kotlin Coroutines.

- **Asynchronous**:
  - **[Coroutine]** - a concurrency design pattern that you can use on Android to simplify code that executes asynchronously.
