package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EmptyCart {

        private final WebDriver driver;
        private static final String MSG_EMPTY_CART = "//*[@id=\"empty_cart\"]/p";


        public EmptyCart(WebDriver driver) {
            this.driver = driver;
            msgEmptyCart = driver.findElement(By.id("empty_cart"));

        }

        private final WebElement msgEmptyCart;


        public String getMsgEmptyCart() {
            return msgEmptyCart.getText();
        }
    }

