import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckOutPage extends BasePage{

    public CheckOutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutOverviewPage goToCheckoutOverview(WebDriver driver){
        driver.findElement(By.id("continue")).click();
        return new CheckoutOverviewPage(driver);
    }

    public CheckOutPage enterData(WebDriver driver, String name, String surname, String postalCode) {
        driver.findElement(By.id("first-name")).sendKeys(name);
        driver.findElement(By.id("last-name")).sendKeys(surname);
        driver.findElement(By.id("postal-code")).sendKeys(postalCode);
    return new CheckOutPage(driver);
    }
}
