package Test;

import Base.BaseTest;
import Pages.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class SortingTest extends BaseTest {

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
    public void userCanSortItemByNZA() {
        logging();
        inventoryPage.itemTitlesBeforeSorting();
        System.out.println(inventoryPage.itemTitlesBeforeSorting());
        inventoryPage.sortedTitlesRO();
        System.out.println(inventoryPage.sortedTitlesRO());
        inventoryPage.selectSortOption("Name (Z to A)");
        inventoryPage.itemTitlesAfterSorting();
        System.out.println(inventoryPage.itemTitlesAfterSorting());
        Assert.assertEquals(inventoryPage.itemTitlesAfterSorting(),inventoryPage.sortedTitlesRO());

    }

    @Test
    public void userCanSortItemByNAZ() {
        logging();
        inventoryPage.selectSortOption("Name (Z to A)");
        inventoryPage.itemTitlesBeforeSorting();
        System.out.println(inventoryPage.itemTitlesBeforeSorting());
        inventoryPage.sortedTitlesNO();
        System.out.println(inventoryPage.sortedTitlesNO());
        inventoryPage.selectSortOption("Name (A to Z)");
        inventoryPage.itemTitlesAfterSorting();
        System.out.println(inventoryPage.itemTitlesAfterSorting());
        Assert.assertEquals(inventoryPage.itemTitlesAfterSorting(),inventoryPage.sortedTitlesNO());
    }

    @Test
    public void userCanSortItemByPLH() {
        logging();
        inventoryPage.itemPricesBeforeSorting();
        System.out.println(inventoryPage.itemPricesBeforeSorting());
        inventoryPage.sortedPricesNO();
        System.out.println(inventoryPage.sortedPricesNO());
        inventoryPage.selectSortOption("Price (low to high)");
        inventoryPage.itemPricesAfterSorting();
        System.out.println(inventoryPage.itemPricesAfterSorting());
    }

    @Test
    public void userCanSortItemByPHL() {
        logging();
        inventoryPage.itemPricesBeforeSorting();
        System.out.println(inventoryPage.itemPricesBeforeSorting());
        inventoryPage.selectSortOption("Price (high to low)");
    }
}
