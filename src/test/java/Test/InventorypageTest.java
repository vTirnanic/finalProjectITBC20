package Test;

import Base.BaseTest;
import Pages.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class InventorypageTest extends BaseTest {

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
    public void userCanSeeAllElementsOnInventoryPage() {
        logging();

        Assert.assertTrue(inventoryPage.hamburger.isDisplayed());
        Assert.assertEquals(inventoryPage.appLogo.getText(),"Swag Labs");
        Assert.assertTrue(inventoryPage.cartIcon.isDisplayed());
        Assert.assertEquals(inventoryPage.titleProducts.getText(),"Products");
        Assert.assertTrue(inventoryPage.sortMenu.isDisplayed());
        Assert.assertEquals(inventoryPage.inventoryItems.size(),6);
        Assert.assertEquals(inventoryPage.itemImages.size(),6);
        Assert.assertEquals(inventoryPage.itemTitles.size(),6);
        Assert.assertEquals(inventoryPage.itemDescriptions.size(),6);
        Assert.assertEquals(inventoryPage.itemPrices.size(),6);
        Assert.assertEquals(inventoryPage.addToCartButtons.size(),6);
        scrollToElement(inventoryPage.footer);
        Assert.assertTrue(inventoryPage.twitterIcon.isDisplayed());
        Assert.assertTrue(inventoryPage.facebookIcon.isDisplayed());
        Assert.assertTrue(inventoryPage.linkedInIcon.isDisplayed());
        Assert.assertEquals(inventoryPage.footerCopy.getText(),"© 2024 Sauce Labs. All Rights Reserved. " +
                "Terms of Service | Privacy Policy");
    }

    @Test
    public void userCanAccessProductPage() throws InterruptedException {
        logging();
        inventoryPage.openProductPageByTitleNumber(0);

        Assert.assertTrue(productPage.backTPButton.isDisplayed());

        productPage.clickOnBackTPButton();
        inventoryPage.openProductPageByTitleNumber(1);

        Assert.assertTrue(productPage.backTPButton.isDisplayed());

        productPage.clickOnBackTPButton();
        inventoryPage.openProductPageByTitleNumber(2);

        Assert.assertTrue(productPage.backTPButton.isDisplayed());

        productPage.clickOnBackTPButton();
        inventoryPage.openProductPageByTitleNumber(3);

        Assert.assertTrue(productPage.backTPButton.isDisplayed());

        productPage.clickOnBackTPButton();
        inventoryPage.openProductPageByTitleNumber(4);

        Assert.assertTrue(productPage.backTPButton.isDisplayed());

        productPage.clickOnBackTPButton();
        inventoryPage.openProductPageByTitleNumber(5);

        Assert.assertTrue(productPage.backTPButton.isDisplayed());

        productPage.clickOnBackTPButton();
    }
}
