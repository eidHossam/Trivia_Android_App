# Online Trivia App

This is an Android application written in Kotlin that demonstrates the use of Clean Architecture and MVVM pattern with Dependency Injection using Dagger Hilt. It integrates with an online API using Retrofit for fetching trivia questions. Users can choose the category of the quiz.

## Table of Contents
- [Features](#features)
- [Architecture](#architecture)
- [Technologies Used](#technologies-used)
- [Setup and Installation](#setup-and-installation)
- [Usage](#usage)
- [API Integration](#api-integration)
- [Dependency Injection](#dependency-injection)

## Features
- Choose from various trivia categories
- Fetch trivia questions from an online API
- Clean Architecture and MVVM pattern
- Dependency Injection with Dagger Hilt
- Retrofit for API integration

## Architecture
The app follows the principles of Clean Architecture, separating the code into different layers to improve maintainability and testability.

### Layers
1. **Presentation Layer**: Contains UI-related code (Activities, Fragments, ViewModels)
2. **Domain Layer**: Contains business logic (UseCases, Repositories)
3. **Data Layer**: Handles data management (API services, Database)

### MVVM Pattern
- **Model**: Represents the data and business logic
- **View**: Displays the data to the user
- **ViewModel**: Acts as a bridge between the Model and the View

## Technologies Used
- **Kotlin**: Programming language
- **Dagger Hilt**: Dependency Injection
- **Retrofit**: API integration
- **Room**: Local database
- **Coroutines**: For asynchronous programming

## Setup and Installation
Download the APK from the Releases section

## Usage
1. **Select a category** from the list of available trivia categories.
2. **Start the quiz** and answer the questions.
3. **View your score** at the end of the quiz.

## API Integration
The app uses Retrofit to fetch trivia questions from an online API. 

## Dependency Injection
The app uses Dagger Hilt for dependency injection.

### Hilt Setup
- Application Class: Annotate with @HiltAndroidApp
- Activity/Fragment: Annotate with @AndroidEntryPoint
- Modules: Provide dependencies
