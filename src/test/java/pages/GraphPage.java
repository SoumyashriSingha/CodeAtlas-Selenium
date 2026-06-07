package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class GraphPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators for graph/visualization page
    private By graphContainer = By.xpath("//canvas | //svg[@class='graph'] | //div[contains(@class, 'graph')]");
    private By architectureDiagram = By.xpath("//svg | //canvas");
    private By nodeElements = By.xpath("//circle | //g[@class='node']");
    private By edgeElements = By.xpath("//line | //path[@class='edge']");
    private By zoomInButton = By.xpath("//button[contains(., '+')]");
    private By zoomOutButton = By.xpath("//button[contains(., '-')]");
    private By resetViewButton = By.xpath("//button[contains(., 'Reset') or contains(., 'Fit')]");
    private By nodeDetail = By.xpath("//div[contains(@class, 'detail') or contains(@class, 'info')]");
    private By loadingSpinner = By.xpath("//div[@class='animate-spin']");

    public GraphPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public boolean isGraphPageLoaded() {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(graphContainer)) != null;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isGraphDisplayed() {
        try {
            return driver.findElement(graphContainer).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isArchitectureDiagramVisible() {
        try {
            return driver.findElement(architectureDiagram).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public int getNodeCount() {
        try {
            return driver.findElements(nodeElements).size();
        } catch (Exception e) {
            return 0;
        }
    }

    public int getEdgeCount() {
        try {
            return driver.findElements(edgeElements).size();
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean isZoomInButtonVisible() {
        try {
            return driver.findElement(zoomInButton).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isZoomOutButtonVisible() {
        try {
            return driver.findElement(zoomOutButton).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickZoomIn() {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(zoomInButton));
            element.click();
        } catch (Exception e) {
            // Handle error
        }
    }

    public void clickZoomOut() {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(zoomOutButton));
            element.click();
        } catch (Exception e) {
            // Handle error
        }
    }

    public void clickResetView() {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(resetViewButton));
            element.click();
        } catch (Exception e) {
            // Handle error
        }
    }

    public void clickOnNode(int nodeIndex) {
        try {
            WebElement node = driver.findElements(nodeElements).get(nodeIndex);
            node.click();
        } catch (Exception e) {
            // Handle error
        }
    }

    public boolean isNodeDetailVisible() {
        try {
            return driver.findElement(nodeDetail).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void dragGraphView(int startX, int startY, int endX, int endY) {
        try {
            WebElement graph = driver.findElement(graphContainer);
            Actions actions = new Actions(driver);
            actions.dragAndDropBy(graph, endX - startX, endY - startY).perform();
        } catch (Exception e) {
            // Handle drag error
        }
    }

    public void waitForGraphLoad() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(graphContainer));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingSpinner));
        } catch (Exception e) {
            // Handle timeout
        }
    }
}
