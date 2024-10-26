package Test;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InventoryPageTest extends BaseTest {

    @Test
    public void userCanSeeAllElementsOnInventoryPage() {
        logIn();

        Assert.assertTrue(inventoryPage.hamburger.isDisplayed());
        Assert.assertEquals(inventoryPage.appLogo.getText(), "Swag Labs");
        Assert.assertTrue(inventoryPage.cartIcon.isDisplayed());
        Assert.assertEquals(inventoryPage.titleProducts.getText(), "Products");
        Assert.assertTrue(inventoryPage.sortMenu.isDisplayed());
        Assert.assertEquals(inventoryPage.inventoryItems.size(), 6);
        Assert.assertEquals(inventoryPage.itemImages.size(), 6);
        Assert.assertEquals(inventoryPage.itemTitles.size(), 6);
        Assert.assertEquals(inventoryPage.itemDescriptions.size(), 6);
        Assert.assertEquals(inventoryPage.itemPrices.size(), 6);
        Assert.assertEquals(inventoryPage.addToCartButtons.size(), 6);
        scrollToElement(inventoryPage.footer);
        Assert.assertTrue(inventoryPage.twitterIcon.isDisplayed());
        Assert.assertTrue(inventoryPage.facebookIcon.isDisplayed());
        Assert.assertTrue(inventoryPage.linkedInIcon.isDisplayed());
        Assert.assertEquals(inventoryPage.footerCopy.getText(), "© 2024 Sauce Labs. All Rights Reserved. " +
                "Terms of Service | Privacy Policy");
    }

    @Test
    public void userCanAccessProductPage() {
        logIn();
        inventoryPage.openProductPageByTitleNumber(0);

        Assert.assertTrue(productPage.backTPButton.isDisplayed());

        productPage.clickOnBackTPButton();

        Assert.assertTrue(inventoryPage.titleProducts.isDisplayed());

        inventoryPage.openProductPageByImageNumber(1);

        Assert.assertTrue(productPage.backTPButton.isDisplayed());

        productPage.clickOnBackTPButton();

        Assert.assertTrue(inventoryPage.titleProducts.isDisplayed());
    }
}
