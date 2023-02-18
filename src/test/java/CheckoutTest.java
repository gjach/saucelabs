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
                .login(driver, username, password)
                .addToCart(driver, productId);
        cartPage
                .checkCart(driver)
                .goToCheckOut(driver);
        checkOutPage
                .goToCheckoutOverview(driver);
        List<WebElement> errorMessages = driver.findElements(By.cssSelector("[class*='input_error']"));
        Assert.assertEquals(errorMessages.size(), 3);
        utils = new Utils(driver);
        utils.cleanCart(driver);
    }

    @Test
    public void instertOnlyName() {
        loginPage
                .login(driver, username, password)
                .addToCart(driver, productId);
        cartPage
                .checkCart(driver)
                .goToCheckOut(driver);
        checkOutPage
                .enterData(driver, name, "", "")
                .goToCheckoutOverview(driver);
        WebElement errorMessageFrame = driver.findElement(By.cssSelector("[class*=error-message-container]"));
        String expectedErrorMessage = "Error: Last Name is required";
        Assert.assertEquals(errorMessageFrame.getText(), expectedErrorMessage);
        utils = new Utils(driver);
        utils.cleanCart(driver);
    }

    @Test
    public void instertOnlySurname() {
        loginPage
                .login(driver, username, password)
                .addToCart(driver, productId);
        cartPage
                .checkCart(driver)
                .goToCheckOut(driver);
        checkOutPage
                .enterData(driver, "", surname, "")
                .goToCheckoutOverview(driver);
        WebElement errorMessageFrame = driver.findElement(By.cssSelector("[class*=error-message-container]"));
        String expectedErrorMessage = "Error: First Name is required";
        Assert.assertEquals(errorMessageFrame.getText(), expectedErrorMessage);
        utils = new Utils(driver);
        utils.cleanCart(driver);
    }

    @Test
    public void instertOnlyPostalCode() {
        loginPage
                .login(driver, username, password)
                .addToCart(driver, productId);
        cartPage
                .checkCart(driver)
                .goToCheckOut(driver);
        checkOutPage
                .enterData(driver, "", "", postalCode)
                .goToCheckoutOverview(driver);
        WebElement errorMessageFrame = driver.findElement(By.cssSelector("[class*=error-message-container]"));
        String expectedErrorMessage = "Error: First Name is required";
        Assert.assertEquals(errorMessageFrame.getText(), expectedErrorMessage);
        utils = new Utils(driver);
        utils.cleanCart(driver);
    }

    @Test
    public void instertAllWithoutPostalCode() {
        loginPage
                .login(driver, username, password)
                .addToCart(driver, productId);
        cartPage
                .checkCart(driver)
                .goToCheckOut(driver);
        checkOutPage
                .enterData(driver, name, surname, "")
                .goToCheckoutOverview(driver);
        WebElement errorMessageFrame = driver.findElement(By.cssSelector("[class*=error-message-container]"));
        String expectedErrorMessage = "Error: Postal Code is required";
        Assert.assertEquals(errorMessageFrame.getText(), expectedErrorMessage);
        utils = new Utils(driver);
        utils.cleanCart(driver);
    }

    @Test
    public void instertAllWithoutSurname() {
        loginPage
                .login(driver, username, password)
                .addToCart(driver, productId);
        cartPage
                .checkCart(driver)
                .goToCheckOut(driver);
        checkOutPage
                .enterData(driver, name, "", postalCode)
                .goToCheckoutOverview(driver);
        WebElement errorMessageFrame = driver.findElement(By.cssSelector("[class*=error-message-container]"));
        String expectedErrorMessage = "Error: Last Name is required";
        Assert.assertEquals(errorMessageFrame.getText(), expectedErrorMessage);
        utils = new Utils(driver);
        utils.cleanCart(driver);
    }

    @Test
    public void instertAllWithoutName() {
        loginPage
                .login(driver, username, password)
                .addToCart(driver, productId);
        cartPage
                .checkCart(driver)
                .goToCheckOut(driver);
        checkOutPage
                .enterData(driver, "", surname, postalCode)
                .goToCheckoutOverview(driver);
        WebElement errorMessageFrame = driver.findElement(By.cssSelector("[class*=error-message-container]"));
        String expectedErrorMessage = "Error: First Name is required";
        Assert.assertEquals(errorMessageFrame.getText(), expectedErrorMessage);
        utils = new Utils(driver);
        utils.cleanCart(driver);
    }

    @Test
    public void instertAllData() {
        loginPage
                .login(driver, username, password)
                .addToCart(driver, productId);
        cartPage
                .checkCart(driver)
                .goToCheckOut(driver);
        checkOutPage
                .enterData(driver, name, surname, postalCode)
                .goToCheckoutOverview(driver);
        String expectedUrl = "https://www.saucedemo.com/checkout-step-two.html";
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "URL is not correct");

    }
}

