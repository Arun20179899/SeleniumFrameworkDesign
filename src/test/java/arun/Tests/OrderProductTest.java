package arun.Tests;

import arun.TestComponents.BaseTest;
import arun.pageobjects.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class OrderProductTest extends BaseTest {
    String productName = "ZARA COAT 3";
    String countryName = "India";

    @Test
    public void submitOrder() throws IOException, InterruptedException {
        ProductCatalog productCatalogPage = landingPage.loginData("ARUN1998.AG12@GMAIL.COM", "Es0ft@2025");
        productCatalogPage.addProductToCart(productName);
        CartPage cartpage = productCatalogPage.goToCart();
        boolean match = cartpage.verifyProductDisplay(productName);
        Assert.assertTrue(match);
        CheckoutPage checkoutpage = cartpage.goToCheckout();
        checkoutpage.selectCountryName(countryName);
        ConfirmationPage confirmationpage = checkoutpage.submitOrder();
        String confirmMessage = confirmationpage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    }

    @Test(dependsOnMethods = {"submitOrder"})
    public void orderHistoryTest() {
        ProductCatalog productCatalogPage = landingPage.loginData("ARUN1998.AG12@GMAIL.COM", "Es0ft@2025");
        OrderPage orderpage = productCatalogPage.goToOrder();
        boolean match = orderpage.verifyDisplayProduct(productName);
        Assert.assertTrue(match);

    }
}

