package arun.pageobjects;

import arun.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPage extends AbstractComponent {
    WebDriver driver;

    public OrderPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    @FindBy(css = "tr td:nth-child(3)")
    List<WebElement> orderProducts;

    By orderList = By.cssSelector("tr td:nth-child(3)");

    public boolean verifyDisplayProduct(String productName) {
        waitForElementToAppear(orderList);
        boolean match = orderProducts.stream().anyMatch(orderProduct -> orderProduct.getText().equalsIgnoreCase(productName));
        return match;
    }

}
