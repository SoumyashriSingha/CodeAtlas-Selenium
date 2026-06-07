# Quick Start Guide - CodeAtlas UI Automation

## ⚡ 5-Minute Setup

### Step 1: Prerequisites
```bash
# Check Java version (must be 11+)
java -version

# Check Maven version (must be 3.6+)
mvn --version
```

### Step 2: Download Dependencies
```bash
cd CodeAtlas-Selenium
mvn clean install
```

### Step 3: Run Tests
```bash
# Run all tests
mvn test

# View Extent Report
# Open: test-output/extent-report.html in browser
```

---

## 🎯 Common Commands

### Run Specific Test Suite
```bash
# Smoke tests only (basic functionality)
mvn test -Dtest=SmokeTests

# Repository analysis tests
mvn test -Dtest=RepositoryAnalysisTests

# Graph/Architecture tests
mvn test -Dtest=GraphTests
```

### Run with Specific Browser
```bash
mvn test -Dbrowser=firefox
mvn test -Dbrowser=edge
mvn test -Dbrowser=chrome      # default
```

### Run Single Test Method
```bash
mvn test -Dtest=SmokeTests#testHomePageLoad
mvn test -Dtest=RepositoryAnalysisTests#testAnalyzeFastAPIRepository
```

### Run in Parallel (Faster Execution)
```bash
mvn test -DparallelMode=methods -DthreadCount=4
```

---

## 📊 Test Results

### Extent Report Location
```
test-output/extent-report.html
```

Open this file in a browser to see:
- ✅ Pass/Fail statistics
- 📸 Failed test screenshots
- ⏱️ Execution timeline
- 🖥️ System information

### Console Output
Tests will show progress:
```
Test Started: testHomePageLoad
Test Passed: testHomePageLoad
Test Started: testAnalyzeFastAPIRepository
Test Passed: testAnalyzeFastAPIRepository
...
```

---

## 🧪 What Each Test Suite Does

### 🚀 Smoke Tests (6 tests, ~30 seconds)
Verify basic application functionality:
- Home page loads ✓
- UI elements visible ✓
- Input field works ✓
- Buttons accessible ✓

**Run**: `mvn test -Dtest=SmokeTests`

### 🔍 Repository Analysis Tests (9 tests, 2-3 minutes)
Test analyzing real GitHub repositories:
- FastAPI (Python)
- Next.js (JavaScript)
- Express (Node.js)

Verifies:
- Stack detection ✓
- Architecture results ✓
- Module mapping ✓
- Improvement suggestions ✓

**Run**: `mvn test -Dtest=RepositoryAnalysisTests`

### 📈 Graph/Architecture Tests (10 tests, 1.5-2.5 minutes)
Test visualization and interactions:
- Graph renders ✓
- Nodes/edges present ✓
- Zoom controls work ✓
- Graph dragging works ✓

**Run**: `mvn test -Dtest=GraphTests`

---

## 🐛 Troubleshooting

### Issue: "WebDriver Manager error"
```bash
# Clear Maven cache and retry
mvn clean install -U
mvn test
```

### Issue: Tests timeout
```bash
# Check if CodeAtlas app is accessible
# Visit: https://codeatlas-frontend-khaki.vercel.app/
# Increase wait time if needed (edit WaitUtils.java)
```

### Issue: Screenshots not saving
```bash
# Ensure directories exist
mkdir -p test-output/screenshots
```

### Issue: Port/Connection errors
```bash
# Check internet connection
# Ensure GitHub API is accessible
```

---

## 📁 Project Structure

```
CodeAtlas-Selenium/
├── README.md                    # Full documentation
├── TEST_CASES.md               # Detailed test cases
├── pom.xml                     # Dependencies & build config
├── testng.xml                  # TestNG configuration
│
├── src/test/java/
│   ├── base/                   # Base classes for tests
│   ├── pages/                  # Page Object Models
│   ├── tests/                  # Test classes
│   ├── utils/                  # Utility functions
│   └── listeners/              # Test listeners & reports
│
├── src/test/resources/
│   ├── config.properties       # Configuration
│   └── testdata.json          # Test data
│
└── test-output/               # Test results
    ├── extent-report.html     # Visual test report
    └── screenshots/           # Failed test screenshots
```

