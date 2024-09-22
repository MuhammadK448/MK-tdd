package tek.tdd.api.test;

import com.aventstack.extentreports.service.ExtentTestManager;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tek.tdd.api.models.EndPoints;
import tek.tdd.api.models.TokenRequest;
import tek.tdd.api.models.UserProfileResponse;
import tek.tdd.base.ApiTestsBase;
import tek.tdd.utility.DatabaseUtility;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class UserProfileTest extends ApiTestsBase {

    @Test(dataProvider = "users")
    public void validateUserProfile(TokenRequest request) throws SQLException {
        DatabaseUtility databaseUtility = new DatabaseUtility();
        String query = "SELECT id, username, full_name, account_type From user where username = '{username}';"
                .replace("{username}", request.getUsername());
        List<Map<String, Object>> expectedDataList = databaseUtility.getResultFromDBQuery(query);
        Assert.assertFalse(expectedDataList.isEmpty());
        Map<String, Object> expectedData = expectedDataList.get(0);

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

    @DataProvider(name = "users")
    private TokenRequest[] users(){
        return new TokenRequest[]{
                new TokenRequest("supervisor", "tek_supervisor"),
                new TokenRequest("operator_readonly", "Tek4u2024"),
                new TokenRequest("PassAt1234", "Pass@1234")
        };
    }
}
