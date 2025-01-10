package CapStoneTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import CapStonePage.LoginPage;
import Utils.Screenshotutils;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        // Set up WebDriver
    	WebDriverManager.chromedriver().setup();
		
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://adactinhotelapp.com/");
        
        // Initialize Page Object
        loginPage = new LoginPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        // Quit WebDriver
        if (driver != null) {
            driver.quit();
        }
    }

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return new Object[][] {
            {"NivashAnish", "Password", true},      
            {"SalmanKhan", "Password", false},  
            {"NivashAnish", "invalidPassword", false},                         
        };
    }

    @Test(dataProvider = "loginData")
    public void testLogin(String username, String password, boolean expectedResult) {
        // Perform login
        loginPage.login(username, password);
        
        if (expectedResult) {
            // Verify successful login
            Assert.assertTrue(driver.getTitle().contains("Search Hotel"), "Login failed for valid credentials!");
            Screenshotutils.takeScreenshot(driver, "Valid_Login_Success");
        } else {
            // Verify error message for invalid login
            Assert.assertTrue(loginPage.getErrorMessage().contains("Invalid Login details"), "Error message not displayed for invalid credentials!");
            Screenshotutils.takeScreenshot(driver, "Invalid_Login_Error");
        }
    }
}
