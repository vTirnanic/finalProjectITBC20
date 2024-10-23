package Test;

import Base.BaseTest;
import Pages.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class CartTest extends BaseTest {

    String invPageURL = "https://www.saucedemo.com/inventory.html";
    String thanksM = "Thank you for your order!";
    String checkOM = "Checkout: Complete!";

    @BeforeMethod
    public void pageSetUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://www.saucedemo.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        homepagePage = new HomepagePage();
        inventoryPage = new InventoryPage();
        productPage = new ProductPage();
        cartPage = new CartPage();
        checkout1Page = new Checkout1Page();
        checkout2Page = new Checkout2Page();
        checkout3Page = new Checkout3Page();
    }

    @Test
    public void userCanOpenCartFromInventoryPage() {
        logging();
        inventoryPage.clickOnCartIcon();

        Assert.assertTrue(cartPage.cartIcon.isDisplayed());
        Assert.assertFalse(cartPage.cartBadgeIsPresent());
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
        logging();
        inventoryPage.openProductPageByTitleNumber(0);
        productPage.clickOnCartIcon();

        Assert.assertTrue(cartPage.cartIcon.isDisplayed());
        Assert.assertFalse(cartPage.cartBadgeIsPresent());
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
        logging();
        inventoryPage.clickOnCartIcon();
        cartPage.clickOnConShopButton();
        inventoryPage.clickOnAtcButton(2);
        inventoryPage.clickOnCartIcon();
        cartPage.clickOnConShopButton();

        Assert.assertEquals(driver.getCurrentUrl(), invPageURL);
        Assert.assertEquals(inventoryPage.cartBadge.getText(), "1");
    }

    @Test
    public void userCanAddOneItemToCart() throws InterruptedException {
        logging();
        Assert.assertFalse(inventoryPage.badgeIsPresent());
        inventoryPage.clickOnAtcButton(2);
        inventoryPage.clickOnCartIcon();

        Assert.assertTrue(cartPage.cartIcon.isDisplayed());
        Assert.assertTrue(cartPage.cartBadge.isDisplayed());
        Assert.assertEquals(cartPage.cartBadge.getText(), "1");
        Assert.assertTrue(cartPage.cartItem.isDisplayed());
    }

    @Test
    public void userCanAddMultipleItemsToCart() {
        logging();
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
        logging();
        inventoryPage.clickOnAtcButton(2);
        inventoryPage.clickOnCartIcon();
        cartPage.clickOnRemoveButton(0);

        Assert.assertTrue(cartPage.cartIcon.isDisplayed());
        Assert.assertFalse(cartPage.cartBadgeIsPresent());
    }

    @Test
    public void userCanRemoveMultipleItemsFromCart() throws InterruptedException {
        logging();
        inventoryPage.clickOnAtcButton(2);
        inventoryPage.clickOnAtcButton(0);
        scrollToElement(inventoryPage.itemTitles.get(4));
        inventoryPage.clickOnAtcButton(3);
        inventoryPage.clickOnCartIcon();
        cartPage.clickOnRemoveButton(0);
        cartPage.clickOnRemoveButton(0);
        cartPage.clickOnRemoveButton(0);

        Assert.assertTrue(cartPage.cartIcon.isDisplayed());
        Assert.assertFalse(cartPage.cartBadgeIsPresent());
    }

    @Test
    public void userCanAccessToProductDetailsFromCart() {
        logging();
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

        logging();
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
    public void userCanPurchaseMultipleItems() throws InterruptedException {
        String firstName = excelReader.getStringData("Sheet1", 1, 4);
        String lastName = excelReader.getStringData("Sheet1", 1, 5);
        String postalCode = String.valueOf(excelReader.getIntegerData("Sheet1", 1, 6));

        logging();
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

        logging();
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
        logging();
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

        Assert.assertFalse(cartPage.cartBadgeIsPresent());
    }
}
