package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutStepOnePage extends BasePage {

    private final By firstNameField = By.id("first-name");
    private final By lastNameField = By.id("last-name");
    private final By zipField = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By cancelButton = By.id("cancel");
    private final By errorMessage = By.cssSelector("h3[data-test='error']");

    public CheckoutStepOnePage(WebDriver driver) {
        super(driver);
    }

    public void fillInfo(String firstName, String lastName, String zip) {
        type(firstNameField, firstName);
        type(lastNameField, lastName);
        type(zipField, zip);
    }

    public CheckoutStepTwoPage clickContinue() {
        click(continueButton);
        return new CheckoutStepTwoPage(driver);
    }

    public String getErrorMessage() {
        return textOf(errorMessage);
    }

    public InventoryPage cancel() {
        click(cancelButton);
        return new InventoryPage(driver);
    }
}
