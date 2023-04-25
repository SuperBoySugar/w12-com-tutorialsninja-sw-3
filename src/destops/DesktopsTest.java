package destops;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.List;

public class DesktopsTest extends Utility {
    String baseUrl = "https://tutorialsninja.com/demo/index.php";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    public void selectMenu(String menu) {
        List<WebElement> topMenuNames = driver.findElements(By.xpath("//ul[@class='nav navbar-nav']//li//a"));
        for (WebElement names : topMenuNames) {
            if (names.getText().equalsIgnoreCase(menu)) {
                names.click();
                break;
            }
        }
    }

    @Test
    public void verifyProductArrangeInAlphaBaticalOrder() {
        // Mouse Hover on "Desktop" and click on "Show allDestops"
        doMouseHoverAndClick(By.xpath("//a[text()='Desktops']"));
        selectMenu("Show AllDesktops");
        // Select Sort by position name Z - A
        selectByVisibleTextFromDropDown(By.id("input-sort"), "Name (Z - A)");
        // verify the product will arrange in Descending order
        verifyProductAreInDescendingOrder();
    }

    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() {
        // Mouse Hover on "Desktop" and click on "Show allDestops"
        doMouseHoverAndClick(By.xpath("//a[text()='Desktops']"));
        selectMenu("Show AllDesktops");
        // Select Sort by position name A - Z
        selectByVisibleTextFromDropDown(By.id("input-sort"), "Name (A - Z)");
        // Select the product
        clickOnElement(By.xpath("//a[text()='HP LP3065']"));
        // verify the text "HP LP3065"
        verifyText("HP LP3065", By.xpath("//h1[text()='HP LP3065']"), "Product is not displayed");

        // Select Delivery date "2022-11-30" using List method
        String year = "2022";
        String month = "November";
        String date = "30";

        clickOnElement(By.xpath("//div[@class='input-group date']//button[@type='button']"));
        while (true) {
            String monthYear = driver.findElement(By.cssSelector("div[class='datepicker-days'] th[class='picker-switch']")).getText();
            System.out.println(monthYear); // Apr 2023
            String[] a = monthYear.split(" ");
            String mon = a[0];
            String yer = a[1];
            if (mon.equalsIgnoreCase(month) && yer.equalsIgnoreCase(year)) {
                break;
            } else {
                clickOnElement(By.xpath("//div[@class='datepicker-days']//th[@class='next'][contains(text(),'›')]"));
            }
        }
        // Select the date
        List<WebElement> allDates = driver.findElements(By.xpath("//tbody//tr//td[@class='day']"));
        for (WebElement dt : allDates) {
            if (dt.getText().equalsIgnoreCase(date)) {
                dt.click();
                break;
            }
            driver.close();
        }

        // Enter Quantity "1"
        sendTextElement(By.id("input-quantity"), Keys.BACK_SPACE + "1");
        // Click On Add to Cart button
        clickOnElement(By.xpath("//button[text()='Add to Cart']"));
        // Verify the Message on Page
        verifyText("Success: You have added HP LP3065 to your shopping cart!\n" +
                "×", By.xpath("//div[text()='Success: You have added ']"), "Shopping Cart is empty");
        // Click on link 'Shopping cart'
        clickOnElement(By.xpath("//a[text()='shopping cart']"));
        // Verify the text 'Shopping Cart'
        verifyText("Shopping Cart  (1.00kg)", By.xpath("//h1[contains(text(),'Shopping Cart')]"), "Shopping Cart is not displayed");
        verifyText("HP LP3065", By.cssSelector("form .text-left a[href^='https']"), "Product is not displayed");
        verifyText("Delivery Date:2022-11-30", By.cssSelector("form tr:nth-child(1) > td:nth-child(2) > small:nth-child(3)"), "Delivery Date is not Dispslayed");
        verifyText("Product 21", By.cssSelector("form tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(3)"), "User can't see the Product 21");
        verifyText("$122.00", By.cssSelector("tbody tr td:nth-child(6)"), "Price is not Displayed");
    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}