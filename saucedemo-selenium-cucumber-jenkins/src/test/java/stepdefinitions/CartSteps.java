package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class CartSteps {

    private final TestContext context;

    public CartSteps(TestContext context) {
        this.context = context;
    }

    @When("the user adds {string} to the cart")
    @And("the user has added {string} to the cart")
    public void theUserAddsItemToCart(String itemName) {
        context.inventoryPage.addItemToCartByName(itemName);
    }

    @Then("the cart badge should show {string}")
    public void theCartBadgeShouldShow(String expectedCount) {
        Assert.assertEquals(context.inventoryPage.getCartBadgeCount(), expectedCount);
    }

    @And("the user goes to the cart page")
    public void theUserGoesToTheCartPage() {
        context.cartPage = context.inventoryPage.goToCart();
    }

    @And("the user removes the first item from the cart")
    public void theUserRemovesTheFirstItem() {
        context.cartPage.removeFirstItem();
    }

    @Then("the cart should contain {int} items")
    public void theCartShouldContainItems(int expectedCount) {
        Assert.assertEquals(context.cartPage.getItemCount(), expectedCount);
    }
}
