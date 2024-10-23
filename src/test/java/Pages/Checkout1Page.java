package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Checkout1Page extends BaseTest {

    public Checkout1Page() {
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "first-name")
    public WebElement firstName;

    @FindBy(id = "last-name")
    public WebElement lastName;

    @FindBy(id = "postal-code")
    public WebElement postalCode;

    @FindBy(id = "continue")
    public WebElement continueButton;

    public void inputFirstName(String fName) {
        firstName.clear();
        firstName.sendKeys(fName);
    }

    public void inputLastName(String lName) {
        lastName.clear();
        lastName.sendKeys(lName);
    }

    public void inputPostalCode(String pCode) {
        postalCode.clear();
        postalCode.sendKeys(pCode);
    }

    public void clickOnContinueButton() {
        continueButton.click();
    }
}
