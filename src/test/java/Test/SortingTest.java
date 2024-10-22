package Test;

import Base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class SortingTest extends BaseTest {

    @BeforeMethod
    public void pageSetUp() {
        driver.navigate().to("https://www.saucedemo.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    public void userCanSortItemByNZA() {
        logging();
        inventoryPage.selectSortOption("Name (Z to A)");
    }

    @Test
    public void userCanSortItemByNAZ() throws InterruptedException {
        logging();
        inventoryPage.selectSortOption("Name (Z to A)");
        Thread.sleep(3000);
        inventoryPage.selectSortOption("Name (A to Z)");
    }

    @Test
    public void userCanSortItemByPLH() {
        logging();
        inventoryPage.selectSortOption("Price (low to high)");
    }

    @Test
    public void userCanSortItemByPHL() {
        logging();
        inventoryPage.selectSortOption("Price (high to low)");
    }
}
