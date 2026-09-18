package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

public class InventoryPage extends BasePage {

    private final By inventoryItems = By.className("inventory_item");
    private final By itemNames = By.className("inventory_item_name");
    private final By itemPrices = By.className("inventory_item_price");
    private final By sortDropdown = By.className("product_sort_container");
    private final By cartBadge = By.className("shopping_cart_badge");
    private final By cartLink = By.className("shopping_cart_link");
    private final By burgerMenu = By.id("react-burger-menu-btn");
    private final By logoutLink = By.id("logout_sidebar_link");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public int getProductCount() {
        return driver.findElements(inventoryItems).size();
    }

    public void addItemToCartByName(String itemName) {
        String id = "add-to-cart-" + itemName.toLowerCase().replace(" ", "-");
        click(By.id(id));
    }

    public void sortBy(String visibleText) {
        Select select = new Select(waitVisible(sortDropdown));
        select.selectByVisibleText(visibleText);
    }

    public List<String> getDisplayedItemNames() {
        return driver.findElements(itemNames).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<Double> getDisplayedItemPrices() {
        return driver.findElements(itemPrices).stream()
                .map(e -> Double.parseDouble(e.getText().replace("$", "")))
                .collect(Collectors.toList());
    }

    public String getCartBadgeCount() {
        return isDisplayed(cartBadge) ? textOf(cartBadge) : "0";
    }

    public CartPage goToCart() {
        click(cartLink);
        return new CartPage(driver);
    }

    public LoginPage logout() {
        click(burgerMenu);
        click(logoutLink);
        return new LoginPage(driver);
    }
}
