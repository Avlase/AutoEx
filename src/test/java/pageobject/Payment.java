package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Payment {

    private final WebDriver driver;

    private static final String CARD_NAME = "//*[@id=\"payment-form\"]/div[1]/div/input";
    private static final String CARD_NUMBER = "//*[@id=\"payment-form\"]/div[2]/div/input";
    private static final String CVC = "//*[@id=\"payment-form\"]/div[3]/div[1]/input";
    private static final String EXP_MM = "//*[@id=\"payment-form\"]/div[3]/div[2]/input";
    private static final String EXP_YYYY = "//*[@id=\"payment-form\"]/div[3]/div[3]/input";
    private static final String PAY_AND_ORDER_BTN = "//*[@id=\"submit\"]";

    public Payment(WebDriver driver) {
        this.driver = driver;
        cardNameInput = driver.findElement(By.xpath(CARD_NAME));
        cardNumberInput = driver.findElement(By.xpath(CARD_NUMBER));
        cvcInput = driver.findElement(By.xpath(CVC));
        expMMInput = driver.findElement(By.xpath(EXP_MM));
        expYYYYInput = driver.findElement(By.xpath(EXP_YYYY));
        payAndOrderBtn = driver.findElement(By.xpath(PAY_AND_ORDER_BTN));

    }
    private final WebElement cardNameInput;
    private final WebElement cardNumberInput;
    private final WebElement cvcInput;
    private final WebElement expMMInput;
    private final WebElement expYYYYInput;
    private final WebElement payAndOrderBtn;

    public void enterCardName(String cardName) {
        cardNameInput.sendKeys(cardName);
    }
    public void enterCardNumber(String cardNumber) {
        cardNumberInput.sendKeys(cardNumber);
    }
    public void enterCVC(String cvc) {
        cvcInput.sendKeys(cvc);
    }
    public void enterExpMM(String expMM) {
        expMMInput.sendKeys(expMM);
    }
    public void enterExpYYYY(String expYYYY) {
        expYYYYInput.sendKeys(expYYYY);
    }

    public void clickPayAndConfirmOrder() {
        payAndOrderBtn.click();
    }
}