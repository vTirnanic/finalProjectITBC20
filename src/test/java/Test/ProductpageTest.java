package Test;

import Base.BaseTest;
import Pages.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ProductpageTest extends BaseTest {

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
    public void userCanSeeAllElementsOnProductPage() {
        logging();
        inventoryPage.openProductPageByTitleNumber(0);

        Assert.assertTrue(productPage.hamburger.isDisplayed());
        Assert.assertEquals(productPage.appLogo.getText(), "Swag Labs");
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
        Assert.assertEquals(inventoryPage.footerCopy.getText(), "© 2024 Sauce Labs. All Rights Reserved. " +
                "Terms of Service | Privacy Policy");
    }
}
