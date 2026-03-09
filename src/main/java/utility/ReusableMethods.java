package utility;

import static org.testng.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
import java.util.concurrent.TimeoutException;

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
import org.testng.asserts.Assertion;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ReusableMethods {
	
	public WebDriver driver;
	static Map<Integer,ExtentTest> extentTestMap				 = new HashMap<Integer,ExtentTest>();
	static Map<Integer,ExtentTest> extentParentMap 				 = new HashMap<Integer,ExtentTest>();
	static Map<Integer,ExtentReports> extentreportsMap			 = new HashMap<Integer,ExtentReports>();
	static Map<Integer,String> reportLocationMap				 = new HashMap<Integer,String>();
	static String reportLocation 								 = null;	
	static String htmlLocation 									 = null;                           
	static ExtentReports reports 								 = null;
	public static Properties prop             					 = new Properties();
	Excel_Reader dataFile										 =new Excel_Reader("./src/main/java/testData/TestData.xlsx");

	
	
	
	
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

	protected static synchronized void initialise() 
	{		
		File file 			 = new File("./src/main/java/testData/testData.properties");
		try {
			FileInputStream fileInput            = new FileInputStream(file);
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(reportLocation==null && htmlLocation==null && reports==null)
		{
			reportLocation 			= System.getProperty("user.dir") + "/Reports/Automation_"+new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date())+"/";	
			htmlLocation 			= "Automation_"+new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss'.html'").format(new Date());                           				
			reports 				= new ExtentReports(reportLocation+htmlLocation,false);
		}
		initializeReportLocation(reportLocation);
		startReporter(reportLocation+htmlLocation);
	}
	
	/*
	 * protected void launchApp() { DesiredCapabilities dc=new
	 * DesiredCapabilities(); dc.setCapability("automationName", "UiAutomator2");
	 * dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
	 * dc.setCapability(MobileCapabilityType.DEVICE_NAME, "Android");
	 * dc.setCapability("appPackage", "com.orgname.OnlineBanking");
	 * dc.setCapability("appActivity", "com.orgname.OnlineBanking.OnlineBanking");
	 * dc.setCapability("noReset", true); dc.setCapability("fullReset", false);
	 * 
	 * URL url; try { url = new URL("http://127.0.0.1:4723/wd/hub"); driver=new
	 * AndroidDriver<WebElement>(url,dc); } catch (MalformedURLException e1) {
	 * e1.printStackTrace(); }
	 * 
	 * logInfo("Invoked app successfully");
	 * 
	 * }
	 */
	
	
	public void openBrowser(String browser, String URL) throws IOException, TimeoutException 
	{
		switch (browser.toLowerCase().trim()) {
		case "chrome": {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
			getTest().log(LogStatus.INFO,
	    			  "Opened chrome Browser Successfully and navigated to url : </br>" + URL);
		}
			break;
		case "firefox": {
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("--window-size=1400,900");
			options.addArguments("--headless");
			options.addArguments("--disable-notifications");
			driver = new FirefoxDriver(options);
			getTest().log(LogStatus.INFO,
	    			  "Opened FireFox Browser Successfully and navigated to url : </br>" + URL);
		}
			break;
		case "headless": {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless");
			options.addArguments("--disable-notifications");
			options.addArguments("start-maximized");
			driver = new ChromeDriver(options);
			getTest().log(LogStatus.INFO,
	    			  "Opened Headless Browser Successfully and navigated to url : </br>" + URL);
		}
			break;

		case "edge": {
			EdgeOptions options = new EdgeOptions();
			options.addArguments("--window-size=1400,900");
			options.addArguments("--headless");
			options.addArguments("--disable-notifications");
			driver = new EdgeDriver(options);
			getTest().log(LogStatus.INFO,
	    			  "Opened Edge Browser Successfully and navigated to url : </br>" + URL);
		}
			break;

		default:
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--window-size=1400,900");
			options.addArguments("--headless");
			options.addArguments("--disable-notifications");
			driver = new ChromeDriver(options);
			getTest().log(LogStatus.INFO,
	    			  "Opened Chrome Browser Successfully and navigated to url : </br>" + URL);
			break;
		}
		try {
			driver.manage().timeouts().pageLoadTimeout(Duration.ofMinutes(1));
			try {
				driver.get(URL);
			} catch (Exception e) {
				fail("Unable to open the browser", e);
			}
		} catch (Exception e) {
			fail("server down", e);
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}
	
	protected void ewait(String xpath){ 
		  new WebDriverWait(driver,Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))); 
	}
	 

	protected void clearNenterText(String xpath,String text,String elementName){
		try {
			ewait(xpath);
			/*WebElement element=driver.findElement(By.xpath(xpath));
			
			 * JavascriptExecutor executor = (JavascriptExecutor)driver; executor.
			 * executeScript("arguments[0].setAttribute('style','border: 2px solid Gold;');"
			 * , element); Thread.sleep(1000);
			 * executor.executeScript("arguments[0].removeAttribute('style');");
			 */
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

	protected void clickByCSS(String css,String elementName){
		try {
			
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
	
	
	
	
	protected String tearDown(WebDriver driver)	
	{
		try
		{
			UUID uuid = UUID.randomUUID();
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(getreportlocation()+uuid+".png"));
			getTest().addScreenCapture(getreportlocation()+uuid+".png");
			return uuid+".png";
		} 
		catch (IOException e)
		{	
			System.out.println("Error while generating screenshot:\n" + e.toString());
			return "";
		}
	}
	
	public String addScreenShot(){
		return getTest().addScreenCapture(tearDown(driver));
	}

	public void closeDriver(){
		driver.quit();
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

}
