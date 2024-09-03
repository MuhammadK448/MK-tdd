package tek.tdd.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import tek.tdd.base.UIBaseClass;
import tek.tdd.page.HomePage;

public class AccountProfileTest extends UIBaseClass {
    /* Activity
    Story 5: navigate to retail app and login with your credential
    Navigate to account page and update Name and phone number.
    Validate your phone number and Name Updated.
    Validate Success toast message displayed.
     */

    @Test
    public void updatePersonalInfoTest() throws InterruptedException {
        validCredentialSignIn();
        clickOnElement(homePage.accountLink);

        accountProfilePage.updateNameAndPhone("Ahmad", "2109876543");
        boolean isToastDisplayed = isElementEnabled(homePage.toastBody);
        Assert.assertTrue(isToastDisplayed, "Toast should display");
        String actualUserName = getElementText(accountProfilePage.accountUserNameText);
        Assert.assertEquals(actualUserName, "Ahmad");
        Thread.sleep(6000);

        //Reset to original name and phone number
        accountProfilePage.updateNameAndPhone("Abdul" , "2103245678");
        Thread.sleep(1000);
        String actualUserNameReset = getElementText(accountProfilePage.accountUserNameText);
        Assert.assertEquals(actualUserNameReset, "Abdul");

    }
}
