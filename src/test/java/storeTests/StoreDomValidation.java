package storeTests;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utility.ReusableMethods;

public class StoreDomValidation extends ReusableMethods {

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

    @Test(priority = 1)
    public void navigateAndLogin() throws IOException, TimeoutException {
        startTest("Navigate and Login for DOM validation");
        openBrowser("chrome", "https://deepa.aurumconnect.in/");

        // reuse checks from Store.loginTest to reach dashboard
        verifyIsDisplayed("//p[text()='Deepa Jewellers']", "Deepa Jewellers header");
        click("//a[text()='Login']", "Login link");
        verifyIsDisplayed("//h3[text()='Login']", "Login label");

        clearNenterText("//button[text()='+91']/..//input[@placeholder='9999988888']", "9392190045",
                "Mobile number field");
        click("//button[text()='Send OTP']", "send OTP button");

        // enter OTP
        String otp = "860821";
        clearNenterText("(//div[@role='group']//input)[1]", "" + otp.charAt(0), "otp 1st character");
        clearNenterText("(//div[@role='group']//input)[2]", "" + otp.charAt(1), "otp 2nd character");
        clearNenterText("(//div[@role='group']//input)[3]", "" + otp.charAt(2), "otp 3rd character");
        clearNenterText("(//div[@role='group']//input)[4]", "" + otp.charAt(3), "otp 4th character");
        clearNenterText("(//div[@role='group']//input)[5]", "" + otp.charAt(4), "otp 5th character");
        clearNenterText("(//div[@role='group']//input)[6]", "" + otp.charAt(5), "otp 6th character");

        click("//button[text()='Login']", "Login button");
        verifyIsDisplayed("//span[text()='Dashboard']/..", "dashboard button");
        click("//span[text()='Dashboard']/..", "dashboard button");

        logScreenshot();
    }

    @Test(priority = 2)
    public void eventsDomValidation() {
        startTest("Events DOM validation");

        clickByCSS(".max-xl\\3Ahidden > .w-full", "Features expand");
        // navigate to Events
        click("//span[text()='Events']", "Events link");

        // Assertions based on Dom.java snapshot
        // Heading
        verifyIsDisplayed("//h3[text()='Store Events & Exhibitions']", "Store Events & Exhibitions heading");

        // Add Exhibition button
        verifyIsDisplayed("//button[contains(text(),'Add Exhibition') or .//text()[contains(.,'Add Exhibition')]]",
                "Add Exhibition button");

        // Search input and placeholder
        verifyIsDisplayed("//input[@id='Search exhibitions']", "Search exhibitions input");
        verifyIsDisplayed("//label[@for='Search exhibitions']//span[text()='Search exhibitions']",
                "Search exhibitions label");

        // Select State and City react-select inputs
        verifyIsDisplayed("//input[@id='exhibition-state']", "Select State input");
        verifyIsDisplayed("//div[contains(@class,'react-select__placeholder') and text()='Search States']",
                "Select State placeholder");

        verifyIsDisplayed("//input[@id='exhibition-city']", "Select City input");
        verifyIsDisplayed("//div[contains(@class,'react-select__placeholder') and text()='Search Cities']",
                "Select City placeholder");

        // Clear and Apply Filters buttons disabled state
        verifyIsDisplayed("//button[text()='Clear' and (@disabled or contains(@class,'cursor-not-allowed'))]",
                "Clear button disabled");
        verifyIsDisplayed("//button[text()='Apply Filters' and (@disabled or contains(@class,'cursor-not-allowed'))]",
                "Apply Filters button disabled");

        // No Exhibitions Found message
        verifyIsDisplayed("//h1[text()='No Exhibitions Found']", "No Exhibitions Found message");

        logScreenshot();
    }

