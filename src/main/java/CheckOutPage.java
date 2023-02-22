import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckOutPage extends BasePage{

    public CheckOutPage(WebDriver driver) {
        super(driver);
    }

    @FindBy (id = "continue")
    WebElement continueBtn;
    @FindBy (id = "first-name")
    WebElement firstNameInput;
    @FindBy (id = "last-name")
    WebElement lastNameInput;
    @FindBy (id = "postal-code")
    WebElement postalCodeInput;


    public CheckoutOverviewPage goToCheckoutOverview(){
        continueBtn.click();
        return new CheckoutOverviewPage(driver);
    }

    public CheckOutPage enterData(String name, String surname, String postalCode) {
        firstNameInput.sendKeys(name);
        lastNameInput.sendKeys(surname);
        postalCodeInput.sendKeys(postalCode);
    return new CheckOutPage(driver);
    }
}
