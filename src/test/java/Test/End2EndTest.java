package Test;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class End2EndTest extends BaseTest {

    @Test
    public void endToEndTest() {
        String firstName = excelReader.getStringData("Sheet1", 1, 4);
        String lastName = excelReader.getStringData("Sheet1", 1, 5);
        String postalCode = String.valueOf(excelReader.getIntegerData("Sheet1", 1, 6));
        String thanksM = "Thank you for your order!";
        String checkOM = "Checkout: Complete!";

        logIn();
        inventoryPage.clickOnAtcButton(0);

        Assert.assertEquals(inventoryPage.cartBadge.getText(),"1");

        inventoryPage.openProductPageByTitleNumber(3);
        productPage.clickOnAtcButton();

        Assert.assertEquals(productPage.cartBadge.getText(),"2");

        productPage.clickOnBackTPButton();

        scrollToElement(inventoryPage.itemTitles.get(4));
        inventoryPage.openProductPageByImageNumber(4);
        productPage.clickOnAtcButton();

        Assert.assertEquals(productPage.cartBadge.getText(),"3");

        productPage.clickOnCartIcon();

        cartPage.clickOnRemoveButton(0);
        cartPage.clickOnRemoveButton(0);

        Assert.assertEquals(cartPage.cartBadge.getText(),"1");

        cartPage.clickOnCheckOutButton();
        checkout1Page.inputFirstName(firstName);
        checkout1Page.inputLastName(lastName);
        checkout1Page.inputPostalCode(postalCode);
        checkout1Page.clickOnContinueButton();
        checkout2Page.clickOnFinishButton();

        Assert.assertTrue(checkout3Page.backHomeButton.isDisplayed());
        Assert.assertEquals(checkout3Page.thanksMessage.getText(), thanksM);
        Assert.assertEquals(checkout3Page.checkOutMessage.getText(), checkOM);
        Assert.assertFalse(elementIsDisplayed(checkout3Page.cartBadge));
    }
}
