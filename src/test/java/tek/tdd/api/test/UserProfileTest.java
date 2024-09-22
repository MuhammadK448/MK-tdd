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
import tek.tdd.utility.DatabaseUtility;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class UserProfileTest extends ApiTestsBase {

    @Test
    public void validateUserProfile() throws SQLException {
        DatabaseUtility databaseUtility = new DatabaseUtility();
        String query = "SELECT id, username, full_name, account_type From user where username = 'supervisor';";
        List<Map<String, Object>> expectedDataList = databaseUtility.getResultFromDBQuery(query);
        Assert.assertFalse(expectedDataList.isEmpty());
        Map<String, Object> expectedData = expectedDataList.get(0);

        TokenRequest request = new TokenRequest("supervisor", "tek_supervisor");
        ExtentTestManager.getTest().info(request.toString());
        Response response = AuthenticatatedToken(request)
                .when().get(EndPoints.GET_USER_PROFILE.getValue())
                .then().statusCode(200)
                .extract().response();

        ExtentTestManager.getTest().info(response.asPrettyString());
        UserProfileResponse userProfileResponse = response.getBody().jsonPath().getObject("", UserProfileResponse.class);

        Assert.assertEquals(userProfileResponse.getUsername(), expectedData.get("username").toString());
        Assert.assertEquals(userProfileResponse.getFullName(), expectedData.get("full_name").toString());
        Assert.assertEquals(userProfileResponse.getAccountType().name(), expectedData.get("account_type"));
        Assert.assertEquals(userProfileResponse.getId(), Integer.parseInt(expectedData.get("id").toString()));

    }
}
