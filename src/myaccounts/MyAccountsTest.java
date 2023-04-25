package myaccounts;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.List;

public class MyAccountsTest extends Utility {
    String baseUrl = "https://tutorialsninja.com/demo/index.php";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    public void selectMyAccountOptions(String option) {
        List<WebElement> topOptionNames = driver.findElements(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']//a"));
        for (WebElement names : topOptionNames) {
            if (names.getText().equalsIgnoreCase(option)) {
                names.click();
                break;


            }
        }
    }

    @Test
    public void verifyUserShouldNavigateToRegisterPageSuccessfully() {
        // Click on My Account Link
        clickOnElement(By.xpath("//span[text()='My Account']"));
        // Call the method and click on Register
        selectMyAccountOptions("Register");
        // Verify the text 'Register'
        verifyText("Register Account", By.xpath("//h1[text()='Register Account']"),"Register Account page is not displayed");
    }

    @Test
    public void verifyUserShouldNavigateToLoginPageSuccessfully(){
        // Click on My Account
        clickOnElement(By.xpath("//span[text()='My Account']"));
        // Call the method and click on Login
        selectMyAccountOptions("Login");
        // verify the text 'Returning Customer'
        verifyText("Returning Customer", By.xpath("//h2[text()='Returning Customer']"),"Page does not displayed");
    }

    @Test
    public void verifyThatUserRegisterAccountSuccessFully(){
        // Click on My Account
        clickOnElement(By.xpath("//span[text()='My Account']"));
        // call the method and click Register
        selectMyAccountOptions("Register");
        // Enter username field
        sendTextElement(By.id("input-firstname"),"Henry");
        // Enter lastname field
        sendTextElement(By.id("input-lastname"),"smith");
        // Enter email address
        sendTextElement(By.id("input-email"),"hick210@gmail.com");
        // Enter Telephone number
        sendTextElement(By.id("input-telephone"),"07860554345");
        // Enter Password field
        sendTextElement(By.id("input-password"),"Password2");
        // Enter confirm password
        sendTextElement(By.id("input-confirm"),"Password2");
        // Select Subscribe Yes radio Button 3.9
        clickOnElement(By.xpath("//label[text()='Yes']"));
        // Click on Checkbox
        clickOnElement(By.xpath("//input[@type='checkbox']"));
        // Click on Continue button
        clickOnElement(By.cssSelector("input[value='Continue']"));
        // Verify the text message
        verifyText("Your Account Has Been Created!", By.xpath("//h1[text()='Your Account Has Been Created!']"),"Your Account is not Created!");
        // Click on Continue button
        clickOnElement(By.xpath("//a[text()='Continue']"));
        // Click on My Account
        clickOnElement(By.xpath("//span[text()='My Account']"));
        // Select Logout button
        selectMyAccountOptions("Logout");
        // verify the text
        verifyText("Account Logout", By.xpath("//h1[text()='Account Logout']")," Erro on Account Logout button");
        // Click on Continue button
        clickOnElement(By.xpath("//a[text()='Continue']"));
    }

    @Test
    public void verifyThatUserShouldLoginAndLogoutSuccessfully(){
        // Click on My Account
        clickOnElement(By.xpath("//span[text()='My Account']"));
        // Call the method and  click on Login
        selectMyAccountOptions("Login");
        // Enter the email
        sendTextElement(By.id("input-email"),"hick310@gmail.com");
        // Enter password
        sendTextElement(By.id("input-password"),"Password2");
       // sendTextElement(By.xpath(""),"Password2");
        clickOnElement(By.xpath("//input[@value='Login']"));
        // verify the text
        verifyText("My Account",By.xpath("//h2[text()='My Account']"),"Text is not displayed");
        // click on My Account
        clickOnElement(By.xpath("//h2[text()='My Account']"));
        // Call the method and click on Logout
        selectMyAccountOptions("Logout");
        // verify the text
        verifyText("Account Logout",By.xpath("//h1[text()='Account Logout']"),"User did not logout");
        // click on Continue button
        clickOnElement(By.xpath("//a[text()='Continue']"));
    }

    @After
    public void tearDown(){
        closeBrowser();
    }
}