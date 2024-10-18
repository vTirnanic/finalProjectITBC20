package Test;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest extends BaseTest {

    String validUsername = "standard_user";
    String validPassword = "secret_sauce";
    String inventoryURL = "https://www.saucedemo.com/inventory.html";

    @BeforeMethod
    public void pageSetUp() {
        driver.navigate().to("https://www.saucedemo.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @Test
    public void userCanLogin() {
        homepagePage.inputUsername(validUsername);
        homepagePage.inputPassword(validPassword);
        homepagePage.clickOnLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(),inventoryURL);
        Assert.assertTrue(inventoryPage.shoppingCart.isDisplayed());
    }
}
