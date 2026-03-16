package storeTests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utility.ReusableMethods;

public class Login extends ReusableMethods {

    @BeforeClass
    public void prerequisites() {
        initialise();
    }

    @AfterClass
    public void quitDriver() {
        driver.quit();
    }

    @AfterMethod
    public void afterTest() {
        endTest();
    }
    @Test(priority = 2)
    public void dashboardAndSidebarValidation() {
        startTest("Dashboard and sidebar/hamburger validation");

        // Click Dashboard from top bar
        click("//span[text()='Dashboard']/..", "dashboard button");

        // Top/dashboard area validations
        verifyIsDisplayed("//span[text()='Redeem Request']", "Redeem Request button");
        verifyIsDisplayed("//span[text()='Redeem Request']/../..//button[1]", "QR scanner button");
        verifyIsDisplayed("//span[text()='Redeem Request']/../..//button[2]", "Share button");

        logScreenshot();

        // Expand hamburger/features to reveal full menu
        clickByCSS(".max-xl\\3Ahidden > .w-full", "features expand");

        // Validate sidebar links present when expanded
        verifyIsDisplayed("//span[text()='Dashboard']", "Dashboard link");
        verifyIsDisplayed("//span[text()='Categories']", "Categories link");
        verifyIsDisplayed("//span[text()='eOffer Card']", "eOffer Card link");
        verifyIsDisplayed("//span[text()='Roles and Staff']", "Roles and Staff link");
        verifyIsDisplayed("//span[text()='Customers']", "Customers link");
        verifyIsDisplayed("//span[text()='Customer Enrollments']", "Customer Enrollments link");
        verifyIsDisplayed("//span[text()='Redeems']", "Redeems link");
        verifyIsDisplayed("//span[text()='Stores']", "Stores link");
        verifyIsDisplayed("//span[text()='Metal Prices']", "Metal Prices link");
        verifyIsDisplayed("//span[text()='Audience']", "Audience link");
        verifyIsDisplayed("//span[text()='Ads']", "Ads link");
        verifyIsDisplayed("//span[text()='Guess Me']", "Guess Me link");
        verifyIsDisplayed("//span[text()='Events']", "Events link");
        verifyIsDisplayed("//span[text()='Offers']", "Offers link");
        verifyIsDisplayed("//span[text()='Reviews']", "Reviews link");
        verifyIsDisplayed("//span[text()='Gift Cards']", "Gift Cards link");
        verifyIsDisplayed("//span[text()='Price Protection']", "Price Protection link");
        verifyIsDisplayed("//span[text()='Terms Documents']", "Terms Documents link");

        logScreenshot();

        // Collapse the menu and validate that some previously visible links are hidden
        clickByCSS(".max-xl\\3Ahidden > .w-full", "features collapse");

        boolean categoriesVisible = isDisplayed("//span[text()='Categories']");
        if (!categoriesVisible) {
            logPass("Categories link is hidden after collapse");
        } else {
            logFail("Categories link is still visible after collapse" + addScreenShot());
        }

        boolean eventsVisible = isDisplayed("//span[text()='Events']");
        if (!eventsVisible) {
            logPass("Events link is hidden after collapse");
        } else {
            logFail("Events link is still visible after collapse" + addScreenShot());
        }

        logScreenshot();
    }
}