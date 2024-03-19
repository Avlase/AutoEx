package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private final WebDriver driver;

    private static final String USER_EMAIL_LOCATOR = "//*[@id=\"form\"]/div/div/div[1]/div/form/input[2]";
    private static final String USER_PASSWORD_LOCATOR = "//*[@id=\"form\"]/div/div/div[1]/div/form/input[3]";
    private static final String LOGIN_BUTTON_LOCATOR = "//*[@id=\"form\"]/div/div/div[1]/div/form/button";
    private static final String MESSAGE_LOCATOR = "//*[@id=\"form\"]/div/div/div[1]/div/form/p";

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        userEmailField = driver.findElement(By.xpath(USER_EMAIL_LOCATOR));
        passwordField = driver.findElement(By.xpath(USER_PASSWORD_LOCATOR));
        buttonField = driver.findElement(By.xpath(LOGIN_BUTTON_LOCATOR));
    }

    private final WebElement userEmailField;
    private final WebElement passwordField;
    private final WebElement buttonField;
    private WebElement messageField;
    public void enterEmail(String email) {
        userEmailField.sendKeys(email);
    }
    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }
    public boolean checkMessage(String str) {
        messageField = driver.findElement(By.xpath(MESSAGE_LOCATOR));
        return (messageField.getText().contains(str));
    }
    public String getEmailValue() {
        return userEmailField.getText();
    }
    public String getPasswordValue() {
        return passwordField.getText();
    }
    public String getEmailPlaceHolder() {
        return userEmailField.getAttribute("placeholder");
    }
    public String getPasswordPlaceHolder() {
        return passwordField.getAttribute("placeholder");
    }
    public void clickLoginButton() {
        buttonField.click();
    }
}
