package storeTests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utility.ReusableMethods;

public class Offers extends ReusableMethods {

    @BeforeClass
    public void prerequisites() throws Exception {
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
    public void navigateAndValidateOffers() {
        startTest("Navigate to Offers and validate fields");

        click("//span[text()='Dashboard']/..", "dashboard button");
        clickByCSS(".max-xl\\3Ahidden > .w-full", "features expand");
        click("//span[text()='Offers']", "Offers link");

        verifyIsDisplayed("//h3[text()='Offers']", "Offers heading");
        
        verifyIsDisplayed("//button[text()='Add Offer']", "Add Offer button");
        verifyIsEnabled("//button[text()='Add Offer']", "Add Offer button");
        
        verifyIsDisplayed("//span[text()='Search by offer name']", "label - Search by offer name");
        verifyIsDisplayed("//input[@id='Search by offer name']", "Search by offer name input");
        verifyIsEnabled("//input[@id='Search by offer name']", "Search by offer name input");
        verifyIsDisplayed("//input[@placeholder='Search offers']", "placeholder - Search offers");
        
        verifyIsDisplayed("//button[text()='Clear']", "Clear button");
		verifyIsDisabled("//button[text()='Clear']", "Clear button");
        
        verifyIsDisplayed("//button[text()='Apply Filters']", "Apply Filters button");
        verifyIsDisabled("//button[text()='Apply Filters']", "Apply Filters button");

        logScreenshot();

    }
    
    @Test(priority = 2)
    public void navigateAndValidateAddOffer() {
        startTest("Add Offer screen validation");
        
        click("//button[text()='Add Offer']", "Add Offer button");
        
        verifyIsDisplayed("//h3[text()='Add New Offer']", "Add New Offer heading");
        verifyIsDisplayed("//h3[text()='Add New Offer']/..//button", "back arrow button");

        verifyIsDisplayed("//p[text()='Offer Details']", "heading - Offer Details");
        
        verifyIsDisplayed("//span[text()='Offer Name']", "Label - Offer Name");
        verifyIsDisplayed("//span[text()='Offer Name']/..//span[text()='*']", "Mandatory - Offer Name");
        verifyIsDisplayed("//input[@id='Offer Name']", "Offer Name input");
        verifyIsEnabled("//input[@id='Offer Name']", "Offer Name input");
        verifyIsDisplayed("//input[@placeholder='Enter offer name']", "Placeholder - Enter offer name");
        
        verifyIsDisplayed("//span[text()='SKU/ID']", "Label - SKU/ID");
        verifyIsDisplayed("//span[text()='SKU/ID']/..//span[text()='*']", "Mandatory - SKU/ID");
        verifyIsDisplayed("//input[@id='SKU/ID']", "SKU/ID input");
        verifyIsEnabled("//input[@id='SKU/ID']", "SKU/ID input");
        verifyIsDisplayed("//input[@placeholder='Enter SKU/ID']", "Placeholder - Enter SKU/ID");
        
        verifyIsDisplayed("//span[text()='Select Offer Type']", "Label - Select Offer Type");
        verifyIsDisplayed("//span[text()='Select Offer Type']/..//span[text()='*']", "Mandatory - Select Offer Type");
        verifyIsDisplayed("//input[@id='offer-type']", "Select Offer Type input");
        verifyIsEnabled("//input[@id='offer-type']", "Select Offer Type input");
        verifyIsDisplayed("//div[text()='Select Offer Type']", "Placeholder - Select Offer Type");
        
        // Start Date & Time
        verifyIsDisplayed("//span[text()='Offer Start Date']", "Label - Offer Start Date");
        verifyIsDisplayed("//span[text()='Offer Start Date']/..//span[text()='*']", "Mandatory - Offer Start Date");
        verifyIsDisplayed("//input[@placeholder='Select start date']", "Select start date input");
        verifyIsEnabled("//input[@placeholder='Select start date']", "Select start date input");
        
        // End Date & Time
        verifyIsDisplayed("//span[text()='End Date']", "Label - End Date");
        verifyIsDisplayed("//span[text()='End Date']/..//span[text()='*']", "Mandatory - End Date");
        verifyIsDisplayed("//input[@placeholder='Select end date']", "Select end date input");
        verifyIsEnabled("//input[@placeholder='Select end date']", "Select end date input");
        
        // Description
        verifyIsDisplayed("//span[text()='Description']", "Label - Description");
        verifyIsDisplayed("//span[text()='Description']/..//span[text()='*']", "Mandatory - Description");
        verifyIsDisplayed("//textarea[@id='Description']", "Description textarea");
        verifyIsEnabled("//textarea[@id='Description']", "Description textarea");
        verifyIsDisplayed("//textarea[@placeholder='Enter offer description']", "Placeholder - Enter offer description");
        
        // Branches
        verifyIsDisplayed("//span[text()='Branches']", "Label - Branches");
        verifyIsDisplayed("//span[text()='Branches']/..//span[text()='*']", "Mandatory - Branches");
        verifyIsDisplayed("//input[@id='offerBranches']", "Branches input");
        verifyIsEnabled("//input[@id='offerBranches']", "Branches input");
        verifyIsDisplayed("//div[text()='Select Branches']", "Placeholder - Select Branches");
        
        // State
        verifyIsDisplayed("//span[text()='Select State']", "Label - Select State");
        verifyIsDisplayed("//span[text()='Select State']/..//span[text()='*']", "Mandatory - Select State");
        verifyIsDisplayed("//input[@id='offer-state']", "State input");
        verifyIsEnabled("//input[@id='offer-state']", "State input");
        verifyIsDisplayed("//div[text()='Search States']", "Placeholder - Search States");

        // City
        verifyIsDisplayed("//span[text()='Select City']", "Label - Select City");
        verifyIsDisplayed("//span[text()='Select City']/..//span[text()='*']", "Mandatory - Select City");
        verifyIsDisplayed("//input[@id='offer-city']", "City input");
        verifyIsEnabled("//input[@id='offer-city']", "City input");
        verifyIsDisplayed("//div[text()='Search Cities']", "Placeholder - Search Cities");

        // occasions
        verifyIsDisplayed("//span[text()='Select Occasion']", "Label - Select Occasion");
        verifyIsDisplayed("//input[@id='offer-occasion']", "Occasion input");
        verifyIsEnabled("//input[@id='offer-occasion']", "Occasion input");
        verifyIsDisplayed("//div[text()='Search Occasion']", "Placeholder - Search Occasion");
        
        
        verifyIsDisplayed("//p[text()='Category Selection']", "heading - Category Selection");
        
        // Select Metal Category
        verifyIsDisplayed("//span[text()='Select Metal Category']", "Label - Select Metal Category");
        verifyIsDisplayed("//input[@id='offer-metal-category']", "Select Metal Category input");
        verifyIsEnabled("//input[@id='offer-metal-category']", "Select Metal Category input");
        verifyIsDisplayed("//div[text()='Search Metal Category']", "Placeholder - Select Metal Category");
        
        // Select Metal Type
        verifyIsDisplayed("//span[text()='Select Metal Type']", "Label - Select Metal Type");
        verifyIsDisplayed("//input[@id='offer-metal-type']", "Select Metal Type input");
        verifyIsEnabled("//input[@id='offer-metal-type']", "Select Metal Type input");
        verifyIsDisplayed("//div[text()='Search Metal Type']", "Placeholder - Select Metal Type");
        
        // Select Category
        verifyIsDisplayed("//span[text()='Select Category']", "Label - Select Category");
        verifyIsDisplayed("//input[@id='offer-category']", "Select Category input");
        verifyIsEnabled("//input[@id='offer-category']", "Select Category input");
        verifyIsDisplayed("//div[text()='Search Category']", "Placeholder - Select Category");
        
        // Select sub Category
        verifyIsDisplayed("//span[text()='Select Sub Category']", "Label - Select Sub Category");
        verifyIsDisplayed("//input[@id='offer-sub-category']", "Select Sub Category input");
        verifyIsEnabled("//input[@id='offer-sub-category']", "Select Sub Category input");
        verifyIsDisplayed("//div[text()='Search Sub Category']", "Placeholder - Select Sub Category");
        
        // Select Types of Karates
        verifyIsDisplayed("//span[text()='Select Types of Karates']", "Label - Select Types of Karates");
        verifyIsDisplayed("//input[@id='offer-karates']", "Select Types of Karates input");
        verifyIsEnabled("//input[@id='offer-karates']", "Select Types of Karates input");
        verifyIsDisplayed("//div[text()='Search Types of Karates']", "Placeholder - Search Types of Karates");
        
        // Select Demographics
        verifyIsDisplayed("//span[text()='Select Demographics']", "Label - Select Demographics");
        verifyIsDisplayed("//input[@id='offer-demographics']", "Select Demographics input");
        verifyIsEnabled("//input[@id='offer-demographics']", "Select Demographics input");
        verifyIsDisplayed("//div[text()='Search Demographics']", "Placeholder - Search Demographics");
        
        // Select Certifications
        verifyIsDisplayed("//span[text()='Select Certifications']", "Label - Select Certifications");
        verifyIsDisplayed("//input[@id='offer-demographics']", "Select Certifications input");
        verifyIsEnabled("//input[@id='offer-demographics']", "Select Certifications input");
        verifyIsDisplayed("//div[text()='Search Certifications']", "Placeholder - Search Certifications");
        
        verifyIsDisplayed("//p[text()='Contact Information']", "heading - Contact Information");
        
        // Mobile
        verifyIsDisplayed("//span[text()='Mobile Number']", "Label - Mobile Number");
        verifyIsDisplayed("//span[text()='Mobile Number']/../..//button[text()='+91']", "contry code +91");
        verifyIsDisplayed("//input[@id='mobileNumber']", "Mobile Number input");
        verifyIsEnabled("//input[@id='mobileNumber']", "Mobile Number input");
        verifyIsDisplayed("//span[text()='Mobile Number']/../..//input[@placeholder='9999988888']", "Placeholder for mobile number - 9999988888");
        
        // WhatsApp
        verifyIsDisplayed("//span[text()='Whatsapp Number']", "Label - Whatsapp Number");
        verifyIsDisplayed("//span[text()='Whatsapp Number']/../..//button[text()='+91']", "contry code +91");
        verifyIsDisplayed("//input[@id='whatsappNumber']", "WhatsApp Number input");
        verifyIsEnabled("//input[@id='whatsappNumber']", "WhatsApp Number input");
        verifyIsDisplayed("//span[text()='Whatsapp Number']/../..//input[@placeholder='9999988888']", "Placeholder for WhatsApp Number - 9999988888");
       
        verifyIsDisplayed("//p[text()='Offer Terms & Conditions']", "heading - Offer Terms & Conditions");
        verifyIsDisplayed("//span[text()='Offer Terms & Conditions']", "label - Offer Terms & Conditions");
        verifyIsDisplayed("//span[text()='Offer Terms & Conditions']/..//span[text()='*']", "Mandatory - Offer Terms & Conditions");
        verifyIsDisplayed("//div[contains(@class,'RichTextEditor-toolbar')]", "RichTextEditor-toolbar");
        verifyIsDisplayed("//div[@contenteditable='true']", "Editable container");
        
        verifyIsDisplayed("//p[text()='Offer Images']", "heading - Offer Images");
        verifyIsDisplayed("//p[text()='Offer Images']/..//span[text()='*']", "Mandatory - Offer Images");
        
        
     // Thumbnail
        verifyIsDisplayed("//span[text()='Upload Offer Thumbnail']", "Label - Upload Offer Thumbnail");
        verifyIsDisplayed("//span[text()='Upload only 1 Offer Thumbnail']", "label - Upload only 1 Offer Thumbnail");

        // Gallery images
        verifyIsDisplayed("//span[text()='Upload Offer Attachments']", "Label - Upload Offer Attachments");
        verifyIsDisplayed("//span[text()='Upload up to 10 Offer Attachments']", "label - Upload up to 10 Offer Attachments");

        // Cancel button
        verifyIsDisplayed("//button[text()='Cancel']", "Cancel button");
        verifyIsEnabled("//button[text()='Cancel']", "Cancel button");
        
        // Cancel button
        verifyIsDisplayed("//button[text()='Create Offer']", "Create Offer button");
        verifyIsEnabled("//button[text()='Create Offer']", "Create Offer button");
        
        
        
        logScreenshot();
    }
    
    @Test(priority = 3)
    public void selectOfferTypeUiValidations() {
        startTest("select offer type and validate UI changes");
        
        click("//input[@id='offer-type']", "Select Offer Type input");
        
        sleep(2);

        click("//div[text()='Making & VA Details']", "option - Making & VA Details");
        
        verifyIsDisplayed("//p[text()='Making and VA']", "heading - Making and VA");
        verifyIsDisplayed("//p[text()='Select atleast one Checkbox']", "Label - Select atleast one Checkbox");
        
        verifyIsDisplayed("//label[text()='VA']/../..//input[@type='checkbox']", "Checkbox - VA");
        verifyIsEnabled("//label[text()='VA']/../..//input[@type='checkbox']", "Checkbox - VA");
        verifyIsDisplayed("//label[text()='VA']", "Label - VA");
        
        verifyIsDisplayed("//label[text()='MAKING']/../..//input[@type='checkbox']", "Checkbox - MAKING");
        verifyIsEnabled("//label[text()='MAKING']/../..//input[@type='checkbox']", "Checkbox - MAKING");
        verifyIsDisplayed("//label[text()='MAKING']", "Label - MAKING");
        
        verifyIsDisplayed("//label[text()='GENERAL']/../..//input[@type='checkbox']", "Checkbox - GENERAL");
        verifyIsEnabled("//label[text()='GENERAL']/../..//input[@type='checkbox']", "Checkbox - GENERAL");
        verifyIsDisplayed("//label[text()='GENERAL']", "Label - GENERAL");
        
        // Select Stones
        verifyIsDisplayed("//span[text()='Select Stones']", "Label - Select Stones");
        verifyIsDisplayed("//input[@id='va-stones']", "Select Stones input");
        verifyIsEnabled("//input[@id='va-stones']", "Select Stones input");
        verifyIsDisplayed("//div[text()='Search Stones']", "Placeholder - Search Stones");
        
        // Avaiable Sizes
        verifyIsDisplayed("//span[text()='Avaiable Sizes']", "Label - Avaiable Sizes");
        verifyIsDisplayed("//textarea[@id='Avaiable Sizes']", "Avaiable Sizes input");
        verifyIsEnabled("//textarea[@id='Avaiable Sizes']", "Avaiable Sizes input");
        verifyIsDisplayed("//textarea[@placeholder='Ex: 1,2,5,6,7 Enter all Available Sizes']", "Placeholder - Ex: 1,2,5,6,7 Enter all Available Sizes");
        

        
        // Starting Prices
        verifyIsDisplayed("//span[text()='Starting Prices']", "Label - Starting Prices");
        verifyIsDisplayed("//input[@id='va_startingPrices']", "Starting Prices input");
        verifyIsEnabled("//input[@id='va_startingPrices']", "Starting Prices input");
        verifyIsDisplayed("//input[@placeholder='Starting prices']", "Placeholder - Starting prices");
        
        // Max Price
        verifyIsDisplayed("//span[text()='Max Price']", "Label - Max Price");
        verifyIsDisplayed("//input[@id='va_maxPrice']", "Max Price input");
        verifyIsEnabled("//input[@id='va_maxPrice']", "Max Price input");
        verifyIsDisplayed("//input[@placeholder='Max price']", "Placeholder - Max Price");
        
        
        
        logScreenshot();
   
    }

    @Test(priority = 4)
    public void selectMakingAndVA_UiValidations() {
    	startTest("Select Making & VA and validate UI changes");
    	
check("//label[text()='VA']/../..//input[@type='checkbox']", "Checkbox - VA");
    	
    	verifyIsDisplayed("//p[text()='VA' and text()=' Details']", "heading - VA Details");
    	
    	 // Event Type - radio group (label + radios)
        verifyIsDisplayed("//p[text()='VA' and text()=' Details']/..//span[text()='Discount Type']", "Label - Discount Type in VA Details");
        verifyIsDisplayed("//p[text()='VA' and text()=' Details']/..//span[text()='Discount Type']/..//span[text()='*']", "Mandatory - Event Type in VA Details");
        
        verifyIsDisplayed("//p[text()='VA' and text()=' Details']/..//input[@value='PERCENT']", "PERCENT radio button in VA Details");
        verifyIsDisplayed("//p[text()='VA' and text()=' Details']/..//label[text()='Percent']", "Percent radio option in VA Details");
        verifyIsEnabled("//p[text()='VA' and text()=' Details']/..//input[@value='PERCENT']", "PERCENT radio button");
        
        verifyIsDisplayed("//p[text()='VA' and text()=' Details']/..//input[@value='FLAT']", "FLAT radio button in VA Details");
        verifyIsDisplayed("//p[text()='VA' and text()=' Details']/..//label[text()='Flat']", "Flat radio option in VA Details");
        verifyIsEnabled("//p[text()='VA' and text()=' Details']/..//input[@value='FLAT']", "FLAT radio button in VA Details");
        
        // Discount Value
        verifyIsDisplayed("//p[text()='VA' and text()=' Details']/..//span[text()='Discount Value']", "Label - Discount Value in VA Details");
        verifyIsDisplayed("//p[text()='VA' and text()=' Details']/..//span[text()='Discount Value']/..//span[text()='*']", "Mandatory - Discount Value in VA Details");
        verifyIsDisplayed("//p[text()='VA' and text()=' Details']/..//input[@id='Discount Value']", "Discount Value input in VA Details");
        verifyIsEnabled("//p[text()='VA' and text()=' Details']/..//input[@id='Discount Value']", "Discount Value input in VA Details");
        verifyIsDisplayed("//p[text()='VA' and text()=' Details']/..//input[@placeholder='Enter discount value']", "Placeholder - Enter discount value in VA Details");

        // Discount Value
        verifyIsDisplayed("//p[text()='VA' and text()=' Details']/..//span[text()='Min Purchase Value']", "Label - Min Purchase Value in VA Details");
        verifyIsDisplayed("//p[text()='VA' and text()=' Details']/..//span[text()='Min Purchase Value']/..//span[text()='*']", "Mandatory - Min Purchase Value in VA Details");
        verifyIsDisplayed("//p[text()='VA' and text()=' Details']/..//input[@id='Min Purchase Value']", "Min Purchase Value input in VA Details");
        verifyIsEnabled("//p[text()='VA' and text()=' Details']/..//input[@id='Min Purchase Value']", "Min Purchase Value input in VA Details");
        verifyIsDisplayed("//p[text()='VA' and text()=' Details']/..//input[@placeholder='Enter min purchase value']", "Placeholder - Enter min purchase value in VA Details");

        // Discount Value
        verifyIsDisplayed("//p[text()='VA' and text()=' Details']/..//span[text()='Min Grams']", "Label - Min Grams in VA Details");
        verifyIsDisplayed("//p[text()='VA' and text()=' Details']/..//input[@id='Min Grams']", "Min Grams input in VA Details");
        verifyIsEnabled("//p[text()='VA' and text()=' Details']/..//input[@id='Min Grams']", "Min Grams input in VA Details");
        verifyIsDisplayed("//p[text()='VA' and text()=' Details']/..//input[@placeholder='Enter min grams']", "Placeholder - Enter min grams in VA Details");

        check("//label[text()='MAKING']/../..//input[@type='checkbox']", "Checkbox - MAKING");
    	
    	verifyIsDisplayed("//p[text()='MAKING' and text()=' Details']", "heading - MAKING Details");
    	
    	 // Event Type - radio group (label + radios)
        verifyIsDisplayed("//p[text()='MAKING' and text()=' Details']/..//span[text()='Discount Type']", "Label - Discount Type in MAKING Details");
        verifyIsDisplayed("//p[text()='MAKING' and text()=' Details']/..//span[text()='Discount Type']/..//span[text()='*']", "Mandatory - Event Type in MAKING Details");
        
        verifyIsDisplayed("//p[text()='MAKING' and text()=' Details']/..//input[@value='PERCENT']", "PERCENT radio button in MAKING Details");
        verifyIsDisplayed("//p[text()='MAKING' and text()=' Details']/..//label[text()='Percent']", "Percent radio option in MAKING Details");
        verifyIsEnabled("//p[text()='MAKING' and text()=' Details']/..//input[@value='PERCENT']", "PERCENT radio button in MAKING Details");
        
        verifyIsDisplayed("//p[text()='MAKING' and text()=' Details']/..//input[@value='FLAT']", "FLAT radio button in MAKING Details");
        verifyIsDisplayed("//p[text()='MAKING' and text()=' Details']/..//label[text()='Flat']", "Flat radio option in MAKING Details");
        verifyIsEnabled("//p[text()='MAKING' and text()=' Details']/..//input[@value='FLAT']", "FLAT radio button in MAKING Details");
        
        // Discount Value
        verifyIsDisplayed("//p[text()='MAKING' and text()=' Details']/..//span[text()='Discount Value']", "Label - Discount Value in MAKING Details");
        verifyIsDisplayed("//p[text()='MAKING' and text()=' Details']/..//span[text()='Discount Value']/..//span[text()='*']", "Mandatory - Discount Value in MAKING Details");
        verifyIsDisplayed("//p[text()='MAKING' and text()=' Details']/..//input[@id='Discount Value']", "Discount Value input in MAKING Details");
        verifyIsEnabled("//p[text()='MAKING' and text()=' Details']/..//input[@id='Discount Value']", "Discount Value input in MAKING Details");
        verifyIsDisplayed("//p[text()='MAKING' and text()=' Details']/..//input[@placeholder='Enter discount value']", "Placeholder - Enter discount value in MAKING Details");

        // Discount Value
        verifyIsDisplayed("//p[text()='MAKING' and text()=' Details']/..//span[text()='Min Purchase Value']", "Label - Min Purchase Value in MAKING Details");
        verifyIsDisplayed("//p[text()='MAKING' and text()=' Details']/..//span[text()='Min Purchase Value']/..//span[text()='*']", "Mandatory - Min Purchase Value in MAKING Details");
        verifyIsDisplayed("//p[text()='MAKING' and text()=' Details']/..//input[@id='Min Purchase Value']", "Min Purchase Value input in MAKING Details");
        verifyIsEnabled("//p[text()='MAKING' and text()=' Details']/..//input[@id='Min Purchase Value']", "Min Purchase Value input in MAKING Details");
        verifyIsDisplayed("//p[text()='MAKING' and text()=' Details']/..//input[@placeholder='Enter min purchase value']", "Placeholder - Enter min purchase value in MAKING Details");

        // Discount Value
        verifyIsDisplayed("//p[text()='MAKING' and text()=' Details']/..//span[text()='Min Grams']", "Label - Min Grams in MAKING Details");
        verifyIsDisplayed("//p[text()='MAKING' and text()=' Details']/..//input[@id='Min Grams']", "Min Grams input in MAKING Details");
        verifyIsEnabled("//p[text()='MAKING' and text()=' Details']/..//input[@id='Min Grams']", "Min Grams input in MAKING Details");
        verifyIsDisplayed("//p[text()='MAKING' and text()=' Details']/..//input[@placeholder='Enter min grams']", "Placeholder - Enter min grams in MAKING Details");

        check("//label[text()='GENERAL']/../..//input[@type='checkbox']", "Checkbox - GENERAL");
    	
    	verifyIsDisplayed("//p[text()='GENERAL' and text()=' Details']", "heading - GENERAL Details");
    	
    	 // Event Type - radio group (label + radios)
        verifyIsDisplayed("//p[text()='GENERAL' and text()=' Details']/..//span[text()='Discount Type']", "Label - Discount Type in GENERAL Details");
        verifyIsDisplayed("//p[text()='GENERAL' and text()=' Details']/..//span[text()='Discount Type']/..//span[text()='*']", "Mandatory - Event Type in GENERAL Details");
        
        verifyIsDisplayed("//p[text()='GENERAL' and text()=' Details']/..//input[@value='PERCENT']", "PERCENT radio button in GENERAL Details");
        verifyIsDisplayed("//p[text()='GENERAL' and text()=' Details']/..//label[text()='Percent']", "Percent radio option in GENERAL Details");
        verifyIsEnabled("//p[text()='GENERAL' and text()=' Details']/..//input[@value='PERCENT']", "PERCENT radio button in GENERAL Details");
        
        verifyIsDisplayed("//p[text()='GENERAL' and text()=' Details']/..//input[@value='FLAT']", "FLAT radio button in GENERAL Details");
        verifyIsDisplayed("//p[text()='GENERAL' and text()=' Details']/..//label[text()='Flat']", "Flat radio option in GENERAL Details");
        verifyIsEnabled("//p[text()='GENERAL' and text()=' Details']/..//input[@value='FLAT']", "FLAT radio button in GENERAL Details");
        
        // Discount Value
        verifyIsDisplayed("//p[text()='GENERAL' and text()=' Details']/..//span[text()='Discount Value']", "Label - Discount Value in GENERAL Details");
        verifyIsDisplayed("//p[text()='GENERAL' and text()=' Details']/..//span[text()='Discount Value']/..//span[text()='*']", "Mandatory - Discount Value in GENERAL Details");
        verifyIsDisplayed("//p[text()='GENERAL' and text()=' Details']/..//input[@id='Discount Value']", "Discount Value input in GENERAL Details");
        verifyIsEnabled("//p[text()='GENERAL' and text()=' Details']/..//input[@id='Discount Value']", "Discount Value input in GENERAL Details");
        verifyIsDisplayed("//p[text()='GENERAL' and text()=' Details']/..//input[@placeholder='Enter discount value']", "Placeholder - Enter discount value in GENERAL Details");

        // Discount Value
        verifyIsDisplayed("//p[text()='GENERAL' and text()=' Details']/..//span[text()='Min Purchase Value']", "Label - Min Purchase Value in GENERAL Details");
        verifyIsDisplayed("//p[text()='GENERAL' and text()=' Details']/..//span[text()='Min Purchase Value']/..//span[text()='*']", "Mandatory - Min Purchase Value in GENERAL Details");
        verifyIsDisplayed("//p[text()='GENERAL' and text()=' Details']/..//input[@id='Min Purchase Value']", "Min Purchase Value input in GENERAL Details");
        verifyIsEnabled("//p[text()='GENERAL' and text()=' Details']/..//input[@id='Min Purchase Value']", "Min Purchase Value input in GENERAL Details");
        verifyIsDisplayed("//p[text()='GENERAL' and text()=' Details']/..//input[@placeholder='Enter min purchase value']", "Placeholder - Enter min purchase value in GENERAL Details");

        // Discount Value
        verifyIsDisplayed("//p[text()='GENERAL' and text()=' Details']/..//span[text()='Min Grams']", "Label - Min Grams in GENERAL Details");
        verifyIsDisplayed("//p[text()='GENERAL' and text()=' Details']/..//input[@id='Min Grams']", "Min Grams input in GENERAL Details");
        verifyIsEnabled("//p[text()='GENERAL' and text()=' Details']/..//input[@id='Min Grams']", "Min Grams input in GENERAL Details");
        verifyIsDisplayed("//p[text()='GENERAL' and text()=' Details']/..//input[@placeholder='Enter min grams']", "Placeholder - Enter min grams in GENERAL Details");

        
    	
    }

    
    
    
}