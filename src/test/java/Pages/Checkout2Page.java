package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Checkout2Page extends BaseTest {

    public Checkout2Page() {
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "finish")
    public WebElement finishButton;

    @FindBy(id = "cancel")
    public WebElement cancelButton;

    public void clickOnFinishButton() {
        finishButton.click();
    }

    public void clickOnCancelButton() {
        cancelButton.click();
    }
}
