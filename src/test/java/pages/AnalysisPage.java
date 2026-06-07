package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AnalysisPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators for analysis results
    private By resultsContainer = By.xpath("//div[contains(@class, 'results') or contains(@class, 'analysis')]");
    private By stackDetectionSection = By.xpath("//h2[contains(text(), 'Stack')] | //div[contains(text(), 'Stack')]");
    private By architectureSection = By.xpath("//h2[contains(text(), 'Architecture')] | //div[contains(text(), 'Architecture')]");
    private By moduleMappingSection = By.xpath("//h2[contains(text(), 'Modules')] | //div[contains(text(), 'Module')]");
    private By improvementsSection = By.xpath("//h2[contains(text(), 'Improvement')] | //div[contains(text(), 'Improvement')]");
    private By loadingSpinner = By.xpath("//div[@class='animate-spin']");
    private By backButton = By.xpath("//button[contains(@class, 'back')] | //a[contains(@class, 'back')]");
    private By repoTitle = By.xpath("//h1 | //span[@class='font-bold' or @class='font-extrabold']");
    private By languageBadges = By.xpath("//span[contains(@class, 'badge') or contains(@class, 'tag')]");

    public AnalysisPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public boolean isAnalysisPageLoaded() {
        try {
            // Wait for results to load (allows up to 15 seconds)
            return wait.until(ExpectedConditions.presenceOfElementLocated(resultsContainer)) != null;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean areResultsDisplayed() {
        try {
            return driver.findElement(resultsContainer).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isStackDetectionResultsVisible() {
        try {
            return driver.findElement(stackDetectionSection).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isArchitectureResultsVisible() {
        try {
            return driver.findElement(architectureSection).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isModuleMappingResultsVisible() {
        try {
            return driver.findElement(moduleMappingSection).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isImprovementsResultsVisible() {
        try {
            return driver.findElement(improvementsSection).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getRepositoryTitle() {
        try {
            return driver.findElement(repoTitle).getText();
        } catch (Exception e) {
            return "";
        }
    }

    public int getLanguageBadgeCount() {
        try {
            return driver.findElements(languageBadges).size();
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean isLoadingSpinnerVisible() {
        try {
            return driver.findElement(loadingSpinner).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void waitForAnalysisComplete() {
        try {
            // Wait for spinner to disappear
            wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingSpinner));
            // Wait for results to appear
            wait.until(ExpectedConditions.visibilityOfElementLocated(resultsContainer));
        } catch (Exception e) {
            // Handle timeout
        }
    }

    public void scrollToStackDetection() {
        try {
            WebElement element = driver.findElement(stackDetectionSection);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        } catch (Exception e) {
            // Handle scroll error
        }
    }

    public void scrollToArchitecture() {
        try {
            WebElement element = driver.findElement(architectureSection);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        } catch (Exception e) {
            // Handle scroll error
        }
    }
}
