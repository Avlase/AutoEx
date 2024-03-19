package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PaymentDone {
    private final WebDriver driver;

    private static final String CONTINUE_BTN = "//*[@id=\"form\"]/div/div/div/div/a";


    public PaymentDone(WebDriver driver) {
        this.driver = driver;
        continueBtn = driver.findElement(By.xpath(CONTINUE_BTN));
    }
    private final WebElement continueBtn;

    public void clickContinueBtn() {
        continueBtn.click();
    }
}
