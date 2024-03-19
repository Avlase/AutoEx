package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Checkout {

    private final WebDriver driver;

    private static final String PRODUCT_ID = "//*[@id=\"product-8\"]/td[2]/h4/a";
    private static final String PLACE_ORDER = "//*[@id=\"cart_items\"]/div/div[7]/a";


    public Checkout(WebDriver driver) {
        this.driver = driver;
        productId = driver.findElement(By.xpath(PRODUCT_ID));
        placeOrder = driver.findElement(By.xpath(PLACE_ORDER));

    }
    private final WebElement productId;
    private final WebElement placeOrder;

    public String getProduct() {
        return productId.getText();
    }
    public void clickPlaceOrder() {
        placeOrder.click();
    }
}