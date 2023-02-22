import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.swing.*;
import java.util.List;

public class CheckoutTest extends BaseTest {

    Utils utils;


    @Test
    public void allEmptyFields() {
        loginPage
                .login(username, password)
                .addToCart(productId);
        cartPage
                .checkCart(driver)
                .goToCheckOut();
        checkOutPage
                .goToCheckoutOverview();
        List<WebElement> errorMessages = driver.findElements(By.cssSelector("[class*='input_error']"));
        Assert.assertEquals(errorMessages.size(), 3);
        utils = new Utils(driver);
        utils.cleanCart(driver);
    }

    @Test
    public void instertOnlyName() {
        loginPage
                .login(username, password)
                .addToCart(productId);
        cartPage
                .checkCart(driver)
                .goToCheckOut();
        checkOutPage
                .enterData(name, "", "")
                .goToCheckoutOverview();
        WebElement errorMessageFrame = driver.findElement(By.cssSelector("[class*=error-message-container]"));
        String expectedErrorMessage = "Error: Last Name is required";
        Assert.assertEquals(errorMessageFrame.getText(), expectedErrorMessage);
        utils = new Utils(driver);
        utils.cleanCart(driver);
    }

    @Test
    public void instertOnlySurname() {
        loginPage
                .login(username, password)
                .addToCart(productId);
        cartPage
                .checkCart(driver)
                .goToCheckOut();
        checkOutPage
                .enterData("", surname, "")
                .goToCheckoutOverview();
        WebElement errorMessageFrame = driver.findElement(By.cssSelector("[class*=error-message-container]"));
        String expectedErrorMessage = "Error: First Name is required";
        Assert.assertEquals(errorMessageFrame.getText(), expectedErrorMessage);
        utils = new Utils(driver);
        utils.cleanCart(driver);
    }

    @Test
    public void instertOnlyPostalCode() {
        loginPage
                .login(username, password)
                .addToCart(productId);
        cartPage
                .checkCart(driver)
                .goToCheckOut();
        checkOutPage
                .enterData("", "", postalCode)
                .goToCheckoutOverview();
        WebElement errorMessageFrame = driver.findElement(By.cssSelector("[class*=error-message-container]"));
        String expectedErrorMessage = "Error: First Name is required";
        Assert.assertEquals(errorMessageFrame.getText(), expectedErrorMessage);
        utils = new Utils(driver);
        utils.cleanCart(driver);
    }

    @Test
    public void instertAllWithoutPostalCode() {
        loginPage
                .login(username, password)
                .addToCart(productId);
        cartPage
                .checkCart(driver)
                .goToCheckOut();
        checkOutPage
                .enterData(name, surname, "")
                .goToCheckoutOverview();
        WebElement errorMessageFrame = driver.findElement(By.cssSelector("[class*=error-message-container]"));
        String expectedErrorMessage = "Error: Postal Code is required";
        Assert.assertEquals(errorMessageFrame.getText(), expectedErrorMessage);
        utils = new Utils(driver);
        utils.cleanCart(driver);
    }

    @Test
    public void instertAllWithoutSurname() {
        loginPage
                .login(username, password)
                .addToCart(productId);
        cartPage
                .checkCart(driver)
                .goToCheckOut();
        checkOutPage
                .enterData(name, "", postalCode)
                .goToCheckoutOverview();
        WebElement errorMessageFrame = driver.findElement(By.cssSelector("[class*=error-message-container]"));
        String expectedErrorMessage = "Error: Last Name is required";
        Assert.assertEquals(errorMessageFrame.getText(), expectedErrorMessage);
        utils = new Utils(driver);
        utils.cleanCart(driver);
    }

    @Test
    public void instertAllWithoutName() {
        loginPage
                .login(username, password)
                .addToCart(productId);
        cartPage
                .checkCart(driver)
                .goToCheckOut();
        checkOutPage
                .enterData("", surname, postalCode)
                .goToCheckoutOverview();
        WebElement errorMessageFrame = driver.findElement(By.cssSelector("[class*=error-message-container]"));
        String expectedErrorMessage = "Error: First Name is required";
        Assert.assertEquals(errorMessageFrame.getText(), expectedErrorMessage);
        utils = new Utils(driver);
        utils.cleanCart(driver);
    }

    @Test
    public void instertAllData() {
        loginPage
                .login(username, password)
                .addToCart(productId);
        cartPage
                .checkCart(driver)
                .goToCheckOut();
        checkOutPage
                .enterData(name, surname, postalCode)
                .goToCheckoutOverview();
        String expectedUrl = "https://www.saucedemo.com/checkout-step-two.html";
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "URL is not correct");

    }
}

