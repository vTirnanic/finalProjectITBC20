package Test;

import Base.BaseTest;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;

public class FooterTest extends BaseTest {

    String footerText = "© 2024 Sauce Labs. All Rights Reserved. Terms of Service | Privacy Policy";
    String twitterURL = "https://x.com/saucelabs?mx=2";
    String fbURL = "https://www.facebook.com/saucelabs";
    String linkedInURL = "https://www.linkedin.com/company/sauce-labs/";

    @BeforeMethod
    public void pageSetUp() {
        driver.navigate().to("https://www.saucedemo.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    public void userCanSeeFooterCopy() {
        logging();
        scrollToElement(inventoryPage.footer);
        Assert.assertEquals(inventoryPage.footerCopy.getText(),footerText);
    }

    @Test
    public void userCanAccessFooterLinks() throws InterruptedException {
        logging();
        scrollToElement(inventoryPage.footer);
        inventoryPage.clickOnTwiterrIcon();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(driver -> driver.getWindowHandles().size() > 1);
        ArrayList<String> listaTabova = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(listaTabova.get(1));
        Thread.sleep(1000);
        Assert.assertEquals(driver.getCurrentUrl(),twitterURL);
        driver.close();
        driver.switchTo().window(listaTabova.get(0));

        inventoryPage.clickOnFbIcon();
        wait.until(driver -> driver.getWindowHandles().size() > 1);
        ArrayList<String> listaTabova2 = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(listaTabova2.get(1));
        Thread.sleep(1000);
        Assert.assertEquals(driver.getCurrentUrl(),fbURL);
        driver.close();
        driver.switchTo().window(listaTabova2.get(0));

        inventoryPage.clickOnLinkedInIcon();
        wait.until(driver -> driver.getWindowHandles().size() > 1);
        ArrayList<String> listaTabova3 = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(listaTabova3.get(1));
        Thread.sleep(1000);
        Assert.assertEquals(driver.getCurrentUrl(),linkedInURL);
    }
}
