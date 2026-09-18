package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.LoginPage;

public class Hooks {

    private final TestContext context;

    // PicoContainer injects the SAME TestContext instance into every
    // step definition class used in a scenario.
    public Hooks(TestContext context) {
        this.context = context;
    }

    @Before
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
        context.driver = new ChromeDriver(options);
        context.driver.manage().window().maximize();
        context.loginPage = new LoginPage(context.driver);
        context.loginPage.open(TestContext.BASE_URL);
    }

    @After
    public void tearDown() {
        if (context.driver != null) {
            context.driver.quit();
        }
    }
}
