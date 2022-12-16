import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Random;

public class CheckoutOverviewTest extends BaseTest {

    int randomNum = ThreadLocalRandom.current().nextInt(1, 6 + 1);


    @Test
    public void checkQuantityOfProducts() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        for(int i=0; i < randomNum; i++ ) {
            driver.findElement(By.cssSelector("[id*='add-to-cart-")).click();
        }
        driver.findElement(By.cssSelector(".shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("John");
        driver.findElement(By.id("last-name")).sendKeys("Wick");
        driver.findElement(By.id("postal-code")).sendKeys("800552");
        driver.findElement(By.id("continue")).click();
        List<WebElement> productsInCart = driver.findElements(By.cssSelector(".inventory_item_name"));
        Assert.assertEquals(randomNum, productsInCart.size());
        utils = new Utils();
        utils.cleanCart(driver);
    }
    @Test
    public void totalPrice() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        for (int i = 0; i < randomNum; i++) {
            driver.findElement(By.cssSelector("[id*='add-to-cart-")).click();
        }
        driver.findElement(By.cssSelector(".shopping_cart_link")).click();
        List<WebElement> pricesLocators = driver.findElements(By.cssSelector(".inventory_item_price"));
        List<Double> prices = new ArrayList<>();
        for (WebElement p : pricesLocators) {
            prices.add(Double.valueOf(p.getText().replace("$", "")));
        }
        double sum = 0;
        for(int i = 0; i < prices.size(); i++)
            sum += prices.get(i);
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("John");
        driver.findElement(By.id("last-name")).sendKeys("Wick");
        driver.findElement(By.id("postal-code")).sendKeys("800552");
        driver.findElement(By.id("continue")).click();
        String itemTotalInformation = driver.findElement(By.cssSelector(".summary_subtotal_label")).getText();
        double totalPrice = Double.valueOf(itemTotalInformation.replace("Item total: $", ""));
        Assert.assertEquals(totalPrice, sum);
    }
}
