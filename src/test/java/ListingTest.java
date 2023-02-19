import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListingTest extends BaseTest {

    By productTitlesLocator = By.cssSelector(".inventory_item_name");
    By sortAtoZLocator = By.cssSelector("option[value='az']");
    By sortZtoALocator = By.cssSelector("option[value='za']");
    By productPricesLocator = By.cssSelector(".inventory_item_price");
    By sortLowToHighPriceLocator = By.cssSelector("option[value='lohi']");
    By sortHighToLowPriceLocator = By.cssSelector("option[value='hilo']");
    By cartLocator = By.cssSelector(".shopping_cart_link");
    String username = "standard_user";
    String password = "secret_sauce";

    @Test
    public void sortingAToZ() {
        loginPage.login(username, password);
        List<WebElement> beforeProductsFromAToZ = driver.findElements(productTitlesLocator);
        List<String> beforeProductsFromAToZList = new ArrayList<>();
        for (WebElement p : beforeProductsFromAToZ) {
            beforeProductsFromAToZList.add(p.getText());
        }
        listingPage.sortBy(driver, sortAtoZLocator);
        List<WebElement> afterProductsFromAToZ = driver.findElements(productTitlesLocator);
        List<String> afterProductsFromAToZList = new ArrayList<>();
        for (WebElement p : afterProductsFromAToZ) {
            afterProductsFromAToZList.add(p.getText());
        }
        Collections.sort(beforeProductsFromAToZList);
        Assert.assertEquals(beforeProductsFromAToZList, afterProductsFromAToZList);
    }

    @Test
    public void sortingZToA() {
        loginPage.login(username, password);
        List<WebElement> beforeProductsFromZToA = driver.findElements(productTitlesLocator);
        List<String> beforeProductsFromZToAList = new ArrayList<>();
        for (WebElement p : beforeProductsFromZToA) {
            beforeProductsFromZToAList.add(p.getText());
        }
        listingPage.sortBy(driver, sortZtoALocator);
        List<WebElement> afterProductsFromZToA = driver.findElements(productTitlesLocator);
        List<String> afterProductsFromZToAList = new ArrayList<>();
        for (WebElement p : afterProductsFromZToA) {
            afterProductsFromZToAList.add(p.getText());
        }
        Collections.sort(beforeProductsFromZToAList, Collections.reverseOrder());
        Assert.assertEquals(beforeProductsFromZToAList, afterProductsFromZToAList);
    }

    @Test
    public void sortingByPriceLowToHigh() {
        loginPage.login(username, password);
        List<WebElement> beforeSortingLowToHigh = driver.findElements(productPricesLocator);
        List<Double> beforeSortingLowToHighList = new ArrayList<>();
        for (WebElement p : beforeSortingLowToHigh) {
            beforeSortingLowToHighList.add(Double.valueOf(p.getText().replace("$", "")));
        }
        listingPage.sortBy(driver, sortLowToHighPriceLocator);

        List<WebElement> afterSortingLowToHigh = driver.findElements(productPricesLocator);
        List<Double> afterSortingLowToHighList = new ArrayList<>();
        for (WebElement p : afterSortingLowToHigh) {
            afterSortingLowToHighList.add(Double.valueOf(p.getText().replace("$", "")));
        }

        Collections.sort(beforeSortingLowToHighList);
        Assert.assertEquals(beforeSortingLowToHighList, afterSortingLowToHighList);
    }

    @Test
    public void sortingByPriceHighToLow() {
        loginPage.login(username, password);
        List<WebElement> beforeSortingHighToLow = driver.findElements(productPricesLocator);
        List<Double> beforeSortingHighToLowList = new ArrayList<>();
        for (WebElement p : beforeSortingHighToLow) {
            beforeSortingHighToLowList.add(Double.valueOf(p.getText().replace("$", "")));
        }
        listingPage.sortBy(driver, sortHighToLowPriceLocator);

        List<WebElement> afterSortingHighToLow = driver.findElements(productPricesLocator);
        List<Double> afterSortingHighToLowList = new ArrayList<>();
        for (WebElement p : afterSortingHighToLow) {
            afterSortingHighToLowList.add(Double.valueOf(p.getText().replace("$", "")));
        }

        Collections.sort(beforeSortingHighToLowList, Collections.reverseOrder());
        Assert.assertEquals(beforeSortingHighToLowList, afterSortingHighToLowList);
    }

    @Test
    public void addToCartFromListing() {
        loginPage.login(username, password)
                .addToCart(driver, productId);
        String itemsInCart = driver.findElement(cartLocator).getText();
        Assert.assertEquals(itemsInCart, "1");
        utils = new Utils(driver);
        utils.cleanCart(driver);
    }

    @Test
    public void removeFromCartFromListing() {
        loginPage.login(username, password)
                        .addToCart(driver, productId);
        String itemsInCart = driver.findElement(cartLocator).getText();
        Assert.assertEquals(itemsInCart, "1");
        driver.findElement(By.id("remove-sauce-labs-backpack")).click();
        String itemsInCartAfterRemove = driver.findElement(cartLocator).getText();
        Assert.assertEquals(itemsInCartAfterRemove, "");
    }

    @Test
    public void areElementsVisible() {
        loginPage.login(username, password);
        List<WebElement> images = driver.findElements(By.cssSelector("[id*='_img_link']"));
        List<WebElement> titles = driver.findElements(By.cssSelector(".inventory_item_name"));
        List<WebElement> prices = driver.findElements(By.cssSelector(".inventory_item_price"));
        List<WebElement> descriptions = driver.findElements(By.cssSelector(".inventory_item_label"));
        List<WebElement> addToCartButtons = driver.findElements(By.cssSelector("[id*='add-to-cart-']"));
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(images.size(), 6);
        softAssert.assertEquals(titles.size(), 6);
        softAssert.assertEquals(prices.size(), 6);
        softAssert.assertEquals(descriptions.size(), 6);
        softAssert.assertEquals(addToCartButtons.size(), 6);
        softAssert.assertAll();
    }

    @Test
    public void updatingIncreasingCartIcon() {
        loginPage.login(username, password);
        listingPage = new ListingPage(driver);
        for (int i = 0; i < 6; i++) {

            listingPage.addToCartRandomProducts(driver);
        }
        int itemsInCart = Integer.parseInt(driver.findElement(cartLocator).getText());
        Assert.assertEquals(itemsInCart, 6);
    }

    @Test
    public void updatingDecreasingCartIcon() {
        loginPage.login(username, password);
        listingPage = new ListingPage(driver);
        for (int i = 0; i < 6; i++) {
            listingPage.addToCartRandomProducts(driver);
        }
        for (int i = 0; i < 5; i++) {
            listingPage.removeFromCartFirstProduct(driver);
        }
        int itemsInCart = Integer.parseInt(driver.findElement(cartLocator).getText());
        Assert.assertEquals(itemsInCart, 1);
        utils = new Utils(driver);
        utils.cleanCart(driver);
    }

    @Test
    public void removeProductAddedFromProdPage() {

        loginPage
                .login(username, password)
                .moveToProductPageOfFirstProduct(driver)
                .addToCart(driver)
                .back(driver);
        listingPage
                .removeFromCartFirstProduct(driver);
        String itemsInCart = driver.findElement(cartLocator).getText();
        Assert.assertEquals(itemsInCart, "");
    }
}