package storeTests;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import utility.ReusableMethods;

public class MetalTypes extends ReusableMethods {

    WebDriverWait wait;

    @BeforeClass
    public void prerequisites() {
        initialise();
    }

    @AfterMethod
    public void afterTest() {
        endTest();
    }
    @AfterClass
    public void quitDriver() {
        driver.quit();
    }

    @Test(priority = 1)
    public void launchTest() throws IOException, TimeoutException {

        startTest("Navigate to store");

        openBrowser("chrome", "https://deepa.aurumconnect.in/");

        verifyIsDisplayed("//p[text()='Deepa Jewellers']", "Deepa Jewellers header");
        verifyIsDisplayed("//a[text()='Login']", "Login link");

        logScreenshot();
    }

    @Test(priority = 2)
    public void loginTest() {

        startTest("login to store");

        click("//a[text()='Login']", "Login link");

        verifyIsDisplayed("//h3[text()='Login']", "Login label");

        verifyIsDisplayed("//button[contains(@class,'text-gray-600')]//span[text()='Back to Website']", "Back to Website");

        clearNenterText("//button[text()='+91']/..//input[@placeholder='9999988888']", "9392190045", "Mobile number");

        click("//button[text()='Send OTP']", "Send OTP button");

        String otp = "860821";

        for (int i = 0; i < otp.length(); i++) {
            clearNenterText("(//div[@role='group']//input)[" + (i + 1) + "]","" + otp.charAt(i),"OTP " + (i + 1));
        }

        click("//button[text()='Login']", "Login button");

        verifyIsDisplayed("//span[text()='Dashboard']/..", "Dashboard button");

        logScreenshot();
    }

    @Test(priority = 3)
    public void metalPricesTest() {

        startTest("Navigate to Metal Prices page");

        click("//span[text()='Dashboard']/..", "Dashboard");

        click("//a[@href=\"/admin/goldPricesList\"]", "Metal Prices");

        verifyIsDisplayed("//h3[text()='Metal Prices']", "Metal Prices heading");

        verifyIsDisplayed("//button[contains(text(),'Update Prices')]", "Update Prices button");

        logScreenshot();
    }

    @Test(priority = 4)
    public void metalPricesGoldFields() {

        startTest("Verify Gold metal fields");

        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        click("//button[contains(text(),'Update Prices')]", "Update Prices button");

        verifyTextDisplayed("(//span[normalize-space(text())='Metal'])[last()]/ancestor::div[contains(@class,'rounded-md')]//div[contains(@class,'react-select__single')]","Gold","Metal dropdown");

        String[] karatFields = {"22K", "24K", "18K", "14K", "10K", "21K", "9K"};

        for (String karat : karatFields) {

            WebElement label = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//label[contains(normalize-space(),'" + karat + "')]")));

            WebElement star = driver.findElement(
                    By.xpath("//label[contains(normalize-space(),'" + karat + "')]/span[contains(text(),'*')]"));

            WebElement input = driver.findElement(
                    By.xpath("//label[contains(normalize-space(),'" + karat + "')]/following::input[1]"));

            if (label.isDisplayed()) {
                getTest().log(LogStatus.PASS, karat + " label displayed");
            } else {
                getTest().log(LogStatus.FAIL, karat + " label NOT displayed " + addScreenShot());
                throw new AssertionError(karat + " label not visible");
            }

            if (star.isDisplayed()) {
                getTest().log(LogStatus.PASS, karat + " mandatory star displayed");
            } else {
                getTest().log(LogStatus.FAIL, karat + " mandatory star NOT displayed " + addScreenShot());
                throw new AssertionError(karat + " star not visible");
            }

            if (input.isDisplayed()) {
                getTest().log(LogStatus.PASS, karat + " input field displayed");
            } else {
                getTest().log(LogStatus.FAIL, karat + " input field NOT displayed " + addScreenShot());
                throw new AssertionError(karat + " input not visible");
            }
        }

        logScreenshot();
    }

