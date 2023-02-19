import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class LoginTest extends BaseTest {


    @Test
    public void loginTest() {
        loginPage
                .login(username, password);
        String expectedURL = "https://www.saucedemo.com/inventory.html";
        Assert.assertEquals(driver.getCurrentUrl(), expectedURL, "There was a problem with login");
    }

    @Test
    public void loginTestWithoutData() {
        loginPage
                .login("", "");
        List<WebElement> errorMessages = driver.findElements(By.xpath(".//input[@class='input_error form_input error']"));
        Assert.assertTrue(errorMessages.size() == 2, "There are not two error messages");
    }

    @Test
    public void loginTestWithoutLogin() {
        loginPage
                .login("", password);
        String errorUsernameMessage = driver.findElement(By.cssSelector("[class='error-message-container error']")).getText();
        String expectedUsernameErrorMessage = "Epic sadface: Username is required";
        Assert.assertEquals(errorUsernameMessage, expectedUsernameErrorMessage, "Error message is not correct");
    }

    @Test
    public void loginTestWithoutPassword() {
        loginPage
                .login(username, "");
        String errorPasswordMessage = driver.findElement(By.cssSelector("[class='error-message-container error']")).getText();
        String expectedPasswordErrorMessage = "Epic sadface: Password is required";
        Assert.assertEquals(errorPasswordMessage, expectedPasswordErrorMessage, "Error message is not correct");
    }

    @Test
    public void loginTestLockedUser() {
        loginPage
                .login("locked_out_user", password);
        String lockedAccountMessage = driver.findElement(By.cssSelector("[class='error-message-container error']")).getText();
        String expectedLockedErrorMessage = "Epic sadface: Sorry, this user has been locked out.";
        Assert.assertEquals(lockedAccountMessage, expectedLockedErrorMessage, "Error message is not correct");
    }

}
