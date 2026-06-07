package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverFactory {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            String browserType = System.getProperty("browser", "chrome").toLowerCase();
            driver.set(initializeDriver(browserType));
        }
        return driver.get();
    }

    private static WebDriver initializeDriver(String browserType) {
        WebDriver driverInstance = null;

        switch (browserType) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driverInstance = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driverInstance = new EdgeDriver();
                break;
            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                driverInstance = new ChromeDriver();
        }

        return driverInstance;
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
