package Test;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest extends BaseTest {

    String homePageURL = "https://www.saucedemo.com/";
    String inventoryURL = "https://www.saucedemo.com/inventory.html";

    @BeforeMethod
    public void pageSetUp() {
        driver.navigate().to("https://www.saucedemo.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @Test
    public void userCanLogin() {
        for (int i = 1; i <= excelReader.getLastRow("Sheet1"); i++) {

            String validUsername = excelReader.getStringData("Sheet1", i, 0);
            String validPassword = excelReader.getStringData("Sheet1",1,1);

            homepagePage.inputUsername(validUsername);
            homepagePage.inputPassword(validPassword);
            homepagePage.clickOnLoginButton();
            Assert.assertEquals(driver.getCurrentUrl(),inventoryURL);
            Assert.assertTrue(inventoryPage.shoppingCart.isDisplayed());
            driver.navigate().back();
        }
    }

    @Test
    public void userCannotLoginWithInvalidUsername() {
        for (int i = 1; i <= 3; i++) {

            String invalidUsername = excelReader.getStringData("Sheet1",i,2);
            String validPassword = excelReader.getStringData("Sheet1",1,1);

            homepagePage.inputUsername(invalidUsername);
            homepagePage.inputPassword(validPassword);
            homepagePage.clickOnLoginButton();
            Assert.assertEquals(driver.getCurrentUrl(),homePageURL);
            Assert.assertTrue(homepagePage.loginButton.isDisplayed());
            driver.navigate().refresh();
        }
    }

    @Test
    public void userCannotLoginWithInvalidPassword() {
        for (int i = 1; i <= 3; i++) {

            String validUsername = excelReader.getStringData("Sheet1",1,0);
            String invalidPassword = excelReader.getStringData("Sheet1",i,3);

            homepagePage.inputUsername(validUsername);
            homepagePage.inputPassword(invalidPassword);
            homepagePage.clickOnLoginButton();
            Assert.assertEquals(driver.getCurrentUrl(),homePageURL);
            Assert.assertTrue(homepagePage.loginButton.isDisplayed());
            driver.navigate().refresh();
        }
    }

    @Test
    public void userCannotLoginWithoutCredentials() {
        homepagePage.clickOnLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(),homePageURL);
        Assert.assertTrue(homepagePage.loginButton.isDisplayed());
    }

    @Test
    public void userCannotLoginWithBlankUsername() {

        String validPassword = excelReader.getStringData("Sheet1",1,1);

        homepagePage.inputPassword(validPassword);
        homepagePage.clickOnLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(),homePageURL);
        Assert.assertTrue(homepagePage.loginButton.isDisplayed());
    }

    @Test
    public void userCannotLoginWithBlankPassword() {

        String validUsername = excelReader.getStringData("Sheet1",1,0);

        homepagePage.inputUsername(validUsername);
        homepagePage.clickOnLoginButton();
        Assert.assertEquals(driver.getCurrentUrl(),homePageURL);
        Assert.assertTrue(homepagePage.loginButton.isDisplayed());
    }

    @Test
    public void userCanLogutFromInvPage() {

        String validUsername = excelReader.getStringData("Sheet1",1,0);
        String validPassword = excelReader.getStringData("Sheet1",1,1);

        homepagePage.inputUsername(validUsername);
        homepagePage.inputPassword(validPassword);
        homepagePage.clickOnLoginButton();

    }
}
