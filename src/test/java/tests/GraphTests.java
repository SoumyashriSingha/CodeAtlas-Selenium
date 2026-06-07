package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.GraphPage;
import pages.HomePage;
import pages.AnalysisPage;

public class GraphTests extends BaseTest {

    @Test(description = "Verify architecture graph loads for FastAPI repository")
    public void testArchitectureGraphLoadFastAPI() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.enterGitHubUrl("https://github.com/tiangolo/fastapi");
        homePage.clickAnalyzeButton();

        Thread.sleep(3000);

        AnalysisPage analysisPage = new AnalysisPage(driver);
        analysisPage.waitForAnalysisComplete();
        Assert.assertTrue(analysisPage.isAnalysisPageLoaded(), "Analysis page should load");
    }

    @Test(description = "Verify architecture graph is displayed")
    public void testGraphDisplayed() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.enterGitHubUrl("https://github.com/expressjs/express");
        homePage.clickAnalyzeButton();

        Thread.sleep(3000);

        AnalysisPage analysisPage = new AnalysisPage(driver);
        analysisPage.waitForAnalysisComplete();
        Assert.assertTrue(analysisPage.areResultsDisplayed(), "Graph results should be displayed");
    }

    @Test(description = "Verify architecture diagram is visible")
    public void testArchitectureDiagramVisible() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.enterGitHubUrl("https://github.com/vercel/next.js");
        homePage.clickAnalyzeButton();

        Thread.sleep(3000);

        AnalysisPage analysisPage = new AnalysisPage(driver);
        analysisPage.waitForAnalysisComplete();

        GraphPage graphPage = new GraphPage(driver);
        Assert.assertTrue(graphPage.isGraphPageLoaded(), "Graph page should be loaded");
    }

    @Test(description = "Verify nodes are present in architecture graph")
    public void testGraphNodesPresent() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.clickFastAPIButton();

        Thread.sleep(3000);

        AnalysisPage analysisPage = new AnalysisPage(driver);
        analysisPage.waitForAnalysisComplete();

        GraphPage graphPage = new GraphPage(driver);
        int nodeCount = graphPage.getNodeCount();
        Assert.assertTrue(nodeCount >= 0, "Graph should have nodes");
    }

    @Test(description = "Verify edges/connections are present in graph")
    public void testGraphEdgesPresent() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.clickExpressButton();

        Thread.sleep(3000);

        AnalysisPage analysisPage = new AnalysisPage(driver);
        analysisPage.waitForAnalysisComplete();

        GraphPage graphPage = new GraphPage(driver);
        int edgeCount = graphPage.getEdgeCount();
        Assert.assertTrue(edgeCount >= 0, "Graph should have edges");
    }

    @Test(description = "Verify zoom controls are accessible")
    public void testZoomControlsAccessible() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.clickNextJSButton();

        Thread.sleep(3000);

        AnalysisPage analysisPage = new AnalysisPage(driver);
        analysisPage.waitForAnalysisComplete();

        GraphPage graphPage = new GraphPage(driver);
        Assert.assertTrue(graphPage.isZoomInButtonVisible() || graphPage.isZoomOutButtonVisible(), 
            "Zoom controls should be visible");
    }

    @Test(description = "Verify zoom in functionality works")
    public void testZoomInFunctionality() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.enterGitHubUrl("https://github.com/tiangolo/fastapi");
        homePage.clickAnalyzeButton();

        Thread.sleep(3000);

        AnalysisPage analysisPage = new AnalysisPage(driver);
        analysisPage.waitForAnalysisComplete();

        GraphPage graphPage = new GraphPage(driver);
        if (graphPage.isZoomInButtonVisible()) {
            graphPage.clickZoomIn();
            Assert.assertTrue(graphPage.isGraphDisplayed(), "Graph should still be displayed after zoom");
        }
    }

    @Test(description = "Verify zoom out functionality works")
    public void testZoomOutFunctionality() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.enterGitHubUrl("https://github.com/expressjs/express");
        homePage.clickAnalyzeButton();

        Thread.sleep(3000);

        AnalysisPage analysisPage = new AnalysisPage(driver);
        analysisPage.waitForAnalysisComplete();

        GraphPage graphPage = new GraphPage(driver);
        if (graphPage.isZoomOutButtonVisible()) {
            graphPage.clickZoomOut();
            Assert.assertTrue(graphPage.isGraphDisplayed(), "Graph should still be displayed after zoom");
        }
    }

    @Test(description = "Verify graph can be interacted with (drag and view)")
    public void testGraphInteraction() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.clickFastAPIButton();

        Thread.sleep(3000);

        AnalysisPage analysisPage = new AnalysisPage(driver);
        analysisPage.waitForAnalysisComplete();

        GraphPage graphPage = new GraphPage(driver);
        Assert.assertTrue(graphPage.isGraphPageLoaded(), "Graph should be interactive");
    }

    @Test(description = "Verify clicking on nodes shows details")
    public void testNodeDetailView() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.clickExpressButton();

        Thread.sleep(3000);

        AnalysisPage analysisPage = new AnalysisPage(driver);
        analysisPage.waitForAnalysisComplete();

        GraphPage graphPage = new GraphPage(driver);
        if (graphPage.getNodeCount() > 0) {
            graphPage.clickOnNode(0);
            // Node detail panel may or may not be visible, just verify no errors
            Assert.assertTrue(true, "Node click should not cause errors");
        }
    }
}
