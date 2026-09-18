package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class LoginSteps {

    private final TestContext context;

    public LoginSteps(TestContext context) {
        this.context = context;
    }

    @Given("the user is on the SauceDemo login page")
    public void theUserIsOnTheLoginPage() {
        // Hooks.setUp() already navigates to the login page before every scenario.
        Assert.assertTrue(context.driver.getCurrentUrl().contains("saucedemo.com"));
    }

    @When("the user logs in with username {string} and password {string}")
    public void theUserLogsInWith(String username, String password) {
        context.loginPage.attemptLogin(username, password);
    }

    @Then("the user should be redirected to the inventory page")
    public void theUserShouldBeOnInventoryPage() {
        Assert.assertTrue(context.driver.getCurrentUrl().contains("inventory.html"));
    }

    @Then("an error message containing {string} should be displayed")
    public void anErrorMessageShouldBeDisplayed(String expectedText) {
        Assert.assertTrue(context.loginPage.isErrorDisplayed());
        Assert.assertTrue(context.loginPage.getErrorMessage().contains(expectedText));
    }

    @Given("the user is logged in as {string}")
    public void theUserIsLoggedInAs(String username) {
        context.inventoryPage = context.loginPage.loginAs(username, "secret_sauce");
    }
}
