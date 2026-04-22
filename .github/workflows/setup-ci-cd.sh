#!/bin/bash

# Mobile Automation CI/CD Helper Script
# This script helps set up and validate the CI/CD environment locally

set -e

echo "=================================="
echo "Mobile Automation CI/CD Setup"
echo "=================================="
echo ""

# Check Java
echo "Checking Java installation..."
if command -v java &> /dev/null; then
    JAVA_VERSION=$(java -version 2>&1 | awk -F '"' '/version/ {print $2}')
    echo "✓ Java found: $JAVA_VERSION"
else
    echo "✗ Java not found. Please install JDK 17 or higher."
    exit 1
fi

# Check Maven
echo "Checking Maven installation..."
if command -v mvn &> /dev/null; then
    MAVEN_VERSION=$(mvn -version | head -n 1 | awk '{print $3}')
    echo "✓ Maven found: $MAVEN_VERSION"
else
    echo "✗ Maven not found. Please install Maven 3.6+"
    exit 1
fi

# Check Node.js (for Appium)
echo "Checking Node.js installation..."
if command -v node &> /dev/null; then
    NODE_VERSION=$(node -v)
    echo "✓ Node.js found: $NODE_VERSION"
else
    echo "⚠ Node.js not found. Required for Appium."
fi

# Check Android SDK
echo "Checking Android SDK..."
if [ -n "$ANDROID_HOME" ]; then
    echo "✓ ANDROID_HOME set: $ANDROID_HOME"
else
    echo "⚠ ANDROID_HOME not set. Required for mobile tests."
fi

# Clean and compile
echo ""
echo "Building project..."
mvn clean compile -DskipTests

echo ""
echo "=================================="
echo "✓ Environment validation complete!"
echo "=================================="
echo ""
echo "Next steps:"
echo "1. Push code to GitHub repository"
echo "2. Go to Actions tab in GitHub"
echo "3. Workflows will run automatically on push/PR"
echo ""
echo "To run tests locally:"
echo "  mvn test"
echo ""
echo "To run specific test:"
echo "  mvn test -Dtest=SignInTest"
