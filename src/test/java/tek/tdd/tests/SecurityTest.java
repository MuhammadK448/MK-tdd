package tek.tdd.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import tek.tdd.base.UIBaseClass;

public class SecurityTest extends UIBaseClass {
    /*
    User Story 1
    Navigate to sign in page and sing in with valid username and password.
    Validate user successfully signed in.
     */
    @Test
    public void validateSingIn() {
        clickOnElement(homePage.signInLink);
        sendText(signInPage.emailInput, "mohammad2536@gmail.com");
        sendText(signInPage.passwordInput, "Password@123");
        clickOnElement(signInPage.loginButton);
        boolean isDisplayed = isElementDisplayed(homePage.accountLink);

        Assert.assertTrue(isDisplayed, "Looking for account Link to be displayed after login");
    }
}
