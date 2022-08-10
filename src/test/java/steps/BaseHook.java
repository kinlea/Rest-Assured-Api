package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import org.testng.annotations.BeforeTest;

import java.util.HashMap;
import java.util.Map;

public class BaseHook {
    Map<String, String> requestMap = new HashMap<>();
    Response response;

    @Given("Get the API Url")
    public void getAndSetTheAPIUrl() {
        requestMap.put("URL", "https://petstore.swagger.io/v2");
    }

    @And("Set the Authorization token")
    public void setTheAuthorizationToken() {
        requestMap.put("token", "");
    }

    @When("Construct the {string} request body with the following data")
    public void constructTheRequestBodyForWithTheFollowingData(String requestName, DataTable datatable) {
        requestMap.put("RequestName", requestName);
        Map<String, String> inputMap = datatable.asMap(String.class, String.class);
        requestMap.putAll(inputMap);
    }

    @When("Make a Rest Call to {string} with {string} method")
    public void makeARestCallWithMethod(String path, String method) throws Throwable {
        requestMap.put("Path", path);
        requestMap.put("RequestMethod", method);
        RestProcessor restProcessor = new RestProcessor();
        response = restProcessor.processRestCall(requestMap);
    }

    @When("Make a Rest Call to {string} con id {string} with {string} method")
    public void makeARestCallWithMethod(String path, String id, String method) throws Throwable {
        requestMap.put("Path", path+id);
        requestMap.put("RequestMethod", method);
        RestProcessor restProcessor = new RestProcessor();
        response = restProcessor.processRestCall(requestMap);
    }

    @Then("Verify if the status code is {string}")
    public void verifyIfTheStatusCodeIs(String statusCode) {
        System.out.println("El statusCode obtenido es " +response.getStatusCode());
        Assert.assertEquals(String.valueOf(response.getStatusCode()), statusCode);
    }

    @Then("Verify if the response contains the following info")
    public void verifyItTheResponseContainsTheFollowingInfo(DataTable datatable) {
        Map<String, String> resultMap = datatable.asMap(String.class, String.class);
        for (Map.Entry<String, String> entry : resultMap.entrySet())
            Assert.assertEquals(response.jsonPath().getString(entry.getKey()), entry.getValue());
    }
//Este lo dejo por si me sirve
    @Then("^Specific customer info will be shown$")
    public void specific_customer_info_will_be_shown()  {
        System.out.println("Response Body is =>  " + response.asString());
        String customerId=response.jsonPath().getString("id");
        Assert.assertEquals(customerId,"101");

    }

}