import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.util.Strings;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.Collection;
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
        Collections.sort(beforeProductsFromAToZList, Collections.reverseOrder());
        Assert.assertEquals(beforeProductsFromAToZList, afterProductsFromAToZList);
    }
}