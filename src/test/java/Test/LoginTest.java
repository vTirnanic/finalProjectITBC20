package Test;

import Base.BaseTest;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    String homePageURL = "https://www.saucedemo.com/";
    String inventoryURL = "https://www.saucedemo.com/inventory.html";

    @Test
    public void userCanLogin() {
        for (int i = 1; i <= excelReader.getLastRow("Sheet1"); i++) {
            String username = excelReader.getStringData("Sheet1", i, 0);
            String password = excelReader.getStringData("Sheet1", 1, 1);

            homepagePage.inputUsername(username);
            homepagePage.inputPassword(password);
            homepagePage.clickOnLoginButton();

            if (!inventoryURL.equals(driver.getCurrentUrl())) {
                System.out.println("invalid username: " + username + " or invalid password: "+ password);
            } else {
                System.out.println("URL is changed, as expected.");
            }

            Assert.assertEquals(driver.getCurrentUrl(),inventoryURL);
            Assert.assertTrue(inventoryPage.cartIcon.isDisplayed());
            driver.navigate().back();
        }
    }

    @Test
    public void userCannotLoginWithInvalidUsername() {
        for (int i = 1; i <= 3; i++) {

            String invalidUsername = excelReader.getStringData("Sheet1", i, 2);
            String validPassword = excelReader.getStringData("Sheet1", 1, 1);

            homepagePage.inputUsername(invalidUsername);
            homepagePage.inputPassword(validPassword);
            homepagePage.clickOnLoginButton();

            Assert.assertEquals(driver.getCurrentUrl(), homePageURL);
            Assert.assertTrue(homepagePage.loginButton.isDisplayed());
            driver.navigate().refresh();
        }
    }

    @Test
    public void userCannotLoginWithInvalidPassword() {
        for (int i = 1; i <= 3; i++) {

            String validUsername = excelReader.getStringData("Sheet1", 1, 0);
            String invalidPassword = excelReader.getStringData("Sheet1", i, 3);

            homepagePage.inputUsername(validUsername);
            homepagePage.inputPassword(invalidPassword);
            homepagePage.clickOnLoginButton();

            Assert.assertEquals(driver.getCurrentUrl(), homePageURL);
            Assert.assertTrue(homepagePage.loginButton.isDisplayed());
            driver.navigate().refresh();
        }
    }

    @Test
    public void userCannotLoginWithoutCredentials() {
        Assert.assertTrue(homepagePage.usernameField.getText().isBlank());
        Assert.assertTrue(homepagePage.passwordField.getText().isBlank());

        homepagePage.clickOnLoginButton();

        Assert.assertEquals(driver.getCurrentUrl(), homePageURL);
        Assert.assertTrue(homepagePage.loginButton.isDisplayed());
    }

    @Test
    public void userCannotLoginWithBlankUsername() {
        String validPassword = excelReader.getStringData("Sheet1", 1, 1);

        homepagePage.inputPassword(validPassword);
        homepagePage.clickOnLoginButton();

        Assert.assertEquals(driver.getCurrentUrl(), homePageURL);
        Assert.assertTrue(homepagePage.loginButton.isDisplayed());
    }

    @Test
    public void userCannotLoginWithBlankPassword() {

        String validUsername = excelReader.getStringData("Sheet1", 1, 0);

        homepagePage.inputUsername(validUsername);
        homepagePage.clickOnLoginButton();

        Assert.assertEquals(driver.getCurrentUrl(), homePageURL);
        Assert.assertTrue(homepagePage.loginButton.isDisplayed());
    }

    @Test
    public void userCanLogoutOnInventoryPage() {
        logIn();
        inventoryPage.clickOnHamburger();
        inventoryPage.clickOnLogoutLink();

        Assert.assertEquals(driver.getCurrentUrl(), homePageURL);
        Assert.assertTrue(homepagePage.loginButton.isDisplayed());
    }

    @Test
    public void userCanLogoutOnProductPage() {
        logIn();
        inventoryPage.openProductPageByTitleNumber(1);
        productPage.clickOnHamburger();
        productPage.clickOnLogoutLink();

        Assert.assertEquals(driver.getCurrentUrl(), homePageURL);
        Assert.assertTrue(homepagePage.loginButton.isDisplayed());
    }

    @Test
    public void userCanLoginUsingKeyboard() {
        String validUsername = excelReader.getStringData("Sheet1", 1, 0);
        String validPassword = excelReader.getStringData("Sheet1", 1, 1);

        homepagePage.usernameField.sendKeys(Keys.TAB);
        homepagePage.inputUsername(validUsername);
        homepagePage.passwordField.sendKeys(Keys.TAB);
        homepagePage.inputPassword(validPassword);
        homepagePage.loginButton.sendKeys(Keys.TAB);
        homepagePage.loginButton.sendKeys(Keys.ENTER);

        Assert.assertEquals(driver.getCurrentUrl(), inventoryURL);
        Assert.assertTrue(inventoryPage.cartIcon.isDisplayed());
    }
}
