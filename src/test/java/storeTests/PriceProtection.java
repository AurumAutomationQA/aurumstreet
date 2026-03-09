package storeTests;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
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

public class PriceProtection extends ReusableMethods
{
    @BeforeClass
    public void prerequisites() {
        initialise();
    }

    @AfterMethod
    public void afterTest() {
        endTest();
    }

    @Test(priority=1)
    public void launchTest() throws IOException, TimeoutException {
        startTest("Navigate to store");
        openBrowser("chrome","https://deepa.aurumconnect.in/");//https://kinhub:KIPL_1122@bala.aurumsconnect.com/
        verifyIsDisplayed("//p[text()='Deepa Jewellers']", "Deepa Jewellers header");
        verifyIsDisplayed("//a[text()='Login']", "Login link");
        logScreenshot();
    }

    @Test(priority=2)
    public void loginTest() {
        startTest("login to store");
        click("//a[text()='Login']", "Login link");
        verifyIsDisplayed("//h3[text()='Login']", "Login label");
        verifyIsDisplayed("//button[contains(@class,'text-gray-600')]//span[text()='Back to Website']", "label - Back to Website and back arrow");
        clearNenterText("//button[text()='+91']/..//input[@placeholder='9999988888']","9392190045","Mobile number field");
        click("//button[text()='Send OTP']", "send OTP button");
        String otp="860821";
        for(int i=0;i<otp.length();i++) {
            clearNenterText("(//div[@role='group']//input)["+(i+1)+"]", ""+otp.charAt(i),"otp "+(i+1)+"th character");
        }
        click("//button[text()='Login']", "Login button");
        verifyIsDisplayed("//span[text()='Dashboard']/..", "dashboard button");
        logScreenshot();
    }

    @Test(priority=3)
    public void priceProtectionTest() {
        startTest("Navigate to Price Protection");
        click("//span[text()='Dashboard']/..", "dashboard button");
        click("//a[@href=\"/admin/priceProtectionPlan\"]", "Price Protection link");
        verifyIsDisplayed("//h3[text()='Price Protection Plans']", "Price Protection Plans heading");
        verifyIsDisplayed("//button[contains(text(),'Add Plan') ]", "Add Plan button");
        logScreenshot();
    }

    
    @Test(priority=4)
    public void priceProtectionFormMandatoryFields() {
    	startTest("Navigate to Price Protection form"); 
    	// click any button whose text contains 'Add Plan'
    	click("//button[contains(text(),'Add Plan') ]", "Add Plan button"); 
    	// Verify mandatory fields present 
    	verifyIsDisplayed("//input[@placeholder='Enter plan name']", "Plan Name input");  
    	verifyIsDisplayed("//input[@id='plan-branches']", "Select Branches input"); 
    	verifyIsDisplayed("//input[@placeholder='Enter plan code']", "Plan Code input"); 
    	verifyIsDisplayed("//input[contains(@id,'plan-tenure')]", "Tenure dropdown"); 
    	verifyIsDisplayed("//input[@placeholder='Start date']", "Plan Validity Start Date"); 
    	verifyIsDisplayed("//input[@placeholder='End date']", "Plan Validity End Date");

        // Plan Name
        clearNenterText("//input[@placeholder='Enter plan name']", "Automation Plan", "Plan Name");

       
        //Select branch by typing branch name and pressing Enter (works for many searchable selects) 
        WebElement branchInput = driver.findElement(By.xpath("//input[@id='plan-branches']")); 
        branchInput.sendKeys("All"); 
        branchInput.sendKeys(Keys.ENTER); 
        getTest().log(com.relevantcodes.extentreports.LogStatus.INFO, "Selected branch 'All'");

        // Plan Code
        clearNenterText("//input[@placeholder='Enter plan code']", "AUTO123", "Plan Code");

        // Tenure
        // Create wait object
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Tenure Dropdown
        WebElement tenureInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[contains(@id,'plan-tenure')]")));

        tenureInput.click();
        
        tenureInput.sendKeys("6"); 
        tenureInput.sendKeys(Keys.ENTER);
        getTest().log(com.relevantcodes.extentreports.LogStatus.INFO, "Selected tenure '6'");

        // Date Handling
        // Get tomorrow date
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        String day = String.valueOf(tomorrow.getDayOfMonth());

        // Open calendar
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.xpath("//input[@placeholder='Start date']")).click();

