import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ListingPage extends BasePage {

    By sortButtonLocator = By.cssSelector("[class='product_sort_container']");


    public void ListingPage(WebDriver driver) {
        this.driver = driver;
    }


    public void sortBy(WebDriver driver, By sortLocator) {
        By sortAtoZLocator = By.cssSelector("option[value='az']");
        driver.findElement(sortButtonLocator).click();
        driver.findElement(sortLocator).click();
    }
}