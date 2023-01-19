import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.lang.reflect.Method;
import java.time.Duration;
import com.aventstack.extentreports.ExtentTest;


public class BaseTest {

    WebDriver driver;
    WebDriverWait wait;
    Utils utils;
    LoginPage loginPage;
    ListingPage listingPage;
    CartPage cartPage;
    ProductPage productPage;
    static ExtentReports extent;
    ExtentHtmlReporter htmlReporter;
    ExtentTest logger;

    @BeforeSuite
    public void reportSetup() {
        htmlReporter = new ExtentHtmlReporter("extent.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @BeforeMethod
    public void setup(Method m) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        logger = extent.createTest(m.getName());
    }


    @AfterMethod

    public void getResult(ITestResult result) {
        if(result.getStatus() == ITestResult.FAILURE) {
            logger.log(Status.FAIL, result.getThrowable());
        }
        else if(result.getStatus() == ITestResult.SUCCESS) {
            logger.log(Status.PASS, result.getTestName());
        }
        else {
            logger.log(Status.SKIP, result.getTestName());
        }
        extent.flush();
        driver.quit();
    }

}


