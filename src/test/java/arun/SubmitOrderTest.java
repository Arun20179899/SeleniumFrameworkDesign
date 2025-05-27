package arun;

import arun.pageobjects.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class SubmitOrderTest {
    public static void main(String[] args) throws InterruptedException {
        String userName = "ARUN1998.AG12@GMAIL.COM";
        String userPwd = "Es0ft@2025";
        String productName = "ZARA COAT 3";
        String countryName = "India";
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        LandingPage landingPage = new LandingPage(driver);
        landingPage.goTo();
        ProductCatalog productCatalog = landingPage.loginData("ARUN1998.AG12@GMAIL.COM", "Es0ft@2025");
        List<WebElement> products = productCatalog.getProductList();
        productCatalog.addProductToCart(productName);
        CartPage cartpage = productCatalog.goToCart();
        boolean match = cartpage.verifyProductDisplay(productName);
        Assert.assertTrue(match);
        CheckoutPage checkoutpage = cartpage.goToCheckout();
        checkoutpage.selectCountryName(countryName);
        ConfirmationPage confirmationpage = checkoutpage.submitOrder();
        String confirmMessage = confirmationpage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        driver.close();


    }
}
