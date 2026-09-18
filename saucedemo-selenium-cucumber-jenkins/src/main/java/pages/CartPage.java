package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class CartPage extends BasePage {

    private final By cartItems = By.className("cart_item");
    private final By removeButtons = By.cssSelector("button[data-test^='remove']");
    private final By checkoutButton = By.id("checkout");
    private final By cartBadge = By.className("shopping_cart_badge");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public int getItemCount() {
        return driver.findElements(cartItems).size();
    }

    public void removeFirstItem() {
        List<org.openqa.selenium.WebElement> buttons = driver.findElements(removeButtons);
        if (!buttons.isEmpty()) {
            buttons.get(0).click();
        }
    }

    public String getCartBadgeCount() {
        return isDisplayed(cartBadge) ? textOf(cartBadge) : "0";
    }

    public CheckoutStepOnePage proceedToCheckout() {
        click(checkoutButton);
        return new CheckoutStepOnePage(driver);
    }
}
