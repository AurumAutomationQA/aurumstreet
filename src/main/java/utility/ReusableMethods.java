package utility;

import static org.testng.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.asserts.Assertion;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;


public class ReusableMethods {
	
	public WebDriver driver;
	public WebDriver webDriver;
	public WebDriver androidDriver;
    
	static Map<Integer,ExtentTest> extentTestMap				 = new HashMap<Integer,ExtentTest>();
	static Map<Integer,ExtentTest> extentParentMap 				 = new HashMap<Integer,ExtentTest>();
	static Map<Integer,ExtentReports> extentreportsMap			 = new HashMap<Integer,ExtentReports>();
	static Map<Integer,String> reportLocationMap				 = new HashMap<Integer,String>();
	static String reportLocation 								 = null;	
	static String htmlLocation 									 = null;                           
	static ExtentReports reports 								 = null;
	public static Properties prop             					 = new Properties();
	protected static AppiumDriverLocalService service=null;
	protected static String localHost="127.0.0.1";
	protected static int localPort=4723;
	protected static String localHostURL="http://127.0.0.1:4723";
	protected static String apkName="aurum_staging_11_Mar.apk";
	protected static String appPath = System.getProperty("user.dir") + File.separator + "Downloads" + File.separator + apkName;
	
	public synchronized static void initializeReportLocation(String reportlocation) {
		reportLocationMap.put((int) (long) (Thread.currentThread().getId()), reportlocation);
	}

	public static synchronized String getreportlocation() {
		return reportLocationMap.get((int) (long) (Thread.currentThread().getId()));
	}

	public synchronized static void startReporter(String location) {
		extentreportsMap.put((int) (long) (Thread.currentThread().getId()), reports);
	}

	public static synchronized ExtentReports getreport() {
		return extentreportsMap.get((int) (long) (Thread.currentThread().getId()));
	}

	public synchronized static void startTest(String test) {
		ExtentTest logger=getreport().startTest(test);
		extentTestMap.put((int) (long) (Thread.currentThread().getId()), logger);
	}

	public synchronized static void endTest(){
		getreport().endTest(extentTestMap.get((int) (long) (Thread.currentThread().getId())));
		getreport().flush();
	}

	public static synchronized ExtentTest getTest() {
		return extentTestMap.get((int) (long) (Thread.currentThread().getId()));
	}

	public void logPass(String details){
		getTest().log(LogStatus.PASS,  details);	
	}
	public void logFail(String details){
		getTest().log(LogStatus.FAIL,  details);	
	}
	public void logInfo(String details){
		getTest().log(LogStatus.INFO,  details);	
	}
	public void logScreenshot(){
		getTest().log(LogStatus.INFO,  addScreenShot());	
	}
	
	
	@AfterSuite
    public void stopAllDrivers() {

        quitAllDrivers();

    }
	
	 public void loginToStore(){
	        openBrowser("chrome","https://deepa.aurumconnect.in/");

	        verifyIsDisplayed("//p[text()='Deepa Jewellers']","Deepa Jewellers header");
	        click("//a[text()='Login']","Login link");

	        verifyIsDisplayed("//h3[text()='Login']","Login label");
	        verifyIsDisplayed("//button[text()='+91']/..//input[@placeholder='9999988888']","Mobile number field");

	        clearNenterText("//button[text()='+91']/..//input[@placeholder='9999988888']","9392190045","Mobile number field");
	        logScreenshot();

	        click("//button[text()='Send OTP']","send OTP button");
	        verifyIsDisplayed("//p[contains(text(),'A OTP has sent to ')]","OTP sent message");
	        verifyIsDisplayed("//label[text()='Enter OTP']","Enter OTP label");

	        String otp = "860821";
	        clearNenterText("(//div[@role='group']//input)[1]", ""+otp.charAt(0), "otp 1st character");
	        clearNenterText("(//div[@role='group']//input)[2]", ""+otp.charAt(1), "otp 2nd character");
	        clearNenterText("(//div[@role='group']//input)[3]", ""+otp.charAt(2), "otp 3rd character");
	        clearNenterText("(//div[@role='group']//input)[4]", ""+otp.charAt(3), "otp 4th character");
	        clearNenterText("(//div[@role='group']//input)[5]", ""+otp.charAt(4), "otp 5th character");
	        clearNenterText("(//div[@role='group']//input)[6]", ""+otp.charAt(5), "otp 6th character");

	        click("//button[text()='Login']","Login button");
	        verifyIsDisplayed("//span[text()='Dashboard']/..","dashboard button");
	        logScreenshot();
	    }