    @Test(priority = 5)
    public void metalPricesSilverFields() {

        startTest("Verify Silver metal fields");

        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement metalInput = driver.findElement(By.xpath("(//span[normalize-space(text())='Metal'])[last()]/ancestor::div[contains(@class,'rounded-md')]//input"));

        metalInput.sendKeys("Silver");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@id,'react-select') and normalize-space()='Silver']"))).click();

        verifyTextDisplayed("(//span[normalize-space(text())='Metal'])[last()]/ancestor::div[contains(@class,'rounded-md')]//div[contains(@class,'react-select__single')]","Silver","Metal dropdown");

        String[] silverFields = {"Silver", "99.9% Silver", "92.5% Silver", "80% Silver", "95.8% Silver"};

        for (String purity : silverFields) {

            WebElement label = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(normalize-space(),'" + purity + "')]")));

            WebElement star = driver.findElement(By.xpath("//label[contains(normalize-space(),'" + purity + "')]/span[contains(text(),'*')]"));

            WebElement input = driver.findElement(By.xpath("//label[contains(normalize-space(),'" + purity + "')]/following::input[1]"));

            if (!label.isDisplayed() || !star.isDisplayed() || !input.isDisplayed()) {
                getTest().log(LogStatus.FAIL, purity + " field validation failed " + addScreenShot());
                throw new AssertionError(purity + " field missing");
            }
            getTest().log(LogStatus.PASS, purity + " field verified");
        }

        logScreenshot();
    }

    @Test(priority = 6)
    public void metalPricesGoldFormSubmission() {

        startTest("Gold form submission");
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        selectMetal("Gold");

        enterValue("24K", "16500");
        enterValue("22K", "15900");
        enterValue("21K", "15400");
        enterValue("18K", "14500");
        enterValue("14K", "13700");
        enterValue("10K", "10000");
        enterValue("9K", "9000");

        submitAndValidateToast("Successfully Updated", "Gold");
    }

    @Test(priority = 7)
    public void metalPricesSilverFormSubmission() {

        startTest("Silver form submission");

        click("//button[contains(text(),'Update Prices')]", "Update Prices button");

        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        selectMetal("Silver");

        enterValue("Silver", "300000");
        enterValue("99.9% Silver", "295000");
        enterValue("92.5% Silver", "290000");
        enterValue("80% Silver", "275000");
        enterValue("95.8% Silver", "280000");

        submitAndValidateToast("Successfully Updated", "Silver");
    }

    public void selectMetal(String metal) {

        WebElement metalInput = driver.findElement(
                By.xpath("(//span[normalize-space(text())='Metal'])[last()]/ancestor::div[contains(@class,'rounded-md')]//input"));

        metalInput.sendKeys(metal);

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@id,'react-select') and normalize-space()='" + metal + "']"))).click();

        getTest().log(LogStatus.PASS, metal + " selected from dropdown");
    }

    public void enterValue(String karat, String value) {

        try {

            WebElement input = driver.findElement(
                    By.xpath("//label[contains(normalize-space(),'" + karat + "')]/ancestor::div[contains(@class,'input')]//input"));

            input.clear();
            input.sendKeys(value);

            getTest().log(LogStatus.PASS, karat + " value entered : " + value);

        } catch (Exception e) {

            getTest().log(LogStatus.FAIL, karat + " input field NOT displayed " + addScreenShot());
            throw e;
        }
    }

    public void submitAndValidateToast(String expectedToast, String metal) {

        try {

            WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[normalize-space()='Submit']")));

            submitBtn.click();

            getTest().log(LogStatus.PASS, "Clicked Submit button");

        } catch (Exception e) {

            getTest().log(LogStatus.FAIL, "Submit button NOT clickable " + addScreenShot());
            throw e;
        }

        try {

            String toastXpath = "//div[contains(@class,'Toastify')]//span";

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(toastXpath)));

            String toastResponse = getResponse(toastXpath);

            if (toastResponse.contains(expectedToast)) {

                getTest().log(LogStatus.PASS, metal + " metal update successful : " + toastResponse);

            } else {

                getTest().log(LogStatus.FAIL, "Unexpected toast : " + toastResponse + addScreenShot());
                throw new AssertionError(toastResponse);
            }

        } catch (Exception e) {

            getTest().log(LogStatus.FAIL, "Toast not captured " + addScreenShot());
            throw e;
        }

        logScreenshot();
    }
}