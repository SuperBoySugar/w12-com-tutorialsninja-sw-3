package laptopsandnotebooks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import utilities.Utility;

public class LaptopsAndNotebooksTest extends Utility {

    String baseUrl = "https://tutorialsninja.com/demo/index.php";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyProductsPriceDisplayHighToLowSuccessfully() {
        // Mouse Hover on Laptops and Notebooks
        doMouseHoverAndClick(By.xpath("//a[text()='Laptops & Notebooks']"));
        // click on "Show All Laptops and Notebooks"
        clickOnElement(By.xpath("//a[text()='Show AllLaptops & Notebooks']"));
        // Select sort By "price (High>low)
        selectByVisibleTextFromDropDown(By.id("input-sort"), "Price (High > Low)");
        // verify the product price
        verifyProductsAreSortedByHighToLow();
        driver.close();
    }

    @Test
    public void verifyThatUserPlaceOrderSuccessfully() {
        // Mouse Hover on Laptops and Notebooks
        doMouseHoverAndClick(By.xpath("//a[text()='Laptops & Notebooks']"));
        // Click on "Show all Laptops and Notebooks"
        clickOnElement(By.xpath("//a[text()='Show AllLaptops & Notebooks']"));
        // Select sort by price High.low
        selectByVisibleTextFromDropDown(By.id("input-sort"), "Price (High > Low)");
        // Select product "Macbook"
        clickOnElement(By.xpath("//a[text()='MacBook']"));
        // verify the text
        verifyText("MacBook", By.xpath("//h1[text()='MacBook']"), "MacBook is not Displayed");
        // click on "Add to Cart" button
        clickOnElement(By.id("button-cart"));
        // verify the text message
        verifyText("Success: You have added MacBook to your shopping cart!\n" +
                "×", By.xpath("//div[text()='Success: You have added ']"), "Shopping cart is not added with items");
        // Click on link 'Shopping cart'
        clickOnElement(By.xpath("//a[text()='shopping cart']"));
        // verify the text
        verifyText("Shopping Cart  (0.00kg)", By.xpath("//h1[contains(text(),'Shopping Cart')]"), "Shopping Cart is empty");
        // verify the text
        verifyText("MacBook", By.cssSelector("form .text-left a[href^='https']"), "MacBook is not displayed");
        // Change the Quantity
        sendTextElement(By.cssSelector("input[name*='quantity']"), Keys.BACK_SPACE + "2");
        // Click on Submit button
        clickOnElement(By.cssSelector("button[type='submit']"));
        // verify the text message
        verifyText("Success: You have modified your shopping cart!\n" +
                "×", By.xpath("//div[contains (text(), 'Success: You have modified')]"), "User Cant modified the shopping cart");
        // verify the price
        verifyText("$1,204.00", By.cssSelector("tbody tr td:nth-child(6)"), "Price is incorrect");
        // Click on Checkout button
        clickOnElement(By.xpath("//a[@class='btn btn-primary']"));
        // START FROM 2.16 Verify the text “Checkout”
        verifyText("Checkout", By.xpath("//h1[text()='Checkout']"), "User can't click Checkout");
        // Verify the Text 'New Customer'
        // verifyText("New Customer", By.xpath("//h2[text()='New Customer']"),"New Customer");
        // Click on 'Guest Checkout' radio button
        clickOnElement(By.xpath("//label[text()='Guest Checkout']"));
        // Click on Continue tab
        clickOnElement(By.cssSelector("#button-account"));
        // Fill the Mandatory fields
        sendTextElement(By.id("input-payment-firstname"), "abcd");
        sendTextElement(By.id("input-payment-lastname"), "desfg");
        sendTextElement(By.id("input-payment-email"), "abc12@gmail.com");
        sendTextElement(By.id("input-payment-telephone"), "07658574462");
        sendTextElement(By.id("input-payment-address-1"), "4,park view");
        sendTextElement(By.id("input-payment-city"), "London");
        sendTextElement(By.id("input-payment-postcode"), "hs14fg");
        sendTextElement(By.id("input-payment-zone"), "Bristol");
        // Click on 'Continue button '
        clickOnElement(By.id("button-guest"));
        // Add Comments about your order into text area
        sendTextElement(By.xpath("//textarea[@name='comment']"), "place order");
        clickOnElement(By.xpath("//input[@type='checkbox']"));
        // Click on 'Continue' button
        clickOnElement(By.id("button-payment-method"));
        // Verify the message "Warning Payment method required!"
        verifyText("Warning: No Payment options are available. Please contact us for assistance!", By.xpath("//div[@class='alert alert-warning alert-dismissible']"), "Warning doesn't displayed");
    }

    @After
    public void tearDown(){
        closeBrowser();
    }
}