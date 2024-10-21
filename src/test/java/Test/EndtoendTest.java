package Test;

import Base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class EndtoendTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.navigate().to("https://www.saucedemo.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    public void endToEndTest() {

        String firstName = excelReader.getStringData("Sheet1",1,4);
        String lastName = excelReader.getStringData("Sheet1",1,5);
        String postalCode = String.valueOf(excelReader.getIntegerData("Sheet1",1,6));

        logging();
        inventoryPage.clickOnItemTitle();
        productPage.clickOnAtcButton();
        productPage.clickOnCartIcon();
        cartPage.clickOnCheckOutButton();
        checkout1Page.inputFirstName(firstName);
        checkout1Page.inputLastName(lastName);
        checkout1Page.inputPostalCode(postalCode);
        checkout1Page.clickOnConButton();



    }
}
