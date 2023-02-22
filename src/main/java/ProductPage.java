import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage{

    @FindBy (css = "[id*=add-to-cart]")
    WebElement addToCartBtn;
    @FindBy (id = "back-to-products")
    WebElement backBtn;
    @FindBy (css = "[id*='remove-']")
    WebElement removeBtn;

    @FindBy (css = ".inventory_details_img")
    WebElement image;


    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public ProductPage addToCart(WebDriver driver){
        addToCartBtn.click();
        return new ProductPage(driver);
    }

    public ProductPage back (WebDriver driver){
        backBtn.click();
    return new ProductPage(driver);
    }

    public void removeProduct(WebDriver driver){
        removeBtn.click();
    }
}
