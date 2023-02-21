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
        loginPage
                .login(username, password)
                .moveToProductPageOfFirstProduct();
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
        loginPage
                .login(username, password)
                .moveToProductPageOfFirstProduct()
                .addToCart(driver);
        String itemsInCart = driver.findElement(By.cssSelector(".shopping_cart_link")).getText();
        Assert.assertEquals(itemsInCart, "1");
        utils.cleanCart(driver);
    }
    @Test
    public void isRemoveButtonVisible() {
        loginPage
                .login(username, password)
                .moveToProductPageOfFirstProduct()
                .addToCart(driver);
        String removeButton = driver.findElement(By.id("remove-sauce-labs-backpack")).getText();
        Assert.assertEquals(removeButton, "REMOVE");
        utils.cleanCart(driver);
    }
    @Test
    public void backToListingButton() {
        loginPage
                .login(username, password)
                .moveToProductPageOfFirstProduct()
                .back(driver);
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
    }
    @Test
    public void removeProdAddedOnListing() {
        loginPage
                .login(username, password)
                .addToCart(productId)
                .navigateToProduct(productName)
                .removeProduct(driver);
        String itemsInCart = driver.findElement(By.cssSelector(".shopping_cart_link")).getText();
        Assert.assertEquals(itemsInCart, "");
    }
}
