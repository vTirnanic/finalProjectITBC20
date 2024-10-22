package Test;

import Base.BaseTest;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class SidebarTest extends BaseTest {

    String homePageURL = "https://www.saucedemo.com/";
    String aboutURL = "https://saucelabs.com/";

    @BeforeMethod
    public void pageSetUp() {
        driver.navigate().to("https://www.saucedemo.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    public void userCanOpenAndCloseSbFromInventory() throws InterruptedException {
        logging();
        inventoryPage.clickOnHamburger();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(inventoryPage.sbCloseIcon));
        Assert.assertTrue(inventoryPage.sbCloseIcon.isDisplayed());

        inventoryPage.clickOnSbCloseIcon();
        boolean sidebarIsPresent = false;
        try {
            sidebarIsPresent = inventoryPage.sideBar.isDisplayed();
        } catch (Exception e) {
            System.out.println(e);
        }
        Assert.assertFalse(sidebarIsPresent);
    }

    @Test
    public void userCanAccessAllLinksInSidebar() {
        logging();
        inventoryPage.clickOnAtcButton(0);
        Assert.assertTrue(inventoryPage.cartBadge.isDisplayed());
        inventoryPage.clickOnHamburger();
        inventoryPage.clickOnAllItems();
        inventoryPage.clickOnAbout();
        Assert.assertEquals(driver.getCurrentUrl(),aboutURL);
        driver.navigate().back();
        inventoryPage.clickOnHamburger();
        inventoryPage.clickOnReset();

        boolean badgeIsPresent = false;
        try {
            badgeIsPresent = inventoryPage.cartBadge.isDisplayed();
        } catch (Exception e) {
            System.out.println(e);
        }
        Assert.assertFalse(badgeIsPresent);

        inventoryPage.clickOnLogoutLink();
        Assert.assertEquals(driver.getCurrentUrl(),homePageURL);
        Assert.assertTrue(homepagePage.loginButton.isDisplayed());
    }
}
