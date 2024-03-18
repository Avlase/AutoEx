package scenarios;
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
    OkHttpClient client = new OkHttpClient.Builder().build();

    @When("I send an API call to create an account with required data: {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}")
    public void iSendAnAPICallToCreateAnAccountWithRequiredData(String name, String email, String password, String firstname, String lastname, String address, String country, String state, String city, String zipcode, String mobile_number) {
        String endpoint = "createAccount";
        RequestBody body = new FormBody.Builder()
                .add("name", name)
                .add("email", email)
                .add("password", password)
                .add("firstname", firstname)
                .add("lastname", lastname)
                .add("address", address)
                .add("country", country)
                .add("state", state)
                .add("city", city)
                .add("mobile_number", mobile_number)
                .build();

        Request request = new Request.Builder()
                .url(apiUrl+endpoint)
                .method("POST", body)
                .header("accept", "text/plain; v=1.0")
                .build();
        try (Response response = client.newCall(request).execute()) {
            int code = response.code();
            Assert.assertEquals(code, 201, "Current response code is " + code + ". But expected - 201");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Then("I should see that account with {string} is created")
    public void iShouldSeeThatAccountWithEmailIsCreated(String email) {
        String endpoint = "getUserDetailByEmail";
        RequestBody body = new FormBody.Builder()
                .add("email", email)
                .build();
        Request request = new Request.Builder()
                .url(apiUrl+endpoint)
                .method("POST", body)
                .header("accept", "text/plain; v=1.0")
                .build();
        try (Response response = client.newCall(request).execute()) {
            int code = response.code();
            Assert.assertEquals(code, 200, "Current response code is " + code + ". But expected - 200");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
