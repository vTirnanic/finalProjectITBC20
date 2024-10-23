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
    public void userCanSeeAllProductPageElements() {
        logging();
        inventoryPage.openProductPageByTitleNumber(0);
        //Assert.assertTrue(inventoryPage.hamburger.isDisplayed());



    }
}
