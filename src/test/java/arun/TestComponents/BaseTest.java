package arun.TestComponents;

import arun.pageobjects.LandingPage;
import arun.pageobjects.ProductCatalog;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;
    public LandingPage landingPage;
    public Properties prop;
    public FileInputStream fis;

    public WebDriver initializeDriver() throws IOException {
        prop = new Properties();
        fis = new FileInputStream("C:\\Users\\lenovo\\IdeaProjects\\SeleniumFrameworkDesign\\src\\main\\resources\\GlobalData.properties");
        prop.load(fis);
        String browserName = prop.getProperty("browser");

        if (browserName.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        }
        driver.manage().window().maximize();
        return driver;
    }
    @BeforeMethod
    public LandingPage lunchApplication() throws IOException {
        initializeDriver();
        landingPage = new LandingPage(driver);
        landingPage.goTo();
//        landingPage.loginData(prop.getProperty("userName"), prop.getProperty("userPwd"));
        return new LandingPage(driver);
    }

    @AfterMethod
    public void closeBrowser(){
        driver.close();
    }
}
