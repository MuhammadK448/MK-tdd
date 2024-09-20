package tek.tdd.api.test;
import com.aventstack.extentreports.service.ExtentTestManager;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.devtools.v85.database.Database;
import org.testng.Assert;
import org.testng.annotations.Test;
import tek.tdd.api.models.*;
import tek.tdd.base.ApiTestsBase;
import tek.tdd.utility.DatabaseUtility;

import java.sql.*;

public class GetPrimaryAccountTest extends ApiTestsBase {

    @Test
    public void getAccountAndValidate(){
        RequestSpecification requestSpecification = getDefaultRequest();
        requestSpecification.queryParam("primaryPersonId", 10035);
        Response response = requestSpecification.when().get(EndPoints.GET_PRIMARY_ACCOUNT.getValue());
        response.then().statusCode(200);
        response.prettyPrint();
       String actualEmail = response.jsonPath().getString("email");
        Assert.assertEquals(actualEmail, "jawid776@gmail.com");
    }
    //Activity: Sending request to get-primary-account with id does not exist,
    //Validate Error Message

    @Test
    public void getPrimaryAccountWithWrongId(){
       RequestSpecification requestSpecification = getDefaultRequest();
        requestSpecification.queryParam("primaryPersonId", 100001);
       Response response = requestSpecification.get(EndPoints.GET_PRIMARY_ACCOUNT.getValue());
        ExtentTestManager.getTest().info(response.getHeaders().toString());
       response.then().statusCode(404);
        response.prettyPrint();
        String errorMessage = response.jsonPath().getString("errorMessage");
        ExtentTestManager.getTest().info(response.getHeaders().toString());
        Assert.assertEquals(errorMessage, "Account with id 100001 not exist");
    }
    //Same Activity Different Approach
    @Test
    public void validateGetAccountExist(){
        Response response = getDefaultRequest()
                .queryParam("primaryPersonId", 100001)
                .when()
                .get(EndPoints.GET_PRIMARY_ACCOUNT.getValue())
                .then()
                .statusCode(404)
                .extract()// used to get response when chaining
                .response();
        ExtentTestManager.getTest().info(response.getHeaders().toString());

        String errorMessage = response.jsonPath().getString("errorMessage");
        Assert.assertEquals(errorMessage, "Account with id 100001 not exist");
    }
    //Same Activity Different Approach
    @Test
    public void validateGetAccountNotExist(){
        String errorMessage = getDefaultRequest()
                .queryParam("primaryPersonId", 100001)
                .when()
                .get(EndPoints.GET_PRIMARY_ACCOUNT.getValue())
                .then()
                .statusCode(404)
                .extract()// used to get response when chaining
                .response()
                .jsonPath().getString("errorMessage"); //the return will be the lastStep Before semiColon
        //String errorMessage = response.jsonPath().getString("errorMessage");
        Assert.assertEquals(errorMessage, "Account with id 100001 not exist");
    }
    @Test
    public void convertResponseToPOJO() {
        //Create object of POJO class
        TokenRequest tokenRequest = new TokenRequest("supervisor", "tek_supervisor");
        Response response = getDefaultRequest()
                .body(tokenRequest)
                .when().post(EndPoints.TOKEN.getValue())
                .then().statusCode(200)
                .extract()
                .response();
        ExtentTestManager.getTest().info(response.asPrettyString());

        //Convert Response body to POJO
        TokenResponse token = response.body().jsonPath().getObject("", TokenResponse.class);
        //the empty string will allow pojo class to have all the Key/value pairs from the star(path)

        //Now we can access all the values from POJO class that we retrieved in from response
        Assert.assertEquals(token.getUsername(), "supervisor");
        Assert.assertNotNull(token.getToken());
        Assert.assertEquals(token.getAccountType(), AccountType.CSR);
        Assert.assertEquals(token.getFullName(), "Supervisor");

    }
    //Retrieve latest primary person from database. call API /get-primary-account
    // Validate API response with database
    @Test
    public void getAccountWithDatabaseValidation() throws SQLException {
        String query = "select id, email from tek_insurance_app.primary_person order by id desc limit 1;";
        DatabaseUtility dbUtility = new DatabaseUtility();

        ResultSet resultSet = dbUtility.executeQuery(query);
        resultSet.next();
        int expectedId = resultSet.getInt("id");
        String expectedEmail = resultSet.getString("email");

        Response response = getDefaultRequest()
                .queryParam("primaryPersonId", expectedId)
                .when().get(EndPoints.GET_PRIMARY_ACCOUNT.getValue())
                .then().statusCode(200)
                .extract().response();
        //get Report
        ExtentTestManager.getTest().info(response.asPrettyString());
        //Convert/add response to AccountResponse class
        AccountResponse accountResponse = response.body().jsonPath().getObject("", AccountResponse.class);

        Assert.assertEquals(accountResponse.getId(), expectedId);
        Assert.assertEquals(accountResponse.getEmail(), expectedEmail);

    }

}


