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

    public void clickOnCheckOutButton() {
        checkOutButton.click();
    }

    public void clickOnConShopButton() {
        conShopButton.click();
    }

}
