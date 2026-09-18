package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.LoginPage;

public class BaseTest {

    protected WebDriver driver;
    protected LoginPage loginPage;
    protected static final String BASE_URL = "https://www.saucedemo.com/";
    protected static final String STANDARD_USER = "standard_user";
    protected static final String LOCKED_USER = "locked_out_user";
    protected static final String PASSWORD = "secret_sauce";

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        // Runs headless automatically on Jenkins/CI (no display available);
        // runs with a visible browser on your local machine.
        if (System.getenv("JENKINS_URL") != null || "true".equalsIgnoreCase(System.getenv("HEADLESS"))) {
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--window-size=1920,1080");
        }
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        loginPage.open(BASE_URL);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
