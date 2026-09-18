package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.InventoryPage;

public class CartTest extends BaseTest {

    @Test
    public void addSingleItemUpdatesBadge() {
        InventoryPage inventoryPage = loginPage.loginAs(STANDARD_USER, PASSWORD);
        inventoryPage.addItemToCartByName("Sauce Labs Backpack");
        Assert.assertEquals(inventoryPage.getCartBadgeCount(), "1");
    }

    @Test
    public void addMultipleItemsUpdatesBadge() {
        InventoryPage inventoryPage = loginPage.loginAs(STANDARD_USER, PASSWORD);
        inventoryPage.addItemToCartByName("Sauce Labs Backpack");
        inventoryPage.addItemToCartByName("Sauce Labs Bike Light");
        inventoryPage.addItemToCartByName("Sauce Labs Bolt T-Shirt");
        Assert.assertEquals(inventoryPage.getCartBadgeCount(), "3");
    }

    @Test
    public void removeItemFromCartPage() {
        InventoryPage inventoryPage = loginPage.loginAs(STANDARD_USER, PASSWORD);
        inventoryPage.addItemToCartByName("Sauce Labs Backpack");
        CartPage cartPage = inventoryPage.goToCart();
        Assert.assertEquals(cartPage.getItemCount(), 1);
        cartPage.removeFirstItem();
        Assert.assertEquals(cartPage.getItemCount(), 0);
    }

    @Test
    public void cartPersistsAcrossNavigation() {
        InventoryPage inventoryPage = loginPage.loginAs(STANDARD_USER, PASSWORD);
        inventoryPage.addItemToCartByName("Sauce Labs Backpack");
        driver.navigate().refresh();
        Assert.assertEquals(inventoryPage.getCartBadgeCount(), "1");
    }

    @Test
    public void checkoutWithEmptyCart() {
        InventoryPage inventoryPage = loginPage.loginAs(STANDARD_USER, PASSWORD);
        CartPage cartPage = inventoryPage.goToCart();
        Assert.assertEquals(cartPage.getItemCount(), 0);
        Assert.assertNotNull(cartPage.proceedToCheckout());
    }
}
