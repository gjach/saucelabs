import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.List;


public class CartTest extends BaseTest {


    @Test
    public void checkProductsInCart() {
        loginPage = new LoginPage(driver);
        loginPage
                .login(username, password)
                .addToCart(productId);
        cartPage = new CartPage(driver);
        cartPage
                .checkCart(driver);
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
        loginPage = new LoginPage(driver);
        loginPage
                .login(username, password)
                .addToCart(productId);
        cartPage = new CartPage(driver);
        cartPage
                .checkCart(driver)
                .continueShopping();
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
    }

    @Test
    public void removeProductInCart() {
        loginPage = new LoginPage(driver);
        loginPage
                .login(username, password)
                .addToCart(productId);
        cartPage = new CartPage(driver);
        cartPage
                .checkCart(driver);
        List<WebElement> removeButtons = driver.findElements(By.cssSelector("[id*='remove-']"));
        for (int i = 1; i <= removeButtons.size(); i++) {
            cartPage.
                    removeProduct();
        }
        String itemsInCart = driver.findElement(By.cssSelector(".shopping_cart_link")).getText();
        Assert.assertEquals(itemsInCart, "");
    }

    @Test
    public void checkoutButton() {
        loginPage = new LoginPage(driver);
        loginPage
                .login(username, password)
                .addToCart(productId);
        cartPage = new CartPage(driver);
        cartPage
                .checkCart(driver)
                .goToCheckOut();
        String expectedUrl = "https://www.saucedemo.com/checkout-step-one.html";
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);


    }




}




