package Test;

import Base.BaseTest;
import Pages.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

        // Dohvatanje cena pre sortiranja kao String vrednosti iz WebElement-a
        List<String> pricesBeforeSorting = inventoryPage.itemPrices.stream()
                .map(WebElement::getText) // Dohvati tekst iz svakog WebElement-a
                .collect(Collectors.toList());

        System.out.println("Prices before sorting: " + pricesBeforeSorting);

        // Konverzija cena iz String u Double, uklanjanje znaka $
        List<Double> doublePricesBeforeSorting = pricesBeforeSorting.stream()
                .map(price -> price.replaceAll("[^0-9.]", "")) // Uklanja sve osim brojeva i tačke
                .map(Double::parseDouble) // Konvertuje u double
                .collect(Collectors.toList());

        System.out.println("Prices before sorting, doubles: " + doublePricesBeforeSorting);

        // Ručno sortiranje LH za proveru
        List<Double> doubleSortedPrices = new ArrayList<>(doublePricesBeforeSorting);
        Collections.sort(doubleSortedPrices); // Sortira cene po principu "low to high"

        System.out.println("Sorted doubles: " + doubleSortedPrices);

        inventoryPage.selectSortOption("Price (low to high)");

        // Dohvatanje cena nakon sortiranja kao String vrednosti iz WebElement-a
        List<String> pricesAfterSorting = inventoryPage.itemPrices.stream()
                .map(WebElement::getText) // Dohvati tekst iz WebElement-a
                .collect(Collectors.toList());

        // Prikazivanje cena nakon sortiranja
        System.out.println("Prices after sorting: " + pricesAfterSorting);

        // Konverzija cena nakon sortiranja iz String u Double
        List<Double> doublePricesAfterSorting = pricesAfterSorting.stream()
                .map(price -> price.replaceAll("[^0-9.]", "")) // Uklanja $ i konvertuje u double
                .map(Double::parseDouble)
                .collect(Collectors.toList());

        System.out.println("Prices after sorting, doubles: " + doublePricesAfterSorting);

        Assert.assertEquals(doublePricesAfterSorting, doubleSortedPrices);
    }


    @Test
    public void userCanSortItemByPHL() {
        logging();

        // Dohvatanje cena pre sortiranja kao String vrednosti iz WebElement-a
        List<String> pricesBeforeSorting = inventoryPage.itemPrices.stream()
                .map(WebElement::getText) // Dohvati tekst iz svakog WebElement-a
                .collect(Collectors.toList());

        System.out.println("Prices before sorting: " + pricesBeforeSorting);

        // Konverzija cena iz String u Double, uklanjanje znaka $
        List<Double> doublePricesBeforeSorting = pricesBeforeSorting.stream()
                .map(price -> price.replaceAll("[^0-9.]", "")) // Uklanja sve osim brojeva i tačke
                .map(Double::parseDouble) // Konvertuje u double
                .collect(Collectors.toList());

        System.out.println("Prices before sorting, doubles: " + doublePricesBeforeSorting);

        // Ručno sortiranje HL za proveru
        List<Double> doubleSortedPrices = new ArrayList<>(doublePricesBeforeSorting);
        Collections.sort(doubleSortedPrices, Collections.reverseOrder());  // Sortira cene po principu "high to low"

        System.out.println("Sorted doubles: " + doubleSortedPrices);

        inventoryPage.selectSortOption("Price (high to low)");

        // Dohvatanje cena nakon sortiranja kao String vrednosti iz WebElement-a
        List<String> pricesAfterSorting = inventoryPage.itemPrices.stream()
                .map(WebElement::getText) // Dohvati tekst iz WebElement-a
                .collect(Collectors.toList());

        // Prikazivanje cena nakon sortiranja
        System.out.println("Prices after sorting: " + pricesAfterSorting);

        // Konverzija cena nakon sortiranja iz String u Double
        List<Double> doublePricesAfterSorting = pricesAfterSorting.stream()
                .map(price -> price.replaceAll("[^0-9.]", "")) // Uklanja $ i konvertuje u double
                .map(Double::parseDouble)
                .collect(Collectors.toList());

        System.out.println("Prices after sorting, doubles: " + doublePricesAfterSorting);

        Assert.assertEquals(doublePricesAfterSorting, doubleSortedPrices);
    }
}
