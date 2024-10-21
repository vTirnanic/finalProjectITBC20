package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends BaseTest {

    public CartPage() {
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "checkout")
    public WebElement checkOutButton;

    public void clickOnCheckOutButton() {
        checkOutButton.click();
    }

}
