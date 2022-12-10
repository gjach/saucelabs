import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListingTest extends BaseTest {

    By sortButtonLocator = By.cssSelector("[class='product_sort_container']");

    @Test
    public void sortingAToZ() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        List<WebElement> beforeProductsFromAToZ = driver.findElements(By.cssSelector(".inventory_item_name"));
        List<String> beforeProductsFromAToZList = new ArrayList<>();
        for(WebElement p : beforeProductsFromAToZ){
            beforeProductsFromAToZList.add(p.getText());
        }
                WebElement sortButton = driver.findElement(sortButtonLocator);
        sortButton.click();
        WebElement sortByAZ = driver.findElement(By.cssSelector("option[value='az']"));
        sortByAZ.click();
        List<WebElement> afterProductsFromAToZ = driver.findElements(By.cssSelector(".inventory_item_name"));
        List<String> afterProductsFromAToZList = new ArrayList<>();
        for(WebElement p : afterProductsFromAToZ){
            afterProductsFromAToZList.add(p.getText());
        }
        Collections.sort(beforeProductsFromAToZList);
        Assert.assertEquals(beforeProductsFromAToZList, afterProductsFromAToZList);
    }
    @Test
    public void sortingZToA() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        List<WebElement> beforeProductsFromZToA = driver.findElements(By.cssSelector(".inventory_item_name"));
        List<String> beforeProductsFromZToAList = new ArrayList<>();
        for(WebElement p : beforeProductsFromZToA){
            beforeProductsFromZToAList.add(p.getText());
        }
        WebElement sortButton = driver.findElement(sortButtonLocator);
        sortButton.click();
        WebElement sortByZA = driver.findElement(By.cssSelector("option[value='za']"));
        sortByZA.click();
        List<WebElement> afterProductsFromZToA = driver.findElements(By.cssSelector(".inventory_item_name"));
        List<String> afterProductsFromZToAList = new ArrayList<>();
        for(WebElement p : afterProductsFromZToA){
            afterProductsFromZToAList.add(p.getText());
        }
        Collections.sort(beforeProductsFromZToAList, Collections.reverseOrder());
        Assert.assertEquals(beforeProductsFromZToAList, afterProductsFromZToAList);
    }
    @Test
    public void sortingByPriceLowToHigh() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        List<WebElement> beforeSortingLowToHigh = driver.findElements(By.cssSelector(".inventory_item_price"));
        List<Double> beforeSortingLowToHighList = new ArrayList<>();
        for(WebElement p : beforeSortingLowToHigh){
            beforeSortingLowToHighList.add(Double.valueOf(p.getText().replace("$", "")));
        }
        WebElement sortButton = driver.findElement(sortButtonLocator);
        sortButton.click();
        WebElement sortLowToHigh = driver.findElement(By.cssSelector("option[value='lohi']"));
        sortLowToHigh.click();

        List<WebElement> afterSortingLowToHigh = driver.findElements(By.cssSelector(".inventory_item_price"));
        List<Double> afterSortingLowToHighList = new ArrayList<>();
        for(WebElement p : afterSortingLowToHigh){
            afterSortingLowToHighList.add(Double.valueOf(p.getText().replace("$", "")));
        }

        Collections.sort(beforeSortingLowToHighList);
        Assert.assertEquals(beforeSortingLowToHighList, afterSortingLowToHighList);
    }
    @Test
    public void sortingByPriceHighToLow() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        List<WebElement> beforeSortingHighToLow = driver.findElements(By.cssSelector(".inventory_item_price"));
        List<Double> beforeSortingHighToLowList = new ArrayList<>();
        for(WebElement p : beforeSortingHighToLow){
            beforeSortingHighToLowList.add(Double.valueOf(p.getText().replace("$", "")));
        }
        WebElement sortButton = driver.findElement(sortButtonLocator);
        sortButton.click();
        WebElement sortHighToLow = driver.findElement(By.cssSelector("option[value='hilo']"));
        sortHighToLow.click();

        List<WebElement> afterSortingHighToLow = driver.findElements(By.cssSelector(".inventory_item_price"));
        List<Double> afterSortingHighToLowList = new ArrayList<>();
        for(WebElement p : afterSortingHighToLow){
            afterSortingHighToLowList.add(Double.valueOf(p.getText().replace("$", "")));
        }

        Collections.sort(beforeSortingHighToLowList, Collections.reverseOrder());
        Assert.assertEquals(beforeSortingHighToLowList, afterSortingHighToLowList);
    }
}