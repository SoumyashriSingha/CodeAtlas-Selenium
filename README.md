# CodeAtlas UI Automation - Test Framework

## Overview
This is a comprehensive UI automation test framework for **CodeAtlas** - an AI-powered GitHub repository analysis tool built using Selenium WebDriver, TestNG, and Java.

**Live Application**: https://codeatlas-frontend-khaki.vercel.app/

## Features
- **Stack Detection** - Automatically identifies technology stack
- **Architecture Diagrams** - Visual representation of code structure
- **Module Mapping** - Detailed module relationships
- **Improvement Insights** - AI-powered suggestions for code improvement

## Framework Components

### Tech Stack
- **Selenium WebDriver**: 4.15.0
- **TestNG**: 7.8.1
- **WebDriver Manager**: 5.8.1 (Automatic driver management)
- **Extent Reports**: 5.1.1 (Professional HTML reporting)
- **Java**: 11+
- **Maven**: Build automation

## Project Structure

```
codeatlas-ui-automation/
├── pom.xml                              # Maven configuration
├── testng.xml                           # TestNG suite configuration
├── src/test/java
│   ├── base/
│   │   ├── BaseTest.java               # Base test class with setup/teardown
│   │   └── DriverFactory.java          # WebDriver instantiation & management
│   │
│   ├── pages/
│   │   ├── HomePage.java               # Home page locators & methods
│   │   ├── AnalysisPage.java           # Analysis results page
│   │   └── GraphPage.java              # Architecture graph page
│   │
│   ├── tests/
│   │   ├── SmokeTests.java             # Basic functionality tests
│   │   ├── RepositoryAnalysisTests.java # Repository analysis tests
│   │   └── GraphTests.java             # Graph interaction tests
│   │
│   ├── utils/
│   │   ├── ConfigReader.java           # Configuration file reader
│   │   ├── WaitUtils.java              # Explicit wait utilities
│   │   └── ScreenshotUtils.java        # Screenshot capture utility
│   │
│   └── listeners/
│       └── TestListener.java           # TestNG listener + Extent Reports
│
├── src/test/resources
│   ├── config.properties                # Application configuration
│   └── testdata.json                   # Test data
│
└── test-output/
    ├── extent-report.html              # Extent report (generated)
    ├── screenshots/                    # Failed test screenshots
    └── emailable-report.html           # TestNG report
```

## Test Cases

### 1. Smoke Tests (`SmokeTests.java`)
Basic functionality tests to verify the application loads correctly:
- Home page loads successfully
- Hero elements (title, subtitle) are visible
- Feature badges are displayed
- Suggested repositories are visible
- GitHub URL input field is accessible
- Analyze button is visible

