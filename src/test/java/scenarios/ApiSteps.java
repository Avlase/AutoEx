package scenarios;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.RespBody;
import dto.UserType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;

import java.io.IOException;


public class ApiSteps {
    final String apiUrl = "https://automationexercise.com/api/";
    final String baseUrl = "https://automationexercise.com/";
    OkHttpClient client = new OkHttpClient.Builder().build();
    ObjectMapper objectMapper = new ObjectMapper();
    String product = "8";
    String sessionid = "pdfrnl7qf0xsn52ck36z0kq1m5mcepvt";
    RespBody resp = new RespBody();
    UserType user;

    @When("I send an API call to create an account with required data: {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string},{string}, {string}, {string}, {string}, {string}")
    public void iSendAnAPICallToCreateAnAccountWithRequiredData(String name, String email, String title, String birth_day, String birth_month, String birth_year, String password, String firstname, String lastname, String address1, String address2,String country, String state, String city, String zipcode, String mobile_number) {
        String endpoint = "createAccount";
        RequestBody body = new FormBody.Builder()
                .add("name", name)
                .add("email", email)
                .add("title", title)
                .add("birth_day", birth_day)
                .add("birth_month", birth_month)
                .add("birth_year", birth_year)
                .add("password", password)
                .add("firstname", firstname)
                .add("lastname", lastname)
                .add("address1", address1)
                .add("address2", address2)
                .add("country", country)
                .add("state", state)
                .add("city", city)
                .add("zipcode", zipcode)
                .add("mobile_number", mobile_number)
                .build();

        Request request = new Request.Builder()
                .url(apiUrl+endpoint)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            resp.setCode(response.code());
            Assert.assertEquals(resp.getCode(), 200, "Current response code is " + resp.getCode() + ". But expected - 200");


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Then("Get details by {string} response code is 200")
    public void getDetailsByEmailResponseCodeIs(String email) {
        String endpoint = "getUserDetailByEmail";
        String urlPar = "?email="+email;

        Request request = new Request.Builder()
                .url(apiUrl+endpoint+urlPar)
                .method("GET", null)
                .build();
        try (Response response = client.newCall(request).execute()) {
            resp.setCode(response.code());
            Assert.assertEquals(resp.getCode(), 200, "Current response code is " + resp.getCode() + ". But expected - 200");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @When("I send an Api call to delete the account with: {string}, {string}")
    public void iSendAnApiCallToDeleteTheAccountWithEmailPassword(String email, String password) {
        String endpoint = "deleteAccount";
        RequestBody body = new FormBody.Builder()
                .add("email", email)
                .add("password", password)
                .build();

        Request request = new Request.Builder()
                .url(apiUrl+endpoint)
                .delete(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            resp.setCode(response.code());
            okhttp3.ResponseBody responseBody = response.body();
            RespBody entity = objectMapper.readValue(responseBody.string(), RespBody.class);
            resp.setRspMsg(entity.getMessage());
            resp.setRspCodeBody(entity.getResponseCode());
            Assert.assertEquals(resp.getCode(), 200, "Current response code is " + resp.getCode() + ". But expected - 200");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @And("response body contains {string} message")
    public void responseBodyContainsAccountDeletedMessage(String msg) {

                Assert.assertEquals(resp.getRspMsg(), msg, "Message is not as " + msg);
    }

    @And("response code is {string}")
    public void responseCodeIs(String rcodeBody) {
        Assert.assertEquals(resp.getRspCodeBody(), rcodeBody, "Response code is not as " + rcodeBody);
    }

    @Given("A product added to the cart for the User")
    public void anProductAddedToTheCartForTheUser() {
        String endpoint = "add_to_cart/";
        Request request = new Request.Builder()
                .url(baseUrl+endpoint+product)
                .header("Cookie", "sessionid=" + sessionid)
                .build();
        try (Response response = client.newCall(request).execute()) {
            Assert.assertEquals(response.body().string(), "Added To Cart", "Step is failed");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @When("the quantity is updated for the product")
    public void theQuantityIsUpdatedForTheProduct() {
        String endpoint = "add_to_cart/";
        String quantity = "?quantity=";
        String qValue = "4";
        Request request = new Request.Builder()
                .url(baseUrl+endpoint+product+quantity+qValue)
                .header("Cookie", "sessionid=" + sessionid)
                .build();
        try (Response response = client.newCall(request).execute()) {
            Assert.assertEquals(response.body().string(), "Added To Cart", "Step is failed");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @When("product is deleted from the cart")
    public void productIsDeletedFromTheCart() {
        String endpoint = "delete_cart/";
        Request request = new Request.Builder()
                .url(baseUrl+endpoint+product)
                .header("Cookie", "sessionid=" + sessionid)
                .build();
        try (Response response = client.newCall(request).execute()) {
            Assert.assertEquals(response.body().string(), "Cart removed", "Step is failed");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @When("I send an API call to get account detail by {string}")
    public void iSendAnAPICallToGetAccountDetailByEmail(String email) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        String endpoint = "getUserDetailByEmail";
        String urlPar = "?email="+email;
        Request request = new Request.Builder()
                .url(apiUrl+endpoint+urlPar)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String body = response.body().string();
            System.out.println(body);
            user = mapper.readValue(body, UserType.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Then("response body contains data which is equal to {string}, {string}, {string}, {string}, {string}")
    public void responseBodyContainsDataWhichIsEqualToNameEmailTitlePasswordFirstNameLastName(String name, String email, String title, String firstName, String lastName) {
        Assert.assertEquals(user.getUser().getName(), name);
        Assert.assertEquals(user.getUser().getEmail(), email);
        Assert.assertEquals(user.getUser().getTitle(), title);
        Assert.assertEquals(user.getUser().getFirstName(), firstName);
        Assert.assertEquals(user.getUser().getLastName(), lastName);
    }
}
