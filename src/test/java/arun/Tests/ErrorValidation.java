package arun.Tests;

import arun.TestComponents.BaseTest;
import arun.pageobjects.CartPage;
import arun.pageobjects.CheckoutPage;
import arun.pageobjects.ConfirmationPage;
import arun.pageobjects.ProductCatalog;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ErrorValidation extends BaseTest {

    @Test
    public void loginTest() throws IOException, InterruptedException {

        ProductCatalog productCatalogPage = landingPage.loginData("ARUN1998.AG12@GMAIL.COM", "Es0ft@2026");

        Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email or password.");

    }
}

