package tek.tdd.api.test;

import com.aventstack.extentreports.service.ExtentTestManager;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import tek.tdd.api.models.AccountType;
import tek.tdd.api.models.EndPoints;
import tek.tdd.api.models.TokenRequest;
import tek.tdd.api.models.UserProfileResponse;
import tek.tdd.base.ApiTestsBase;

public class UserProfileTest extends ApiTestsBase {

    @Test
    public void validateUserProfile(){
        TokenRequest request = new TokenRequest("supervisor", "tek_supervisor");
        ExtentTestManager.getTest().info(request.toString());
        Response response = AuthenticatatedToken(request)
                .when().get(EndPoints.GET_USER_PROFILE.getValue())
                .then().statusCode(200)
                .extract().response();

        ExtentTestManager.getTest().info(response.asPrettyString());
        UserProfileResponse userProfileResponse = response.getBody().jsonPath().getObject("", UserProfileResponse.class);

        Assert.assertEquals(userProfileResponse.getUsername(), "SUPERVISOR");
        Assert.assertEquals(userProfileResponse.getFullName(), "Supervisor");
        Assert.assertEquals(userProfileResponse.getAccountType(), AccountType.CSR);

    }
}
