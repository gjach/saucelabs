import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends BasePage{


    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public ProductPage addToCart(WebDriver driver){
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        return new ProductPage(driver);
    }
    public ProductPage back (WebDriver driver){
        driver.findElement(By.id("back-to-products")).click();
    return new ProductPage(driver);
    }

    public void removeProduct(WebDriver driver){
        driver.findElement(By.cssSelector("[id*='remove-']")).click();
    }
}
