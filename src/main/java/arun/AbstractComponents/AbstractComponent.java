package arun.AbstractComponents;

import arun.pageobjects.CartPage;
import arun.pageobjects.OrderPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponent {
    //    AbstractComponent class is a parent class for all POM classes
//    as because all POM class take reusable code from here
    WebDriver driver;

    public AbstractComponent(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "button[routerlink*='cart']")
    WebElement cartHeader;

    @FindBy(css = "button[routerlink*='myorders']")
    WebElement orderHeader;

    public void waitForElementToAppear(By findBY) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBY));
    }

    public void waitForWebElementToAppear(WebElement findBY) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(findBY));
    }

    // Header is same for all pages in the application than we can declare "goToCart" inside the
    // "AbstractComponent" so we can reuse this for all pages
    public CartPage goToCart() {
        cartHeader.click();
        CartPage cartpage = new CartPage(driver);
        return cartpage;
    }

    public OrderPage goToOrder() {
        waitForWebElementToAppear(orderHeader);
        orderHeader.click();
        OrderPage orderpage = new OrderPage(driver);
        return orderpage;
    }

    public void waitForElementToDisappear(WebElement ele) throws InterruptedException {
        // for time being
        Thread.sleep(2000);
        // it takes 4sec disappear the spinner becuase have another hidden spinner located
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//        wait.until(ExpectedConditions.invisibilityOf(ele));
    }
}
