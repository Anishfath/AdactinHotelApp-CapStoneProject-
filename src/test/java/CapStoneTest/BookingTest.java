package CapStoneTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import CapStonePage.BookingPage;
import CapStonePage.LoginPage;
import CapStonePage.SearchHotelPage;
import Utils.Screenshotutils;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BookingTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private SearchHotelPage searchHotelPage;
    private BookingPage bookingPage;

    @BeforeMethod
    public void setUp() throws InterruptedException {
        // Set up WebDriver
    	WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://adactinhotelapp.com/");

        // Initialize Page Objects
        loginPage = new LoginPage(driver);
        searchHotelPage = new SearchHotelPage(driver);
        bookingPage = new BookingPage(driver);

        // Perform login and hotel search
        loginPage.login("NivashAnish", "Password");
        Thread.sleep(4000);
        searchHotelPage.searchHotel("Sydney", "Hotel Creek", "Double", "10-02-2025", "14-02-2025"); 
        Thread.sleep(2000);
        bookingPage.selectRoom(); // Select a room
    }

    @AfterMethod
    public void tearDown() {
        // Quit WebDriver
        if (driver != null) {
            driver.quit();
        }
    }

    @DataProvider(name = "bookingData")
    public Object[][] getBookingData() {
        return new Object[][] {
            {"1234567812345678", "VISA", "01", "2026", "123", true},  // Valid payment details
            {"1", "VISA", "01", "2000", "999", false} // Invalid payment details
        };
    }

    @Test(dataProvider = "bookingData")
    public void testBooking(String cardNumber, String cardType, String expiryMonth, String expiryYear, String cvv, boolean expectedResult) throws InterruptedException {
        // Perform booking
        bookingPage.enterPaymentDetails(cardNumber, cardType, expiryMonth, expiryYear, cvv);
        Thread.sleep(4000);
        bookingPage.confirmBooking();

        if (expectedResult) {
        	 Assert.assertTrue(driver.getPageSource().contains(""), "Error message not displayed for invalid payment details!");
        	 Screenshotutils.takeScreenshot(driver, "Invalid_Booking");
        } 
        }
    }

