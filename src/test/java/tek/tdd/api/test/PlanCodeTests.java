package tek.tdd.api.test;

import com.aventstack.extentreports.service.ExtentTestManager;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import tek.tdd.api.models.EndPoints;
import tek.tdd.api.models.PlanCodeResponse;
import tek.tdd.api.models.TokenRequest;
import tek.tdd.base.ApiTestsBase;

import java.util.List;

public class PlanCodeTests extends ApiTestsBase {
    private static final Logger LOGGER = LogManager.getLogger(PlanCodeTests.class);

    @Test
    public void validatePlanCodes(){
        TokenRequest tokenRequest = new TokenRequest("supervisor", "tek_supervisor");

        String token = getDefaultRequest()
                .body(tokenRequest)
                .when()
                .post(EndPoints.TOKEN.getValue())
                .then().statusCode(200)
                .extract()
                .response()
                .jsonPath().getString("token");

        Response response = getDefaultRequest()
                .header("Authorization", "Bearer " + token)
                .when()
                .get(EndPoints.GET_ALL_PLAN_CODE.getValue())
                .then()
                .statusCode(200)
                .extract()
                .response();

        ExtentTestManager.getTest().info(response.asPrettyString());
        LOGGER.info(response.asPrettyString());

        List<PlanCodeResponse> planCodeResponseList = response.body().jsonPath().getList("", PlanCodeResponse.class);
        Assert.assertNotNull(planCodeResponseList);
        Assert.assertEquals(planCodeResponseList.size(), 4);
        for(PlanCodeResponse planCode : planCodeResponseList){
            Assert.assertFalse(planCode.isPlanExpired());
        }
    }
}