        // Click day
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@class,'day') and text()='" + day + "']"))).click();

        getTest().log(LogStatus.INFO, "Selected start date: " + day);
        
        // End Date
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // End date = next month
        LocalDate nextMonth = LocalDate.now().plusMonths(1);
        String endDay = String.valueOf(nextMonth.getDayOfMonth());
        
        // Open End Date calendar
        WebElement endDateField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='End date']")));
        endDateField.click();
        
        // Click next month arrow
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class,'react-datepicker__navigation--next')]"))).click();
        
        // Select the day
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'react-datepicker__day') and text()='" + endDay + "']"))).click();

        getTest().log(LogStatus.INFO, "Selected End Date: " + endDay);

        // ----------------------
        // Pricing Rules Section
        // ----------------------

        // Weight Type → Net
        click("//label[contains(text(),'Net')]", "Weight Type Net");

        // Min Weight
        clearNenterText("//input[@placeholder='Enter minimum weight']", "1", "Min Weight");

        // Max Weight
        clearNenterText("//input[@placeholder='Enter maximum weight']", "50", "Max Weight");

        // Plan Fee Type → Flat
        click("//label[contains(text(),'Flat')]", "Plan Fee Type Flat");

        // Plan Fee Value
        clearNenterText("//input[@placeholder='Enter plan fee value']", "100", "Plan Fee Value");


        // ----------------------
        // Submit Form (robust)
        // ----------------------

        try {
			click("//button[normalize-space()='Create Plan']", "Click Create Plan button");
		} catch (Exception e) {
			getTest().log(com.relevantcodes.extentreports.LogStatus.FAIL, "In the Price Proctection plan form, unable to click submit button" + addScreenShot());
		}

        // ----------------------
        // Confirm Popup Handling
        // ----------------------
        try {
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement confirmBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Confirm')]")));
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", confirmBtn);
            getTest().log(com.relevantcodes.extentreports.LogStatus.INFO, "In the Price Proctection plan form, in the process of submitting the form, confirmation popup accepted");
            try {
                wait = new WebDriverWait(driver, Duration.ofSeconds(30));
                String toastXpath = "//div[contains(@class,'Toastify')]//div[contains(@class,'toast-icon')]/..//span";
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(toastXpath)));
                String toastResponse = getResponse(toastXpath);
                if (toastResponse.contains("Plan created successfully") || toastResponse.contains("Plan updated successfully")) {
                    getTest().log(com.relevantcodes.extentreports.LogStatus.PASS, "Price Protection Plan creation flow successful with toast message: " + toastResponse);
                } 
                else {
                    // Log failure without taking an extra screenshot here; rely on final logScreenshot()
                    getTest().log(com.relevantcodes.extentreports.LogStatus.FAIL, "Price Protection Plan creation flow failed with unexpected toast message: " + toastResponse);
                }
            } catch (Exception e) {
                // Log failure without taking an extra screenshot here; rely on final logScreenshot()
                getTest().log(com.relevantcodes.extentreports.LogStatus.FAIL, "In the Price Proctection plan form, in the process of submitting the form, Toast message not displayed or not captured: " + e + addScreenShot());
            }
        } catch (Exception e) {
            getTest().log(com.relevantcodes.extentreports.LogStatus.INFO, "In the Price Proctection plan form, in the process of submitting the form, No confirmation popup found or click functionality is not working: " + e);
        }

        // ----------------------
        // Verification
        // ----------------------
        //verifyIsDisplayed("//td[contains(text(),'Automation Plan') or contains(text(),'AUTO123')]", "Created Plan Verification");

        logScreenshot();
    }
    
    @Test(priority=5)
    public void priceProtectionFormAllFields() {
    	startTest("Navigate to Price Protection form");

    	// Open Add Plan
    	click("//button[contains(text(),'Add Plan')]", "Add Plan button");

    	// Verify fields
    	verifyIsDisplayed("//input[@placeholder='Enter plan name']", "Plan Name input");
    	verifyIsDisplayed("//input[@id='plan-branches']", "Select Branches input");
    	verifyIsDisplayed("//input[@placeholder='Enter plan code']", "Plan Code input"); // need to update xpath as id is dynamic
    	verifyIsDisplayed("//input[contains(@id,'plan-tenure')]", "Tenure dropdown");
    	verifyIsDisplayed("//input[@placeholder='Start date']", "Plan Validity Start Date");
    	verifyIsDisplayed("//input[@placeholder='End date']", "Plan Validity End Date");
    	// ----------------------
    	// Mandatory Fields
    	// ----------------------

    	// Plan Name
    	clearNenterText("//input[@placeholder='Enter plan name']", "Automation Plan", "Plan Name");

    	// Branch
    	WebElement branchInput = driver.findElement(By.id("plan-branches"));
    	branchInput.sendKeys("All");
    	branchInput.sendKeys(Keys.ENTER);
    	getTest().log(LogStatus.INFO, "Selected branch");

    	// Plan Code
    	clearNenterText("//input[@placeholder='Enter plan code']", "AUTO123", "Plan Code"); //need to update xpath as id is dynamic

    	// Tenure
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	WebElement tenureInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[contains(@id,'plan-tenure')]")));
    	tenureInput.click();
    	tenureInput.sendKeys("6");
    	tenureInput.sendKeys(Keys.ENTER);
    	getTest().log(LogStatus.INFO, "Selected tenure");

    	// ----------------------
    	// Date Handling
    	// ----------------------

    	// Start Date
    	LocalDate tomorrow = LocalDate.now().plusDays(1);
    	String startDay = String.valueOf(tomorrow.getDayOfMonth());

    	driver.findElement(By.xpath("//input[@placeholder='Start date']")).click();

    	wait.until(ExpectedConditions.elementToBeClickable(
    	        By.xpath("//div[contains(@class,'day') and text()='" + startDay + "']"))).click();

    	getTest().log(LogStatus.INFO, "Selected Start Date");

    	// End Date
    	LocalDate nextMonth = LocalDate.now().plusMonths(1);
    	String endDay = String.valueOf(nextMonth.getDayOfMonth());

    	WebElement endDateField = wait.until(ExpectedConditions.elementToBeClickable(
    	        By.xpath("//input[@placeholder='End date']")));

    	endDateField.click();

    	wait.until(ExpectedConditions.elementToBeClickable(
    	        By.xpath("//button[contains(@class,'react-datepicker__navigation--next')]"))).click();

    	wait.until(ExpectedConditions.elementToBeClickable(
    	        By.xpath("//div[contains(@class,'react-datepicker__day') and text()='" + endDay + "']"))).click();

    	getTest().log(LogStatus.INFO, "Selected End Date");


    	// ----------------------
    	// Non-Mandatory Fields
    	// ----------------------

    	// Terms & Conditions
    	clearNenterText("//div[@class='m_c2204cc2 mantine-RichTextEditor-content']",
    	        "Automation Terms and Conditions", "Terms");

    	// Additional Info Description
    	clearNenterText("//textarea[@placeholder='Enter plan description']",
    	        "Automation Price Protection Plan", "Description");
    	
    	// Monthly Risk Limit
    	setCheckbox("//label[contains(text(),'Monthly Risk Limit')]/ancestor::div[contains(@class,'Checkbox')]//input", true, "Monthly Risk Limit");
    	
    	clearNenterText("//p[text()='Additional Info']/..//input[contains(@id,'Max Risk Amount')]", "5", "Max Risk Amount");

    	// Metal Category
    	WebElement metalCategory = driver.findElement(By.xpath("//input[@id='plan-metal-category']"));
    	metalCategory.sendKeys("Gold");
    	metalCategory.sendKeys(Keys.ENTER);

    	// Metal Type
    	WebElement metalType = driver.findElement(By.xpath("//input[@id='plan-metal-type']"));
    	metalType.sendKeys("Ornaments");
    	metalType.sendKeys(Keys.ENTER);

    	// Category
    	WebElement category = driver.findElement(By.xpath("//div[text()='Search Category']"));
    	category.sendKeys("All");
    	category.sendKeys(Keys.ENTER);

    	// Sub Category
    	WebElement subCategory = driver.findElement(By.xpath("//input[@id='plan-sub-category']"));
    	subCategory.sendKeys("All");
    	subCategory.sendKeys(Keys.ENTER);

    	// Karats
    	WebElement karat = driver.findElement(By.xpath("//input[@id='plan-karates']"));
    	karat.sendKeys("22k");
    	karat.sendKeys(Keys.ENTER);

    	// Demographics
    	WebElement demo = driver.findElement(By.xpath("//input[@id='plan-demographics']"));
    	demo.sendKeys("Male");
    	demo.sendKeys(Keys.ENTER);


    	// ----------------------
    	// Benefits
    	// ----------------------

    	clearNenterText("//input[@id='Discount in % while Exchanging on next buying']", "5", "Exchange Discount");

    	clearNenterText("//p[text()='Benefit Packs on Exchanging']/..//input[contains(@id,'Min Buy Value')]", "10000", "Min Buy Value");

    	clearNenterText("//p[text()='Benefit Packs on Exchanging']/..//input[contains(@id,'Min Buy Weight')]", "5", "Min Buy Weight");
    	
    	WebElement validity = driver.findElement(By.xpath("//p[text()='Benefit Packs on Exchanging']/..//input[contains(@id,'benefits-validity')]"));
    	validity.sendKeys("12");
    	validity.sendKeys(Keys.ENTER);

    	setCheckbox("//label[contains(text(),'Service Charge Applicable')]/ancestor::div[contains(@class,'Checkbox')]//input", true, "Service Charge Applicable");
    	
    	clearNenterText("//p[text()='Benefit Packs on Exchanging']/..//input[contains(@id,'Service Charges')]", "5", "Service Charge Value");


    	// ----------------------
    	// Renewal Offers
    	// ----------------------

    	clearNenterText("//input[@id='Discount in % on VA/Making Charges if not claimed plans']", "10", "Renewal Discount");

    	clearNenterText("(//input[@placeholder='Enter min buy value'])[2]", "15000", "Renewal Min Value");

    	clearNenterText("(//input[@placeholder='Enter min buy weight'])[2]", "10", "Renewal Min Weight");


    	// ----------------------
    	// Pricing Rules
    	// ----------------------

    	click("//label[contains(text(),'Net')]", "Weight Type Net");

    	clearNenterText("//input[@placeholder='Enter minimum weight']", "1", "Min Weight");

    	clearNenterText("//input[@placeholder='Enter maximum weight']", "50", "Max Weight");

    	click("//label[contains(text(),'Flat')]", "Plan Fee Type Flat");

    	clearNenterText("//input[@placeholder='Enter plan fee value']", "100", "Plan Fee Value");


    	// ----------------------
    	// Redemption Rules
    	// ----------------------

    	clearNenterText("//input[@placeholder='Enter coverage percent']", "80", "Coverage");

    	clearNenterText("//input[@placeholder='Enter instant pay percent']", "20", "Instant Pay");

    	clearNenterText("//input[@placeholder='Enter pay on next purchase perc']", "10", "Next Purchase");

    	clearNenterText("//input[@placeholder='Enter max buyback value']", "50000", "Max Buyback");

    	clearNenterText("//input[@placeholder='Enter waiting period']", "7", "Waiting Period");

    	clearNenterText("//input[@placeholder='Enter applicable after days']", "10", "Applicable After");


    	// ----------------------
    	// Next Purchase Rules
    	// ----------------------

    	clearNenterText("(//input[@placeholder='Enter coverage percent'])[2]", "50", "Next Coverage");

    	clearNenterText("//input[@placeholder='Enter minimum purchase value']", "20000", "Min Purchase Value");

    	clearNenterText("//input[@placeholder='Enter minimum purchase weight']", "10", "Min Purchase Weight");


    	// ----------------------
    	// Additional Offers
    	// ----------------------

    	click("//label[contains(text(),'Percentage')]", "Offer Type");

    	clearNenterText("//input[@placeholder='Enter offer value']", "5", "Offer Value");

    	clearNenterText("//input[@placeholder='Enter making charge']", "500", "Making Charge");

    	click("//label[contains(text(),'Flat')]", "Making Charge Type");


    	// ----------------------
    	// Submit Form
    	// ----------------------

    	click("//button[normalize-space()='Create Plan']", "Create Plan");


    	// ----------------------
    	// Confirm Popup
    	// ----------------------

    	WebElement confirmBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(
    	        By.xpath("//button[contains(text(),'Confirm')]")));

    	((JavascriptExecutor)driver).executeScript("arguments[0].click();", confirmBtn);

    	getTest().log(LogStatus.INFO, "Confirmation popup accepted");


    	// ----------------------
    	// Toast Validation
    	// ----------------------

    	String toastXpath = "//div[contains(@class,'Toastify')]//span";

    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(toastXpath)));

    	String toastResponse = getResponse(toastXpath);

    	if (toastResponse.contains("Plan created successfully")) {
    	    getTest().log(LogStatus.PASS, "Price Protection Plan created successfully");
    	} else {
    	    getTest().log(LogStatus.FAIL, "Unexpected toast message: " + toastResponse);
    	}

    	logScreenshot();
    }
    
    
    @AfterClass
    public void tearDown() {
        closeDriver();
    }
}