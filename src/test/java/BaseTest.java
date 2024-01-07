import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;

import com.aventstack.extentreports.ExtentTest;


public class BaseTest {

    WebDriver driver;
    WebDriverWait wait;
    LoginPage loginPage;
    ListingPage listingPage;
    CartPage cartPage;
    CheckOutPage checkOutPage;
    Utils utils = new Utils(driver);
    static ExtentReports extent;
    ExtentHtmlReporter htmlReporter;
    ExtentTest logger;
    String username = "standard_user";
    String password = "secret_sauce";
    String productId = "add-to-cart-sauce-labs-backpack";
    String productName = "Sauce Labs Backpack";
    String name = "John";
    String surname = "Wick";
    String postalCode = "800552";

    @BeforeSuite
    public void reportSetup() {
        htmlReporter = new ExtentHtmlReporter("extent.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @BeforeMethod
    public void setup(Method m) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
//        options.addArguments("headless");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");

        logger = extent.createTest(m.getName());
    }


    @AfterMethod

    public void getResult(ITestResult result) throws Exception {
        if (result.getStatus() == ITestResult.FAILURE) {
            logger.log(Status.FAIL, result.getThrowable());
            String screenshot = captureScreenshot(driver);
            logger.fail("Screenshot: ", MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            logger.log(Status.PASS, result.getTestName());
        } else {
            logger.log(Status.SKIP, result.getTestName());
        }
        extent.flush();
        driver.quit();
    }


    public static String captureScreenshot(WebDriver driver){

        TakesScreenshot scrShot = ((TakesScreenshot) driver);
        String screenshot = scrShot.getScreenshotAs(OutputType.BASE64);
//        String methodName = result.getMethod().getMethodName();
//        String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
//        String fileName = "/screenshots/" + methodName + "_" + timeStamp + ".png";
//        String destination = System.getProperty("user.dir") + fileName;
//        File DestFile = new File(destination);
//        FileHandler.copy(SrcFile, DestFile);
//        String pathForJenkins = "http://localhost:8080/job/newtest/ws" + fileName;
        return screenshot;
    }
}


