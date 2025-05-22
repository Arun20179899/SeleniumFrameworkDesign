package arun;

import arun.pageobjects.LandingPage;
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

public class StandAloneTest {
    public static void main(String[] args) throws InterruptedException {
        String userName = "ARUN1998.AG12@GMAIL.COM";
        String userPwd = "Es0ft@2025";
        String productName = "IPHONE 13 PRO";
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/client");
        LandingPage landingPage = new LandingPage(driver);
        driver.findElement(By.id("userEmail")).sendKeys(userName);
        driver.findElement(By.id("userPassword")).sendKeys(userPwd);
        driver.findElement(By.id("login")).click();
        // grab all the product cards
        // put wait for loading all the products
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
        // after getting all products we will filter one by one and implement limited scope
        // to access the product name of that particular product
        WebElement prod = products.stream().filter(product->
                product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
        prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
        // put wait to display the add to cart message
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
        // put wait to display card
        Thread.sleep(5000);
        driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();

        // Logic to verify items in the cart with stream and checkout

        List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
        boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
        Assert.assertTrue(match);
        // click on the checkout button
        driver.findElement(By.cssSelector(".totalRow button")).click();

        Actions ac = new Actions(driver);
        ac.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"India").build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
        driver.findElement(By.cssSelector(".btnn")).click();
        String confirmMessage= driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        driver.close();




    }
}
