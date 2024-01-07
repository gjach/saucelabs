import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ListingPage extends BasePage {


    @FindBy (css = "[class='product_sort_container']")
    WebElement sortBtn;
    @FindBy (css = "[id*='remove']")
    WebElement removeBtn;
    @FindBy (css ="[id*='_img_link']" )
    WebElement firstProductFromList;
    @FindBy(css = "[id*='add-to-cart']")
    WebElement addToCartRandomProd;


    public ListingPage(WebDriver driver) {
        super(driver);
    }

    public void sortBy (By sortLocator){
        sortBtn.click();
            driver.findElement(sortLocator).click();
        }

        public ListingPage addToCart (String productId){
            driver.findElement(By.cssSelector("[id*='" + productId + "']")).click();
            return new ListingPage(driver);
        }

        public void removeFromCartFirstProduct (){
            removeBtn.click();
        }

        public ProductPage moveToProductPageOfFirstProduct (){
            firstProductFromList.click();
            return new ProductPage(driver);
        }

    public ListingPage addToCartRandomProducts() {
        addToCartRandomProd.click();
        return new ListingPage(driver);
    }

    public ProductPage navigateToProduct(String productName) {
        driver.findElement(By.xpath(".//*[@class='inventory_item_name ' and text()='"+ productName +"']")).click();
        return new ProductPage(driver);
    }
}
