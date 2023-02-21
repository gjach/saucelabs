import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class CheckoutOverviewTest extends BaseTest {

    int randomNum = ThreadLocalRandom.current().nextInt(1, 6 + 1);


    @Test
    public void checkQuantityOfProducts() {
        loginPage
                .login(username, password);
        for (int i = 0; i < randomNum; i++) {
            listingPage
                    .addToCartRandomProducts();
        }
        cartPage
                .checkCart(driver)
                .goToCheckOut(driver)
                .enterData(driver, name, surname, postalCode)
                .goToCheckoutOverview(driver);
        List<WebElement> productsInCart = driver.findElements(By.cssSelector(".inventory_item_name"));
        Assert.assertEquals(randomNum, productsInCart.size());
        utils = new Utils(driver);
        utils.cleanCart(driver);
    }

    @Test
    public void totalPrice() {
        loginPage
                .login(username, password);
        for (int i = 0; i < randomNum; i++) {
            listingPage
                    .addToCartRandomProducts();
        }
        cartPage
                .checkCart(driver);
        List<WebElement> pricesLocators = driver.findElements(By.cssSelector(".inventory_item_price"));
        List<Double> prices = new ArrayList<>();
        for (WebElement p : pricesLocators) {
            prices.add(Double.valueOf(p.getText().replace("$", "")));
        }
        double sum = 0;
        for (int i = 0; i < prices.size(); i++)
            sum += prices.get(i);
        cartPage
                .goToCheckOut(driver)
                .enterData(driver, name, surname, postalCode)
                .goToCheckoutOverview(driver);
        String itemTotalInformation = driver.findElement(By.cssSelector(".summary_subtotal_label")).getText();
        double totalPrice = Double.valueOf(itemTotalInformation.replace("Item total: $", ""));
        Assert.assertEquals(totalPrice, sum);
        utils = new Utils(driver);
        utils.cleanCart(driver);
    }

    @Test
    public void summaryTaxAndProducts() {
        loginPage
                .login(username, password);
        for (int i = 0; i < randomNum; i++) {
            listingPage
                    .addToCartRandomProducts();
        }
        cartPage
                .checkCart(driver);
        List<WebElement> pricesLocators = driver.findElements(By.cssSelector(".inventory_item_price"));
        List<Double> prices = new ArrayList<>();
        for (WebElement p : pricesLocators) {
            prices.add(Double.valueOf(p.getText().replace("$", "")));
        }
        double sum = 0;
        for (int i = 0; i < prices.size(); i++)
            sum += prices.get(i);
        cartPage
                .goToCheckOut(driver)
                .enterData(driver, name, surname, postalCode)
                .goToCheckoutOverview(driver);
        String productsPriceInformation = driver.findElement(By.cssSelector(".summary_subtotal_label")).getText();
        double productsPrice = Double.valueOf(productsPriceInformation.replace("Item total: $", ""));
        String taxInformation = driver.findElement(By.cssSelector(".summary_tax_label")).getText();
        double taxValue = Double.valueOf(taxInformation.replace("Tax: $", ""));
        String totalPriceInformation = driver.findElement(By.cssSelector(".summary_total_label")).getText();
        double totalPrice = Double.valueOf(totalPriceInformation.replace("Total: $", ""));
        double expectedTotalPrice = Math.round((productsPrice + taxValue) * 100.0) / 100.0;
        Assert.assertEquals(totalPrice, expectedTotalPrice);
        utils = new Utils(driver);
        utils.cleanCart(driver);
    }

    @Test
    public void checkCancelButton() {
        loginPage
                .login(username, password);
        for (int i = 0; i < randomNum; i++) {
            listingPage
                    .addToCartRandomProducts();
        }
        cartPage
                .checkCart(driver);
        cartPage
                .goToCheckOut(driver)
                .enterData(driver, name, surname, postalCode)
                .goToCheckoutOverview(driver)
                .cancel(driver);
        String expectedURL = "https://www.saucedemo.com/inventory.html";
        Assert.assertEquals(driver.getCurrentUrl(), expectedURL, "There was a problem with login");
    }

    @Test
    public void checkFinishButton() {
        loginPage.login(username, password);
        for (int i = 0; i < randomNum; i++) {
            listingPage
                    .addToCartRandomProducts();
        }
        cartPage
                .checkCart(driver);
        cartPage
                .goToCheckOut(driver)
                .enterData(driver, name, surname, postalCode)
                .goToCheckoutOverview(driver)
                .finish(driver);
        String confirmationOfOrder = driver.findElement(By.cssSelector(".complete-header")).getText();
        String expectedConfirmationOfOrder = "THANK YOU FOR YOUR ORDER";
        Assert.assertEquals(confirmationOfOrder, expectedConfirmationOfOrder);
    }
}
