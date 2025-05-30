package arun.Tests;

import arun.TestComponents.BaseTest;
import arun.pageobjects.CartPage;
import arun.pageobjects.ProductCatalog;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class ErrorValidationTest extends BaseTest {

    @Test(groups = {"Error Handling"})
    public void loginErrorValidationTest() throws IOException, InterruptedException {

        ProductCatalog productCatalogPage = landingPage.loginData("admin1998@gmail.com", "Es0ft@2027");

        Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email or password.");

    }

    @Test
    public void productErrorValidationTest() throws IOException, InterruptedException {
        String productName = "ZARA COAT 33";
        String countryName = "India";
        ProductCatalog productCatalogPage = landingPage.loginData("admin1998@gmail.com", "Es0ft@2026");
        Assert.assertFalse(false);
    }
}

