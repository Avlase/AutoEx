package scenarios;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import pageobject.Cart;
import pageobject.Checkout;
import pageobject.EmptyCart;
import pageobject.LoginPage;
import pageobject.Payment;
import pageobject.PaymentDone;

public class BrowserSteps {
    final String baseUrl = "https://automationexercise.com/";
    WebDriver driver = new ChromeDriver();
    String product = "Fancy Green Top";
    String checkoutUrl = baseUrl + "checkout";
    String paymentUrl = baseUrl + "payment";
    String paymentDoneUrl = baseUrl + "payment_done/700";

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

    @When("I open the checkout screen")
    public void iOpenTheCheckoutScreen() {
        driver.get(checkoutUrl);
    }

    @Then("I see the product on the checkout screen")
    public void iSeeTheProductOnTheCheckoutScreen() {
        Checkout checkout = new Checkout(driver);
        Assert.assertEquals(checkout.getProduct(), product, "Another product added or no product available " + checkout.getProduct());
    }

    @When("I select Place Order button")
    public void iSelectPlaceOrderButton() {
        driver.navigate().refresh();
        Checkout checkout = new Checkout(driver);
        checkout.clickPlaceOrder();
    }

    @Then("I see Payment screen")
    public void iSeePaymentScreen() {
        driver.get(paymentUrl);
        String url = driver.getCurrentUrl();
        Assert.assertEquals(url, paymentUrl, "Wrong page is opened");
    }

    @When("I enter payment info: {string}, {string}, {string}, {string}, {string}")
    public void iEnterPaymentInfoCardNameCardNumberCVCExpMMExpYYYY(String cardName, String cardNumber, String cvc, String expMM, String expYYYY) {
        Payment pay = new Payment(driver);
        pay.enterCardName(cardName);
        pay.enterCardNumber(cardNumber);
        pay.enterCVC(cvc);
        pay.enterExpMM(expMM);
        pay.enterExpYYYY(expYYYY);
    }

    @And("I select a confirmation button")
    public void iSelectAConfirmationButton() {
        Payment pay = new Payment(driver);
        pay.clickPayAndConfirmOrder();
    }

    @Then("Payment done screen is opened")
    public void paymentDoneScreenIsOpened() {
        driver.navigate().refresh();
        String url = driver.getCurrentUrl();
        Assert.assertEquals(url, paymentDoneUrl, "Wrong page is opened");
    }
    @When("I select continue button")
    public void iSelectContinueButton() {
        PaymentDone payDone = new PaymentDone(driver);
        payDone.clickContinueBtn();
    }
}
