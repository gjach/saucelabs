import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ProductTest extends BaseTest {

    String username = "standard_user";
    String password = "secret_sauce";

    @Test
    public void areElementsVisible() {
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage();
        loginPage.login(driver, username, password);
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
        loginPage = new LoginPage();
        loginPage.login(driver, username, password);
        driver.findElement(By.cssSelector("[id*='_img_link']")).click();
        productPage = new ProductPage(driver);
        productPage.addToCart(driver);
        String itemsInCart = driver.findElement(By.cssSelector(".shopping_cart_link")).getText();
        Assert.assertEquals(itemsInCart, "1");
        utils = new Utils();
        utils.cleanCart(driver);
    }
    @Test
    public void isRemoveButtonVisible() {
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage();
        loginPage.login(driver, username, password);
        driver.findElement(By.cssSelector("[id*='_img_link']")).click();
        driver.findElement(By.cssSelector("[id*='add-to-cart-']")).click();
        String removeButton = driver.findElement(By.id("remove-sauce-labs-backpack")).getText();
        Assert.assertEquals(removeButton, "REMOVE");
        utils = new Utils();
        utils.cleanCart(driver);
    }
    @Test
    public void backToListingButton() {
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage();
        loginPage.login(driver, username, password);
        listingPage = new ListingPage(driver);
        listingPage.moveToProductPageOfFirstProduct(driver);
        productPage = new ProductPage(driver);
        productPage.back(driver);
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
    }
    @Test
    public void removeProdAddedOnListing() {
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage();
        loginPage.login(driver, username, password);
        driver.findElement(By.cssSelector("[id='add-to-cart-sauce-labs-backpack']")).click();
        driver.findElement(By.xpath(".//*[@class='inventory_item_name' and text()='Sauce Labs Backpack']")).click();
        productPage = new ProductPage(driver);
        productPage.removeProduct(driver);
        String itemsInCart = driver.findElement(By.cssSelector(".shopping_cart_link")).getText();
        Assert.assertEquals(itemsInCart, "");
    }
}
