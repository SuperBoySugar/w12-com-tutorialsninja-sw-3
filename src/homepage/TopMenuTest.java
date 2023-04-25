package homepage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.List;

public class TopMenuTest extends Utility {

    String baseUrl = "https://tutorialsninja.com/demo/index.php";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    // Method to select category from top menu on homepage
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
    public void verifyUserShouldNavigateToDesktopsPageSuccessfully() {
        // Mouse Hover on "Desktop" and click on "Show allDestops"
        doMouseHoverAndClick(By.xpath("//a[text()='Desktops']"));
        selectMenu("Show AllDesktops");
    // Verify the Text 'Desktops
        verifyText("Desktops", By.xpath("//h2[text()='Desktops']"), "User is not able to see Desktops");

        driver.close();
    }

    @Test
    public void verifyUserShouldNavigateToLaptopsAndNotebooksPageSuccessfully() {
        // Mouse Hover on "Laptops & Notebooks" and click on "show allLaptos and Notebooks"
        doMouseHoverAndClick(By.xpath("//a[text()='Laptops & Notebooks']"));
        selectMenu("Show AllLaptops & Notebooks");
        // Verify the Text 'Desktops
        verifyText("Laptops & Notebooks", By.xpath("//h2[text()='Laptops & Notebooks']"), "User is not able seeLaptops & Notebooks");
        driver.close();
    }

    @Test
    public void verifyUserShouldNavigateToComponentsPageSuccessfully(){
        // Mouse Hover on "Componant" and click on "Show allComponant"
        doMouseHoverAndClick(By.xpath("//a[text()='Components']"));
        selectMenu("Show AllComponents");
        // Verify the Text 'Desktops
        verifyText("Components", By.xpath("//h2[text()='Components']"), "User can't see Components");
        driver.close();
    }

    @After
    public void tearDown(){
        closeBrowser();
    }
}
