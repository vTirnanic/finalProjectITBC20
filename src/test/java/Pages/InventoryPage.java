package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class InventoryPage extends BaseTest {

    public InventoryPage() {
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "shopping_cart_container")
    public WebElement shoppingCart;

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

    @FindBy(css = ".inventory_item_name")
    public List<WebElement> itemTitleLinks;

    @FindBy(css = "span[class='shopping_cart_badge']")
    public WebElement cartBadge;

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

    @FindBy(id = "shopping_cart_container")
    public WebElement cartIcon;

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

    public void openProductPageByTitle(int titleNumber) {
        if (titleNumber >= 0 && titleNumber < itemTitleLinks.size()) {
            itemTitleLinks.get(titleNumber).click();
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
}
