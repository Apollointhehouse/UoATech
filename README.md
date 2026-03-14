# UoATech

UoATech is a Kotlin Multiplatform application designed for students at the University of Auckland. It aggregates upcoming events from various tech clubs across the university, providing a single, convenient list for students to stay informed about workshops, talks, and networking opportunities.

## Features

- **Event Aggregation**: Automatically fetches events from multiple tech club websites (e.g., DEVS).
- **Unified List**: Displays all upcoming events in a clean, searchable, and easy-to-read interface.
- **Multiplatform Support**: Available on Android, iOS, and Web.
- **Detailed Event Information**: Shows event name, hosting club, date, time, location, and description.

## Project Structure

This project is built using **Compose Multiplatform** and follows a standard Kotlin Multiplatform structure:

*   [`/composeApp`](./composeApp/src): Contains shared code for all platforms.
    *   [`commonMain`](./composeApp/src/commonMain/kotlin): Core logic, data models, and UI components.
    *   `androidMain`, `iosMain`, `webMain`: Platform-specific implementations and entry points.
*   [`/iosApp`](./iosApp): The iOS-specific Xcode project and SwiftUI entry point.

## Technology Stack

- **Kotlin Multiplatform (KMP)**: For sharing business logic across platforms.
- **Compose Multiplatform**: For building a shared UI.
- **Ktor**: Used for making asynchronous network requests to fetch event data.
- **Kotlinx Serialization**: For parsing JSON data from club APIs.
- **Kotlinx Coroutines & Flow**: For reactive and asynchronous data handling.
- **Kotlinx Datetime**: For consistent date and time management.

## How it Works

The app uses a "Scraper" architecture to fetch data from different sources. Each tech club has a corresponding `EventScraper` implementation (e.g., `DevsScraper`) that:
1.  Fetches data from the club's website or API.
2.  Parses the data into a common `Event` model.
3.  Filters out past events.

The `API` object coordinates these scrapers to provide a unified flow of events to the UI.

## Getting Started

### Prerequisites

- [Android Studio](https://developer.android.com/studio) or [IntelliJ IDEA](https://www.jetbrains.com/idea/)
- [Xcode](https://developer.apple.com/xcode/) (for iOS development)
- [JDK 17](https://www.oracle.com/java/technologies/downloads/) or higher

### Build and Run Android Application

To build and run the development version of the Android app, use the run configuration from the IDE or run from the terminal:
- **Windows**: `.\gradlew.bat :composeApp:assembleDebug`
- **macOS/Linux**: `./gradlew :composeApp:assembleDebug`

### Build and Run Web Application

The web app supports both Wasm and JS targets:
- **Wasm (Modern browsers)**: `.\gradlew.bat :composeApp:wasmJsBrowserDevelopmentRun`
- **JS (Broad compatibility)**: `.\gradlew.bat :composeApp:jsBrowserDevelopmentRun`

### Build and Run iOS Application

Use the run configuration in your IDE or open the [`/iosApp`](./iosApp) directory in Xcode.

---

Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html) and [Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform/#compose-multiplatform).

## License

This project is licensed under the [MIT License](./LICENSE).