### 2. Repository Analysis Tests (`RepositoryAnalysisTests.java`)
Tests repository analysis functionality with real GitHub repositories:
- **FastAPI** (https://github.com/tiangolo/fastapi) - Python framework
- **Java** (https://github.com/SoumyashriSingha/taskManagerFS) - Java framework
- **Node.js** (https://github.com/ChaitanyaNirf/redis-client-tracking) - Node.js framework

Test scenarios:
- Analyze repository from URL
- Verify stack detection results
- Verify architecture analysis results
- Verify module mapping results
- Verify improvement suggestions
- Test with suggested repository buttons
- Test with multiple repositories (Data-driven tests)

### 3. Graph/Architecture Tests (`GraphTests.java`)
Tests visualization and graph interaction:
- Architecture graph displays correctly
- Nodes and edges are present
- Zoom controls are functional (zoom in/out)
- Graph can be interacted with (drag and pan)
- Node details are accessible on click

## Setup & Installation

### Prerequisites
- Java 11 or higher
- Maven 3.6+
- Chrome, Firefox, or Edge browser

### Installation Steps

1. **Clone or navigate to the project**
   ```bash
   cd CodeAtlas-Selenium
   ```

2. **Install dependencies**
   ```bash
   mvn clean install
   ```

3. **Run all tests**
   ```bash
   mvn test
   ```

4. **Run specific test class**
   ```bash
   mvn test -Dtest=SmokeTests
   mvn test -Dtest=RepositoryAnalysisTests
   mvn test -Dtest=GraphTests
   ```

5. **Run with specific browser**
   ```bash
   mvn test -Dbrowser=firefox
   mvn test -Dbrowser=edge
   mvn test -Dbrowser=chrome  # default
   ```

## Configuration

### Browser Configuration (`config.properties`)
```properties
browser=chrome                          # chrome, firefox, edge
base.url=https://codeatlas-frontend-khaki.vercel.app/
implicit.wait=10                        # seconds
explicit.wait=10                        # seconds
screenshot.path=test-output/screenshots/
```

### Test Data (`testdata.json`)
Contains sample repositories and test users for data-driven tests.

## Usage Examples

### Running Smoke Tests Only
```bash
mvn test -Dgroups=smoke
```

### Running with Parallel Execution (2 threads)
```bash
mvn test -DparallelMode=methods -DthreadCount=2
```

### Generating Reports
```bash
mvn test
# Reports are generated at: test-output/extent-report.html
```

## Test Execution Flow

1. **Setup Phase**
   - Initialize WebDriver with WebDriver Manager
   - Maximize browser window
   - Navigate to application

2. **Test Phase**
   - Enter GitHub repository URL
   - Click Analyze button
   - Wait for results to load
   - Verify expected elements are displayed

3. **Teardown Phase**
   - Capture screenshot on failure (via TestListener)
   - Close browser
   - Generate Extent Report

## Key Features

### WebDriver Manager
- **Automatic driver download and setup**
- No manual driver management needed
- Supports Chrome, Firefox, Edge, and other browsers

### Extent Reports
- **Beautiful HTML test reports**
- Screenshots for failed tests
- Test duration tracking
- Pass/Fail/Skip statistics
- System information capture

### Page Object Model (POM)
- **Centralized locator management**
- Reusable methods for common actions
- Easy maintenance and scalability

### Explicit Waits
- Uses WebDriverWait instead of Thread.sleep()
- Waits for elements to be clickable, visible, or disappear
- Reduces flakiness

## Locators Strategy

### HomePage Locators
- Title and subtitle: XPath with text matching
- Input field: CSS Selector for GitHub URL
- Buttons: XPath with contains() for flexible matching
- Feature badges: XPath with specific text

### AnalysisPage Locators
- Results container: XPath with class names
- Section headers: XPath with h2 tags
- Loading spinner: Class-based XPath

### GraphPage Locators
- Graph container: XPath for SVG/Canvas elements
- Nodes: XPath for circle or group elements
- Zoom controls: XPath with button text

## Common Issues & Troubleshooting

### Issue: WebDriver Manager fails to download driver
**Solution**: Check internet connection, clear Maven cache
```bash
mvn clean install -U
```

### Issue: Tests timeout waiting for elements
**Solution**: Increase wait time in WaitUtils.java or check if elements exist in DOM

### Issue: Screenshots not captured
**Solution**: Ensure `test-output/screenshots/` directory exists and is writable

## Continuous Integration (CI/CD)

### GitHub Actions Example
```yaml
- name: Run Selenium Tests
  run: mvn test -Dbrowser=chrome
  
- name: Upload Reports
  if: always()
  uses: actions/upload-artifact@v2
  with:
    name: Test Reports
    path: test-output/
```

## Dependencies

All dependencies are managed by Maven:
- Selenium WebDriver 4.15.0
- TestNG 7.8.1
- WebDriver Manager 5.8.1
- Extent Reports 5.1.1
- Commons IO 2.13.0
- Log4j 1.2.17

## Best Practices Implemented

✅ **Page Object Model** - Locators centralized in page classes
✅ **Explicit Waits** - Prevents timing issues
✅ **ThreadLocal for WebDriver** - Thread-safe driver management
✅ **Parametrized Tests** - Data-driven testing
✅ **Screenshot on Failure** - Easier debugging
✅ **Extent Reports** - Professional test reporting
✅ **Base Test Class** - DRY principle
✅ **Listener Integration** - Automated report generation

## Contributing

To add new tests:
1. Create page object in `pages/` directory
2. Add test class in `tests/` directory
3. Follow naming conventions and documentation
4. Ensure all locators are properly validated

## Author
Soumyashri Singha

## License
MIT License