    @Test(priority = 3)
    public void addExhibitionScreenFieldValidations() {
        startTest("Add Exhibition - verify form, fill values and create");

        // Click Add Exhibition
        click("//button[text()='Add Exhibition']", "Add Exhibition button");

        // Verify form heading and back button
        verifyIsDisplayed("//h3[text()='Add New Exhibition']", "Add New Exhibition heading");
        verifyIsDisplayed("//h3[text()='Add New Exhibition']/..//button", "back arrow button");

        // Event Type - radio group (label + radios)
        verifyIsDisplayed("//label[text()='Event Type']", "Label - Event Type");
        verifyIsDisplayed("//input[@id='eventType EXHIBITION']", "Exhibition radio button");
        verifyIsDisplayed("//label[@for='eventType EXHIBITION']", "Exhibition radio label");
        verifyIsDisplayed("//input[@id='eventType EVENT']", "Event radio buttton");
        verifyIsDisplayed("//label[@for='eventType EVENT']", "Event radio label");

        // Exhibition / Event Name
        verifyIsDisplayed("//label[text()='Exhibition/Event Name']", "Label - Exhibition/Event Name");
        verifyIsDisplayed("//label[text()='Exhibition/Event Name']/..//span[text()='*']", "Mandatory - Exhibition/Event Name");
        verifyIsDisplayed("//input[@id='Exhibition/Event Name']", "Exhibition/Event Name input");

        // Venue
        verifyIsDisplayed("//label[text()='Venue']", "Label - Venue");
        // Venue may be mandatory in some flows; check for asterisk if present
        verifyIsDisplayed("//label[text()='Venue']/..//span[text()='*']", "Mandatory - Venue");
        verifyIsDisplayed("//textarea[@id='Venue']", "Venue textarea");

        // Description
        verifyIsDisplayed("//label[text()='Description']", "Label - Description");
        verifyIsDisplayed("//label[text()='Description']/..//span[text()='*']", "Mandatory - Description");
        verifyIsDisplayed("//textarea[@id='Description']", "Description textarea");

        // Start Date & Time
        verifyIsDisplayed("//label[text()='Start date']", "Label - Start Date & Time");
        verifyIsDisplayed("//label[text()='Start date']/..//span[text()='*']", "Mandatory - Start Date & Time");
        verifyIsDisplayed("//input[@placeholder='Select start date and time']", "Start Date & Time input");

        // End Date & Time
        verifyIsDisplayed("//label[text()='End date']", "Label - End Date & Time");
        verifyIsDisplayed("//label[text()='End date']/..//span[text()='*']", "Mandatory - End Date & Time");
        verifyIsDisplayed("//input[@placeholder='Select end date and time']", "End Date & Time input");

        // State
        verifyIsDisplayed("//label[text()='State']", "Label - State");
        verifyIsDisplayed("//label[text()='State']/..//span[text()='*']", "Mandatory - State");
        verifyIsDisplayed("//input[@id='exhibitionState' or @id='exhibition-state' or @id='exhibition-state-input']", "State input");

        // City
        verifyIsDisplayed("//label[text()='City']", "Label - City");
        verifyIsDisplayed("//label[text()='City']/..//span[text()='*']", "Mandatory - City");
        verifyIsDisplayed("//input[@id='exhibitionCity' or @id='exhibition-city' or @id='exhibition-city-input']", "City input");

        // Mobile
        verifyIsDisplayed("//label[text()='Mobile']", "Label - Mobile Number");
        verifyIsDisplayed("//label[text()='Mobile']/..//span[text()='*']", "Mandatory - Mobile Number");
        verifyIsDisplayed("//input[@id='Mobile Number' or @name='mobile' or contains(@placeholder,'Mobile')]", "Mobile Number input");

        // WhatsApp
        verifyIsDisplayed("//label[text()='WhatsApp']", "Label - WhatsApp Number");
        verifyIsDisplayed("//label[text()='WhatsApp']/..//span[text()='*']", "Mandatory - WhatsApp Number");
        verifyIsDisplayed("//input[@id='WhatsApp Number' or contains(@placeholder,'WhatsApp')]", "WhatsApp Number input");

        // Location
        verifyIsDisplayed("//label[text()='Location']", "Label - Location");
        verifyIsDisplayed("//label[text()='Location']/..//span[text()='*']", "Mandatory - Location");
        verifyIsDisplayed("//input[@id='Location' or contains(@placeholder,'Location')]", "Location input");

        // Thumbnail
        verifyIsDisplayed("//label[text()='Thumbnail']", "Label - Thumbnail");
        verifyIsDisplayed("//label[text()='Thumbnail']/..//span[text()='*']", "Mandatory - Thumbnail");
        verifyIsDisplayed("//input[@id='thumbnail-image' or (@type='file' and contains(@id,'thumbnail'))]", "Thumbnail file input");

        // Gallery images
        verifyIsDisplayed("//label[text()='Gallery']", "Label - Gallery Images");
        verifyIsDisplayed("//label[text()='Gallery']/..//span[text()='*']", "Mandatory - Gallery Images");
        verifyIsDisplayed("//input[@id='gallery-images' or (@type='file' and contains(@id,'gallery'))]", "Gallery file input");

        // Custom button text & link
        verifyIsDisplayed("//label[text()='Custom Button Text']", "Label - Custom Button Text");
        verifyIsDisplayed("//label[text()='Custom Button Text']/..//span[text()='*']", "Mandatory - Custom Button Text");
        verifyIsDisplayed("//input[@id='Custom Button Text']", "Custom Button Text input");

        verifyIsDisplayed("//label[text()='Custom Button Link']", "Label - Custom Button Link");
        verifyIsDisplayed("//label[text()='Custom Button Link']/..//span[text()='*']", "Mandatory - Custom Button Link");
        verifyIsDisplayed("//input[@id='Custom Button Link']", "Custom Button Link input");

        // Create button
        verifyIsDisplayed("//button[normalize-space(text())='Create Exhibition']", "Create Exhibition button");

        // Select Exhibition radio and fill example values (to keep behavior same as before)
        click("//input[@id='eventType EXHIBITION']", "Event Type - Exhibition");

        clearNenterText("//input[@id='Exhibition/Event Name']", "Test Exhibition - Auto", "Exhibition/Event Name");
        clearNenterText("//textarea[@id='Venue']", "Test Venue, Sample Street, Bengaluru", "Venue");
        clearNenterText("//textarea[@id='Description']", "Automated test exhibition created by StoreDomValidation.", "Description");

        try {
            ((JavascriptExecutor) driver).executeScript("document.querySelector(\"input[placeholder='Select start date and time']\").removeAttribute('readonly');");
            ((JavascriptExecutor) driver).executeScript("document.querySelector(\"input[placeholder='Select end date and time']\").removeAttribute('readonly');");
        } catch (Exception e) {
            // ignore if not present
        }

        clearNenterText("//input[@placeholder='Select start date and time']", "20-03-2026 10:00", "Start Date & Time");
        clearNenterText("//input[@placeholder='Select end date and time']", "21-03-2026 18:00", "End Date & Time");

        clearNenterText("//input[@id='exhibitionState']", "Karnataka", "Select State");
        clearNenterText("//input[@id='exhibitionCity']", "Bengaluru", "Select City");

        clearNenterText("//input[@id='Mobile Number']", "9898989898", "Mobile Number");
        clearNenterText("//input[@id='WhatsApp Number']", "9898989898", "WhatsApp Number");

        clearNenterText("//input[@id='Location']", "Test Exhibition Location, Bengaluru", "Location");
        clearNenterText("//input[@id='Custom Button Text']", "Register Now", "Custom Button Text");
        clearNenterText("//input[@id='Custom Button Link']", "https://example.com/register", "Custom Button Link");

        try {
            String thumbPath = "C:\\aurumstreet\\mlocal\\ss\\image.png";
            driver.findElement(By.id("thumbnail-image")).sendKeys(thumbPath);
            getTest().log(com.relevantcodes.extentreports.LogStatus.INFO, "Uploaded thumbnail: " + thumbPath);
        } catch (Exception e) {
            getTest().log(com.relevantcodes.extentreports.LogStatus.INFO, "Thumbnail upload skipped or failed: " + e.getMessage());
        }

        try {
            String galleryPath = "C:\\aurumstreet\\mlocal\\ss\\image.png";
            driver.findElement(By.id("gallery-images")).sendKeys(galleryPath);
            getTest().log(com.relevantcodes.extentreports.LogStatus.INFO, "Uploaded gallery image: " + galleryPath);
        } catch (Exception e) {
            getTest().log(com.relevantcodes.extentreports.LogStatus.INFO, "Gallery upload skipped or failed: " + e.getMessage());
        }

        // Submit
        click("//button[normalize-space(text())='Create Exhibition']", "Create Exhibition button");

        verifyIsDisplayed("//button[normalize-space(text())='Back to Events']", "Back to Events button");

        logScreenshot();
    }

