package Base;

import Pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

// Automation tests for saucedemo.com

public class BaseTest {

    public static WebDriver driver;
    public HomepagePage homepagePage;
    public InventoryPage inventoryPage;
    public ProductPage productPage;
    public CartPage cartPage;
    public Checkout1Page checkout1Page;
    public ExcelReader excelReader;


    @BeforeClass
    public void setUp() throws IOException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        excelReader = new ExcelReader("Credentials.xlsx");

        homepagePage = new HomepagePage();
        inventoryPage = new InventoryPage();
        productPage = new ProductPage();
        cartPage = new CartPage();
        checkout1Page = new Checkout1Page();
    }

    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void jsClick(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public void logging() {
        String validUsername = excelReader.getStringData("Sheet1",1,0);
        String validPassword = excelReader.getStringData("Sheet1",1,1);

        homepagePage.inputUsername(validUsername);
        homepagePage.inputPassword(validPassword);
        homepagePage.clickOnLoginButton();
    }
/*
    @AfterClass
    public void tearDown() {
        driver.quit();
    }
*/
}
