# CodeAtlas UI Automation - Test Cases Summary

## Application Details
- **App URL**: https://codeatlas-frontend-khaki.vercel.app/
- **Purpose**: AI-powered GitHub repository analysis tool
- **Key Features**: Stack Detection, Architecture Diagrams, Module Mapping, Improvement Insights

## Test Repositories Used in Tests
1. **FastAPI** - https://github.com/tiangolo/fastapi (Python Web Framework)
2. **Next.js** - https://github.com/vercel/next.js (JavaScript Framework)
3. **Express** - https://github.com/expressjs/express (Node.js Framework)

---

## Test Suite 1: SMOKE TESTS (6 Tests)
**Purpose**: Verify basic functionality and UI elements load correctly

### Test Cases

| # | Test Name | Description | Expected Result |
|---|-----------|-------------|-----------------|
| 1 | testHomePageLoad | Verify CodeAtlas Home Page loads successfully | Home page loads with all elements |
| 2 | testHeroElementsVisible | Verify CodeAtlas title and subtitle are visible | Title "CodeAtlas" and subtitle "Understand any GitHub repository in minutes" visible |
| 3 | testFeatureBadgesVisible | Verify feature badges (Stack Detection, Architecture) are displayed | All 5 feature badges visible on page |
| 4 | testSuggestedRepositoriesVisible | Verify suggested repositories are visible | FastAPI, Next.js, Express buttons visible |
| 5 | testGitHubUrlInputAccessible | Verify GitHub URL input field is accessible and accepts input | User can enter GitHub URL successfully |
| 6 | testAnalyzeButtonVisible | Verify Analyze button is visible and accessible | Analyze button visible on page |

---

## Test Suite 2: REPOSITORY ANALYSIS TESTS (9 Tests)
**Purpose**: Verify repository analysis functionality with different GitHub repositories

### Test Cases

| # | Test Name | Repository | Expected Result |
|---|-----------|-----------|-----------------|
| 1 | testAnalyzeFastAPIRepository | tiangolo/fastapi | Analysis completes and results page loads |
| 2 | testStackDetectionResults | expressjs/express | Stack Detection section visible (e.g., Python, FastAPI, Uvicorn) |
| 3 | testArchitectureAnalysisResults | vercel/next.js | Architecture Diagrams section visible |
| 4 | testModuleMappingResults | tiangolo/fastapi | Module Mapping section visible with modules listed |
| 5 | testImprovementSuggestions | expressjs/express | Improvement Insights section visible with AI suggestions |
| 6 | testRepositoryTitleDisplay | tiangolo/fastapi | Repository title displayed in results |
| 7 | testMultipleRepositoriesAnalysis (Data-Driven) | FastAPI, Next.js, Express | All repositories analyze successfully |
| 8 | testSuggestedRepositoryFastAPI | tiangolo/fastapi | Analysis loads when FastAPI suggested button clicked |
| 9 | testSuggestedRepositoryExpress | expressjs/express | Analysis loads when Express suggested button clicked |

**Bonus Test**: testSuggestedRepositoryNextJS - Next.js analysis from suggested button

---

## Test Suite 3: GRAPH/ARCHITECTURE TESTS (10 Tests)
**Purpose**: Verify architecture visualization and graph interactions

### Test Cases

| # | Test Name | Focus Area | Expected Result |
|---|-----------|-----------|-----------------|
| 1 | testArchitectureGraphLoadFastAPI | FastAPI Graph Load | Architecture graph renders for FastAPI |
| 2 | testGraphDisplayed | Graph Rendering | Architecture graph visible on results page |
| 3 | testArchitectureDiagramVisible | Diagram Visibility | SVG/Canvas diagram elements visible |
| 4 | testGraphNodesPresent | Node Detection | Graph contains nodes (>= 0 nodes found) |
| 5 | testGraphEdgesPresent | Edge Detection | Graph contains edges/connections (>= 0 edges found) |
| 6 | testZoomControlsAccessible | Zoom UI | Zoom in/out buttons visible |
| 7 | testZoomInFunctionality | Zoom Interaction | Zoom in button clickable and functional |
| 8 | testZoomOutFunctionality | Zoom Interaction | Zoom out button clickable and functional |
| 9 | testGraphInteraction | Graph UX | Graph interactive, draggable without errors |
| 10 | testNodeDetailView | Detail Panel | Clicking nodes doesn't cause errors |

---

## Test Execution Flow

### User Journey 1: Basic Repository Analysis
```
1. User visits https://codeatlas-frontend-khaki.vercel.app/
2. HomePage loads with title, subtitle, and feature badges
3. User enters GitHub URL: https://github.com/tiangolo/fastapi
4. User clicks "Analyze" button
5. Page shows loading indicator
6. AnalysisPage loads with results
7. Results display: Stack Detection, Architecture, Modules, Improvements
8. User can scroll through results and view graphs
```

