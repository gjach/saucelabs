import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{

    By usernameLocator = By.id("user-name");
    By passwordLocator = By.id("password");
    By loginBtn = By.id("login-button");
    public void login(WebDriver driver, String username, String password){
        driver.findElement(usernameLocator).sendKeys(username);
        driver.findElement(passwordLocator).sendKeys(password);
        driver.findElement(loginBtn).click();
    }

}
