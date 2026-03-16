package storeTests;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import utility.ReusableMethods;

public class PriceProtectionUI extends ReusableMethods
{
    @BeforeClass
    public void prerequisites() throws Exception {
        initialise();
        startTest("Login flow - Store");
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
    public void navigateAndValidatePriceProtection() {
        startTest("Navigate to Price Protection and validate fields");

        click("//span[text()='Dashboard']/..", "dashboard button");
        clickByCSS(".max-xl\\3Ahidden > .w-full", "features expand");
        click("//span[text()='Price Protection']", "Price Protection link");

        verifyIsDisplayed("//h3[text()='Price Protection Plans']", "Price Protection Plans heading");
        
        verifyIsDisplayed("//button[text()='+ Add Plan']", "+ Add Plan button");
        verifyIsEnabled("//button[text()='+ Add Plan']", "+ Add Plan button");
        
        verifyIsDisplayed("//span[text()='Search']", "label - Search");
        verifyIsDisplayed("//input[@id='Search']", "Search input");
        verifyIsEnabled("//input[@id='Search']", "Search input");
        verifyIsDisplayed("//input[@placeholder='Search plans']", "placeholder - Search plans");
        
        verifyIsDisplayed("//button[text()='Clear']", "Clear button");
		verifyIsDisabled("//button[text()='Clear']", "Clear button");
        
        verifyIsDisplayed("//button[text()='Apply Filters']", "Apply Filters button");
        verifyIsDisabled("//button[text()='Apply Filters']", "Apply Filters button");

        logScreenshot();

    }
    
    @Test(priority = 2)
    public void navigateAndValidateAddPlan() {
        startTest("Add plan screen validation");
        
        click("//button[text()='+ Add Plan']", "+ Add Plan button");
        
        verifyIsDisplayed("//h3[text()='Add Price Protection Plan']", "Add Price Protection Plan heading");
		
        verifyIsDisplayed("//span[text()='Plan Name']", "label - Plan Name");
		verifyIsDisplayed("//input[@id='Plan Name']", "Plan Name input");
		verifyIsEnabled("//input[@id='Plan Name']", "Plan Name input");
		verifyIsDisplayed("//input[@placeholder='Enter plan name']", "placeholder - Enter plan name");
		
		verifyIsDisplayed("//span[text()='Select Branches']", "label - Select Branches");
		verifyIsDisplayed("//input[@id='plan-branches']", "Select Branches input");
		verifyIsEnabled("//input[@id='plan-branches']", "Select Branches input");
		verifyIsDisplayed("//div[text()='Search Branches']", "placeholder - Search Branches");
		
		
		
		verifyIsDisplayed("//p[text()='Basic Details']", "heading - Basic Details");
		
		verifyIsDisplayed("//span[text()='Plan Code']", "label - Plan Code");
		verifyIsDisplayed("//input[@id='Plan Code']", "Plan Code input");
		verifyIsEnabled("//input[@id='Plan Code']", "Plan Code input");
		verifyIsDisplayed("//input[@placeholder='Enter plan code']", "placeholder - Enter plan code");
		
		verifyIsDisplayed("//span[text()='Tenure']", "label - Tenure");
		verifyIsDisplayed("//input[@id='plan-tenure']", "Tenure input");
		verifyIsEnabled("//input[@id='plan-tenure']", "Tenure input");
		verifyIsDisplayed("//div[text()='Select...']", "placeholder - Select...");
		
		// Start Date & Time
        verifyIsDisplayed("//span[text()='Plan Validity Start Date']", "Label - Plan Validity Start Date");
        verifyIsDisplayed("//span[text()='Plan Validity Start Date']/..//span[text()='*']", "Mandatory - Plan Validity Start Date");
        verifyIsDisplayed("//input[@placeholder='Start date']", "Start date input");
        verifyIsEnabled("//input[@placeholder='Start date']", "Start date input");
        
		// Start Date & Time
        verifyIsDisplayed("//span[text()='Plan Validity End Date']", "Label - Plan Validity End Date");
        verifyIsDisplayed("//span[text()='Plan Validity End Date']/..//span[text()='*']", "Mandatory - Plan Validity End Date");
        verifyIsDisplayed("//input[@placeholder='End date']", "End date input");
        verifyIsEnabled("//input[@placeholder='End date']", "End date input");
        
        verifyIsDisplayed("//span[text()='Description']", "label - Description");
		verifyIsDisplayed("//input[@id='Description']", "Description input");
		verifyIsEnabled("//input[@id='Description']", "Description input");
		verifyIsDisplayed("//input[@placeholder='Enter plan description']", "placeholder - Enter plan description");
		
        verifyIsDisplayed("//span[text()='Terms & Conditions']", "label - Terms & Conditions");
		verifyIsDisplayed("//input[@id='Terms & Conditions']", "Terms & Conditions input");
		verifyIsEnabled("//input[@id='Terms & Conditions']", "Terms & Conditions input");
		verifyIsDisplayed("//input[@placeholder='Enter terms and conditions']", "placeholder - Enter terms and conditions");
		
		
		verifyIsDisplayed("//label[text()='Monthly Risk Limit']/../..//input[@type='checkbox']", "Monthly Risk Limit checkbox");
		verifyIsEnabled("//label[text()='Monthly Risk Limit']/../..//input[@type='checkbox']", "Monthly Risk Limit checkbox");
		verifyIsDisplayed("//label[text()='Monthly Risk Limit']", "label - Monthly Risk Limit");

		// Select Metal Category
        verifyIsDisplayed("//span[text()='Select Metal Category']", "Label - Select Metal Category");
        verifyIsDisplayed("//input[@id='plan-metal-category']", "Select Metal Category input");
        verifyIsEnabled("//input[@id='plan-metal-category']", "Select Metal Category input");
        verifyIsDisplayed("//div[text()='Search Metal Category']", "Placeholder - Select Metal Category");
        
        // Select Metal Type
        verifyIsDisplayed("//span[text()='Select Metal Type']", "Label - Select Metal Type");
        verifyIsDisplayed("//input[@id='plan-metal-type']", "Select Metal Type input");
        verifyIsEnabled("//input[@id='plan-metal-type']", "Select Metal Type input");
        verifyIsDisplayed("//div[text()='Search Metal Type']", "Placeholder - Select Metal Type");
        
        // Select Category
        verifyIsDisplayed("//span[text()='Select Category']", "Label - Select Category");
        verifyIsDisplayed("//input[@id='plan-category']", "Select Category input");
        verifyIsEnabled("//input[@id='plan-category']", "Select Category input");
        verifyIsDisplayed("//div[text()='Search Category']", "Placeholder - Select Category");
        
        // Select sub Category
        verifyIsDisplayed("//span[text()='Select Sub Category']", "Label - Select Sub Category");
        verifyIsDisplayed("//input[@id='plan-sub-category']", "Select Sub Category input");
        verifyIsEnabled("//input[@id='plan-sub-category']", "Select Sub Category input");
        verifyIsDisplayed("//div[text()='Search Sub Category']", "Placeholder - Select Sub Category");
        
        // Select Types of Karates
        verifyIsDisplayed("//span[text()='Select Types of Karates']", "Label - Select Types of Karates");
        verifyIsDisplayed("//input[@id='plan-karates']", "Select Types of Karates input");
        verifyIsEnabled("//input[@id='plan-karates']", "Select Types of Karates input");
        verifyIsDisplayed("//div[text()='Search Types of Karates']", "Placeholder - Search Types of Karates");
        
        // Select Demographics
        verifyIsDisplayed("//span[text()='Select Demographics']", "Label - Select Demographics");
        verifyIsDisplayed("//input[@id='plan-demographics']", "Select Demographics input");
        verifyIsEnabled("//input[@id='plan-demographics']", "Select Demographics input");
        verifyIsDisplayed("//div[text()='Search Demographics']", "Placeholder - Search Demographics");
        
        
        
        verifyIsDisplayed("//p[text()='Pricing Rules']", "heading - Pricing Rules");
        
        verifyIsDisplayed("//span[text()='Weight Type']", "Label - Weight Type");
        verifyIsDisplayed("//span[text()='Weight Type']/..//span[text()='*']", "Mandatory - Weight Type");
        
        verifyIsDisplayed("//input[@id='weightType net']", "Net radio button");
        verifyIsDisplayed("//label[text()='Net']", "Net radio option");
        verifyIsEnabled("//input[@id='weightType net']", "Net radio button");
        
        verifyIsDisplayed("//input[@id='weightType gross']", "Gross radio buttton");
        verifyIsDisplayed("//label[text()='Gross']", "Gross radio label");
        verifyIsEnabled("//input[@id='weightType gross']", "Gross radio buttton");

		
        verifyIsDisplayed("//span[text()='Min Weight']", "label - Min Weight");
		verifyIsDisplayed("//input[@id='Min Weight']", "Min Weight input");
		verifyIsEnabled("//input[@id='Min Weight']", "Min Weight input");
		verifyIsDisplayed("//input[@placeholder='Enter minimum weight']", "placeholder - Enter minimum weight");
		
		verifyIsDisplayed("//span[text()='Max Weight']", "label - Max Weight");
		verifyIsDisplayed("//input[@id='Max Weight']", "Max Weight input");
		verifyIsEnabled("//input[@id='Max Weight']", "Max Weight input");
		verifyIsDisplayed("//input[@placeholder='Enter maximum weight']", "placeholder - Enter maximum weight");
		
		verifyIsDisplayed("//span[text()='Plan Fee Type']", "Label - Plan Fee Type");
        verifyIsDisplayed("//span[text()='Plan Fee Type']/..//span[text()='*']", "Mandatory - Plan Fee Type");
        
        verifyIsDisplayed("//input[@id='planFeeType flat']", "Flat radio button");
        verifyIsDisplayed("//label[text()='Net']", "Flat radio option");
        verifyIsEnabled("//input[@id='planFeeType flat']", "Flat radio button");
        
        verifyIsDisplayed("//input[@id='planFeeType percentage']", "Percentage radio buttton");
        verifyIsDisplayed("//label[text()='Percentage']", "Percentage radio label");
        verifyIsEnabled("//input[@id='planFeeType percentage']", "Percentage radio buttton");

        verifyIsDisplayed("//span[text()='Plan Fee Value']", "label - Plan Fee Value");
		verifyIsDisplayed("//input[@id='Plan Fee Value']", "Plan Fee Value input");
		verifyIsEnabled("//input[@id='Plan Fee Value']", "Plan Fee Value input");
		verifyIsDisplayed("//input[@placeholder='Enter plan fee value']", "placeholder - Enter plan fee value");
		
		verifyIsDisplayed("//span[text()='Service Charge']", "label - Service Charge");
		verifyIsDisplayed("//input[@id='Service Charge']", "Service Charge input");
		verifyIsEnabled("//input[@id='Service Charge']", "Service Charge input");
		verifyIsDisplayed("//input[@placeholder='Enter service charge']", "placeholder - Enter service charge");
		
		
		verifyIsDisplayed("//p[text()='Redemption Rules']", "heading - Redemption Rules");
        
		verifyIsDisplayed("//p[text()='Redemption Rules']/..//span[text()='Coverage Percent']", "label - Coverage Percent in Redemption Rules");
		verifyIsDisplayed("//p[text()='Redemption Rules']/..//input[@id='Coverage Percent']", "Coverage Percent input in Redemption Rules");
		verifyIsEnabled("//p[text()='Redemption Rules']/..//input[@id='Coverage Percent']", "Coverage Percent input in Redemption Rules");
		verifyIsDisplayed("//p[text()='Redemption Rules']/..//input[@placeholder='Enter coverage percent']", "placeholder - Enter coverage percent in Redemption Rules");
		
		verifyIsDisplayed("//p[text()='Redemption Rules']/..//span[text()='Instant Pay Percent']", "label - Instant Pay Percent in Redemption Rules");
		verifyIsDisplayed("//p[text()='Redemption Rules']/..//input[@id='Instant Pay Percent']", "Instant Pay Percent input in Redemption Rules");
		verifyIsEnabled("//p[text()='Redemption Rules']/..//input[@id='Instant Pay Percent']", "Instant Pay Percent input in Redemption Rules");
		verifyIsDisplayed("//p[text()='Redemption Rules']/..//input[@placeholder='Enter instant pay percent']", "placeholder - Enter instant pay percent in Redemption Rules");
		
		verifyIsDisplayed("//p[text()='Redemption Rules']/..//span[text()='Pay On Next Purchase Percent']", "label - Pay On Next Purchase Percent in Redemption Rules");
		verifyIsDisplayed("//p[text()='Redemption Rules']/..//p[text()='Redemption Rules']/..//input[@id='Pay On Next Purchase Percent']", "Pay On Next Purchase Percent input in Redemption Rules");
		verifyIsEnabled("//p[text()='Redemption Rules']/..//input[@id='Pay On Next Purchase Percent']", "Pay On Next Purchase Percent input in Redemption Rules");
		verifyIsDisplayed("//p[text()='Redemption Rules']/..//input[@placeholder='Enter pay on next purchase percent']", "placeholder - Enter pay on next purchase percent in Redemption Rules");
		
		verifyIsDisplayed("//p[text()='Redemption Rules']/..//span[text()='Max Buyback Value']", "label - Max Buyback Value in Redemption Rules");
		verifyIsDisplayed("//p[text()='Redemption Rules']/..//input[@id='Max Buyback Value']", "Max Buyback Value input in Redemption Rules");
		verifyIsEnabled("//p[text()='Redemption Rules']/..//input[@id='Max Buyback Value']", "Max Buyback Value input in Redemption Rules");
		verifyIsDisplayed("//p[text()='Redemption Rules']/..//input[@placeholder='Enter max buyback value']", "placeholder - Enter max buyback value in Redemption Rules");
		
		verifyIsDisplayed("//p[text()='Redemption Rules']/..//span[text()='Waiting Period (Days)']", "label - Waiting Period (Days) in Redemption Rules");
		verifyIsDisplayed("//p[text()='Redemption Rules']/..//input[@id='Waiting Period (Days)']", "Waiting Period (Days) input in Redemption Rules");
		verifyIsEnabled("//p[text()='Redemption Rules']/..//input[@id='Waiting Period (Days)']", "Waiting Period (Days) input in Redemption Rules");
		verifyIsDisplayed("//p[text()='Redemption Rules']/..//input[@placeholder='Enter waiting period']", "placeholder - Enter waiting period in Redemption Rules");
		
		verifyIsDisplayed("//p[text()='Redemption Rules']/..//span[text()='Applicable After (Days)']", "label - Applicable After (Days) in Redemption Rules");
		verifyIsDisplayed("//p[text()='Redemption Rules']/..//input[@id='Applicable After (Days)']", "Applicable After (Days) input in Redemption Rules");
		verifyIsEnabled("//p[text()='Redemption Rules']/..//input[@id='Applicable After (Days)']", "Applicable After (Days) input in Redemption Rules");
		verifyIsDisplayed("//p[text()='Redemption Rules']/..//input[@placeholder='Enter applicable after days']", "placeholder - Enter applicable after days in Redemption Rules");
		
		
		
		verifyIsDisplayed("//p[text()='Next Purchase Rules']", "heading - Next Purchase Rules");
        
		verifyIsDisplayed("//p[text()='Next Purchase Rules']/..//span[text()='Coverage Percent']", "label - Coverage Percent in Next Purchase Rules");
		verifyIsDisplayed("//p[text()='Next Purchase Rules']/..//input[@id='Coverage Percent']", "Coverage Percent input in Next Purchase Rules");
		verifyIsEnabled("//p[text()='Next Purchase Rules']/..//input[@id='Coverage Percent']", "Coverage Percent input in Next Purchase Rules");
		verifyIsDisplayed("//p[text()='Next Purchase Rules']/..//input[@placeholder='Enter coverage percent']", "placeholder - Enter coverage percent in Next Purchase Rules");
		
		verifyIsDisplayed("//p[text()='Next Purchase Rules']/..//span[text()='Min Purchase Value']", "label - Min Purchase Value in Next Purchase Rules");
		verifyIsDisplayed("//p[text()='Next Purchase Rules']/..//input[@id='Min Purchase Value']", "Min Purchase Value input in Next Purchase Rules");
		verifyIsEnabled("//p[text()='Next Purchase Rules']/..//input[@id='Min Purchase Value']", "Min Purchase Value input in Next Purchase Rules");
		verifyIsDisplayed("//p[text()='Next Purchase Rules']/..//input[@placeholder='Enter minimum purchase value']", "placeholder - Enter minimum purchase value in Next Purchase Rules");
        
		verifyIsDisplayed("//p[text()='Next Purchase Rules']/..//span[text()='Min Purchase Weight']", "label - Min Purchase Weight in Next Purchase Rules");
		verifyIsDisplayed("//p[text()='Next Purchase Rules']/..//input[@id='Min Purchase Weight']", "Min Purchase Weight input in Next Purchase Rules");
		verifyIsEnabled("//p[text()='Next Purchase Rules']/..//input[@id='Min Purchase Weight']", "Min Purchase Weight input in Next Purchase Rules");
		verifyIsDisplayed("//p[text()='Next Purchase Rules']/..//input[@placeholder='Enter minimum purchase weight']", "placeholder - Enter minimum purchase weight in Next Purchase Rules");
		
		verifyIsDisplayed("//p[text()='Next Purchase Rules']/..//span[text()='Validity']", "label - Validity in Next Purchase Rules");
		verifyIsDisplayed("//p[text()='Next Purchase Rules']/..//input[@id='next-purchase-validity']", "Validity input in Next Purchase Rules");
		verifyIsEnabled("//p[text()='Next Purchase Rules']/..//input[@id='next-purchase-validity']", "Validity input in Next Purchase Rules");
		verifyIsDisplayed("//p[text()='Next Purchase Rules']/..//div[text()='Select...']", "placeholder - Select...");
		
		// Select Metal Category
        verifyIsDisplayed("//p[text()='Next Purchase Rules']/..//span[text()='Select Metal Category']", "Label - Select Metal Category Next Purchase Rules");
        verifyIsDisplayed("//p[text()='Next Purchase Rules']/..//input[@id='next-purchase-metal-category']", "Select Metal Category input Next Purchase Rules");
        verifyIsEnabled("//p[text()='Next Purchase Rules']/..//input[@id='next-purchase-metal-category']", "Select Metal Category input Next Purchase Rules");
        verifyIsDisplayed("//p[text()='Next Purchase Rules']/..//div[text()='Search Metal Categories']", "Placeholder - Select Metal Categories Next Purchase Rules");
        
        // Select Metal Type
        verifyIsDisplayed("//p[text()='Next Purchase Rules']/..//span[text()='Select Metal Type']", "Label - Select Metal Type Next Purchase Rules");
        verifyIsDisplayed("//p[text()='Next Purchase Rules']/..//input[@id='next-purchase-metal-type']", "Select Metal Type input Next Purchase Rules");
        verifyIsEnabled("//p[text()='Next Purchase Rules']/..//input[@id='next-purchase-metal-type']", "Select Metal Type input Next Purchase Rules");
        verifyIsDisplayed("//p[text()='Next Purchase Rules']/..//div[text()='Search Metal Types']", "Placeholder - Select Metal Types Next Purchase Rules");
        
        // Select Category
        verifyIsDisplayed("//p[text()='Next Purchase Rules']/..//span[text()='Select Category']", "Label - Select Category Next Purchase Rules");
        verifyIsDisplayed("//p[text()='Next Purchase Rules']/..//input[@id='next-purchase-category']", "Select Category input Next Purchase Rules");
        verifyIsEnabled("//p[text()='Next Purchase Rules']/..//input[@id='next-purchase-category']", "Select Category input Next Purchase Rules");
        verifyIsDisplayed("//p[text()='Next Purchase Rules']/..//div[text()='Search Categories']", "Placeholder - Select Categories Next Purchase Rules");
        
        // Select sub Category
        verifyIsDisplayed("//p[text()='Next Purchase Rules']/..//span[text()='Select Sub Category']", "Label - Select Sub Category Next Purchase Rules");
        verifyIsDisplayed("//p[text()='Next Purchase Rules']/..//input[@id='next-purchase-sub-category']", "Select Sub Category input Next Purchase Rules");
        verifyIsEnabled("//p[text()='Next Purchase Rules']/..//input[@id='next-purchase-sub-category']", "Select Sub Category input Next Purchase Rules");
        verifyIsDisplayed("//p[text()='Next Purchase Rules']/..//div[text()='Search Sub Categories']", "Placeholder - Select Sub Categories Next Purchase Rules");
        
        
        verifyIsDisplayed("//p[text()='Next Purchase Additional Offers']", "heading - Next Purchase Additional Offers");
        
        verifyIsDisplayed("//p[text()='Next Purchase Additional Offers']/..//span[text()='Offer Type']", "Label - Offer Type");
        verifyIsDisplayed("//p[text()='Next Purchase Additional Offers']/..//span[text()='Offer Type']/..//span[text()='*']", "Mandatory - Offer Type");
        
        verifyIsDisplayed("//p[text()='Next Purchase Additional Offers']/..//input[@id='offerType flat']", "Flat radio button");
        verifyIsDisplayed("//p[text()='Next Purchase Additional Offers']/..//label[text()='Flat']", "Flat radio option");
        verifyIsEnabled("//p[text()='Next Purchase Additional Offers']/..//input[@id='offerType flat']", "Flat radio button");
        
        verifyIsDisplayed("//p[text()='Next Purchase Additional Offers']/..//input[@id='offerType percentage']", "Percentage radio buttton");
        verifyIsDisplayed("//p[text()='Next Purchase Additional Offers']/..//label[text()='Percentage']", "Percentage radio label");
        verifyIsEnabled("//p[text()='Next Purchase Additional Offers']/..//input[@id='offerType percentage']", "Percentage radio buttton");

        
        
        verifyIsDisplayed("//p[text()='Next Purchase Additional Offers']/..//span[text()='Offer Value']", "label - Offer Value in Next Purchase Additional Offers");
		verifyIsDisplayed("//p[text()='Next Purchase Additional Offers']/..//input[@id='Offer Value']", "Offer Value input in Next Purchase Additional Offers");
		verifyIsEnabled("//p[text()='Next Purchase Additional Offers']/..//input[@id='Offer Value']", "Offer Value input in Next Purchase Additional Offers");
		verifyIsDisplayed("//p[text()='Next Purchase Additional Offers']/..//input[@placeholder='Enter offer value']", "placeholder - Enter offer value in Next Purchase Additional Offers");
		
        verifyIsDisplayed("//p[text()='Next Purchase Additional Offers']/..//span[text()='Making Charge']", "label - Making Charge in Next Purchase Additional Offers");
		verifyIsDisplayed("//p[text()='Next Purchase Additional Offers']/..//input[@id='Making Charge']", "Making Charge input in Next Purchase Additional Offers");
		verifyIsEnabled("//p[text()='Next Purchase Additional Offers']/..//input[@id='Making Charge']", "Making Charge input in Next Purchase Additional Offers");
		verifyIsDisplayed("//p[text()='Next Purchase Additional Offers']/..//input[@placeholder='Enter making charge']", "placeholder - Enter making charge in Next Purchase Additional Offers");
		
		verifyIsDisplayed("//p[text()='Next Purchase Additional Offers']/..//span[text()='Making Charge Type']", "Label - Making Charge Type in Next Purchase Additional Offers");
        verifyIsDisplayed("//p[text()='Next Purchase Additional Offers']/..//span[text()='Making Charge Type']/..//span[text()='*']", "Mandatory - Making Charge Type in Next Purchase Additional Offers");
        
        verifyIsDisplayed("//p[text()='Next Purchase Additional Offers']/..//input[@id='makingChargeType flat']", "makingChargeType Flat radio button in Next Purchase Additional Offers");
        verifyIsDisplayed("//p[text()='Next Purchase Additional Offers']/..//input[@id='makingChargeType flat']/../../../..//label[text()='Flat']", "makingChargeType Flat radio option in Next Purchase Additional Offers");
        verifyIsEnabled("//p[text()='Next Purchase Additional Offers']/..//input[@id='makingChargeType flat']", "makingChargeTypeFlat radio button in Next Purchase Additional Offers");
        
        verifyIsDisplayed("//p[text()='Next Purchase Additional Offers']/..//input[@id='makingChargeType percentage']", "makingChargeType Percentage radio buttton in Next Purchase Additional Offers");
        verifyIsDisplayed("//p[text()='Next Purchase Additional Offers']/..//input[@id='makingChargeType percentage']/../../../..//label[text()='Percentage']", "makingChargeType Percentage radio label in Next Purchase Additional Offers");
        verifyIsEnabled("//p[text()='Next Purchase Additional Offers']/..//input[@id='makingChargeType percentage']", "makingChargeType Percentage radio buttton in Next Purchase Additional Offers");

        verifyIsDisplayed("//p[text()='Next Purchase Additional Offers']/..//span[text()='Validity']", "label - Validity in Next Purchase Rules in Next Purchase Additional Offers");
		verifyIsDisplayed("//p[text()='Next Purchase Additional Offers']/..//input[@id='additional-offers-validity']", "Validity input in Next Purchase Rules in Next Purchase Additional Offers");
		verifyIsEnabled("//p[text()='Next Purchase Additional Offers']/..//input[@id='additional-offers-validity']", "Validity input in Next Purchase Rules in Next Purchase Additional Offers");
		verifyIsDisplayed("//p[text()='Next Purchase Additional Offers']/..//div[text()='Select...']", "placeholder - Select... in Next Purchase Additional Offers");
		

        
        logScreenshot();
        
        
    }

}
