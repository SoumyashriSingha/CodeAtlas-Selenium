
package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By codeAtlasTitle = By.xpath("//h1//span[contains(text(), 'Code')]");
    private By heroSubtitle = By.xpath("//p[contains(text(), 'Understand any GitHub repository')]");
    private By githubUrlInput = By.cssSelector("input[placeholder*='github.com']");
    private By analyzeButton = By.xpath("//button[contains(text(), 'Analyze')]");
    private By stackDetectionBadge = By.xpath("//span[contains(text(), 'Stack Detection')]");
    private By architectureDiagramBadge = By.xpath("//span[contains(text(), 'Architecture Diagrams')]");
    private By fastAPIButton = By.xpath("//button[contains(text(), 'fastapi')]");
    private By nextJSButton = By.xpath("//button[contains(text(), 'next.js')]");
    private By expressButton = By.xpath("//button[contains(text(), 'express')]");
    private By loadingSpinner = By.xpath("//div[@class='animate-spin']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open() {
        driver.get("https://codeatlas-frontend-khaki.vercel.app/");
    }

    public boolean isHomePageLoaded() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(codeAtlasTitle)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isHeroTitleVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(codeAtlasTitle)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSubtitleVisible() {
        try {
            return driver.findElement(heroSubtitle).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void enterGitHubUrl(String repositoryUrl) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(githubUrlInput));
        element.clear();
        element.sendKeys(repositoryUrl);
    }

    public void clickAnalyzeButton() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(analyzeButton));
        element.click();
    }

    public boolean isSuggestedRepositoriesVisible() {
        try {
            return driver.findElement(fastAPIButton).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickFastAPIButton() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(fastAPIButton));
        element.click();
    }

    public void clickNextJSButton() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(nextJSButton));
        element.click();
    }

    public void clickExpressButton() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(expressButton));
        element.click();
    }

    public boolean isStackDetectionBadgeVisible() {
        try {
            return driver.findElement(stackDetectionBadge).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isArchitectureDiagramBadgeVisible() {
        try {
            return driver.findElement(architectureDiagramBadge).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getGitHubUrlInputValue() {
        return driver.findElement(githubUrlInput).getAttribute("value");
    }

    public boolean isAnalyzeButtonEnabled() {
        return driver.findElement(analyzeButton).isEnabled();
    }

    public void waitForPageLoad() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(codeAtlasTitle));
        } catch (Exception e) {
            // Handle timeout
        }
    }
}
