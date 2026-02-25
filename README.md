# Flutter Mobile Automation Framework

A robust, scalable, and maintainable Mobile Automation Framework for Flutter applications using Java, Appium, Maven, and TestNG.

## Framework Structure

```
FlutterMobileAutomationFramework/
├── src/
│   ├── main/
│   │   ├── java/com/mobile/framework/
│   │   │   ├── base/                 # Base classes
│   │   │   ├── config/               # Configuration management
│   │   │   ├── drivers/              # Driver management
│   │   │   ├── pages/                # Page Object Model classes
│   │   │   ├── reports/              # Reporting utilities
│   │   │   ├── utilities/            # Utility classes
│   │   │   └── tests/                # Test classes
│   │   └── resources/                # Resource files
│   └── test/
│       ├── java/com/mobile/framework/tests/  # Test classes
│       └── resources/                       # Test resource files
├── reports/                          # Generated reports and screenshots
├── logs/                             # Log files
├── pom.xml                           # Maven configuration
└── testng.xml                        # TestNG configuration
```

## Technology Stack

- **Language**: Java
- **Automation Tool**: Appium Java Client
- **Framework**: TestNG
- **Reporting**: ExtentReports
- **Logging**: Log4j2
- **Build Tool**: Maven
- **Application Type**: Flutter Mobile Application

## Key Features

1. **Page Object Model (POM)**: Clean separation of test logic and page elements
2. **Configuration Management**: Centralized configuration using properties files
3. **Driver Management**: Programmatic control of Appium driver lifecycle
4. **Server Management**: Automatic start/stop of Appium server
5. **Reporting**: Comprehensive HTML reports with screenshots
6. **Logging**: Detailed logging with Log4j2
7. **Retry Mechanism**: Automatic retry of failed tests
8. **Parallel Execution Ready**: Designed for parallel test execution
9. **Flutter Support**: Specialized locators for Flutter applications

## Setup Instructions

### Prerequisites

1. Java JDK 11 or higher
2. Android SDK
3. Node.js and npm
4. Appium (`npm install -g appium`)
5. Android device/emulator

### Configuration

1. Update `src/main/resources/config.properties` with your application and device details:
   ```
   app.path = /path/to/your/flutter/app.apk
   android.device.name = YourDeviceName
   android.udid = YourDeviceUDID
   ```

### Running Tests

1. **Using Maven**:
   ```bash
   mvn clean test
   ```

2. **Using TestNG**:
   ```bash
   mvn test -DsuiteXmlFile=testng.xml
   ```

## Design Decisions

### 1. Folder Structure
- **Modular Organization**: Separated concerns into distinct packages for better maintainability
- **Scalability**: Easy to add new pages, tests, and utilities without disrupting existing code

### 2. Page Object Model Implementation
- **BasePage Class**: Contains common methods and utilities shared across all pages
- **Element Locators**: Used XPath for Android elements; can be extended for Flutter-specific locators
- **Method Chaining**: Fluent interface for better readability and test maintenance

### 3. Configuration Management
- **ConfigManager**: Singleton pattern for efficient resource utilization
- **Externalized Configuration**: All configurable values in properties file for easy environment switching

### 4. Driver Management
- **DriverFactory**: Centralized driver initialization with thread-local storage for parallel execution
- **AppiumServerManager**: Programmatic control of Appium server lifecycle

### 5. Reporting and Logging
- **ExtentReports**: Professional HTML reports with step-by-step execution details
- **Log4j2**: Structured logging with multiple appenders (console and file)

### 6. Test Structure
- **BaseTest**: Common setup and teardown methods to reduce code duplication
- **TestNG Annotations**: Proper use of @BeforeSuite, @BeforeMethod, @AfterMethod, @AfterSuite
- **RetryAnalyzer**: Automatic retry mechanism for flaky tests

## Best Practices Implemented

1. **DRY Principle**: Common functionalities abstracted into base classes and utilities
2. **Exception Handling**: Proper error handling with meaningful messages
3. **Logging**: Comprehensive logging for debugging and audit purposes
4. **Screenshot Capture**: Automatic screenshots on test failures
5. **Wait Strategies**: Explicit waits for reliable element interactions
6. **Code Reusability**: Utility classes for common actions
7. **Maintainability**: Clear separation of concerns and well-documented code

## Extending the Framework

### Adding New Pages
1. Create a new class in `src/main/java/com/mobile/framework/pages/`
2. Extend `BasePage` class
3. Implement page-specific elements and methods

### Adding New Tests
1. Create a new class in `src/test/java/com/mobile/framework/tests/`
2. Extend `BaseTest` class
3. Implement test methods with appropriate TestNG annotations

### Adding New Utilities
1. Create a new class in `src/main/java/com/mobile/framework/utilities/`
2. Implement utility methods as static methods for easy access

## CI/CD Integration

The framework is designed to be CI/CD ready:
- Maven-based build for easy integration with Jenkins, GitHub Actions, etc.
- Configurable through properties files for different environments
- Comprehensive reporting for test results visualization
- Structured logging for monitoring and debugging

## Troubleshooting

### Common Issues

1. **Appium Server Not Starting**:
   - Ensure Appium is installed globally (`npm install -g appium`)
   - Check if the configured port is available

2. **Driver Initialization Failure**:
   - Verify device connectivity
   - Check if the app path is correct
   - Ensure device UDID is correct

3. **Element Not Found**:
   - Update locators in page classes
   - Add appropriate waits
   - Check if the element is in an iframe or shadow DOM

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a pull request

## License

This project is licensed under the MIT License.