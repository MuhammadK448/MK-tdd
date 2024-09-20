package tek.tdd.api.test;

import com.aventstack.extentreports.service.ExtentTestManager;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import tek.tdd.api.models.AccountResponse;
import tek.tdd.api.models.AddAccountRequest;
import tek.tdd.api.models.EndPoints;
import tek.tdd.base.ApiTestsBase;
import tek.tdd.utility.DataGenerator;

public class CreateAccountTests extends ApiTestsBase {
    private static final Logger LOGGER = LogManager.getLogger(CreateAccountTests.class);
    @Test
    public void createPrimaryAccount(){
        String randomEmail = DataGenerator.genereteRandomEmail("Khusravi");
        //Using LOmBOk Builder instead of creating object of the class
        AddAccountRequest request = AddAccountRequest.builder()
                .email(randomEmail)
                .firstName("Muhammad")
                .lastName("Khusravi")
                .title("Mr.")
                .gender("MALE")
                .maritalStatus("MARRIED")
                .employmentStatus("Employed")
                .dateOfBirth("1900-10-25")
                .build();

        //To get the response
        Response response = getDefaultRequest()
                .body(request)
                .when().post(EndPoints.ADD_PRIMARY_ACCOUNT.getValue())
                .then().statusCode(201)
                .extract().response();
        //Get Report
        ExtentTestManager.getTest().info(response.asPrettyString());
        //Assign/add the response to the AccountResponse object(POJO) class
        AccountResponse accountResponse = response.body().jsonPath().getObject("", AccountResponse.class);
        System.out.println(response.asPrettyString());
        Assert.assertNotNull(accountResponse);

    }
}
