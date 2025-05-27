package arun.pageobjects;

import arun.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutPage extends AbstractComponent {
    WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[placeholder='Select Country']")
    WebElement country;

    @FindBy(css = ".ta-item:nth-of-type(2)")
    WebElement selectCountry;

    @FindBy(css = ".btnn")
    WebElement submit;

    By result = By.cssSelector(".ta-results");

    public void selectCountryName(String countryName) {
        Actions ac = new Actions(driver);
        ac.sendKeys(country, countryName).build().perform();
        waitForElementToAppear(result);
        selectCountry.click();
    }

    public ConfirmationPage submitOrder() {
        submit.click();
        return new ConfirmationPage(driver);
    }
}
