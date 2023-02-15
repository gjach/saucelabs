import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ListingPage extends BasePage {

    By sortButtonLocator = By.cssSelector("[class='product_sort_container']");

    public ListingPage(WebDriver driver) {
        this.driver = driver;
    }

    public void sortBy (WebDriver driver, By sortLocator){
            driver.findElement(sortButtonLocator).click();
            driver.findElement(sortLocator).click();
        }

        public CartPage addToCartFirstProduct (WebDriver driver){
            driver.findElement(By.cssSelector("[id*='add-to-cart-']")).click();
            return new CartPage(driver);
        }

        public void removeFromCartFirstProduct (WebDriver driver){
            driver.findElement(By.cssSelector("[id*='remove']")).click();
        }

        public ProductPage moveToProductPageOfFirstProduct (WebDriver driver){
            driver.findElement(By.cssSelector("[id*='_img_link']")).click();
            return new ProductPage(driver);
        }
    }
