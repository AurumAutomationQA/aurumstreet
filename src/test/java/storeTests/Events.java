package storeTests;

import java.util.concurrent.ThreadLocalRandom;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utility.ReusableMethods;

public class Events extends ReusableMethods {

    @BeforeClass
    public void prerequisites() {
        initialise();
        startTest("Login");
        loginToStore();
        endTest();
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
    public void navigateAndValidateExhibitions() {
        startTest("Events screen validation");

        click("//span[text()='Dashboard']/..", "dashboard button");
        clickByCSS(".max-xl\\3Ahidden > .w-full", "features expand");
        click("//span[text()='Events']", "Events link");

        verifyIsDisplayed("//h3[text()='Store Events & Exhibitions']", "Store Events & Exhibitions heading");

        // Add Exhibition button
        verifyIsDisplayed("//button[text()='Add Exhibition']","Add Exhibition button");
        verifyIsEnabled("//button[text()='Add Exhibition']", "Add Exhibition button enabled");
        
        // Search input and placeholder
        verifyIsDisplayed("//span[text()='Search exhibitions']","Search exhibitions label");
        verifyIsDisplayed("//input[@id='Search exhibitions']", "Search exhibitions input");
        verifyIsEnabled("//input[@id='Search exhibitions']", "Search exhibitions input");
		verifyIsDisplayed("//input[@placeholder='Search by name, venue, description']","Search by name, venue, description placeholder");

		// Select State and City react-select inputs
        verifyIsDisplayed("//input[@id='exhibition-state']", "Select State input");
        verifyIsEnabled("//input[@id='exhibition-state']", "Select State input");
        verifyIsDisplayed("//div[contains(@class,'react-select__placeholder') and text()='Search States']",
                "Select State placeholder");

        verifyIsDisplayed("//input[@id='exhibition-city']", "Select City input");
        verifyIsEnabled("//input[@id='exhibition-city']", "Select City input");
        verifyIsDisplayed("//div[contains(@class,'react-select__placeholder') and text()='Search Cities']",
                "Select City placeholder");

        // Clear and Apply Filters buttons disabled state
        verifyIsDisplayed("//button[text()='Clear']", "Clear button");
		verifyIsDisabled("//button[text()='Clear']", "Clear button");
        
        verifyIsDisplayed("//button[text()='Apply Filters']", "Apply Filters button");
        verifyIsDisabled("//button[text()='Apply Filters']", "Apply Filters button");

        
        logScreenshot();
    }
    
    @Test(priority = 2)
    public void navigateAndValidateAddExhibitions() {
        startTest("Add Exhibition screen validation");

        // Click Add Exhibition
        click("//button[text()='Add Exhibition']", "Add Exhibition button");

        // Verify form heading and back button
        verifyIsDisplayed("//h3[text()='Add New Exhibition']", "Add New Exhibition heading");
        verifyIsDisplayed("//h3[text()='Add New Exhibition']/..//button", "back arrow button");

        // Event Type - radio group (label + radios)
        verifyIsDisplayed("//span[text()='Event Type']", "Label - Event Type");
        verifyIsDisplayed("//span[text()='Event Type']/..//span[text()='*']", "Mandatory - Event Type");
        
        verifyIsDisplayed("//input[@id='eventType EXHIBITION']", "Exhibition radio button");
        verifyIsDisplayed("//label[text()='Exhibition']", "Exhibition radio option");
        verifyIsEnabled("//input[@id='eventType EXHIBITION']", "Exhibition radio button");
        
        verifyIsDisplayed("//input[@id='eventType EVENT']", "Event radio buttton");
        verifyIsDisplayed("//label[text()='Event']", "Event radio label");
        verifyIsEnabled("//input[@id='eventType EVENT']", "Event radio buttton");

        // Exhibition / Event Name
        verifyIsDisplayed("//span[text()='Exhibition/Event Name']", "Label - Exhibition/Event Name");
        verifyIsDisplayed("//span[text()='Exhibition/Event Name']/..//span[text()='*']", "Mandatory - Exhibition/Event Name");
        verifyIsDisplayed("//input[@id='Exhibition/Event Name']", "Exhibition/Event Name input");
        verifyIsDisplayed("//input[@placeholder='Enter exhibition name']", "Placeholder - Enter exhibition name");
        verifyIsEnabled("//input[@id='Exhibition/Event Name']", "Exhibition/Event Name input");
        

        // Venue
        verifyIsDisplayed("//span[text()='Venue']", "Label - Venue");
        verifyIsDisplayed("//span[text()='Venue']/..//span[text()='*']", "Mandatory - Venue");
        verifyIsDisplayed("//textarea[@id='Venue']", "Venue textarea");
        verifyIsEnabled("//textarea[@id='Venue']", "Venue textarea");
        verifyIsDisplayed("//textarea[@placeholder='Enter venue name']", "Placeholder - Venue");
        
        // Description
        verifyIsDisplayed("//span[text()='Description']", "Label - Description");
        verifyIsDisplayed("//span[text()='Description']/..//span[text()='*']", "Mandatory - Description");
        verifyIsDisplayed("//textarea[@id='Description']", "Description textarea");
        verifyIsEnabled("//textarea[@id='Description']", "Description textarea");
        verifyIsDisplayed("//textarea[@placeholder='Enter description']", "Placeholder - Enter description");
        
        
        // Start Date & Time
        verifyIsDisplayed("//span[text()='Start Date & Time']", "Label - Start Date & Time");
        verifyIsDisplayed("//span[text()='Start Date & Time']/..//span[text()='*']", "Mandatory - Start Date & Time");
        verifyIsDisplayed("//input[@placeholder='Select start date and time']", "Start Date & Time input");
        verifyIsEnabled("//input[@placeholder='Select start date and time']", "Start Date & Time input");
        
        // End Date & Time
        verifyIsDisplayed("//span[text()='End Date & Time']", "Label - End Date & Time");
        verifyIsDisplayed("//span[text()='End Date & Time']/..//span[text()='*']", "Mandatory - End Date & Time");
        verifyIsDisplayed("//input[@placeholder='Select end date and time']", "End Date & Time input");
        verifyIsEnabled("//input[@placeholder='Select end date and time']", "End Date & Time input");
        
        // State
        verifyIsDisplayed("//span[text()='Select State']", "Label - Select State");
        verifyIsDisplayed("//span[text()='Select State']/..//span[text()='*']", "Mandatory - Select State");
        verifyIsDisplayed("//input[@id='exhibitionState']", "State input");
        verifyIsDisplayed("//div[text()='Search States']", "Placeholder - Search States");

        // City
        verifyIsDisplayed("//span[text()='Select City']", "Label - Select City");
        verifyIsDisplayed("//span[text()='Select City']/..//span[text()='*']", "Mandatory - Select City");
        verifyIsDisplayed("//input[@id='exhibitionCity']", "City input");
        verifyIsDisplayed("//div[text()='Search Cities']", "Placeholder - Search Cities");

        // Mobile
        verifyIsDisplayed("//span[text()='Mobile Number']", "Label - Mobile Number");
        verifyIsDisplayed("//span[text()='(optional)']", "Label - (optional)");
        verifyIsDisplayed("//span[text()='Mobile Number']/../..//button[text()='+91']", "contry code +91");
        verifyIsDisplayed("//input[@id='Mobile Number']", "Mobile Number input");
        verifyIsEnabled("//input[@id='Mobile Number']", "Mobile Number input");
        verifyIsDisplayed("//span[text()='Mobile Number']/../..//input[@placeholder='9999988888']", "Placeholder for mobile number - 9999988888");
        
        // WhatsApp
        verifyIsDisplayed("//span[text()='WhatsApp Number']", "Label - WhatsApp Number");
        verifyIsDisplayed("//span[text()='WhatsApp Number']/../..//span[text()='(optional)']", "Label - (optional)");
        verifyIsDisplayed("//span[text()='WhatsApp Number']/../..//button[text()='+91']", "contry code +91");
        verifyIsDisplayed("//input[@id='WhatsApp Number']", "WhatsApp Number input");
        verifyIsEnabled("//input[@id='WhatsApp Number']", "WhatsApp Number input");
        verifyIsDisplayed("//span[text()='WhatsApp Number']/../..//input[@placeholder='9999988888']", "Placeholder for WhatsApp Number - 9999988888");
        
        // Location
        verifyIsDisplayed("//span[text()='Location']", "Label - Location");
        verifyIsDisplayed("//span[text()='Location']/../..//span[text()='(optional)']", "Label - (optional)");
        verifyIsDisplayed("//input[@id='Location']", "Location input");
        verifyIsEnabled("//input[@id='Location']", "Location input");
        verifyIsDisplayed("//input[@placeholder='Enter Google Maps URL or address']", "Placeholder - Enter Google Maps URL or address");
        
        // Custom button text & link
        verifyIsDisplayed("//span[text()='Custom Button Text']", "Label - Custom Button Text");
        verifyIsDisplayed("//span[text()='Custom Button Text']/../..//span[text()='(optional)']", "Label - (optional)");
        verifyIsDisplayed("//input[@id='Custom Button Text']", "Custom Button Text input");
        verifyIsEnabled("//input[@id='Custom Button Text']", "Custom Button Text input");
        verifyIsDisplayed("//input[@placeholder='e.g., Register Now, Book Tickets']", "Placeholder - e.g., Register Now, Book Tickets");
        
        verifyIsDisplayed("//span[text()='Custom Button Link']", "Label - Custom Button Link");
        verifyIsDisplayed("//span[text()='Custom Button Link']/../..//span[text()='(optional)']", "Label - (optional)");
        verifyIsDisplayed("//input[@id='Custom Button Link']", "Custom Button Link input");
        verifyIsEnabled("//input[@id='Custom Button Link']", "Custom Button Link input");
        verifyIsDisplayed("//input[@placeholder='Enter registration/booking URL']", "Placeholder - Enter registration/booking URL");
        
        // Thumbnail
        verifyIsDisplayed("//span[text()='Upload Thumbnail Image']", "Label - Upload Thumbnail Image");
        verifyIsDisplayed("//span[text()='Upload main promotional image']", "label - upload main promotional image");

        // Gallery images
        verifyIsDisplayed("//span[text()='Upload Gallery Images']", "Label - Upload Gallery Images");
        verifyIsDisplayed("//span[text()='Upload up to 10 additional images and videos']", "label - Upload up to 10 additional images and videos");

        // Create button
        verifyIsDisplayed("//button[text()='Back to Events']", "Back to Events button");
        verifyIsEnabled("//button[text()='Back to Events']", "Back to Events button");
        
        // Reset button
        verifyIsDisplayed("//button[text()='Reset']", "Reset button");
        verifyIsEnabled("//button[text()='Reset']", "Reset button");
        
        // Create button
        verifyIsDisplayed("//button[text()='Create Exhibition']", "Create Exhibition button");
        verifyIsEnabled("//button[text()='Create Exhibition']", "Create Exhibition button");
        
        
		

        logScreenshot();
    }
    
    @Test(priority = 3)
    public void addExhibitions() throws Exception {
    	
    	  startTest("Add Exhibition flow");
		  click("//input[@id='eventType EXHIBITION']", "Exhibition radio button");
		  clearNenterText("//input[@id='Exhibition/Event Name']", "Event - "+ThreadLocalRandom.current().nextInt(),"Exhibition/Event Name input");
		  clearNenterText("//textarea[@id='Venue']", "Venue - "+ThreadLocalRandom.current().nextInt(),"Venue textarea");
		  clearNenterText("//textarea[@id='Description']", "Description - "+ThreadLocalRandom.current().nextInt(),"Description textarea");
		  
		  click("//input[@placeholder='Select start date and time']", "Start Date & Time input");
		  Thread.sleep(1000);
		  click("//div[contains(@class,'react-datepicker__day--today')]", "Select today's date");
		  
		  
		  click("//input[@placeholder='Select end date and time']", "End Date & Time input");
		  Thread.sleep(1000);
		  click("//div[contains(@class,'react-datepicker__day--today')]", "Select today's date");
		  
		  click("//input[@id='exhibitionState']", "Select State input");
		  Thread.sleep(1000);
		  click("//div[contains(@class,'react-select__option') and text()='Maharashtra']", "Select Maharashtra state");
		  
		  click("//input[@id='exhibitionCity']", "Select City input");
		  Thread.sleep(1000);
		  click("//div[contains(@class,'react-select__option') and text()='Mumbai']", "Select Mumbai city");
		  
		  clearNenterText("//input[@id='Mobile Number']", "9999988888","Mobile Number input");
		  clearNenterText("//input[@id='WhatsApp Number']", "9999988888","WhatsApp Number input");
		  clearNenterText("//input[@id='Location']", "Mumbai","Location input");
		  clearNenterText("//input[@id='Custom Button Text']", "Register Now","Custom Button Text input");
		  clearNenterText("//input[@id='Custom Button Link']", "https://www.google.com","Custom Button Link input");
		  click("//button[text()='Create Exhibition']", "Create Exhibition button");
		 
    }

    
}