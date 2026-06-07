package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestContext;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void setDriver(WebDriver webDriver) {
        driver.set(webDriver);
    }

    public static ExtentReports getExtent() {
        if (extent == null) {
            try {
                ExtentSparkReporter spark = new ExtentSparkReporter("test-output/extent-report.html");
                spark.config().setReportName("CodeAtlas UI Automation Report");
                spark.config().setDocumentTitle("CodeAtlas Test Execution Report");

                extent = new ExtentReports();
                extent.attachReporter(spark);
                extent.setSystemInfo("Application", "CodeAtlas");
                extent.setSystemInfo("Browser", "Chrome");
                extent.setSystemInfo("OS", System.getProperty("os.name"));
                extent.setSystemInfo("Java Version", System.getProperty("java.version"));
            } catch (Exception e) {
                System.err.println("Failed to initialize Extent Reports: " + e.getMessage());
            }
        }
        return extent;
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String description = result.getMethod().getDescription();
        
        try {
            ExtentTest extentTest = getExtent().createTest(testName, description);
            test.set(extentTest);
            System.out.println("[TEST START] " + testName);
        } catch (Exception e) {
            System.err.println("[ERROR] Failed to start test: " + e.getMessage());
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        try {
            if (test.get() != null) {
                test.get().log(Status.PASS, testName + " PASSED");
            }
            System.out.println("[TEST PASS] " + testName);
        } catch (Exception e) {
            System.err.println("[ERROR] Failed to log test success: " + e.getMessage());
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        try {
            if (test.get() != null) {
                test.get().log(Status.FAIL, testName + " FAILED");
                
                Throwable throwable = result.getThrowable();
                if (throwable != null) {
                    test.get().log(Status.FAIL, "Error: " + throwable.getMessage());
                }
            }
            System.out.println("[TEST FAIL] " + testName);
        } catch (Exception e) {
            System.err.println("[ERROR] Failed to log test failure: " + e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        try {
            if (test.get() != null) {
                test.get().log(Status.SKIP, testName + " SKIPPED");
            }
            System.out.println("[TEST SKIP] " + testName);
        } catch (Exception e) {
            System.err.println("[ERROR] Failed to log test skip: " + e.getMessage());
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        try {
            if (extent != null) {
                extent.flush();
                System.out.println("[REPORT] Extent report generated: test-output/extent-report.html");
            }
        } catch (Exception e) {
            System.err.println("[ERROR] Failed to flush reports: " + e.getMessage());
        }
    }
}
