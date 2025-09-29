# WeatherApp 🌦️

A modern and simple Android application, built to demonstrate a robust, scalable, and maintainable architecture. This app fetches the current weather for a predefined location and displays it in a clean, adaptive UI.

## ✨ Features

*   **Weather Data Fetching**: Fetches and displays the current temperature for San Jose, CA.
*   **State Handling**: Gracefully manages loading and error states.
*   **Adaptive UI**: The user interface seamlessly adapts to different screen sizes and orientations, providing an optimal experience on phones, tablets, and TV devices.
*   **Product Flavors**: Supports different build variants for standard Android (`googleAndroid`) and Amazon Fire TV (`amazonTv`).

## 🏛️ Architecture

This project follows the principles of **Clean Architecture** and is structured into multiple modules to promote separation of concerns, scalability, and independent development.

```
+-----------------+      +------------------+      +----------------+
|   app (Layer)   |----->| ui-weather (Lib) |----->|  core-weather  |
| (Presentation)  |      |   (UI Widgets)   |      | (Domain & Data)|
+-----------------+      +------------------+      +----------------+
```

### Module Breakdown

#### 📦 `app`
This is the main application module and acts as the **Presentation Layer**. It is responsible for:
*   **UI Orchestration**: `MainActivity` and `WeatherScreen` tie all the pieces together. `MainActivity` calculates the screen size, and `WeatherScreen` manages the UI state.
*   **State Management**: `MainViewModel` fetches data from the `UseCase`, handles screen logic, and exposes the state (`MainUiState`) to the Composables.
*   **Dependency Injection**: Sets up the Hilt modules for the entire application.
*   **Product Flavors**: Defines the `googleAndroid` and `amazonTv` build variants, each with its own `AndroidManifest.xml` to configure device capabilities.

#### 📦 `core-weather`
This is a pure Kotlin module, independent of Android, representing the **Domain and Data layers**.
*   **Domain**: Contains the business models (`Weather`), the repository interface (`WeatherRepository`), and the use cases (`GetCurrentWeatherUseCase`) that encapsulate business logic.
*   **Data**: Includes the repository implementation (`WeatherRepositoryImpl`), data sources (`WeatherApi`), DTOs (`PointsDto`, `ForecastDto`), and mappers to convert DTOs into domain models.
*   **Network**: Defines the Retrofit interface (`WeatherApi`) for communicating with the weather API.

#### 📦 `ui-weather`
This is an Android library module focused exclusively on **reusable Jetpack Compose components**.
*   **Widgets**: Contains data-agnostic UI widgets like `TemperatureWidget`, `LoadingWidget`, and `ErrorWidget`.
*   **Adaptability**: The `TemperatureWidget` is adaptive itself, changing its internal layout from a `Column` to a `Row` based on the available screen space.
*   **Extensions**: Provides UI extension functions, such as the `glass` effect for backgrounds.

## 🛠️ Tech Stack

*   **Language**: [Kotlin](https://kotlinlang.org/)
*   **UI**: [Jetpack Compose](https://developer.android.com/jetpack/compose)
*   **Architecture**: Clean Architecture, MVVM
*   **Dependency Injection**: [Hilt](https://dagger.dev/hilt/)
*   **Networking**: [Retrofit](https://square.github.io/retrofit/) & [OkHttp](https://square.github.io/okhttp/)
*   **Asynchrony**: [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) & [Flow](https://kotlinlang.org/docs/flow.html)

## 🚀 Getting Started

1.  **Clone the repository**:
    ```bash
    git clone https://github.com/your-username/WeatherApp.git
    ```
2.  **Open the project** in Android Studio.
3.  **Build and run**: Select one of the build variants (`googleAndroidDebug` or `amazonTvDebug`) and a compatible device (emulator or physical).

## 🧪 Testing

The modular architecture of this project makes unit and instrumentation testing straightforward.

*   **`core-weather`**: Unit tests can be added using JUnit and MockK/Mockito for `UseCases`, `Repositories`, and `Mappers` without needing an Android emulator.
*   **`ui-weather`**: Screenshot testing and UI tests can be added for the Compose widgets in isolation.
*   **`app`**: Instrumentation tests can be added for the `ViewModel` and end-to-end UI tests.