	protected static synchronized void initialise() {

        try {

            File file = new File("./src/main/java/testData/testData.properties");

            FileInputStream fileInput = new FileInputStream(file);

            prop.load(fileInput);

        } catch (IOException e) {

            e.printStackTrace();
        }

        if (reportLocation==null && htmlLocation==null && reports==null) {

            reportLocation = System.getProperty("user.dir")+ "/Reports/Automation_"+ new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date())+ "/";

            htmlLocation = "Automation_"+ new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss'.html'").format(new Date());

            reports = new ExtentReports(reportLocation + htmlLocation, false);
        }
        initializeReportLocation(reportLocation);
		startReporter(reportLocation+htmlLocation);
    }
	
    
    public static void startAppiumService() {

        if (service == null) {
            String appiumHome = System.getenv("APPDATA") + File.separator + "npm";

            File mainJS = new File(appiumHome+ File.separator + "node_modules"+ File.separator + "appium"+ File.separator + "build"+ File.separator + "lib"+ File.separator + "main.js");

            service = new AppiumServiceBuilder()
                    .withAppiumJS(mainJS)
                    .withIPAddress(localHost)
                    .usingPort(localPort)
                    .build();
            service.start();

            System.out.println("Appium Server Started");
        }
    }
    
    
    public void launchApp() {

    	try {

            startAppiumService();

            UiAutomator2Options options = new UiAutomator2Options();

            options.setPlatformName("Android");
            options.setAutomationName("UiAutomator2");

            options.setApp(appPath);

            options.setAutoGrantPermissions(true);
            options.setNoReset(false);
            options.setFullReset(false);

            driver = new AndroidDriver(new URL("http://" + localHost + ":" + localPort),options);
            androidDriver=driver;

            driver.manage().timeouts()
                    .implicitlyWait(Duration.ofSeconds(10));

            if (driver != null) {
                System.out.println("App launched successfully");
            } else {
                throw new RuntimeException("Driver session not created");
            }

        } catch (Exception e) {

            throw new RuntimeException("Failed to launch app", e);
        }
    	logInfo("App Launched Successfully");

    }
    
    
    public void openBrowser(String browser, String URL){

		try {
			 if (driver == null) {

		        	switch (browser.toLowerCase().trim()) {
		    		case "chrome": {
		    			ChromeOptions options = new ChromeOptions();
		    			options.addArguments("--disable-notifications");
		    			driver = new ChromeDriver(options);
		    			webDriver=driver;
		    			driver.manage().window().maximize();
						getTest().log(LogStatus.INFO,"Opened chrome Browser Successfully and navigated to url : </br>" + URL);
						 
		    		}
		    			break;
		    		case "firefox": {
		    			FirefoxOptions options = new FirefoxOptions();
		    			options.addArguments("--window-size=1400,900");
		    			options.addArguments("--headless");
		    			options.addArguments("--disable-notifications");
		    			driver = new FirefoxDriver(options);
		    			webDriver=driver;
						getTest().log(LogStatus.INFO,"Opened FireFox Browser Successfully and navigated to url : </br>" + URL);
						 
		    		}
		    			break;

		    		case "edge": {
		    			EdgeOptions options = new EdgeOptions();
		    			options.addArguments("--window-size=1400,900");
		    			options.addArguments("--headless");
		    			options.addArguments("--disable-notifications");
		    			driver = new EdgeDriver(options);
		    			webDriver=driver;
						getTest().log(LogStatus.INFO,"Opened Edge Browser Successfully and navigated to url : </br>" + URL);
						 
		    		}
		    			break;

		    		default:
		    			ChromeOptions options = new ChromeOptions();
		    			options.addArguments("--window-size=1400,900");
		    			options.addArguments("--headless");
		    			options.addArguments("--disable-notifications");
		    			driver = new ChromeDriver(options);
		    			webDriver=driver;
						getTest().log(LogStatus.INFO,"Opened Chrome Browser Successfully and navigated to url : </br>" + URL);
						 
		    			break;
		    		}
		            driver.manage().window().maximize();

		            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		        }
		        driver.get(URL);
			
			
		} catch (Exception e) {
			logFail("Exception while opening browser "+browser+" and navigating to url : </br>" + URL+" is due to <br/>"+e+addScreenShot());
			    new Assertion().fail();
		}
		
		
       
    }
	
	
	protected void ewait(String xpath){ 
		  new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))); 
	}
	 

	protected void clearNenterText(String xpath,String text,String elementName){
		try {
			ewait(xpath);
			driver.findElement(By.xpath(xpath)).clear();
			driver.findElement(By.xpath(xpath)).sendKeys(text);
			getTest().log(LogStatus.INFO, "Cleared and entered text in "+elementName);
		} catch (Exception e) {
			getTest().log(LogStatus.FAIL, "Exception while clearing and entering text in "+elementName+" is due to <br/>"+e+addScreenShot());
			new Assertion().fail();
		}
	}
	

	protected void verifyTextDisplayed(String xpath,String text,String elementName){
		try {
			ewait(xpath);
			String actualText=driver.findElement(By.xpath(xpath)).getText();
			if(actualText.contains(text)) {
				logPass(elementName+" contains the text '"+text+"'");
			}
			else {
				logFail(elementName+" doesnot contains the text '"+text+"'"+addScreenShot());
			}
		} catch (Exception e) {
			getTest().log(LogStatus.FAIL, "Exception while verifying text in "+elementName+" is due to <br/>"+e+addScreenShot());
			new Assertion().fail();
		}
	}

	protected void click(String xpath,String elementName){
		try {
			ewait(xpath);
			driver.findElement(By.xpath(xpath)).click();
			getTest().log(LogStatus.INFO, "clicked "+elementName);
		} catch (Exception e) {
			getTest().log(LogStatus.FAIL, "Exception while clicking "+elementName+" is due to <br/>"+e);
			new Assertion().fail();
		}
	}

	protected void check(String xpath,String elementName){
		try {
			if (!driver.findElement(By.xpath(xpath)).isSelected()) {
				ewait(xpath);
				driver.findElement(By.xpath(xpath)).click();
				getTest().log(LogStatus.INFO, "Checked " + elementName);
			}
		} catch (Exception e) {
			getTest().log(LogStatus.FAIL, "Exception while checking "+elementName+" is due to <br/>"+e);
			new Assertion().fail();
		}
	}

	protected void sleep(int seconds){
		try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	protected void clickByCSS(String css,String elementName){
		try {
			Thread.sleep(2000);
			driver.findElement(By.cssSelector(css)).click();
			getTest().log(LogStatus.INFO, "clicked "+elementName);
		} catch (Exception e) {
			getTest().log(LogStatus.FAIL, "Exception while clicking "+elementName+" is due to <br/>"+e);
			new Assertion().fail();
		}
	}
	
	public boolean isDisplayed(String xpath) {
		try {
	        boolean displayed = driver.findElement(By.xpath(xpath)).isDisplayed();
	        if (displayed) {
	            getTest().log(LogStatus.INFO, "Displayed: " + xpath);
	        }
	        return displayed;
	    } catch (NoSuchElementException e) {
	        getTest().log(LogStatus.INFO, "Element not found: " + xpath);
	        return false;
	    } catch (Exception e) {
	        getTest().log(LogStatus.ERROR, "Error checking display status of: " + xpath + " - " + e);
	        return false;
	    }
	}

	protected void verifyIsDisplayed(String xpath,String elementName){
		try {
			ewait(xpath);
			driver.findElement(By.xpath(xpath)).isDisplayed();
			getTest().log(LogStatus.INFO, "displayed "+elementName);
		} catch (Exception e) {
			getTest().log(LogStatus.FAIL, "Exception while checking the visibility of "+elementName+" is due to <br/>"+e+addScreenShot());
			new Assertion().fail();
		}
	}
	
	protected void verifyIsDisabled(String xpath,String elementName){
		try {
			ewait(xpath);
			if (driver.findElement(By.xpath(xpath)).isEnabled()) {
				getTest().log(LogStatus.FAIL,elementName + " is enabled" + addScreenShot());
				new Assertion().fail();
			} else {
				getTest().log(LogStatus.PASS,elementName + " is disabled");
			}
		} catch (Exception e) {
			getTest().log(LogStatus.FAIL, "Exception while checking the disability of "+elementName+" is due to <br/>"+e+addScreenShot());
			new Assertion().fail();
		}
	}
	
	protected void verifyIsEnabled(String xpath,String elementName){
		try {
			ewait(xpath);
			driver.findElement(By.xpath(xpath)).isEnabled();
			getTest().log(LogStatus.INFO, "Enabled "+elementName);
		} catch (Exception e) {
			getTest().log(LogStatus.FAIL, "Exception while checking the Enability of "+elementName+" is due to <br/>"+e+addScreenShot());
			new Assertion().fail();
		}
	}
	
	protected String tearDown() {

	    try {

	        UUID uuid = UUID.randomUUID();

	        File scrFile = ((TakesScreenshot) driver)
	                .getScreenshotAs(OutputType.FILE);

	        File dest = new File(getreportlocation() + uuid + ".png");

	        FileUtils.copyFile(scrFile, dest);

	        return dest.getAbsolutePath();

	    } catch (Exception e) {

	        System.out.println("Error while generating screenshot:\n" + e);

	        return "";
	    }
	}
	
	public String addScreenShot(){

	    if(driver != null){

	        return getTest().addScreenCapture(tearDown());

	    }

	    return "";
	}

	public void closeDriver(){
	    if(driver != null){
	        driver.quit();
	        driver = null;
	    }
	}

	public static String getFormattedDateTime() {

        LocalDateTime now = LocalDateTime.now();

        int day = now.getDayOfMonth();
        String suffix;

        if (day >= 11 && day <= 13) {
            suffix = "th";
        } else {
            switch (day % 10) {
                case 1: suffix = "st"; break;
                case 2: suffix = "nd"; break;
                case 3: suffix = "rd"; break;
                default: suffix = "th";
            }
        }

        return now.format(DateTimeFormatter.ofPattern("EEE, MMMM d"))
                + suffix
                + now.format(DateTimeFormatter.ofPattern(", yyyy, h:mm:ss a"));
    }
	
	
	
	public String getResponse(String xpath) {
		String message=null;
		try {
			WebElement toastInfo=driver.findElement(By.xpath(xpath));
			message = toastInfo.getText();
			getTest().log(LogStatus.INFO, "Toast message info: " + message);
		}catch (Exception e) {
			fail("Unable to fetch toast message info", e);
		}
		return message;
	}
	
	
	public void setCheckbox(String xpath, boolean shouldBeChecked, String fieldName) {
	    try {
	        WebElement checkbox = driver.findElement(By.xpath(xpath));

	        boolean isChecked = checkbox.isSelected();

	        if (shouldBeChecked && !isChecked) {
	            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", checkbox);
	            getTest().log(LogStatus.INFO, fieldName + " checkbox checked");
	        } 
	        else if (!shouldBeChecked && isChecked) {
	            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", checkbox);
	            getTest().log(LogStatus.INFO, fieldName + " checkbox unchecked");
	        } 
	        else {
	            getTest().log(LogStatus.INFO, fieldName + " checkbox already in desired state");
	        }

	    } catch (Exception e) {
	        getTest().log(LogStatus.FAIL, "Unable to handle checkbox: " + fieldName + addScreenShot());
	    }
	}
	
	
	public String randomPhoneNumber()
	{
		long rNum=System.currentTimeMillis();
		String randomNum=String.valueOf(rNum);
		randomNum=randomNum.substring(2, 10);
		String phoneNumber="98"+randomNum;
		int i=1;
	    while(phoneNumber.length()<10)
	    {
	    	rNum=System.currentTimeMillis();
	    	randomNum=String.valueOf(rNum);
	    	randomNum=randomNum.substring(2, 10);
			phoneNumber="98"+randomNum;
	    	if(i==5)
	    	{
	    		break;
	    	}
	    	i++;
	    }
		return phoneNumber;
	}
	
	
	public String randomEmail()
	{
	    long rNum = System.currentTimeMillis();
	    String randomNum = String.valueOf(rNum);

	    String email = "test" + randomNum + "@mail.com";

	    return email;
	}

	
	public void quitAllDrivers() {

        if (driver != null) {
            driver.quit();
            driver = null;
        }
        
        if (service != null) {
            service.stop();
            service = null;
        }
    }
	
	
	protected void switchToWebDriver() {
		driver = webDriver;
	}
	
	protected void switchToAndroidDriver() {
		driver = androidDriver;
	}
	
	protected void datePicker() {
		Date date = new Date();

		String day = new SimpleDateFormat("d").format(date);
		String month = new SimpleDateFormat("MMMM").format(date);
		String year = new SimpleDateFormat("yyyy").format(date);

		driver.findElement(By.xpath("//select[contains(@class,'react-datepicker__month-select')]")).sendKeys(month);
		driver.findElement(By.xpath("//select[contains(@class,'react-datepicker__year-select')]")).sendKeys(year);
		driver.findElement(By.xpath("//div[contains(@class,'react-datepicker__day') and not(contains(@class,'outside-month')) and text()='"+day+"']")).click();
	}
	
	protected void uploadFile(String xpath, String filePath, String elementName) {
		try {
			WebElement upload = driver.findElement(By.xpath(xpath));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", upload);
			upload.sendKeys(filePath);
			getTest().log(LogStatus.INFO, "Uploaded file in "+elementName);
		} catch (Exception e) {
			getTest().log(LogStatus.FAIL, "Exception while uploading file in "+elementName+" is due to <br/>"+e+addScreenShot());
			new Assertion().fail();
		}
	}
	
	protected void toastMessageValidation(String xpath, String expectedMessage, String elementName) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			WebElement toastInfo = driver.findElement(By.xpath(xpath));
			String actualMessage = toastInfo.getText();
			System.out.println(actualMessage);
			if (actualMessage.contains(expectedMessage)) {
				getTest().log(LogStatus.PASS, "Toast message validation passed: " + actualMessage);
			} else {
				getTest().log(LogStatus.FAIL, "Toast message validation failed. Expected to contain: '" + expectedMessage + "' but got: '" + actualMessage + "'" + addScreenShot());
				new Assertion().fail("Toast message validation failed. Expected to contain: '" + expectedMessage + "' but got: '" + actualMessage + "'");
			}
		} catch (Exception e) {
			getTest().log(LogStatus.FAIL, "Exception while validating toast message is due to <br/>" + e + addScreenShot());
			new Assertion().fail("Exception while validating toast message", e);
		}
	}
	
	
}