[![CI](https://github.com/PiaVillalba/WeatherApp/actions/workflows/android-ci.yml/badge.svg)](https://github.com/PiaVillalba/WeatherApp/actions/workflows/android-ci.yml)
[![Kotlin](https://img.shields.io/badge/Kotlin-2.2.0-blue.svg?logo=kotlin)](https://kotlinlang.org/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)


# WeatherApp 🌦️

A modern and simple Android application, built to demonstrate a robust, scalable, and maintainable architecture.
This app fetches the current weather for a predefined location and displays it. It also demonstrates integration of weather APIs, adaptive UI for different devices, and product flavors for **Google Android** and **Amazon FireTV**.

## 📚 Table of Contents
- [✨ Features](#-features)
- [🏛️ Architecture](#-architecture)
- [🌐 Product Flavors](#-product-flavors)
- [🛠️ Tech Stack](#-tech-stack)
- [🚀 Getting Started](#-getting-started)
- [🧪 Testing](#-testing)
- [📸 Screenshots](#-screenshots)
- [🎥 Demo Video](#-demo-video)
- [🤝 Contributing](#-contributing)
- [📄 License](#-license)


## ✨ Features

- 🌍 **Weather Data Fetching** → Retrieves and displays current weather conditions.
- 🔄 **State Handling** → Manages loading, error, and success states gracefully.
- 📱 **Adaptive UI** → Optimized for different screen sizes (phone, tablet, TV) and orientations (portrait/landscape).
- 🖥 **Product Flavors** → Supports build variants for `googleAndroid` and `amazonTv`.
- 🧩 **Modularization** → Clear separation of concerns with independent modules.
- ✅ **Testing Support** → Unit tests for domain/data logic and UI tests for Compose widgets.

## 🏛️ Architecture

This project follows the principles of **Clean Architecture** and is structured into multiple modules to promote separation of concerns, scalability, and independent development.

Is organized into **three modules** for clear separation of concerns:

- **`app/`** → Main application module. Handles navigation, flavors, and app entry points.
- **`core-weather/`** → Core business logic, networking, repositories, and models for weather data.
- **`ui-weather/`** → Reusable UI components and widgets (Compose) such as `TemperatureWidget`, `LoadingWidget`, and error screens.

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

## 🌐 Product Flavors

Defined in `app/build.gradle.kts`:

- **Google Android** → For phones and tablets.
- **Amazon FireTV** → TV-ready with launcher intent filter.

Build commands:

```bash
# Google Android (phone/tablet)
./gradlew installGoogleAndroidDebug

# Amazon FireTV
./gradlew installAmazonTvDebug
```

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
4.  **Run the app**: with:
    ```bash
    ./gradlew installGoogleAndroidDebug
    # or
    ./gradlew installAmazonTvDebug
    ```

## 🧪 Testing

The project is modularized to simplify testing at different layers:

- **`core-weather` (Unit Tests)**
    - Tests for use cases, repositories, and mappers.
    - Runs with **JUnit** + **MockK/Mockito**.
    - Does not require an Android emulator.

- **`ui-weather` (UI Tests)**
    - Compose UI tests for widgets like `TemperatureWidget`, `ErrorCard`, and `LoadingWidget`.
    - Supports screenshot testing and interaction validation.

- **`app` (Instrumentation Tests)**
    - End-to-end UI tests for `MainActivity` and `WeatherScreen`.
    - Verifies orientation changes, adaptive layouts, and state handling.

### Run all tests
```bash
./gradlew test connectedCheck
```

## 📸 Screenshots

| Phone | Tablet | TV |
|-------|--------|----|
| <img src="https://github.com/user-attachments/assets/a0e9b3f4-a0a0-4dfc-b750-528e6b40aad2" width="250" /> | <img src="https://github.com/user-attachments/assets/864325fd-9170-4872-85d2-1129404a6ec3" width="400" /> | <img src="https://github.com/user-attachments/assets/7b4d9e2c-4799-46ca-8818-a1b7c86b6bb8" width="400" /> |
| <img src="https://github.com/user-attachments/assets/2593f3b8-bbf1-4331-be2f-f8da985188ed" width="250" /> | <img src="https://github.com/user-attachments/assets/fa087a95-95c7-4a9c-b6d7-f6990d235522" width="400" /> | <img src="https://github.com/user-attachments/assets/afacdb67-38c8-4cce-909c-5986151d3823" width="400" /> |
| <img src="https://github.com/user-attachments/assets/d1559d01-505c-47cc-9b93-b3af0cc7dc11" width="250" /> | <img src="https://github.com/user-attachments/assets/e8c3b14d-e8bc-4d1b-ac7a-e0213bbf60fb" width="400" /> | <img src="https://github.com/user-attachments/assets/e8c3b14d-e8bc-4d1b-ac7a-e0213bbf60fb" width="400" /> |

## 🎥 Demo Video
[mobile.webm](https://github.com/user-attachments/assets/57d491c4-968b-4b03-8b1b-705f59deee53)

## 🤝 Contributing
Contributions, issues, and feature requests are welcome!  
Feel free to open a [discussion](../../discussions) or a [pull request](../../pulls).

