package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InventoryTest extends BaseTest {

    @Test
    public void sortByNameAToZ() {
        InventoryPage inventoryPage = loginPage.loginAs(STANDARD_USER, PASSWORD);
        inventoryPage.sortBy("Name (A to Z)");
        List<String> names = inventoryPage.getDisplayedItemNames();
        List<String> sorted = new ArrayList<>(names);
        Collections.sort(sorted);
        Assert.assertEquals(names, sorted);
    }

    @Test
    public void sortByPriceLowToHigh() {
        InventoryPage inventoryPage = loginPage.loginAs(STANDARD_USER, PASSWORD);
        inventoryPage.sortBy("Price (low to high)");
        List<Double> prices = inventoryPage.getDisplayedItemPrices();
        List<Double> sorted = new ArrayList<>(prices);
        Collections.sort(sorted);
        Assert.assertEquals(prices, sorted);
    }

    @Test
    public void inventoryShowsSixProducts() {
        InventoryPage inventoryPage = loginPage.loginAs(STANDARD_USER, PASSWORD);
        Assert.assertEquals(inventoryPage.getProductCount(), 6);
    }
}
