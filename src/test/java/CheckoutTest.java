import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class CheckoutTest extends BaseTest{

  Utils utils;


    @Test
    public void allEmptyFields (){
        loginPage.login(driver, username, password);
        driver.findElement(By.cssSelector("[id*='add-to-cart-']")).click();
        driver.findElement(By.cssSelector(".shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("continue")).click();
        List<WebElement> errorMessages = driver.findElements(By.cssSelector("[class*='input_error']"));
        Assert.assertEquals(errorMessages.size(), 3);
        utils = new Utils(driver);
        utils.cleanCart(driver);
    }
    @Test
    public void instertOnlyName (){
        loginPage.login(driver, username, password);
        driver.findElement(By.cssSelector("[id*='add-to-cart-']")).click();
        driver.findElement(By.cssSelector(".shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("John");
        driver.findElement(By.id("continue")).click();
        WebElement errorMessageFrame = driver.findElement(By.cssSelector("[class*=error-message-container]"));
        String expectedErrorMessage = "Error: Last Name is required";
        Assert.assertEquals(errorMessageFrame.getText(), expectedErrorMessage);
        utils = new Utils(driver);
        utils.cleanCart(driver);
    }
    @Test
    public void instertOnlySurname (){
        loginPage.login(driver, username, password);
        driver.findElement(By.cssSelector("[id*='add-to-cart-']")).click();
        driver.findElement(By.cssSelector(".shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("last-name")).sendKeys("Wick");
        driver.findElement(By.id("continue")).click();
        WebElement errorMessageFrame = driver.findElement(By.cssSelector("[class*=error-message-container]"));
        String expectedErrorMessage = "Error: First Name is required";
        Assert.assertEquals(errorMessageFrame.getText(), expectedErrorMessage);
        utils = new Utils(driver);
        utils.cleanCart(driver);
    }
    @Test
    public void instertOnlyPostalCode (){
        loginPage.login(driver, username, password);
        driver.findElement(By.cssSelector("[id*='add-to-cart-']")).click();
        driver.findElement(By.cssSelector(".shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("postal-code")).sendKeys("800552");
        driver.findElement(By.id("continue")).click();
        WebElement errorMessageFrame = driver.findElement(By.cssSelector("[class*=error-message-container]"));
        String expectedErrorMessage = "Error: First Name is required";
        Assert.assertEquals(errorMessageFrame.getText(), expectedErrorMessage);
        utils = new Utils(driver);
        utils.cleanCart(driver);
    }
    @Test
    public void instertAllWithoutPostalCode (){
        loginPage.login(driver, username, password);
        driver.findElement(By.cssSelector("[id*='add-to-cart-']")).click();
        driver.findElement(By.cssSelector(".shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("John");
        driver.findElement(By.id("last-name")).sendKeys("Wick");
        driver.findElement(By.id("continue")).click();
        WebElement errorMessageFrame = driver.findElement(By.cssSelector("[class*=error-message-container]"));
        String expectedErrorMessage = "Error: Postal Code is required";
        Assert.assertEquals(errorMessageFrame.getText(), expectedErrorMessage);
        utils = new Utils(driver);
        utils.cleanCart(driver);
    }
    @Test
    public void instertAllWithoutSurname (){
        loginPage.login(driver, username, password);
        driver.findElement(By.cssSelector("[id*='add-to-cart-']")).click();
        driver.findElement(By.cssSelector(".shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("John");
        driver.findElement(By.id("postal-code")).sendKeys("800552");
        driver.findElement(By.id("continue")).click();
        WebElement errorMessageFrame = driver.findElement(By.cssSelector("[class*=error-message-container]"));
        String expectedErrorMessage = "Error: Last Name is required";
        Assert.assertEquals(errorMessageFrame.getText(), expectedErrorMessage);
        utils = new Utils(driver);
        utils.cleanCart(driver);
    }
    @Test
    public void instertAllWithoutName (){
        loginPage.login(driver, username, password);
        driver.findElement(By.cssSelector("[id*='add-to-cart-']")).click();
        driver.findElement(By.cssSelector(".shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("last-name")).sendKeys("Wick");
        driver.findElement(By.id("postal-code")).sendKeys("800552");
        driver.findElement(By.id("continue")).click();
        WebElement errorMessageFrame = driver.findElement(By.cssSelector("[class*=error-message-container]"));
        String expectedErrorMessage = "Error: First Name is required";
        Assert.assertEquals(errorMessageFrame.getText(), expectedErrorMessage);
        utils = new Utils(driver);
        utils.cleanCart(driver);
    }
    }