---

## 📚 Page Object Models

### HomePage.java
```java
HomePage homePage = new HomePage(driver);
homePage.open();
homePage.enterGitHubUrl("https://github.com/tiangolo/fastapi");
homePage.clickAnalyzeButton();
```

### AnalysisPage.java
```java
AnalysisPage analysisPage = new AnalysisPage(driver);
analysisPage.waitForAnalysisComplete();
boolean stackDetectionVisible = analysisPage.isStackDetectionResultsVisible();
boolean architectureVisible = analysisPage.isArchitectureResultsVisible();
```

### GraphPage.java
```java
GraphPage graphPage = new GraphPage(driver);
boolean graphLoaded = graphPage.isGraphPageLoaded();
int nodeCount = graphPage.getNodeCount();
graphPage.clickZoomIn();
```

---

## 🔗 Test Repositories

Tests use these real GitHub repositories:

1. **FastAPI**
   - URL: https://github.com/tiangolo/fastapi
   - Language: Python
   - Framework: FastAPI (Web framework)

2. **Next.js**
   - URL: https://github.com/vercel/next.js
   - Language: JavaScript/TypeScript
   - Framework: React-based framework

3. **Express**
   - URL: https://github.com/expressjs/express
   - Language: JavaScript
   - Framework: Node.js web framework

---

## ⚙️ Configuration

### Browser Settings
Edit `src/test/resources/config.properties`:
```properties
browser=chrome              # chrome, firefox, edge
base.url=https://codeatlas-frontend-khaki.vercel.app/
explicit.wait=10            # seconds
```

### Parallel Execution
Edit `testng.xml`:
```xml
<suite parallel="methods" thread-count="2">
```

---

## 📊 Expected Results

Run all tests:
```bash
mvn test
```

Expected output:
```
Total Tests: 25
Passed: 25
Failed: 0
Skipped: 0
Duration: 5-6 minutes

Report: test-output/extent-report.html
```

---

## 🚀 Advanced Usage

### Debug Mode
```bash
# Run with verbose output
mvn test -X
```

### Generate JavaDoc
```bash
mvn javadoc:javadoc
# Output: target/site/apidocs/
```

### Run Specific Test Group
```bash
# Edit testng.xml with @Test(groups="smoke")
# Then run:
mvn test -Dgroups=smoke
```

---

## 📝 Adding New Tests

1. **Create page object** in `src/test/java/pages/NewPage.java`
   - Add locators
   - Add page methods

2. **Create test class** in `src/test/java/tests/NewTests.java`
   - Extend BaseTest
   - Add @Test methods

3. **Add to testng.xml**
   - Add `<class name="tests.NewTests"/>`

4. **Run tests**
   - `mvn test`

---

## 🎓 Learning Resources

- **Selenium Documentation**: https://www.selenium.dev/
- **TestNG Documentation**: https://testng.org/
- **Extent Reports**: https://www.extentreports.com/
- **WebDriver Manager**: https://github.com/bonigarcia/webdrivermanager

---

## 💡 Best Practices

✅ Always use Page Object Model
✅ Use explicit waits instead of Thread.sleep()
✅ Capture screenshots on test failure
✅ Use data-driven testing for multiple scenarios
✅ Keep tests independent
✅ Use descriptive test names
✅ Add assertions for every verification
✅ Clean up resources in tearDown()

---

## 🆘 Need Help?

1. Check `README.md` for detailed documentation
2. Review `TEST_CASES.md` for test details
3. Check test output in `test-output/extent-report.html`
4. Review console logs for error messages
5. Check screenshot artifacts in `test-output/screenshots/`

---

**Ready to automate!** 🎯

```bash
cd CodeAtlas-Selenium
mvn clean test
```

Then open: `test-output/extent-report.html` 📊
