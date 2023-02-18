import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage extends BasePage{

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public CartPage checkCart(WebDriver driver){
        driver.findElement(By.cssSelector(".shopping_cart_link")).click();
    return new CartPage(driver);
    }

    public void continueShopping(WebDriver driver){
        driver.findElement(By.id("continue-shopping")).click();
    }
    public void removeProduct(WebDriver driver){
        driver.findElement(By.cssSelector("[id*=remove")).click();
    }
    public CheckOutPage goToCheckOut(WebDriver driver){
        driver.findElement(By.id("checkout")).click();
        return new CheckOutPage(driver);
    }
}
