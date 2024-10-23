package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage extends BaseTest {

    public ProductPage() {
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

    @FindBy(id = "shopping_cart_container")
    public WebElement cartIcon;

    @FindBy(css = ".btn.btn_primary.btn_small.btn_inventory")
    public WebElement atcButton;

    @FindBy(css = "span[class='shopping_cart_badge']")
    public WebElement cartBadge;

    @FindBy(css = ".inventory_details_name.large_size")
    public WebElement itemTitle;

    @FindBy(className = "inventory_details_img")
    public WebElement itemImage;

    @FindBy(id = "remove")
    public WebElement removeButton;

    @FindBy(css = ".inventory_details_desc.large_size")
    public WebElement itemDescription;

    @FindBy(className = "inventory_details_price")
    public WebElement itemPrice;

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

    public void clickOnCartIcon() {
        cartIcon.click();
    }

    public void clickOnAtcButton() {
        atcButton.click();
    }

    public boolean badgeIsPresent() {
        boolean bIsPresent = false;
        try {
            bIsPresent = productPage.cartBadge.isDisplayed();
        } catch (Exception e) {
            System.out.println(e);
        }
        return bIsPresent;
    }

    public boolean sidebarIsPresent() {
        boolean sbIsPresent = false;
        try {
            sbIsPresent = productPage.sideBar.isDisplayed();
        } catch (Exception e) {
            System.out.println(e);
        }
        return sbIsPresent;
    }
}
