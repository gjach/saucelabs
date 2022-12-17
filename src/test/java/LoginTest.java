import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class LoginTest extends BaseTest {
    @Test
    public void loginTest() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        String expectedURL = "https://www.saucedemo.com/inventory.html";
        Assert.assertEquals(driver.getCurrentUrl(), expectedURL, "There was a problem with login");
    }

    @Test
    public void loginTestWithoutData() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("login-button")).click();
        List<WebElement> errorMessages = driver.findElements(By.xpath(".//input[@class='input_error form_input error']"));
        Assert.assertTrue(errorMessages.size() == 2, "There are not two error messages");
    }

    @Test
    public void loginTestWithoutLogin() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        String errorUsernameMessage = driver.findElement(By.cssSelector("[class='error-message-container error']")).getText();
        String expectedUsernameErrorMessage = "Epic sadface: Username is required";
        Assert.assertEquals(errorUsernameMessage, expectedUsernameErrorMessage, "Error message is not correct");
    }

    @Test
    public void loginTestWithoutPassword() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("login-button")).click();
        String errorPasswordMessage = driver.findElement(By.cssSelector("[class='error-message-container error']")).getText();
        String expectedPasswordErrorMessage = "Epic sadface: Password is required";
        Assert.assertEquals(errorPasswordMessage, expectedPasswordErrorMessage, "Error message is not correct");
    }

    @Test
    public void loginTestLockedUser() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("locked_out_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        String lockedAccountMessage = driver.findElement(By.cssSelector("[class='error-message-container error']")).getText();
        String expectedLockedErrorMessage = "Epic sadface: Sorry, this user has been locked out.";
        Assert.assertEquals(lockedAccountMessage, expectedLockedErrorMessage, "Error message is not correct");
    }
}
