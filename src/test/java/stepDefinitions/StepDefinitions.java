package stepDefinitions;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import pojo.LocDetails;
import pojo.Location;
import resources.APIUris;
import resources.TestDataBuild;
import resources.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class StepDefinitions extends Utils {

    RequestSpecification res;
    ResponseSpecification resSpec;
    Response response;
    TestDataBuild data=new TestDataBuild();
    static String place_id;

    @Given("^Add Place Payload with \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void add_place_payload_with_something_something_something(String name, String language, String address) throws Throwable {
        res = given().spec(requestSpecification())
                .body(data.addPlacePayload(name,language,address));
    }


    @When("^user calls \"([^\"]*)\" with \"([^\"]*)\" http request$")
    public void user_calls_something_with_http_request(String resourceUri, String method){
        //Constructor will be called with the resource URI you pass from step(uses enum)
        APIUris resourceAPI=APIUris.valueOf(resourceUri);

        //Response Spec Builder
        resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        if(method.equalsIgnoreCase("POST"))
          response = res.when().post(resourceAPI.getResourceUri());
        else if(method.equalsIgnoreCase("GET"))
            response = res.when().get(resourceAPI.getResourceUri());

    }

    @Then("^the API call got success with status code 200$")
    public void the_api_call_got_success_with_status_code() throws Throwable {
        assertEquals(response.getStatusCode(),200);
    }

    @And("^\"([^\"]*)\" in response body is \"([^\"]*)\"$")
    public void something_in_response_body_is_something(String keyValue, String expectedValue) throws Throwable {
        assertEquals(getJsonPath(response,keyValue),expectedValue);
    }

    @And("^verify place_id created maps to \"([^\"]*)\" using \"([^\"]*)\"$")
    public void verifyPlace_idCreatedMapsToUsingGetPlaceAPI(String expectedName, String resource) throws Throwable {
        place_id = getJsonPath(response, "place_id");
        res = given().spec(requestSpecification()).queryParam("place_id",place_id);
        user_calls_something_with_http_request(resource,"Get");
        String actualName = getJsonPath(response, "name");
        assertEquals(expectedName,actualName);

    }

    @Given("^DeletePlace Payload$")
    public void deleteplace_payload() throws IOException {
       res=given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
    }
}