### User Journey 2: Quick Analysis (Suggested Repository)
```
1. User visits home page
2. User clicks "FastAPI" suggested repository button
3. Analysis auto-starts with URL: https://github.com/tiangolo/fastapi
4. Results load and display immediately
5. User views architecture diagrams
6. User can zoom and interact with graph
```

### User Journey 3: Graph Exploration
```
1. Analysis results display with architecture graph
2. User sees nodes (modules) and edges (dependencies)
3. User clicks zoom in to see more detail
4. User drags graph to pan around
5. User clicks on nodes to see module details
6. User zooms out to see full architecture
```

---

## Test Data

### Repository URLs
```json
{
  "repositories": [
    {
      "url": "https://github.com/tiangolo/fastapi",
      "name": "FastAPI",
      "language": "Python"
    },
    {
      "url": "https://github.com/vercel/next.js",
      "name": "Next.js",
      "language": "JavaScript"
    },
    {
      "url": "https://github.com/expressjs/express",
      "name": "Express",
      "language": "JavaScript"
    }
  ]
}
```

---

## Key Locators

### HomePage
- **Title**: `//h1//span[contains(text(), 'Code')]`
- **Subtitle**: `//p[contains(text(), 'Understand any GitHub repository')]`
- **URL Input**: `input[placeholder*='github.com']`
- **Analyze Button**: `//button[contains(text(), 'Analyze')]`
- **FastAPI Button**: `//button[contains(text(), 'fastapi')]`
- **Next.js Button**: `//button[contains(text(), 'next.js')]`
- **Express Button**: `//button[contains(text(), 'express')]`

### AnalysisPage
- **Results Container**: `//div[contains(@class, 'results') or contains(@class, 'analysis')]`
- **Stack Detection**: `//h2[contains(text(), 'Stack')] | //div[contains(text(), 'Stack')]`
- **Architecture Section**: `//h2[contains(text(), 'Architecture')] | //div[contains(text(), 'Architecture')]`
- **Module Mapping**: `//h2[contains(text(), 'Modules')] | //div[contains(text(), 'Module')]`
- **Improvements**: `//h2[contains(text(), 'Improvement')] | //div[contains(text(), 'Improvement')]`

### GraphPage
- **Graph Container**: `//canvas | //svg[@class='graph'] | //div[contains(@class, 'graph')]`
- **Nodes**: `//circle | //g[@class='node']`
- **Edges**: `//line | //path[@class='edge']`
- **Zoom In Button**: `//button[contains(., '+')]`
- **Zoom Out Button**: `//button[contains(., '-')]`

---

## Test Execution Commands

### Run All Tests
```bash
mvn test
```

### Run Specific Test Suite
```bash
mvn test -Dtest=SmokeTests                      # Run smoke tests only
mvn test -Dtest=RepositoryAnalysisTests         # Run analysis tests
mvn test -Dtest=GraphTests                      # Run graph tests
```

### Run with Different Browser
```bash
mvn test -Dbrowser=chrome                       # Chrome (default)
mvn test -Dbrowser=firefox                      # Firefox
mvn test -Dbrowser=edge                         # Edge
```

### Run in Parallel (2 threads)
```bash
mvn test -DparallelMode=methods -DthreadCount=2
```

---

## Reports Generated

### Extent Report
- **Location**: `test-output/extent-report.html`
- **Contents**: Test results, screenshots, system info, execution timeline
- **Format**: Interactive HTML dashboard

### Screenshot Artifacts
- **Location**: `test-output/screenshots/`
- **Captured**: Failed test screenshots with timestamp
- **Naming**: `{TestName}_{YYYY-MM-DD_HH-mm-ss}.png`

---

## Expected Test Results

| Test Suite | Total Tests | Expected Pass | Duration |
|-----------|-----------|--------------|----------|
| Smoke Tests | 6 | 6 | ~30 seconds |
| Repository Analysis | 9 | 9 | ~120-180 seconds (depends on API) |
| Graph Tests | 10 | 10 | ~90-150 seconds (depends on rendering) |
| **Total** | **25** | **25** | **~5-6 minutes** |

---

## Notes

- **API Dependency**: Tests depend on GitHub REST API and CodeAtlas backend
- **Timeouts**: Analysis can take 10-15 seconds per repository
- **Flakiness Risk**: Graph rendering may vary based on browser/performance
- **Success Criteria**: All elements render without JS errors

---

## Future Test Enhancements

- [ ] Test error handling for invalid GitHub URLs
- [ ] Test rate limiting/API failures
- [ ] Add performance testing (load time metrics)
- [ ] Add accessibility testing (a11y)
- [ ] Add mobile responsive tests
- [ ] Add cross-browser compatibility tests
- [ ] Add detailed comparison of different repository types
- [ ] Add visual regression testing for graphs
