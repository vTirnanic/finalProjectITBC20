package Test;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {
    String invPageURL = "https://www.saucedemo.com/inventory.html";
    String thanksM = "Thank you for your order!";
    String checkOM = "Checkout: Complete!";

    @Test
    public void userCanOpenCartFromInventoryPage() {
        logIn();
        inventoryPage.clickOnCartIcon();

        Assert.assertTrue(cartPage.cartIcon.isDisplayed());
        Assert.assertFalse(cartPage.elementIsDisplayed(cartPage.cartBadge));
        Assert.assertTrue(cartPage.checkOutButton.isDisplayed());

        driver.navigate().back();
        inventoryPage.clickOnAtcButton(1);
        inventoryPage.clickOnCartIcon();

        Assert.assertTrue(cartPage.cartIcon.isDisplayed());
        Assert.assertTrue(cartPage.cartBadge.isDisplayed());
        Assert.assertTrue(cartPage.quantityCell.isDisplayed());
        Assert.assertTrue(cartPage.checkOutButton.isDisplayed());
    }

    @Test
    public void userCanOpenCartFromProductPage() {
        logIn();
        inventoryPage.openProductPageByTitleNumber(0);
        productPage.clickOnCartIcon();

        Assert.assertTrue(cartPage.cartIcon.isDisplayed());
        Assert.assertFalse(elementIsDisplayed(cartPage.cartBadge));
        Assert.assertTrue(cartPage.checkOutButton.isDisplayed());

        cartPage.clickOnConShopButton();
        inventoryPage.openProductPageByTitleNumber(0);
        productPage.clickOnAtcButton();
        productPage.clickOnCartIcon();

        Assert.assertTrue(cartPage.cartIcon.isDisplayed());
        Assert.assertTrue(cartPage.cartBadge.isDisplayed());
        Assert.assertTrue(cartPage.quantityCell.isDisplayed());
        Assert.assertTrue(cartPage.checkOutButton.isDisplayed());
    }

    @Test
    public void userCanContinueShopFromCart() {
        logIn();
        inventoryPage.clickOnCartIcon();
        cartPage.clickOnConShopButton();
        inventoryPage.clickOnAtcButton(2);
        inventoryPage.clickOnCartIcon();
        cartPage.clickOnConShopButton();

        Assert.assertEquals(driver.getCurrentUrl(), invPageURL);
        Assert.assertEquals(inventoryPage.cartBadge.getText(), "1");
    }

    @Test
    public void userCanAddOneItemToCart() {
        logIn();
        Assert.assertFalse(elementIsDisplayed(inventoryPage.cartBadge));
        inventoryPage.clickOnAtcButton(2);
        inventoryPage.clickOnCartIcon();

        Assert.assertTrue(cartPage.cartIcon.isDisplayed());
        Assert.assertTrue(cartPage.cartBadge.isDisplayed());
        Assert.assertEquals(cartPage.cartBadge.getText(), "1");
        Assert.assertTrue(cartPage.cartItem.isDisplayed());
    }

    @Test
    public void userCanAddMultipleItemsToCart() {
        logIn();
        inventoryPage.clickOnAtcButton(2);
        inventoryPage.clickOnAtcButton(1);
        scrollToElement(inventoryPage.itemTitles.get(4));
        inventoryPage.clickOnAtcButton(3);
        inventoryPage.clickOnCartIcon();

        Assert.assertTrue(cartPage.cartIcon.isDisplayed());
        Assert.assertTrue(cartPage.cartBadge.isDisplayed());
        Assert.assertEquals(cartPage.cartBadge.getText(), "3");
        Assert.assertEquals(cartPage.cartItems.size(), 3);
    }

    @Test
    public void userCanRemoveOneItemFromCart() {
        logIn();
        inventoryPage.clickOnAtcButton(2);
        inventoryPage.clickOnCartIcon();
        cartPage.clickOnRemoveButton(0);

        Assert.assertTrue(cartPage.cartIcon.isDisplayed());
        Assert.assertFalse(elementIsDisplayed(cartPage.cartBadge));
    }

    @Test
    public void userCanRemoveMultipleItemsFromCart() {
        logIn();
        inventoryPage.clickOnAtcButton(2);
        inventoryPage.clickOnAtcButton(0);
        scrollToElement(inventoryPage.itemTitles.get(4));
        inventoryPage.clickOnAtcButton(3);
        inventoryPage.clickOnCartIcon();
        cartPage.clickOnRemoveButton(0);
        cartPage.clickOnRemoveButton(0);
        cartPage.clickOnRemoveButton(0);

        Assert.assertTrue(cartPage.cartIcon.isDisplayed());
        Assert.assertFalse(elementIsDisplayed(cartPage.cartBadge));
    }

    @Test
    public void userCanAccessToProductDetailsFromCart() {
        logIn();
        inventoryPage.clickOnAtcButton(1);
        inventoryPage.clickOnCartIcon();
        cartPage.clickOnItemTitle(0);

        Assert.assertTrue(productPage.itemTitle.isDisplayed());
        Assert.assertTrue(productPage.itemDescription.isDisplayed());
        Assert.assertTrue(productPage.itemImage.isDisplayed());
        Assert.assertTrue(productPage.itemPrice.isDisplayed());
    }

    @Test
    public void userCanPurchaseOneItem() {
        String firstName = excelReader.getStringData("Sheet1", 1, 4);
        String lastName = excelReader.getStringData("Sheet1", 1, 5);
        String postalCode = String.valueOf(excelReader.getIntegerData("Sheet1", 1, 6));

        logIn();
        inventoryPage.openProductPageByTitleNumber(1);
        productPage.clickOnAtcButton();
        productPage.clickOnCartIcon();
        cartPage.clickOnCheckOutButton();
        checkout1Page.inputFirstName(firstName);
        checkout1Page.inputLastName(lastName);
        checkout1Page.inputPostalCode(postalCode);
        checkout1Page.clickOnContinueButton();
        checkout2Page.clickOnFinishButton();

        Assert.assertTrue(checkout3Page.backHomeButton.isDisplayed());
        Assert.assertEquals(checkout3Page.thanksMessage.getText(), thanksM);
        Assert.assertEquals(checkout3Page.checkOutMessage.getText(), checkOM);
    }

    @Test
    public void userCanPurchaseMultipleItems() {
        String firstName = excelReader.getStringData("Sheet1", 1, 4);
        String lastName = excelReader.getStringData("Sheet1", 1, 5);
        String postalCode = String.valueOf(excelReader.getIntegerData("Sheet1", 1, 6));

        logIn();
        inventoryPage.clickOnAtcButton(0);
        inventoryPage.clickOnAtcButton(2);
        inventoryPage.openProductPageByTitleNumber(1);
        productPage.clickOnAtcButton();
        productPage.clickOnCartIcon();
        cartPage.clickOnCheckOutButton();
        checkout1Page.inputFirstName(firstName);
        checkout1Page.inputLastName(lastName);
        checkout1Page.inputPostalCode(postalCode);
        checkout1Page.clickOnContinueButton();
        checkout2Page.clickOnFinishButton();

        Assert.assertTrue(checkout3Page.backHomeButton.isDisplayed());
        Assert.assertEquals(checkout3Page.thanksMessage.getText(), thanksM);
        Assert.assertEquals(checkout3Page.checkOutMessage.getText(), checkOM);
    }

    @Test
    public void userCanCancelCheckoutProcess() throws InterruptedException {
        String firstName = excelReader.getStringData("Sheet1", 1, 4);
        String lastName = excelReader.getStringData("Sheet1", 1, 5);
        String postalCode = String.valueOf(excelReader.getIntegerData("Sheet1", 1, 6));

        logIn();
        inventoryPage.clickOnAtcButton(1);
        inventoryPage.clickOnAtcButton(0);
        inventoryPage.openProductPageByTitleNumber(2);
        productPage.clickOnAtcButton();
        productPage.clickOnCartIcon();
        cartPage.clickOnCheckOutButton();
        checkout1Page.inputFirstName(firstName);
        checkout1Page.inputLastName(lastName);
        checkout1Page.inputPostalCode(postalCode);
        checkout1Page.clickOnContinueButton();
        scrollToElement(checkout2Page.cancelButton);
        checkout2Page.clickOnCancelButton();

        Assert.assertEquals(driver.getCurrentUrl(), invPageURL);
        Assert.assertEquals(inventoryPage.cartBadge.getText(), "3");
    }

    @Test
    public void cartBadgeShowsCorrectValue() {
        logIn();
        inventoryPage.clickOnAtcButton(0);
        inventoryPage.clickOnCartIcon();

        Assert.assertEquals(cartPage.cartItems.size(), 1);

        cartPage.clickOnConShopButton();
        inventoryPage.clickOnAtcButton(0);
        inventoryPage.clickOnCartIcon();

        Assert.assertEquals(cartPage.cartItems.size(), 2);

        cartPage.clickOnConShopButton();
        inventoryPage.clickOnAtcButton(0);
        inventoryPage.clickOnCartIcon();

        Assert.assertEquals(cartPage.cartItems.size(), 3);

        cartPage.clickOnConShopButton();
        inventoryPage.clickOnAtcButton(0);
        inventoryPage.clickOnCartIcon();

        Assert.assertEquals(cartPage.cartItems.size(), 4);

        cartPage.clickOnConShopButton();
        inventoryPage.clickOnAtcButton(0);
        inventoryPage.clickOnCartIcon();

        Assert.assertEquals(cartPage.cartItems.size(), 5);

        cartPage.clickOnConShopButton();
        inventoryPage.clickOnAtcButton(0);
        inventoryPage.clickOnCartIcon();

        Assert.assertEquals(cartPage.cartItems.size(), 6);

        cartPage.clickOnRemoveButton(0);

        Assert.assertEquals(cartPage.cartItems.size(), 5);

        cartPage.clickOnRemoveButton(0);

        Assert.assertEquals(cartPage.cartItems.size(), 4);

        cartPage.clickOnRemoveButton(0);

        Assert.assertEquals(cartPage.cartItems.size(), 3);

        cartPage.clickOnRemoveButton(0);

        Assert.assertEquals(cartPage.cartItems.size(), 2);

        cartPage.clickOnRemoveButton(0);

        Assert.assertEquals(cartPage.cartItems.size(), 1);

        cartPage.clickOnRemoveButton(0);

        Assert.assertFalse(elementIsDisplayed(cartPage.cartBadge));

        //TODO: Test - sum of prices
    }
}
