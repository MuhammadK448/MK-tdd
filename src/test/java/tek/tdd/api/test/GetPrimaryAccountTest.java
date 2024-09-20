package tek.tdd.api.test;
import com.aventstack.extentreports.service.ExtentTestManager;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import tek.tdd.api.models.AccountType;
import tek.tdd.api.models.EndPoints;
import tek.tdd.api.models.TokenRequest;
import tek.tdd.api.models.TokenResponse;
import tek.tdd.base.ApiTestsBase;

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
}


