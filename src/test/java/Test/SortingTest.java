package Test;

import Base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SortingTest extends BaseTest {

    @Test
    public void userCanSortItemByNZA() {
        logIn();
        System.out.println(inventoryPage.itemTitlesBeforeSorting());
        System.out.println(inventoryPage.sortedTitlesRO());
        inventoryPage.selectSortOption("Name (Z to A)");
        System.out.println(inventoryPage.itemTitlesAfterSorting());

        Assert.assertEquals(inventoryPage.itemTitlesAfterSorting(), inventoryPage.sortedTitlesRO());
    }

    @Test
    public void userCanSortItemByNAZ() {
        logIn();
        inventoryPage.selectSortOption("Name (Z to A)");
        System.out.println(inventoryPage.itemTitlesBeforeSorting());
        System.out.println(inventoryPage.sortedTitlesNO());
        inventoryPage.selectSortOption("Name (A to Z)");
        System.out.println(inventoryPage.itemTitlesAfterSorting());

        Assert.assertEquals(inventoryPage.itemTitlesAfterSorting(), inventoryPage.sortedTitlesNO());
    }

    @Test
    public void userCanSortItemByPLH() {
        logIn();
        System.out.println("Prices before sorting: " + inventoryPage.pricesBeforeSorting());
        System.out.println("Prices before sorting, doubles: " + inventoryPage.doublePricesBeforeSorting());
        System.out.println("Sorted doubles: " + inventoryPage.sortedDoublePricesLowToHigh());
        inventoryPage.selectSortOption("Price (low to high)");
        System.out.println("Prices after sorting: " + inventoryPage.pricesAfterSorting());
        System.out.println("Prices after sorting, doubles: " + inventoryPage.doublePricesAfterSorting());

        Assert.assertEquals(inventoryPage.doublePricesAfterSorting(), inventoryPage.sortedDoublePricesLowToHigh());
    }

    @Test
    public void userCanSortItemByPHL() {
        logIn();
        System.out.println("Prices before sorting: " + inventoryPage.pricesBeforeSorting());
        System.out.println("Prices before sorting, doubles: " + inventoryPage.doublePricesBeforeSorting());
        System.out.println("Sorted doubles: " + inventoryPage.sortedDoublePricesHighToLow());
        inventoryPage.selectSortOption("Price (high to low)");
        System.out.println("Prices after sorting: " + inventoryPage.pricesAfterSorting());
        System.out.println("Prices after sorting, doubles: " + inventoryPage.doublePricesAfterSorting());

        Assert.assertEquals(inventoryPage.doublePricesAfterSorting(), inventoryPage.sortedDoublePricesHighToLow());
    }
}
