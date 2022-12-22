import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Utils extends BasePage{

    public void cleanCart(WebDriver driver){
        driver.findElement(By.cssSelector(".shopping_cart_link")).click();
        List<WebElement> removeButtons = driver.findElements(By.cssSelector("[id*='remove-']"));
        for (int i=1;i<=removeButtons.size(); i++){
            driver.findElement(By.cssSelector("[id*='remove-']")).click();
        }
    }
    public void takeScreenshot() throws IOException {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File("C:\\screenshots\\test.jpg"));
        }
}