    public void addExhibition() {
        startTest("Add Exhibition - verify form, fill values and create");

        // Click Add Exhibition
        click("//button[contains(text(),'Add Exhibition') or .//text()[contains(.,'Add Exhibition')]]", "Add Exhibition button");

        // Verify form heading and back button
        verifyIsDisplayed("//h3[text()='Add New Exhibition']", "Add New Exhibition heading");
        verifyIsDisplayed("//h3[text()='Add New Exhibition']/..//button", "back arrow button");

        // Event Type - radio group (label + radios)
        verifyIsDisplayed("//label[text()='Event Type']", "Label - Event Type");
        verifyIsDisplayed("//input[@id='eventType EXHIBITION']", "Exhibition radio button");
        verifyIsDisplayed("//label[@for='eventType EXHIBITION']", "Exhibition radio label");
        verifyIsDisplayed("//input[@id='eventType EVENT']", "Event radio buttton");
        verifyIsDisplayed("//label[@for='eventType EVENT']", "Event radio label");

        // Exhibition / Event Name
        verifyIsDisplayed("//label[text()='Exhibition/Event Name']", "Label - Exhibition/Event Name");
        verifyIsDisplayed("//label[text()='Exhibition/Event Name']/..//span[text()='*']", "Mandatory - Exhibition/Event Name");
        verifyIsDisplayed("//input[@id='Exhibition/Event Name']", "Exhibition/Event Name input");

        // Venue
        verifyIsDisplayed("//label[text()='Venue']", "Label - Venue");
        verifyIsDisplayed("//label[text()='Venue']/..//span[text()='*']", "Mandatory - Venue");
        verifyIsDisplayed("//textarea[@id='Venue']", "Venue textarea");

        // Description
        verifyIsDisplayed("//label[text()='Description']", "Label - Description");
        verifyIsDisplayed("//label[text()='Description']/..//span[text()='*']", "Mandatory - Description");
        verifyIsDisplayed("//textarea[@id='Description']", "Description textarea");

        // Start Date & Time
        verifyIsDisplayed("//label[text()='Start date']", "Label - Start Date & Time");
        verifyIsDisplayed("//label[text()='Start date']/..//span[text()='*']", "Mandatory - Start Date & Time");
        verifyIsDisplayed("//input[@placeholder='Select start date and time']", "Start Date & Time input");

        // End Date & Time
        verifyIsDisplayed("//label[text()='End date']", "Label - End Date & Time");
        verifyIsDisplayed("//label[text()='End date']/..//span[text()='*']", "Mandatory - End Date & Time");
        verifyIsDisplayed("//input[@placeholder='Select end date and time']", "End Date & Time input");

        // State
        verifyIsDisplayed("//label[text()='State']", "Label - State");
        verifyIsDisplayed("//label[text()='State']/..//span[text()='*']", "Mandatory - State");
        verifyIsDisplayed("//input[@id='exhibitionState' or @id='exhibition-state' or @id='exhibition-state-input']", "State input");

        // City
        verifyIsDisplayed("//label[text()='City']", "Label - City");
        verifyIsDisplayed("//label[text()='City']/..//span[text()='*']", "Mandatory - City");
        verifyIsDisplayed("//input[@id='exhibitionCity' or @id='exhibition-city' or @id='exhibition-city-input']", "City input");

        // Mobile
        verifyIsDisplayed("//label[text()='Mobile']", "Label - Mobile Number");
        verifyIsDisplayed("//label[text()='Mobile']/..//span[text()='*']", "Mandatory - Mobile Number");
        verifyIsDisplayed("//input[@id='Mobile Number' or @name='mobile' or contains(@placeholder,'Mobile')]", "Mobile Number input");

        // WhatsApp
        verifyIsDisplayed("//label[text()='WhatsApp']", "Label - WhatsApp Number");
        verifyIsDisplayed("//label[text()='WhatsApp']/..//span[text()='*']", "Mandatory - WhatsApp Number");
        verifyIsDisplayed("//input[@id='WhatsApp Number' or contains(@placeholder,'WhatsApp')]", "WhatsApp Number input");

        // Location
        verifyIsDisplayed("//label[text()='Location']", "Label - Location");
        verifyIsDisplayed("//label[text()='Location']/..//span[text()='*']", "Mandatory - Location");
        verifyIsDisplayed("//input[@id='Location' or contains(@placeholder,'Location')]", "Location input");

        // Thumbnail
        verifyIsDisplayed("//label[text()='Thumbnail']", "Label - Thumbnail");
        verifyIsDisplayed("//label[text()='Thumbnail']/..//span[text()='*']", "Mandatory - Thumbnail");
        verifyIsDisplayed("//input[@id='thumbnail-image' or (@type='file' and contains(@id,'thumbnail'))]", "Thumbnail file input");

        // Gallery images
        verifyIsDisplayed("//label[text()='Gallery']", "Label - Gallery Images");
        verifyIsDisplayed("//label[text()='Gallery']/..//span[text()='*']", "Mandatory - Gallery Images");
        verifyIsDisplayed("//input[@id='gallery-images' or (@type='file' and contains(@id,'gallery'))]", "Gallery file input");

        // Custom button text & link
        verifyIsDisplayed("//label[text()='Custom Button Text']", "Label - Custom Button Text");
        verifyIsDisplayed("//label[text()='Custom Button Text']/..//span[text()='*']", "Mandatory - Custom Button Text");
        verifyIsDisplayed("//input[@id='Custom Button Text']", "Custom Button Text input");

        verifyIsDisplayed("//label[text()='Custom Button Link']", "Label - Custom Button Link");
        verifyIsDisplayed("//label[text()='Custom Button Link']/..//span[text()='*']", "Mandatory - Custom Button Link");
        verifyIsDisplayed("//input[@id='Custom Button Link']", "Custom Button Link input");

        // Create button
        verifyIsDisplayed("//button[normalize-space(text())='Create Exhibition']", "Create Exhibition button");

        // Select Exhibition radio
        click("//input[@id='eventType EXHIBITION']", "Event Type - Exhibition");

        // Fill example values
        clearNenterText("//input[@id='Exhibition/Event Name']", "Test Exhibition - Auto", "Exhibition/Event Name");
        clearNenterText("//textarea[@id='Venue']", "Test Venue, Sample Street, Bengaluru", "Venue");
        clearNenterText("//textarea[@id='Description']", "Automated test exhibition created by StoreDomValidation.", "Description");

        try {
            ((JavascriptExecutor) driver).executeScript("document.querySelector(\"input[placeholder='Select start date and time']\").removeAttribute('readonly');");
            ((JavascriptExecutor) driver).executeScript("document.querySelector(\"input[placeholder='Select end date and time']\").removeAttribute('readonly');");
        } catch (Exception e) {
            // ignore if not present
        }

        clearNenterText("//input[@placeholder='Select start date and time']", "20-03-2026 10:00", "Start Date & Time");
        clearNenterText("//input[@placeholder='Select end date and time']", "21-03-2026 18:00", "End Date & Time");

        clearNenterText("//input[@id='exhibitionState']", "Karnataka", "Select State");
        clearNenterText("//input[@id='exhibitionCity']", "Bengaluru", "Select City");

        clearNenterText("//input[@id='Mobile Number']", "9898989898", "Mobile Number");
        clearNenterText("//input[@id='WhatsApp Number']", "9898989898", "WhatsApp Number");

        clearNenterText("//input[@id='Location']", "Test Exhibition Location, Bengaluru", "Location");
        clearNenterText("//input[@id='Custom Button Text']", "Register Now", "Custom Button Text");
        clearNenterText("//input[@id='Custom Button Link']", "https://example.com/register", "Custom Button Link");

        try {
            String thumbPath = "C:\\aurumstreet\\mlocal\\ss\\image.png";
            driver.findElement(By.id("thumbnail-image")).sendKeys(thumbPath);
            getTest().log(com.relevantcodes.extentreports.LogStatus.INFO, "Uploaded thumbnail: " + thumbPath);
        } catch (Exception e) {
            getTest().log(com.relevantcodes.extentreports.LogStatus.INFO, "Thumbnail upload skipped or failed: " + e.getMessage());
        }

        try {
            String galleryPath = "C:\\aurumstreet\\mlocal\\ss\\image.png";
            driver.findElement(By.id("gallery-images")).sendKeys(galleryPath);
            getTest().log(com.relevantcodes.extentreports.LogStatus.INFO, "Uploaded gallery image: " + galleryPath);
        } catch (Exception e) {
            getTest().log(com.relevantcodes.extentreports.LogStatus.INFO, "Gallery upload skipped or failed: " + e.getMessage());
        }

        // Submit
        click("//button[normalize-space(text())='Create Exhibition']", "Create Exhibition button");

        verifyIsDisplayed("//button[normalize-space(text())='Back to Events']", "Back to Events button");

        logScreenshot();
    }





}