package Test;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ProductpageTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.navigate().to("https://www.saucedemo.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    public void userCanSeeAllElementsOnProductPage() {
        logging();
        inventoryPage.openProductPageByTitleNumber(0);
        Assert.assertTrue(productPage.hamburger.isDisplayed());
        Assert.assertEquals(productPage.appLogo.getText(),"Swag Labs");
        Assert.assertTrue(productPage.cartIcon.isDisplayed());
        Assert.assertTrue(productPage.backTPButton.isDisplayed());
        Assert.assertTrue(productPage.itemImage.isDisplayed());
        Assert.assertTrue(productPage.itemTitle.isDisplayed());
        Assert.assertTrue(productPage.itemDescription.isDisplayed());
        Assert.assertTrue(productPage.itemPrice.isDisplayed());
        Assert.assertTrue(productPage.atcButton.isDisplayed());
        scrollToElement(inventoryPage.footer);
        Assert.assertTrue(inventoryPage.twitterIcon.isDisplayed());
        Assert.assertTrue(inventoryPage.facebookIcon.isDisplayed());
        Assert.assertTrue(inventoryPage.linkedInIcon.isDisplayed());
        Assert.assertEquals(inventoryPage.footerCopy.getText(),"© 2024 Sauce Labs. All Rights Reserved. " +
                "Terms of Service | Privacy Policy");
    }
}
