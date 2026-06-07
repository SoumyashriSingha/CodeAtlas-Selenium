package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

public class SmokeTests extends BaseTest {

    @Test(description = "Verify CodeAtlas Home Page loads successfully")
    public void testHomePageLoad() {
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.waitForPageLoad();
        Assert.assertTrue(homePage.isHomePageLoaded(), "Home page should be loaded");
    }

    @Test(description = "Verify CodeAtlas title and subtitle are visible")
    public void testHeroElementsVisible() {
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.waitForPageLoad();
        Assert.assertTrue(homePage.isHeroTitleVisible(), "Hero title should be visible");
    }

    @Test(description = "Verify GitHub URL input field is accessible")
    public void testGitHubUrlInputAccessible() {
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.waitForPageLoad();
        homePage.enterGitHubUrl("https://github.com/SoumyashriSingha/taskManagerFS");
        String inputValue = homePage.getGitHubUrlInputValue();
        Assert.assertTrue(inputValue.contains("SoumyashriSingha/taskManagerFS"), "URL should be entered in input field");
    }

    @Test(description = "Verify page elements load without errors")
    public void testPageElementsLoad() {
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.waitForPageLoad();
        Assert.assertTrue(homePage.isHomePageLoaded(), "Page should load successfully");
    }

    @Test(description = "Verify home page displays without errors")
    public void testHomePageDisplay() {
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.waitForPageLoad();
        Assert.assertTrue(homePage.isHomePageLoaded(), "Home page should display correctly");
    }

    @Test(description = "Verify input field accepts GitHub URLs")
    public void testInputFieldAcceptsGitHubURL() {
        HomePage homePage = new HomePage(driver);
        homePage.open();
        homePage.waitForPageLoad();
        String testUrl = "https://github.com/SoumyashriSingha/taskManagerFS";
        homePage.enterGitHubUrl(testUrl);
        String savedUrl = homePage.getGitHubUrlInputValue();
        Assert.assertTrue(savedUrl.contains("taskManagerFS"), "Input field should accept GitHub URLs");
    }
}
