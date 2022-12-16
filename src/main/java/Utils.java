import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Utils extends BasePage{

    public void cleanCart(WebDriver driver){
        driver.findElement(By.cssSelector(".shopping_cart_link")).click();
        List<WebElement> removeButtons = driver.findElements(By.cssSelector("[id*='remove-']"));
        for (int i=1;i<=removeButtons.size(); i++){
            driver.findElement(By.cssSelector("[id*='remove-']")).click();
        }
    }
}
