package Test;

import Base.BaseTest;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class SidebarTest extends BaseTest {

    String homePageURL = "https://www.saucedemo.com/";
    String aboutURL = "https://www.saucedemo.com/about.html";

    @Test
    public void userCanOpenAndCloseSidebarOnInventoryPage() {
        logIn();
        inventoryPage.clickOnHamburger();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(inventoryPage.sbCloseIcon));

        Assert.assertTrue(inventoryPage.sbCloseIcon.isDisplayed());

        inventoryPage.clickOnSbCloseIcon();

        Assert.assertFalse(elementIsDisplayed(inventoryPage.sideBar));
    }

    @Test
    public void userCanAccessAllLinksInSidebarOnInventoryPage() {
        logIn();
        inventoryPage.clickOnAtcButton(0);

        Assert.assertTrue(inventoryPage.cartBadge.isDisplayed());

        inventoryPage.clickOnHamburger();
        inventoryPage.clickOnAllItems();

        Assert.assertEquals(inventoryPage.inventoryItems.size(),6);

        inventoryPage.clickOnAbout();

        Assert.assertEquals(driver.getCurrentUrl(), aboutURL);

        driver.navigate().back();
        inventoryPage.clickOnHamburger();
        inventoryPage.clickOnReset();

        Assert.assertFalse(elementIsDisplayed(inventoryPage.cartBadge));

        inventoryPage.clickOnLogoutLink();

        Assert.assertEquals(driver.getCurrentUrl(), homePageURL);
        Assert.assertTrue(homepagePage.loginButton.isDisplayed());
    }

    @Test
    public void userCanOpenAndCloseSidebarOnProductPage() throws InterruptedException {
        logIn();
        inventoryPage.openProductPageByTitleNumber(1);
        productPage.clickOnHamburger();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(productPage.sbCloseIcon));

        Assert.assertTrue(productPage.sbCloseIcon.isDisplayed());

        productPage.clickOnSbCloseIcon();

        Assert.assertFalse(elementIsDisplayed(productPage.sideBar));
    }

    @Test
    public void userCanAccessAllLinksInSidebarOnProductPage() {
        logIn();
        inventoryPage.openProductPageByTitleNumber(2);
        productPage.clickOnAtcButton();

        Assert.assertTrue(productPage.cartBadge.isDisplayed());

        productPage.clickOnHamburger();
        productPage.clickOnAllItems();
        inventoryPage.openProductPageByTitleNumber(1);
        productPage.clickOnHamburger();
        productPage.clickOnAbout();

        Assert.assertEquals(driver.getCurrentUrl(), aboutURL);

        driver.navigate().back();
        productPage.clickOnHamburger();
        productPage.clickOnReset();

        Assert.assertFalse(elementIsDisplayed(productPage.cartBadge));

        productPage.clickOnLogoutLink();

        Assert.assertEquals(driver.getCurrentUrl(), homePageURL);
        Assert.assertTrue(homepagePage.loginButton.isDisplayed());
    }
}
