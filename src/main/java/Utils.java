import org.openqa.selenium.*;
import java.util.List;

public class Utils extends BasePage{

    public Utils(WebDriver driver) {
        this.driver = driver;
    }

    public void cleanCart(WebDriver driver){
        driver.findElement(By.cssSelector(".shopping_cart_link")).click();
        List<WebElement> removeButtons = driver.findElements(By.cssSelector("[id*='remove-']"));
        for (int i=1;i<=removeButtons.size(); i++){
            driver.findElement(By.cssSelector("[id*='remove-']")).click();
        }
    }
}
