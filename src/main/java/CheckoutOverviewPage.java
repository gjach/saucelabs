import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutOverviewPage extends BasePage {

    public CheckoutOverviewPage(WebDriver driver){
        super(driver);
    }

    public void cancel() {
        driver.findElement(By.id("cancel")).click();
    }

    public void finish() {
        driver.findElement(By.id("finish")).click();
    }
}
