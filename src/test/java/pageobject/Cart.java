package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Cart {
    private final WebDriver driver;

    private static final String PRODUCT_ID = "//*[@id=\"product-8\"]/td[2]/h4/a";
    private static final String QUAINTITY = "//*[@id=\"product-8\"]/td[4]/button";

    public Cart(WebDriver driver) {
        this.driver = driver;
        productId = driver.findElement(By.xpath(PRODUCT_ID));
        quaintity = driver.findElement(By.xpath(QUAINTITY));

    }
    private final WebElement productId;
    private final WebElement quaintity;

    public String getProduct() {
        return productId.getText();
    }
    public String getQuaintity() {
        return quaintity.getText();
    }
}
