package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Checkout3Page extends BaseTest {

    public Checkout3Page() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "complete-header")
    public WebElement thanksMessage;

    @FindBy(id = "back-to-products")
    public WebElement backHomeButton;

    @FindBy(css = "span[class='title']")
    public WebElement checkOutMessage;

    @FindBy(css = "span[class='shopping_cart_badge']")
    public WebElement cartBadge;
}
