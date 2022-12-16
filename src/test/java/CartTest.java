import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class CartTest extends BaseTest {

    @Test
    public void checkProductsInCart(){
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        driver.findElement(By.cssSelector("[id*='add-to-cart-']")).click();
        driver.findElement(By.cssSelector(".shopping_cart_link")).click();
        WebElement title = driver.findElement(By.cssSelector(".inventory_item_name"));
        WebElement description = driver.findElement(By.cssSelector(".inventory_item_desc"));
        WebElement price = driver.findElement(By.cssSelector(".inventory_item_price"));
        WebElement quantity = driver.findElement(By.cssSelector(".cart_quantity"));
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(title.isDisplayed());
        softAssert.assertTrue(description.isDisplayed());
        softAssert.assertTrue(price.isDisplayed());
        softAssert.assertTrue(quantity.isDisplayed());
        softAssert.assertAll();
    }
    @Test
    public void continueShoppingButton() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        driver.findElement(By.cssSelector("[id*='add-to-cart-']")).click();
        driver.findElement(By.cssSelector(".shopping_cart_link")).click();
        driver.findElement(By.id("continue-shopping")).click();
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
    }
    @Test
    public void removeProductInCart() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        driver.findElement(By.cssSelector("[id*='add-to-cart-']")).click();
        driver.findElement(By.cssSelector(".shopping_cart_link")).click();
        List<WebElement> removeButtons = driver.findElements(By.cssSelector("[id*='remove-']"));
        for (int i=1;i<=removeButtons.size(); i++){
            driver.findElement(By.cssSelector("[id*='remove-']")).click();
        }
        String itemsInCart = driver.findElement(By.cssSelector(".shopping_cart_link")).getText();
        Assert.assertEquals(itemsInCart, "");
    }
    @Test
    public void checkoutButton() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        driver.findElement(By.cssSelector("[id*='add-to-cart-']")).click();
        driver.findElement(By.cssSelector(".shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();
        String expectedUrl = "https://www.saucedemo.com/checkout-step-one.html";
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
    }

}
