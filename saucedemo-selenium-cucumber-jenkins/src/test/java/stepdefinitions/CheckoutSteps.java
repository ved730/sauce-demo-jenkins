package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class CheckoutSteps {

    private final TestContext context;

    public CheckoutSteps(TestContext context) {
        this.context = context;
    }

    @And("the user proceeds to checkout")
    public void theUserProceedsToCheckout() {
        context.checkoutStepOnePage = context.cartPage.proceedToCheckout();
    }

    @When("the user enters checkout information {string} {string} {string}")
    public void theUserEntersCheckoutInformation(String firstName, String lastName, String zip) {
        context.checkoutStepOnePage.fillInfo(firstName, lastName, zip);
    }

    @And("the user continues to the overview page")
    public void theUserContinuesToOverview() {
        // clickContinue() navigates only if validation passes; on failure the
        // driver stays on step-one, so we keep a reference to step two only
        // when it is actually reached.
        try {
            context.checkoutStepTwoPage = context.checkoutStepOnePage.clickContinue();
        } catch (Exception ignored) {
            // validation error kept the user on step one
        }
    }

    @And("the user finishes the order")
    public void theUserFinishesTheOrder() {
        context.checkoutCompletePage = context.checkoutStepTwoPage.clickFinish();
    }

    @Then("the order confirmation should contain {string}")
    public void theOrderConfirmationShouldContain(String expectedText) {
        Assert.assertTrue(context.checkoutCompletePage.getConfirmationText().contains(expectedText));
    }

    @Then("a checkout error containing {string} should be displayed")
    public void aCheckoutErrorShouldBeDisplayed(String expectedText) {
        Assert.assertTrue(context.checkoutStepOnePage.getErrorMessage().contains(expectedText));
    }
}
