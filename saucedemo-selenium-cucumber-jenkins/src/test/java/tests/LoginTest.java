package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InventoryPage;

public class LoginTest extends BaseTest {

    @Test
    public void validLoginRedirectsToInventory() {
        InventoryPage inventoryPage = loginPage.loginAs(STANDARD_USER, PASSWORD);
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"));
        Assert.assertTrue(inventoryPage.getProductCount() > 0);
    }

    @Test
    public void lockedOutUserShowsError() {
        loginPage.attemptLogin(LOCKED_USER, PASSWORD);
        Assert.assertTrue(loginPage.isErrorDisplayed());
        Assert.assertTrue(loginPage.getErrorMessage().contains("locked out"));
    }

    @Test
    public void invalidPasswordShowsError() {
        loginPage.attemptLogin(STANDARD_USER, "wrong_password");
        Assert.assertTrue(loginPage.isErrorDisplayed());
        Assert.assertTrue(loginPage.getErrorMessage().contains("do not match"));
    }

    @Test
    public void emptyUsernameShowsError() {
        loginPage.attemptLogin("", PASSWORD);
        Assert.assertTrue(loginPage.isErrorDisplayed());
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username is required"));
    }

    @Test
    public void emptyPasswordShowsError() {
        loginPage.attemptLogin(STANDARD_USER, "");
        Assert.assertTrue(loginPage.isErrorDisplayed());
        Assert.assertTrue(loginPage.getErrorMessage().contains("Password is required"));
    }

    @Test
    public void emptyCredentialsShowsError() {
        loginPage.attemptLogin("", "");
        Assert.assertTrue(loginPage.isErrorDisplayed());
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username is required"));
    }
}
