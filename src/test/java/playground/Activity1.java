package playground;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class Activity1 {
    WebDriver driver;

    @BeforeMethod
    public void setupTest(){
        driver = new ChromeDriver();
        driver.get("https://www.facebook.com/");
    }

    @Test
    public void testFacebookTitle(){
        String titleTxt = driver.getTitle();
        System.out.println("Page Title {}" +  titleTxt);
        Assert.assertEquals(titleTxt, "Facebook - log in or sign up");
    }

    @AfterMethod
    public void cleanupAfterTest(){
        driver.quit();
    }

    @BeforeClass
    public void runBeforeEachClass(){
        System.out.println("This runs once before each class");
    }

    @AfterClass
    public void runAfterEachClass(){
        System.out.println("This runs once After each class");
    }
}
