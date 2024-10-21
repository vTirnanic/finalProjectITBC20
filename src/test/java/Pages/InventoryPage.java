package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InventoryPage extends BaseTest {

    public InventoryPage() {
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "shopping_cart_container")
    public WebElement shoppingCart;

    @FindBy(id = "react-burger-menu-btn")
    public WebElement hamburger;

    @FindBy(id = "logout_sidebar_link")
    public WebElement logoutLink;

    public void clickOnHamburger() {
        hamburger.click();
    }

    public void clickOnLogoutLink() {
        logoutLink.click();
    }
}
