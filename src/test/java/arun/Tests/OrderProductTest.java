package arun.Tests;

import arun.TestComponents.BaseTest;
import arun.pageobjects.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class OrderProductTest extends BaseTest {
    String productName = "ZARA COAT 3";
    String countryName = "India";

    @Test(dataProvider = "getData", groups = {"Purchase", "Smoke"})
    public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
        ProductCatalog productCatalogPage = landingPage.loginData(input.get("email"), input.get("password"));
        productCatalogPage.addProductToCart(input.get("product"));
        CartPage cartpage = productCatalogPage.goToCart();
        boolean match = cartpage.verifyProductDisplay(input.get("product"));
        Assert.assertTrue(match);
        CheckoutPage checkoutpage = cartpage.goToCheckout();
        checkoutpage.selectCountryName(input.get("country"));
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

//    @DataProvider
//    public Object[][] getData() {
//        // below is the example of 2 Dimensional Array
//        return new Object[][]{
//                {"ARUN1998.AG12@GMAIL.COM", "Es0ft@2025", "ZARA COAT 3", "India"},
//                {"admin1998@gmail.com", "Es0ft@2026", "ADIDAS ORIGINAL", "India"}};
//    }

    //    Integration of HashMap to Data Provider to send the data as one Hash Object
//    @DataProvider
//    public Object[][] getData() {

    /// /        Create a HashMap object and declare the Key and Value
//        HashMap<String, String> mapSet1 = new HashMap<String, String>();
//        mapSet1.put("email", "ARUN1998.AG12@GMAIL.COM");
//        mapSet1.put("password", "Es0ft@2025");
//        mapSet1.put("product", "ZARA COAT 3");
//        mapSet1.put("country", "India");
//
//        HashMap<String, String> mapSet2 = new HashMap<String, String>();
//        mapSet2.put("email", "admin1998@gmail.com");
//        mapSet2.put("password", "Es0ft@2026");
//        mapSet2.put("product", "ADIDAS ORIGINAL");
//        mapSet2.put("country", "India");
//
//        // below is the example of 2 Dimensional Array
//        return new Object[][]{
//                {mapSet1},
//                {mapSet2}};
//    }

//    Read the data from the json file and create the list of HashMaps for testing
    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "//src//test//java//arun//data//PurchaseOrder.json");

        // below is the example of 2 Dimensional Array
        return new Object[][]{
                {data.get(0)},
                {data.get(1)}};
    }

}

