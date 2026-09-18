package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class CheckoutTest extends BaseTest {

    private CartPage addItemAndGoToCart(InventoryPage inventoryPage, String... items) {
        for (String item : items) {
            inventoryPage.addItemToCartByName(item);
        }
        return inventoryPage.goToCart();
    }

    @Test
    public void completeCheckoutWithValidInfo() {
        InventoryPage inventoryPage = loginPage.loginAs(STANDARD_USER, PASSWORD);
        CartPage cartPage = addItemAndGoToCart(inventoryPage, "Sauce Labs Backpack");
        CheckoutStepOnePage stepOne = cartPage.proceedToCheckout();
        stepOne.fillInfo("John", "Doe", "411001");
        CheckoutStepTwoPage stepTwo = stepOne.clickContinue();
        CheckoutCompletePage complete = stepTwo.clickFinish();
        Assert.assertTrue(complete.getConfirmationText().contains("Thank you"));
    }

    @Test
    public void checkoutMissingFirstNameShowsError() {
        InventoryPage inventoryPage = loginPage.loginAs(STANDARD_USER, PASSWORD);
        CartPage cartPage = addItemAndGoToCart(inventoryPage, "Sauce Labs Backpack");
        CheckoutStepOnePage stepOne = cartPage.proceedToCheckout();
        stepOne.fillInfo("", "Doe", "411001");
        stepOne.clickContinue();
        Assert.assertTrue(stepOne.getErrorMessage().contains("First Name is required"));
    }

    @Test
    public void checkoutMissingZipShowsError() {
        InventoryPage inventoryPage = loginPage.loginAs(STANDARD_USER, PASSWORD);
        CartPage cartPage = addItemAndGoToCart(inventoryPage, "Sauce Labs Backpack");
        CheckoutStepOnePage stepOne = cartPage.proceedToCheckout();
        stepOne.fillInfo("John", "Doe", "");
        stepOne.clickContinue();
        Assert.assertTrue(stepOne.getErrorMessage().contains("Postal Code is required"));
    }

    @Test
    public void orderSummaryTotalsAreCorrect() {
        InventoryPage inventoryPage = loginPage.loginAs(STANDARD_USER, PASSWORD);
        CartPage cartPage = addItemAndGoToCart(inventoryPage, "Sauce Labs Backpack", "Sauce Labs Bike Light");
        CheckoutStepOnePage stepOne = cartPage.proceedToCheckout();
        stepOne.fillInfo("John", "Doe", "411001");
        CheckoutStepTwoPage stepTwo = stepOne.clickContinue();
        double expectedTotal = stepTwo.getItemTotal() + stepTwo.getTax();
        Assert.assertEquals(stepTwo.getTotal(), expectedTotal, 0.01);
    }

    @Test
    public void cancelFromOverviewReturnsToProducts() {
        InventoryPage inventoryPage = loginPage.loginAs(STANDARD_USER, PASSWORD);
        CartPage cartPage = addItemAndGoToCart(inventoryPage, "Sauce Labs Backpack");
        CheckoutStepOnePage stepOne = cartPage.proceedToCheckout();
        stepOne.fillInfo("John", "Doe", "411001");
        CheckoutStepTwoPage stepTwo = stepOne.clickContinue();
        InventoryPage backToInventory = stepTwo.cancel();
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"));
        Assert.assertEquals(backToInventory.getCartBadgeCount(), "1");
    }
}
