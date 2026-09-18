package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutStepTwoPage extends BasePage {

    private final By itemTotal = By.className("summary_subtotal_label");
    private final By tax = By.className("summary_tax_label");
    private final By total = By.className("summary_total_label");
    private final By finishButton = By.id("finish");
    private final By cancelButton = By.id("cancel");

    public CheckoutStepTwoPage(WebDriver driver) {
        super(driver);
    }

    private double extractAmount(String text) {
        return Double.parseDouble(text.replaceAll("[^0-9.]", ""));
    }

    public double getItemTotal() {
        return extractAmount(textOf(itemTotal));
    }

    public double getTax() {
        return extractAmount(textOf(tax));
    }

    public double getTotal() {
        return extractAmount(textOf(total));
    }

    public CheckoutCompletePage clickFinish() {
        click(finishButton);
        return new CheckoutCompletePage(driver);
    }

    public InventoryPage cancel() {
        click(cancelButton);
        return new InventoryPage(driver);
    }
}
