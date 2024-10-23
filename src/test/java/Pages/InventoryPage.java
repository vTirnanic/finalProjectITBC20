package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InventoryPage extends BaseTest {

    public InventoryPage() {
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "react-burger-menu-btn")
    public WebElement hamburger;

    @FindBy(id = "bm-menu-wrap")
    public WebElement sideBar;

    @FindBy(id = "inventory_sidebar_link")
    public WebElement allItemsLink;

    @FindBy(id = "about_sidebar_link")
    public WebElement aboutLink;

    @FindBy(id = "logout_sidebar_link")
    public WebElement logoutLink;

    @FindBy(id = "reset_sidebar_link")
    public WebElement resetLink;

    @FindBy(id = "react-burger-cross-btn")
    public WebElement sbCloseIcon;

    @FindBy(className = "app_logo")
    public WebElement appLogo;

    @FindBy(id = "shopping_cart_container")
    public WebElement cartIcon;

    @FindBy(css = "span[class='shopping_cart_badge']")
    public WebElement cartBadge;

    @FindBy(className = "product_sort_container")
    public WebElement sortMenu;

    @FindBy(className = "title")
    public WebElement titleProducts;

    @FindBy(className = "inventory_item")
    public List<WebElement> inventoryItems;

    @FindBy(css = "div[class='inventory_item_img']")
    public List<WebElement> itemImages;

    @FindBy(css = ".inventory_item_name")
    public List<WebElement> itemTitles;

    @FindBy(className = "inventory_item_desc")
    public List<WebElement> itemDescriptions;

    @FindBy(className = "inventory_item_price")
    public List<WebElement> itemPrices;

    @FindBy(css = ".btn.btn_primary.btn_small.btn_inventory")
    public List<WebElement> addToCartButtons;

    @FindBy(className = "footer")
    public WebElement footer;

    @FindBy(className = "footer_copy")
    public WebElement footerCopy;

    @FindBy(className = "social_twitter")
    public WebElement twitterIcon;

    @FindBy(className = "social_facebook")
    public WebElement facebookIcon;

    @FindBy(className = "social_linkedin")
    public WebElement linkedInIcon;

    public void clickOnHamburger() {
        hamburger.click();
    }

    public void clickOnSbCloseIcon() {
        sbCloseIcon.click();
    }

    public void clickOnAllItems() {
        allItemsLink.click();
    }

    public void clickOnAbout() {
        aboutLink.click();
    }

    public void clickOnLogoutLink() {
        logoutLink.click();
    }

    public void clickOnReset() {
        resetLink.click();
    }

    public void clickOnTwiterrIcon() {
        twitterIcon.click();
    }

    public void clickOnFbIcon() {
        facebookIcon.click();
    }

    public void clickOnLinkedInIcon() {
        linkedInIcon.click();
    }

    public void clickOnCartIcon() {
        cartIcon.click();
    }

    public void openProductPageByTitleNumber(int titleNumber) {
        if (titleNumber >= 0 && titleNumber < itemTitles.size()) {
            itemTitles.get(titleNumber).click();
        } else {
            System.out.println("Error: titleNumber is out of bounds.");
        }
    }

    public void clickOnAtcButton(int buttonNumber) {
        if (buttonNumber >= 0 && buttonNumber < addToCartButtons.size()) {
            addToCartButtons.get(buttonNumber).click();
        } else {
            System.out.println("Error: buttonNumber is out of bounds.");
        }
    }

    public void selectSortOption(String sortOptionTitle) {
        sortMenu.click();
        Select select = new Select(sortMenu);
        select.selectByVisibleText(sortOptionTitle);
    }

    public ArrayList<String> itemTitlesBeforeSorting() {
        ArrayList<String> itemTitlesBS = new ArrayList<>();

        for (WebElement it : itemTitles) {
            itemTitlesBS.add(it.getText());
        }
        return itemTitlesBS;
    }

    public ArrayList<String> sortedTitlesRO() {
        ArrayList<String> sortedT = new ArrayList<>(itemTitlesBeforeSorting());
        sortedT.sort(Collections.reverseOrder());
        return sortedT;
    }

    public ArrayList<String> sortedTitlesNO() {
        ArrayList<String> sortedT = new ArrayList<>(itemTitlesBeforeSorting());
        Collections.sort(sortedT);
        return sortedT;
    }

    public ArrayList<String> itemTitlesAfterSorting() {
        ArrayList<String> itemTitlesAS = new ArrayList<>();

        for (WebElement it : itemTitles) {
            itemTitlesAS.add(it.getText());
        }
        return itemTitlesAS;
    }

    public ArrayList<String> pricesBeforeSorting() {
        ArrayList<String> pricesBS = new ArrayList<>();
        for (WebElement ip : itemPrices) {
            pricesBS.add(ip.getText());
        }
        return pricesBS;
    }

    public ArrayList<Double> doublePricesBeforeSorting() {
        ArrayList<Double> doublePricesBS = new ArrayList<>();

        ArrayList<String> pricesAsStrings = pricesBeforeSorting();

        for (String priceText : pricesAsStrings) {
            String cleanedPriceText = priceText.replaceAll("[^0-9.]", ""); // Uklanja sve osim brojeva i tačke
            doublePricesBS.add(Double.parseDouble(cleanedPriceText)); // Konvertuje u double i dodaje u listu
        }

        return doublePricesBS;
    }

    public ArrayList<Double> sortedDoublePricesLowToHigh() {
        ArrayList<Double> sortedPricesLTH = new ArrayList<>(doublePricesBeforeSorting()); // Pravi kopiju liste cean
        Collections.sort(sortedPricesLTH); // Sortira cene po principu "low to high"
        return sortedPricesLTH;
    }

    public ArrayList<Double> sortedDoublePricesHighToLow() {
        ArrayList<Double> sortedPricesHTL = new ArrayList<>(doublePricesBeforeSorting()); // Napravi kopiju liste cena
        Collections.sort(sortedPricesHTL, Collections.reverseOrder()); // Sortira cene po principu "high to low"
        return sortedPricesHTL;
    }

    public ArrayList<String> pricesAfterSorting() {
        ArrayList<String> pricesAS = new ArrayList<>();
        for (WebElement ip : itemPrices) {
            pricesAS.add(ip.getText());
        }
        return pricesAS;
    }

    public ArrayList<Double> doublePricesAfterSorting() {
        ArrayList<Double> doublePricesAS = new ArrayList<>();

        ArrayList<String> pricesAsStrings = pricesAfterSorting();

        for (String priceText : pricesAsStrings) {
            String cleanedPriceText = priceText.replaceAll("[^0-9.]", ""); // Uklanja sve osim brojeva i tačke
            doublePricesAS.add(Double.parseDouble(cleanedPriceText)); // Konvertuje u double i dodaje u listu
        }

        return doublePricesAS;
    }

    public boolean badgeIsPresent() {
        boolean bIsPresent = false;
        try {
            bIsPresent = inventoryPage.cartBadge.isDisplayed();
        } catch (Exception e) {
            System.out.println(e);
        }
        return bIsPresent;
    }

    public boolean sidebarIsPresent() {
        boolean sbIsPresent = false;
        try {
            sbIsPresent = inventoryPage.sideBar.isDisplayed();
        } catch (Exception e) {
            System.out.println(e);
        }
        return sbIsPresent;
    }
}
