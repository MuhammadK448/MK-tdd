package tek.tdd.base;

import io.restassured.RestAssured;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

/**
 * Read Config File
 * Open Browser
 * Quit Browser
 * Encapsulate instance of WebDriver
 */
public abstract class  BaseSetup {
    private static final Logger LOGGER = LogManager.getLogger(BaseSetup.class);
    private static WebDriver driver;
    private final Properties properties;
    protected static final long WAIT_IN_SECONDS = 20;

    public BaseSetup(){
        //Reading config files and loading to properties
        String configFilePath = System.getProperty("user.dir")
                + "/src/test/resources/configs/dev-config.properties";
        try{
            LOGGER.debug("Reading config file from path {}", configFilePath);
            InputStream inputStream = new FileInputStream(configFilePath);
            properties = new Properties();
            properties.load(inputStream);
            //Get API Base URL and setup RestAssured
            String baseURL = properties.getProperty("api.url");
            RestAssured.baseURI = baseURL;
        }catch (IOException ioException){
            LOGGER.error("Config file error with message {}", ioException.getMessage());
            throw new RuntimeException("Config file error with message " + ioException.getMessage());
        }
    }

    public void setupBrowser(){
        String url = properties.getProperty("ui.url");
        String browserType = properties.getProperty("ui.browser");
        boolean isHeadless = Boolean.parseBoolean(properties.getProperty("ui.browser.headless"));
        LOGGER.info("Opening on {} browser", browserType);
        LOGGER.info("Is headless On {}", isHeadless);

        switch (browserType.toLowerCase()){
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                if (isHeadless) chromeOptions.addArguments("--headless");
                driver = new ChromeDriver(chromeOptions);
                break;
            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                if(isHeadless) edgeOptions.addArguments("--headless");
                driver = new EdgeDriver();
                break;
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if(isHeadless) firefoxOptions.addArguments("--headless");
                driver = new FirefoxDriver();
                break;
            default:
                throw new RuntimeException("Wrong Browser type selected. Please choose from chrome, firefox, or edge");
        }

        LOGGER.info("Navigating to URL {}", url);
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(WAIT_IN_SECONDS));
    }

    public void quitBrowser(){
        if(driver != null){
            LOGGER.info("Quiting the Browser");
            driver.quit();
        }
    }
    //Encapsulation
    public WebDriver getDriver(){
        if(driver == null)
            throw new RuntimeException("Driver is null");
        return driver;
    }

    public String getProperty(String key){
        return properties.getProperty(key);
    }
}
