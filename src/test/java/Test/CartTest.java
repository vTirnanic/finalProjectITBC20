package Test;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class CartTest extends BaseTest {

    String invPageURL = "https://www.saucedemo.com/inventory.html";

    @BeforeMethod
    public void pageSetUp() {
        driver.navigate().to("https://www.saucedemo.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    public void userCanOpenCartFromInvPage() {
        logging();
        inventoryPage.clickOnCartIcon();
        driver.navigate().back();
        inventoryPage.clickOnAtcButton(1);
        inventoryPage.clickOnCartIcon();
        Assert.assertTrue(cartPage.cartIcon.isDisplayed());
        Assert.assertTrue(cartPage.cartBadge.isDisplayed());
        Assert.assertTrue(cartPage.quantityCell.isDisplayed());
        Assert.assertTrue(cartPage.checkOutButton.isDisplayed());
    }

    @Test
    public void userCanContinueShoppingFromCart() {
        logging();
        inventoryPage.clickOnCartIcon();
        cartPage.clickOnConShopButton();
        inventoryPage.clickOnAtcButton(2);
        inventoryPage.clickOnCartIcon();
        cartPage.clickOnConShopButton();
        Assert.assertEquals(driver.getCurrentUrl(),invPageURL);
        Assert.assertEquals(inventoryPage.cartBadge.getText(),"1");
    }

    @Test
    public void userCanAddOneItemtoCart() {
        logging();
        inventoryPage.clickOnAtcButton(2);
        inventoryPage.clickOnCartIcon();
        Assert.assertTrue(cartPage.cartIcon.isDisplayed());
        Assert.assertTrue(cartPage.cartBadge.isDisplayed());
        Assert.assertEquals(cartPage.cartBadge.getText(),"1");
        Assert.assertTrue(cartPage.cartItem.isDisplayed());
    }

    @Test
    public void userCanAddMultipleItemstoCart() {
        logging();
        inventoryPage.clickOnAtcButton(2);
        inventoryPage.clickOnAtcButton(1);
        scrollToElement(inventoryPage.itemTitles.get(4));
        inventoryPage.clickOnAtcButton(3);
        inventoryPage.clickOnCartIcon();
        Assert.assertTrue(cartPage.cartIcon.isDisplayed());
        Assert.assertTrue(cartPage.cartBadge.isDisplayed());
        Assert.assertEquals(cartPage.cartBadge.getText(),"3");
        Assert.assertEquals(cartPage.cartItems.size(),3);
    }




}
