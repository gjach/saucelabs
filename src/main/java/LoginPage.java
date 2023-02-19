import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

    @FindBy(id = "user-name")
    public WebElement inputUsername;
    @FindBy(id = "password")
    public WebElement inputPassword;

    public LoginPage (WebDriver driver){
        super(driver);
git
    }

//    By usernameLocator = By.id("user-name");
//    By passwordLocator = By.id("password");
    By loginBtn = By.id("login-button");
    public ListingPage login(WebDriver driver, String username, String password){
        inputUsername.sendKeys(username);
        inputPassword.sendKeys(password);
//        driver.findElement(usernameLocator).sendKeys(username);
//        driver.findElement(passwordLocator).sendKeys(password);
        driver.findElement(loginBtn).click();
        return new ListingPage(driver);
    }

}
