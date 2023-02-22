import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage{

    @FindBy (css = ".shopping_cart_link")
    WebElement cartBtn;
    @FindBy (id = "continue-shopping")
    WebElement continueBtn;

    @FindBy (css = "[id*=remove")
    WebElement removeBtn;

    @FindBy (id = "checkout")
    WebElement checkoutBtn;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public CartPage checkCart(WebDriver driver){
        cartBtn.click();
    return new CartPage(driver);
    }

    public void continueShopping(){
        continueBtn.click();
    }
    public void removeProduct(){
        removeBtn.click();
    }
    public CheckOutPage goToCheckOut(){
        checkoutBtn.click();
        return new CheckOutPage(driver);
    }
}
