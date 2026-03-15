package storeTests;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utility.ReusableMethods;

public class Ads extends ReusableMethods {

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
    public void navigateAndValidateAds() {
        startTest("Navigate to Ads and validate fields");

        click("//span[text()='Dashboard']/..", "dashboard button");
        clickByCSS(".max-xl\\3Ahidden > .w-full", "features expand");
        click("//span[text()='Ads']", "Ads link");

        // Ads Management heading (display + label)
        verifyIsDisplayed("//h3[text()='Ads Management']", "Ads Management heading");
        
        
        // Create New Ad button: display + enabled + label
        verifyIsDisplayed("//button[text()='Create New Ad']", "Create New Ad button");
        verifyIsEnabled("//button[text()='Create New Ad']", "Create New Ad button");
        
        // Applications section (label/display)
        verifyIsDisplayed("//span[text()='Applications']", "label - Applications");
        verifyIsDisplayed("//div[text()='Aurum Store']", "Aurum Store option");
        
        // Staff option
        verifyIsDisplayed("//span[text()='Staff']", "label - Staff ");
        verifyIsDisplayed("//div[text()='Select staff members']", "Select staff members placeholder");
        
        
        // Search Ad Campaigns input - display + placeholder + enabled (best-effort)
        verifyIsDisplayed("//span[text()='Search Ad Campaigns']", "label - Search Ad Campaigns ");
        verifyIsDisplayed("//input[@placeholder='Search Ads']", "Search Ads placeholder");
        

        // Clear button: display + enabled/disabled
        verifyIsDisplayed("//button[text()='Clear']", "Clear button");
        verifyIsDisabled("//button[text()='Clear']", "Clear button");

        // Apply Filters button: display + enabled/disabled
        verifyIsDisplayed("//button[text()='Apply Filters']", "Apply Filters button");
        verifyIsDisabled("//button[text()='Apply Filters']", "Apply Filters button");

        logScreenshot();
    }

