package arun.pageobjects;

import arun.AbstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponent {
    WebDriver driver;

    public LandingPage(WebDriver driver) {
        super(driver); // this will provide a driver life to AbstractComponenet Class
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //    WebElement userEmails = driver.findElement(By.id("userEmail"));
//        driver.findElement(By.id("userPassword"))
//        driver.findElement(By.id("login"))
    @FindBy(id = "userEmail")
    WebElement userEmail;

    @FindBy(id = "userPassword")
    WebElement userPassword;

    @FindBy(id = "login")
    WebElement login;

    @FindBy(css = "[class*='toast-message']")
    WebElement errorMsg;

    // create action methods

    public ProductCatalog loginData(String userName, String userPwd) {
        userEmail.sendKeys(userName);
        userPassword.sendKeys(userPwd);
        login.click();
        //IMP..... if you sure once we login we are landing to product catalog page
        // than you can create object of that class inside this method
        // so this avoids object creation in the test class
        ProductCatalog productCatalog = new ProductCatalog(driver);
        return productCatalog;
    }

    public void goTo() {
        driver.get("https://rahulshettyacademy.com/client");
    }

    public String getErrorMessage() {
        waitForWebElementToAppear(errorMsg);
        return errorMsg.getText();
    }
}
