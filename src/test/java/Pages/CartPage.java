package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends BaseTest {

    public CartPage() {
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "shopping_cart_container")
    public WebElement cartIcon;

    @FindBy(css = "span[class='shopping_cart_badge']")
    public WebElement cartBadge;

    @FindBy(className = "cart_quantity")
    public WebElement quantityCell;

    @FindBy(id = "continue-shopping")
    public WebElement conShopButton;

    @FindBy(id = "checkout")
    public WebElement checkOutButton;

    @FindBy(className = "cart_item")
    public WebElement cartItem;

    @FindBy(className = "cart_item")
    public List<WebElement> cartItems;

    @FindBy(css = ".btn.btn_secondary.btn_small.cart_button")
    public List<WebElement> removeButtons;

    @FindBy(className = "inventory_item_name")
    public List<WebElement> itemTitles;

    public void clickOnCheckOutButton() {
        checkOutButton.click();
    }

    public void clickOnConShopButton() {
        conShopButton.click();
    }

    public void clickOnRemoveButton(int buttonNumber) {
        if (buttonNumber >= 0 && buttonNumber < removeButtons.size()) {
            removeButtons.get(buttonNumber).click();
        } else {
            System.out.println("Error: buttonNumber is out of bounds.Total buttons available: " + removeButtons.size());
        }
    }

    public boolean cartBadgeIsPresent() {
        boolean cbbIsPresent = false;
        try {
            cbbIsPresent = cartBadge.isDisplayed();
        } catch (Exception e) {
            System.out.println(e);
        }
        return cbbIsPresent;
    }

    public void clickOnItemTitle(int titleNumber) {
        if (titleNumber >= 0 && titleNumber < itemTitles.size()) {
            itemTitles.get(titleNumber).click();
        } else {
            System.out.println("Error: titleNumber is out of bounds.");
        }
    }
}
