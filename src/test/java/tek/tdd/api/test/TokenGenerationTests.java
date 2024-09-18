package tek.tdd.api.test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tek.tdd.base.ApiTestsBase;

import java.util.HashMap;
import java.util.Map;

// Three important steps for api call
/*
* 1-Prepare Request
* 2-Send Request
* 3-Validate Request
* */
public class TokenGenerationTests extends ApiTestsBase {
    private static final Logger LOGGER = LogManager.getLogger(TokenGenerationTests.class);
    //Create a test validate token generated with supervisor User

    @Test(dataProvider = "credentials")
    public void generateValidToken(String username, String password){
        RequestSpecification requestSpecification = getDefaultRequest();
        Map<String, String> body = getTokenRequestBody(username, password);

        requestSpecification.body(body);

        //Send Request to /api/token
        Response response = requestSpecification.when().post("/api/token");
        response.then().statusCode(200);

        //To access data from response body e.g. Username
        String actualUsername = response.body().jsonPath().getString("username");
        Assert.assertEquals(actualUsername, username, "user names should match");

        String token = response.body().jsonPath().getString("token");
        Assert.assertNotNull(token);

        String actualAccountType = response.body().jsonPath().getString("accountType");
        Assert.assertEquals(actualAccountType, "CSR");

        LOGGER.info("Response is {}", response.asPrettyString());
    }

    @DataProvider(name = "credentials")
    private String[][] credentials(){
        return new String[][]{
                {"supervisor", "tek_supervisor"},
                {"operator_readonly", "Tek4u2024"}
        };
    }

    @DataProvider(name = "inValidCredentials")
    private Object[][] invalidCredentials(){
        return new Object[][]{
                {"supervisor", "Wron_supervisor", 400, "Password not matched"},
                {"Xupervisor", "tek_supervisor", 404, "User Xupervisor not found"},
                {"XSupervisor", "Wron_supervisor", 404, "User XSupervisor not found"}
        };
    }

    //Activity-1 generate token with operator user
    // "username": "operator_readonly"
    // "password": "Tek4u2024"

    //==================
    /*
    * Activity - 2
    * Try to generate Token with Negative/Invalid Credentials
    * And Validate Error message along with status code
    * */

    @Test(dataProvider = "inValidCredentials")
    public void generateTokenWithInvalidCredentials(String username, String password, int statusCode, String errorMessage){
        RequestSpecification requestSpecification = getDefaultRequest();
        Map<String, String> data = getTokenRequestBody(username, password);
        requestSpecification.body(data);

        Response response = requestSpecification.when().post("/api/token");
        response.then().statusCode(statusCode);
        LOGGER.info("Response body: {}", response.prettyPrint());
        String actualErrorMessage = response.body().jsonPath().getString("errorMessage");
        Assert.assertEquals(actualErrorMessage, errorMessage);
    }





}
