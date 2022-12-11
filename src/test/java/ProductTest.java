import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ProductTest extends BaseTest {

    @Test
    public void areElementsVisible() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        driver.findElement(By.cssSelector("[id*='_img_link']")).click();
        WebElement image = driver.findElement(By.cssSelector(".inventory_details_img"));
        WebElement title = driver.findElement(By.cssSelector("[class*='inventory_details_name']"));
        WebElement description = driver.findElement(By.cssSelector("[class*='inventory_details_desc']"));
        WebElement price = driver.findElement(By.cssSelector(".inventory_details_price"));
        WebElement addToCartButton = driver.findElement(By.cssSelector("[id*='add-to-cart-']"));

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(image.isDisplayed());
        softAssert.assertTrue(title.isDisplayed());
        softAssert.assertTrue(description.isDisplayed());
        softAssert.assertTrue(price.isDisplayed());
        softAssert.assertTrue(addToCartButton.isDisplayed());
        softAssert.assertAll();
    }

    @Test
    public void addToCart() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        driver.findElement(By.cssSelector("[id*='_img_link']")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        String itemsInCart = driver.findElement(By.cssSelector(".shopping_cart_link")).getText();
        Assert.assertEquals(itemsInCart, "1");
    }
    @Test
    public void isRemoveButtonVisible() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        driver.findElement(By.cssSelector("[id*='_img_link']")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        String removeButton = driver.findElement(By.id("remove-sauce-labs-backpack")).getText();
        Assert.assertEquals(removeButton, "REMOVE");
    }
    @Test
    public void backToListingButton() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        driver.findElement(By.cssSelector("[id*='_img_link']")).click();
        driver.findElement(By.id("back-to-products")).click();
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
    }
    @Test
    public void removeProdAddedOnListing() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.cssSelector("[id*='_img_link']")).click();
        driver.findElement(By.id("remove-sauce-labs-backpack")).click();
        String itemsInCart = driver.findElement(By.cssSelector(".shopping_cart_link")).getText();
        Assert.assertEquals(itemsInCart, "");
    }
}
