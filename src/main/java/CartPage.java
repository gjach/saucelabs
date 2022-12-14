import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage{

    public CartPage checkCart(WebDriver driver){
        driver.findElement(By.cssSelector(".shopping_cart_link")).click();
    return new CartPage();
    }

    public void continueShopping(WebDriver driver){
        driver.findElement(By.id("continue-shopping")).click();
    }
    public void removeProduct(WebDriver driver){
        driver.findElement(By.cssSelector(".shopping_cart_link")).click();
    }
}
