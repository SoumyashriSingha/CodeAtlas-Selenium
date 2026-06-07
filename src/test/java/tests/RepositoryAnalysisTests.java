package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import pages.HomePage;
import pages.AnalysisPage;

public class RepositoryAnalysisTests extends BaseTest {

    @DataProvider(name = "repositoriesData")
    public Object[][] repositoriesData() {
        return new Object[][] {
            { "https://github.com/tiangolo/fastapi", "fastapi" },
            { "https://github.com/SoumyashriSingha/taskManagerFS", "java" },
            { "https://github.com/ChaitanyaNirf/redis-client-tracking", "typescript" }
        };
    }

    @Test(description = "Verify user can analyze FastAPI repository")
    public void testAnalyzeFastAPIRepository() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.open();
        Assert.assertTrue(homePage.isHomePageLoaded(), "Home page should be loaded");

        homePage.enterGitHubUrl("https://github.com/tiangolo/fastapi");
        homePage.clickAnalyzeButton();

        Thread.sleep(3000); // Wait for navigation

        AnalysisPage analysisPage = new AnalysisPage(driver);
        analysisPage.waitForAnalysisComplete();
        Assert.assertTrue(analysisPage.isAnalysisPageLoaded(), "Analysis page should load");
        Assert.assertTrue(analysisPage.areResultsDisplayed(), "Analysis results should be displayed");
    }

    @Test(description = "Verify stack detection results are visible")
    public void testStackDetectionResults() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.enterGitHubUrl("https://github.com/SoumyashriSingha/taskManagerFS");
        homePage.clickAnalyzeButton();

        Thread.sleep(3000);

        AnalysisPage analysisPage = new AnalysisPage(driver);
        analysisPage.waitForAnalysisComplete();
        Assert.assertTrue(analysisPage.isStackDetectionResultsVisible(), 
            "Stack detection results should be visible");
    }

    @Test(description = "Verify architecture analysis results are displayed")
    public void testArchitectureAnalysisResults() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.enterGitHubUrl("https://github.com/ChaitanyaNirf/redis-client-tracking");
        homePage.clickAnalyzeButton();

        Thread.sleep(3000);

        AnalysisPage analysisPage = new AnalysisPage(driver);
        analysisPage.waitForAnalysisComplete();
        Assert.assertTrue(analysisPage.isArchitectureResultsVisible(), 
            "Architecture results should be visible");
    }

    @Test(description = "Verify module mapping results are available")
    public void testModuleMappingResults() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.enterGitHubUrl("https://github.com/ChaitanyaNirf/redis-client-tracking");
        homePage.clickAnalyzeButton();

        Thread.sleep(3000);

        AnalysisPage analysisPage = new AnalysisPage(driver);
        analysisPage.waitForAnalysisComplete();
        Assert.assertTrue(analysisPage.isModuleMappingResultsVisible(), 
            "Module mapping results should be visible");
    }

    @Test(description = "Verify improvement suggestions are displayed")
    public void testImprovementSuggestions() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.enterGitHubUrl("https://github.com/ChaitanyaNirf/redis-client-tracking");
        homePage.clickAnalyzeButton();

        Thread.sleep(3000);

        AnalysisPage analysisPage = new AnalysisPage(driver);
        analysisPage.waitForAnalysisComplete();
        Assert.assertTrue(analysisPage.isImprovementsResultsVisible(), 
            "Improvement suggestions should be visible");
    }

    @Test(description = "Verify repository title is correctly displayed")
    public void testRepositoryTitleDisplay() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.enterGitHubUrl("https://github.com/ChaitanyaNirf/redis-client-tracking");
        homePage.clickAnalyzeButton();

        Thread.sleep(3000);

        AnalysisPage analysisPage = new AnalysisPage(driver);
        analysisPage.waitForAnalysisComplete();
        String repoTitle = analysisPage.getRepositoryTitle();
        Assert.assertFalse(repoTitle.isEmpty(), "Repository title should be displayed");
    }

    @Test(dataProvider = "repositoriesData", description = "Verify multiple repositories can be analyzed")
    public void testMultipleRepositoriesAnalysis(String repoUrl, String repoName) throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.enterGitHubUrl(repoUrl);
        homePage.clickAnalyzeButton();

        Thread.sleep(3000);

        AnalysisPage analysisPage = new AnalysisPage(driver);
        analysisPage.waitForAnalysisComplete();
        Assert.assertTrue(analysisPage.isAnalysisPageLoaded(), 
            "Analysis should load for " + repoName);
    }

    @Test(description = "Verify analyze button using suggested repository (FastAPI)")
    public void testSuggestedRepositoryFastAPI() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.clickFastAPIButton();

        Thread.sleep(3000);

        AnalysisPage analysisPage = new AnalysisPage(driver);
        analysisPage.waitForAnalysisComplete();
        Assert.assertTrue(analysisPage.isAnalysisPageLoaded(), "Analysis should load for FastAPI");
    }

    @Test(description = "Verify analyze button using suggested repository (Next.js)")
    public void testSuggestedRepositoryNextJS() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.clickNextJSButton();

        Thread.sleep(3000);

        AnalysisPage analysisPage = new AnalysisPage(driver);
        analysisPage.waitForAnalysisComplete();
        Assert.assertTrue(analysisPage.isAnalysisPageLoaded(), "Analysis should load for Next.js");
    }

    @Test(description = "Verify analyze button using suggested repository (Express)")
    public void testSuggestedRepositoryExpress() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.clickExpressButton();

        Thread.sleep(3000);

        AnalysisPage analysisPage = new AnalysisPage(driver);
        analysisPage.waitForAnalysisComplete();
        Assert.assertTrue(analysisPage.isAnalysisPageLoaded(), "Analysis should load for Express");
    }
}
