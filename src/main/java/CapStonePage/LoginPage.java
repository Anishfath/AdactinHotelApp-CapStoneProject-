package CapStonePage;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;

    // Locators
    @FindBy(id = "username")
    WebElement usernameField;

    @FindBy(id = "password")
    WebElement passwordField;


    @FindBy(id = "login")
    WebElement loginButton;

    @FindBy(className = "auth_error")
    WebElement errorMessage;
    
    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Actions
    public void login(String username, String password) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

	public boolean isLoginSuccessful() {
		// TODO Auto-generated method stub
		return false;
	}

  
}


