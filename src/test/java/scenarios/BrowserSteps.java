package scenarios;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import pageobject.Cart;
import pageobject.EmptyCart;
import pageobject.LoginPage;

public class BrowserSteps {
    final String baseUrl = "https://automationexercise.com/";
    WebDriver driver = new ChromeDriver();

    @Given("Login page")
    public void LoginPage() {
        driver.get(baseUrl + "login");
    }

    @When("the field email is empty")
    public void theFieldEmailIsEmpty() {
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertEquals(loginPage.getEmailValue(), "", "Email value is not empty");
    }

    @And("the field password is empty")
    public void theFieldPasswordIsEmpty() {
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertEquals(loginPage.getPasswordValue(), "", "Password value is not empty");
    }

    @Then("the field email has Email Address placeholder")
    public void theFieldEmailHasEmailAddressPlaceholder() {
        String eMailValue = "Email Address";
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertEquals(loginPage.getEmailPlaceHolder(), eMailValue, "Should be: " + eMailValue);
    }

    @And("the field password has Password placeholder")
    public void theFieldPasswordHasPasswordPlaceholder() {
        String passValue = "Password";
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertEquals(loginPage.getPasswordPlaceHolder(), passValue, "Should be: " + passValue);
        driver.quit();
    }

    @When("I click on login button")
    public void iClickOnLoginButton() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickLoginButton();
    }

    @When("I enter {string} to the email field")
    public void iEnterUserEmailToTheEmailField(String email) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail(email);
    }

    @And("I enter {string} to the password field")
    public void iEnterToThePasswordField(String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterPassword(password);
    }

    @Then("an error message is displayed {string}")
    public void anErrorMessageIsDisplayedYourEmailOrPasswordIsIncorrect(String errMsg) {
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.checkMessage(errMsg));
        driver.quit();
    }

    @Then("Redirected to the Homepage")
    public void redirectedToTheHomepage() {
        String url = driver.getCurrentUrl();
        Assert.assertEquals(url, baseUrl, "Wrong page is opened");
        driver.quit();
    }

    @When("I open the cart")
    public void iOpenTheCart() {
        driver.get(baseUrl + "view_cart");
    }

    @Then("I see the product in the cart")
    public void iSeeTheProductInTheCart() {
        String product = "Fancy Green Top";
        Cart cart = new Cart(driver);
        Assert.assertEquals(cart.getProduct(), product, "Another product added or no product available " + cart.getProduct());
    }

    @Then("update ie reflected on the cart")
    public void updateIeReflectedOnTheCart() {
        driver.navigate().refresh();
        Cart cart = new Cart(driver);
        Assert.assertEquals(cart.getQuaintity(), "5", "Should be 5 but I see " + cart.getQuaintity());
    }

    @Then("I see a message {string}")
    public void iSeeAMessageCartIsEmptyClickHereToBuyProducts(String msg) throws InterruptedException {
        driver.navigate().refresh();
        EmptyCart emptycart = new EmptyCart(driver);
        Assert.assertEquals(emptycart.getMsgEmptyCart(), msg, "Step failed as wrong msg");
        driver.quit();
    }
}
