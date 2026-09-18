package stepdefinitions;

import org.openqa.selenium.WebDriver;
import pages.*;

/**
 * Simple shared "world" object so different step definition methods
 * (Given/When/Then) can pass page objects to each other within one scenario.
 * Cucumber creates one instance of each stepdefinitions class per scenario,
 * so a single injected TestContext instance ties them together.
 */
public class TestContext {

    public WebDriver driver;
    public static final String BASE_URL = "https://www.saucedemo.com/";

    public LoginPage loginPage;
    public InventoryPage inventoryPage;
    public CartPage cartPage;
    public CheckoutStepOnePage checkoutStepOnePage;
    public CheckoutStepTwoPage checkoutStepTwoPage;
    public CheckoutCompletePage checkoutCompletePage;
}