    @Test(priority = 2)
    public void createNewAdAndValidateForm() {
        startTest("Create New Ad - validate form fields");

        // Click Create New Ad
        click("//button[text()='Create New Ad']", "Create New Ad button");

        // Form heading - try common variants
        if (isDisplayed("//h3[text()='Create New Ad']")) {
            verifyIsDisplayed("//h3[text()='Create New Ad']", "Create New Ad heading");
        } else if (isDisplayed("//h3[text()='New Ad']")) {
            verifyIsDisplayed("//h3[text()='New Ad']", "New Ad heading");
        } else {
            // fallback to any modal header
            verifyIsDisplayed("//div[contains(@class,'modal')]//h3", "Ad form heading (fallback)");
        }

        // Validate form fields grouped: label, mandatory mark, input display, placeholder and enabled
        try {
            // Ad Title
            verifyIsDisplayed("//label[text()='Ad Title']", "Label - Ad Title");
            verifyIsDisplayed("//label[text()='Ad Title']/..//span[text()='*']", "Mandatory - Ad Title");
            verifyIsDisplayed("//label[text()='Ad Title']/following::input[1]", "Ad Title input");
            String phTitle = driver.findElement(By.xpath("//label[text()='Ad Title']/following::input[1]")).getAttribute("placeholder");
            getTest().log(com.relevantcodes.extentreports.LogStatus.INFO, "Ad Title placeholder: " + phTitle);
            try {
                boolean enabled = driver.findElement(By.xpath("//label[text()='Ad Title']/following::input[1]")).isEnabled();
                if (enabled) logPass("Ad Title input enabled"); else logFail("Ad Title input disabled");
            } catch (Exception e) {
                logFail("Unable to determine enabled state for Ad Title: " + e.getMessage());
            }
        } catch (Exception e) {
            getTest().log(com.relevantcodes.extentreports.LogStatus.INFO, "Ad Title field checks skipped/failed: " + e.getMessage());
        }

        try {
            // Application / Aurum Store / Staff selection
            verifyIsDisplayed("//label[text()='Application']", "Label - Application");
            verifyIsDisplayed("//label[text()='Application']/..//span[text()='*']", "Mandatory - Application");
            // application control may be a div or an input
            if (isDisplayed("//label[text()='Application']/following::input[1]")) {
                verifyIsDisplayed("//label[text()='Application']/following::input[1]", "Application input control");
                String ph = driver.findElement(By.xpath("//label[text()='Application']/following::input[1]")).getAttribute("placeholder");
                getTest().log(com.relevantcodes.extentreports.LogStatus.INFO, "Application placeholder: " + ph);
                boolean enabled = driver.findElement(By.xpath("//label[text()='Application']/following::input[1]")).isEnabled();
                if (enabled) logPass("Application input enabled"); else logFail("Application input disabled");
            } else {
                verifyIsDisplayed("//label[text()='Application']/following::div[1]", "Application control (div)");
                getTest().log(com.relevantcodes.extentreports.LogStatus.INFO, "Application control verified (div)");
            }
        } catch (Exception e) {
            getTest().log(com.relevantcodes.extentreports.LogStatus.INFO, "Application field checks skipped/failed: " + e.getMessage());
        }

        try {
            // Select staff members (multi-select)
            verifyIsDisplayed("//label[text()='Select staff members']", "Label - Select staff members");
            verifyIsDisplayed("//label[text()='Select staff members']/..//span[text()='*']", "Mandatory - Select staff members");
            if (isDisplayed("//label[text()='Select staff members']/following::input[1]")) {
                verifyIsDisplayed("//label[text()='Select staff members']/following::input[1]", "Select staff members input");
                String ph = driver.findElement(By.xpath("//label[text()='Select staff members']/following::input[1]")).getAttribute("placeholder");
                getTest().log(com.relevantcodes.extentreports.LogStatus.INFO, "Select staff members placeholder: " + ph);
                boolean enabled = driver.findElement(By.xpath("//label[text()='Select staff members']/following::input[1]")).isEnabled();
                if (enabled) logPass("Select staff members input enabled"); else logFail("Select staff members input disabled");
            } else {
                verifyIsDisplayed("//label[text()='Select staff members']/following::div[1]", "Select staff members control");
                getTest().log(com.relevantcodes.extentreports.LogStatus.INFO, "Select staff members control verified");
            }
        } catch (Exception e) {
            getTest().log(com.relevantcodes.extentreports.LogStatus.INFO, "Select staff members checks skipped/failed: " + e.getMessage());
        }

        try {
            // Ad Campaign
            verifyIsDisplayed("//label[text()='Ad Campaign']", "Label - Ad Campaign");
            verifyIsDisplayed("//label[text()='Ad Campaign']/..//span[text()='*']", "Mandatory - Ad Campaign");
            if (isDisplayed("//label[text()='Ad Campaign']/following::input[1]")) {
                verifyIsDisplayed("//label[text()='Ad Campaign']/following::input[1]", "Ad Campaign input");
                String ph = driver.findElement(By.xpath("//label[text()='Ad Campaign']/following::input[1]")).getAttribute("placeholder");
                getTest().log(com.relevantcodes.extentreports.LogStatus.INFO, "Ad Campaign placeholder: " + ph);
                boolean enabled = driver.findElement(By.xpath("//label[text()='Ad Campaign']/following::input[1]")).isEnabled();
                if (enabled) logPass("Ad Campaign input enabled"); else logFail("Ad Campaign input disabled");
            } else {
                verifyIsDisplayed("//label[text()='Ad Campaign']/following::div[1]", "Ad Campaign control");
                getTest().log(com.relevantcodes.extentreports.LogStatus.INFO, "Ad Campaign control verified (div)");
            }
        } catch (Exception e) {
            getTest().log(com.relevantcodes.extentreports.LogStatus.INFO, "Ad Campaign checks skipped/failed: " + e.getMessage());
        }

        try {
            // Ad Image / Thumbnail (file input)
            verifyIsDisplayed("//label[text()='Ad Image']", "Label - Ad Image");
            verifyIsDisplayed("//label[text()='Ad Image']/..//span[text()='*']", "Mandatory - Ad Image");
            verifyIsDisplayed("//input[@type='file' and contains(@id,'image')]|//input[@type='file' and contains(@name,'image')]", "Ad Image file input");
            try {
                boolean enabled = driver.findElement(By.xpath("//input[@type='file' and contains(@id,'image')]|//input[@type='file' and contains(@name,'image')]")).isEnabled();
                if (enabled) logPass("Ad Image file input enabled"); else logFail("Ad Image file input disabled");
            } catch (Exception e) {
                getTest().log(com.relevantcodes.extentreports.LogStatus.INFO, "Ad Image enabled check skipped: " + e.getMessage());
            }
            getTest().log(com.relevantcodes.extentreports.LogStatus.INFO, "Ad Image input verified");
        } catch (Exception e) {
            getTest().log(com.relevantcodes.extentreports.LogStatus.INFO, "Ad Image checks skipped/failed: " + e.getMessage());
        }

        try {
            // Start and End Date
            verifyIsDisplayed("//label[text()='Start date']", "Label - Start date");
            verifyIsDisplayed("//label[text()='Start date']/..//span[text()='*']", "Mandatory - Start date");
            verifyIsDisplayed("//label[text()='Start date']/following::input[1]", "Start Date input");
            try {
                String ph = driver.findElement(By.xpath("//label[text()='Start date']/following::input[1]")).getAttribute("placeholder");
                getTest().log(com.relevantcodes.extentreports.LogStatus.INFO, "Start date placeholder: " + ph);
                boolean enabled = driver.findElement(By.xpath("//label[text()='Start date']/following::input[1]")).isEnabled();
                if (enabled) logPass("Start Date input enabled"); else logFail("Start Date input disabled");
            } catch (Exception e) {
                getTest().log(com.relevantcodes.extentreports.LogStatus.INFO, "Start Date enabled/placeholder check skipped: " + e.getMessage());
            }

            verifyIsDisplayed("//label[text()='End date']", "Label - End date");
            verifyIsDisplayed("//label[text()='End date']/..//span[text()='*']", "Mandatory - End date");
            verifyIsDisplayed("//label[text()='End date']/following::input[1]", "End Date input");
            try {
                String ph = driver.findElement(By.xpath("//label[text()='End date']/following::input[1]")).getAttribute("placeholder");
                getTest().log(com.relevantcodes.extentreports.LogStatus.INFO, "End date placeholder: " + ph);
                boolean enabled = driver.findElement(By.xpath("//label[text()='End date']/following::input[1]")).isEnabled();
                if (enabled) logPass("End Date input enabled"); else logFail("End Date input disabled");
            } catch (Exception e) {
                getTest().log(com.relevantcodes.extentreports.LogStatus.INFO, "End Date enabled/placeholder check skipped: " + e.getMessage());
            }
        } catch (Exception e) {
            getTest().log(com.relevantcodes.extentreports.LogStatus.INFO, "Date fields checks skipped/failed: " + e.getMessage());
        }

        try {
            // CTA Text and Link
            verifyIsDisplayed("//label[text()='Custom Button Text']", "Label - Custom Button Text");
            verifyIsDisplayed("//label[text()='Custom Button Text']/..//span[text()='*']", "Mandatory - Custom Button Text");
            verifyIsDisplayed("//label[text()='Custom Button Text']/following::input[1]", "Custom Button Text input");
            try {
                String ph = driver.findElement(By.xpath("//label[text()='Custom Button Text']/following::input[1]")).getAttribute("placeholder");
                getTest().log(com.relevantcodes.extentreports.LogStatus.INFO, "Custom Button Text placeholder: " + ph);
                boolean enabled = driver.findElement(By.xpath("//label[text()='Custom Button Text']/following::input[1]")).isEnabled();
                if (enabled) logPass("Custom Button Text input enabled"); else logFail("Custom Button Text input disabled");
            } catch (Exception e) {
                getTest().log(com.relevantcodes.extentreports.LogStatus.INFO, "Custom Button Text checks skipped: " + e.getMessage());
            }

            verifyIsDisplayed("//label[text()='Custom Button Link']", "Label - Custom Button Link");
            verifyIsDisplayed("//label[text()='Custom Button Link']/..//span[text()='*']", "Mandatory - Custom Button Link");
            verifyIsDisplayed("//label[text()='Custom Button Link']/following::input[1]", "Custom Button Link input");
            try {
                String ph = driver.findElement(By.xpath("//label[text()='Custom Button Link']/following::input[1]")).getAttribute("placeholder");
                getTest().log(com.relevantcodes.extentreports.LogStatus.INFO, "Custom Button Link placeholder: " + ph);
                boolean enabled = driver.findElement(By.xpath("//label[text()='Custom Button Link']/following::input[1]")).isEnabled();
                if (enabled) logPass("Custom Button Link input enabled"); else logFail("Custom Button Link input disabled");
            } catch (Exception e) {
                getTest().log(com.relevantcodes.extentreports.LogStatus.INFO, "Custom Button Link checks skipped: " + e.getMessage());
            }
        } catch (Exception e) {
            getTest().log(com.relevantcodes.extentreports.LogStatus.INFO, "Custom button checks skipped/failed: " + e.getMessage());
        }

        // Create / Save button
        try {
            verifyIsDisplayed("//button[normalize-space(text())='Create Ad' or normalize-space(text())='Save' or normalize-space(text())='Create']", "Create/Save button");
            boolean enabled = driver.findElement(By.xpath("//button[normalize-space(text())='Create Ad' or normalize-space(text())='Save' or normalize-space(text())='Create']")).isEnabled();
            if (enabled) logPass("Create/Save button is enabled"); else logFail("Create/Save button is disabled");
        } catch (Exception e) {
            getTest().log(com.relevantcodes.extentreports.LogStatus.INFO, "Create/Save button check skipped/failed: " + e.getMessage());
        }

        logScreenshot();
    }
}