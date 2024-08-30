package playground;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class MyFirstTest {
    WebDriver driver;
    @BeforeSuite
    public void navigateToPage(){
        driver = new ChromeDriver();
        driver.get("https://www.facebook.com/");
    }

    @AfterSuite
    public void quitBrowser(){
        driver.quit();
    }
    @BeforeMethod
    public void runBeforeTest(){
        System.out.println("Run Before each test====");

    }

    @Test
    public void myFirstMethod(){
        System.out.println("Hello, This is the main tes (first test)");
    }

    @AfterMethod
    public void runAfterEachTestMethod(){
        System.out.println("Runs after each test to Clean up");
    }

    @Test
    public void secondTest(){
        System.out.println("This is the second test");
    }
}
