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

import utility.Excel_Reader;
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

	//@AfterClass
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
		verifyIsDisplayed("//input[@placeholder='Enter plan name']",
				"placeholder - Enter plan name");

		verifyIsDisplayed("//span[text()='Select Branches']",
				"label - Select Branches"); verifyIsDisplayed("//input[@id='plan-branches']",
						"Select Branches input"); verifyIsEnabled("//input[@id='plan-branches']",
								"Select Branches input");
						verifyIsDisplayed("//div[text()='Search Branches']",
								"placeholder - Search Branches");



						verifyIsDisplayed("//p[text()='Basic Details']", "heading - Basic Details");

						verifyIsDisplayed("//span[text()='Plan Code']", "label - Plan Code");
						verifyIsDisplayed("//input[@id='Plan Code']", "Plan Code input");
						verifyIsEnabled("//input[@id='Plan Code']", "Plan Code input");
						verifyIsDisplayed("//input[@placeholder='Enter plan code']",
								"placeholder - Enter plan code");

						verifyIsDisplayed("//span[text()='Tenure']", "label - Tenure");
						verifyIsDisplayed("//input[@id='plan-tenure']", "Tenure input");
						verifyIsEnabled("//input[@id='plan-tenure']", "Tenure input");
						verifyIsDisplayed("//div[text()='Select...']", "placeholder - Select...");

						// Start Date & Time
						verifyIsDisplayed("//span[text()='Plan Validity Start Date']",
								"Label - Plan Validity Start Date");
						verifyIsDisplayed("//span[text()='Plan Validity Start Date']/..//span[text()='*']"
								, "Mandatory - Plan Validity Start Date");
						verifyIsDisplayed("//input[@placeholder='Start date']", "Start date input");
						verifyIsEnabled("//input[@placeholder='Start date']", "Start date input");

						// Start Date & Time
						verifyIsDisplayed("//span[text()='Plan Validity End Date']",
								"Label - Plan Validity End Date");
						verifyIsDisplayed("//span[text()='Plan Validity End Date']/..//span[text()='*']"
								, "Mandatory - Plan Validity End Date");
						verifyIsDisplayed("//input[@placeholder='End date']", "End date input");
						verifyIsEnabled("//input[@placeholder='End date']", "End date input");

						verifyIsDisplayed("//span[text()='Description']", "label - Description");
						verifyIsDisplayed("//input[@id='Description']", "Description input");
						verifyIsEnabled("//input[@id='Description']", "Description input");
						verifyIsDisplayed("//input[@placeholder='Enter plan description']",
								"placeholder - Enter plan description");

						verifyIsDisplayed("//span[text()='Terms & Conditions']",
								"label - Terms & Conditions");
						verifyIsDisplayed("//input[@id='Terms & Conditions']",
								"Terms & Conditions input");
						verifyIsEnabled("//input[@id='Terms & Conditions']",
								"Terms & Conditions input");
						verifyIsDisplayed("//input[@placeholder='Enter terms and conditions']",
								"placeholder - Enter terms and conditions");


						verifyIsDisplayed("//label[text()='Monthly Risk Limit']/../..//input[@type='checkbox']"
								, "Monthly Risk Limit checkbox");
						verifyIsEnabled("//label[text()='Monthly Risk Limit']/../..//input[@type='checkbox']"
								, "Monthly Risk Limit checkbox");
						verifyIsDisplayed("//label[text()='Monthly Risk Limit']",
								"label - Monthly Risk Limit");

						check("//label[text()='Monthly Risk Limit']/../..//input[@type='checkbox']","Monthly Risk Limit checkbox");

						verifyIsDisplayed("//span[text()='Max Risk Amount']","label - Max Risk Amount");
						verifyIsDisplayed("//input[@id='Max Risk Amount']","Max Risk Amount input");
						verifyIsEnabled("//input[@id='Max Risk Amount']","Max Risk Amount input");
						verifyIsDisplayed("//input[@placeholder='Enter max risk amount']","placeholder - Enter max risk amount");


						// Select Metal Category
						verifyIsDisplayed("//span[text()='Select Metal Category']",
								"Label - Select Metal Category");
						verifyIsDisplayed("//input[@id='plan-metal-category']",
								"Select Metal Category input");
						verifyIsEnabled("//input[@id='plan-metal-category']",
								"Select Metal Category input");
						verifyIsDisplayed("//div[text()='Search Metal Category']",
								"Placeholder - Select Metal Category");

						// Select Metal Type 
						verifyIsDisplayed("//span[text()='Select Metal Type']",
								"Label - Select Metal Type");
						verifyIsDisplayed("//input[@id='plan-metal-type']",
								"Select Metal Type input"); verifyIsEnabled("//input[@id='plan-metal-type']",
										"Select Metal Type input");
								verifyIsDisplayed("//div[text()='Search Metal Type']",
										"Placeholder - Select Metal Type");

								// Select Category 
								verifyIsDisplayed("//span[text()='Select Category']",
										"Label - Select Category"); 
								verifyIsDisplayed("//input[@id='plan-category']",
										"Select Category input"); 
								verifyIsEnabled("//input[@id='plan-category']",
										"Select Category input");
								verifyIsDisplayed("//div[text()='Search Category']",
										"Placeholder - Select Category");

								// Select sub Category
								verifyIsDisplayed("//span[text()='Select Sub Category']",
										"Label - Select Sub Category");
								verifyIsDisplayed("//input[@id='plan-sub-category']",
										"Select Sub Category input");
								verifyIsEnabled("//input[@id='plan-sub-category']",
										"Select Sub Category input");
								verifyIsDisplayed("//div[text()='Search Sub Category']",
										"Placeholder - Select Sub Category");

								// Select Types of Karates
								verifyIsDisplayed("//span[text()='Select Types of Karates']",
										"Label - Select Types of Karates");
								verifyIsDisplayed("//input[@id='plan-karates']",
										"Select Types of Karates input");
								verifyIsEnabled("//input[@id='plan-karates']",
										"Select Types of Karates input");
								verifyIsDisplayed("//div[text()='Search Types of Karates']",
										"Placeholder - Search Types of Karates");

								// Select Demographics
								verifyIsDisplayed("//span[text()='Select Demographics']",
										"Label - Select Demographics");
								verifyIsDisplayed("//input[@id='plan-demographics']",
										"Select Demographics input");
								verifyIsEnabled("//input[@id='plan-demographics']",
										"Select Demographics input");
								verifyIsDisplayed("//div[text()='Search Demographics']",
										"Placeholder - Search Demographics");



								verifyIsDisplayed("//p[text()='Pricing Rules']", "heading - Pricing Rules");

								verifyIsDisplayed("//span[text()='Weight Type']", "Label - Weight Type");
								verifyIsDisplayed("//span[text()='Weight Type']/..//span[text()='*']",
										"Mandatory - Weight Type");

								verifyIsDisplayed("//input[@id='weightType net']", "Net radio button");
								verifyIsDisplayed("//label[text()='Net']", "Net radio option");
								verifyIsEnabled("//input[@id='weightType net']", "Net radio button");

								verifyIsDisplayed("//input[@id='weightType gross']", "Gross radio buttton");
								verifyIsDisplayed("//label[text()='Gross']", "Gross radio label");
								verifyIsEnabled("//input[@id='weightType gross']", "Gross radio buttton");


								verifyIsDisplayed("//span[text()='Min Weight']", "label - Min Weight");
								verifyIsDisplayed("//input[@id='Min Weight']", "Min Weight input");
								verifyIsEnabled("//input[@id='Min Weight']", "Min Weight input");
								verifyIsDisplayed("//input[@placeholder='Enter minimum weight']",
										"placeholder - Enter minimum weight");

								verifyIsDisplayed("//span[text()='Max Weight']", "label - Max Weight");
								verifyIsDisplayed("//input[@id='Max Weight']", "Max Weight input");
								verifyIsEnabled("//input[@id='Max Weight']", "Max Weight input");
								verifyIsDisplayed("//input[@placeholder='Enter maximum weight']",
										"placeholder - Enter maximum weight");

								verifyIsDisplayed("//span[text()='Plan Fee Type']", "Label - Plan Fee Type");
								verifyIsDisplayed("//span[text()='Plan Fee Type']/..//span[text()='*']",
										"Mandatory - Plan Fee Type");

								verifyIsDisplayed("//input[@id='planFeeType flat']", "Flat radio button");
								verifyIsDisplayed("//label[text()='Net']", "Flat radio option");
								verifyIsEnabled("//input[@id='planFeeType flat']", "Flat radio button");

								verifyIsDisplayed("//input[@id='planFeeType percentage']",
										"Percentage radio buttton");
								verifyIsDisplayed("//label[text()='Percentage']", "Percentage radio label");
								verifyIsEnabled("//input[@id='planFeeType percentage']",
										"Percentage radio buttton");

								verifyIsDisplayed("//span[text()='Plan Fee Value']",
										"label - Plan Fee Value"); verifyIsDisplayed("//input[@id='Plan Fee Value']",
												"Plan Fee Value input"); verifyIsEnabled("//input[@id='Plan Fee Value']",
														"Plan Fee Value input");
												verifyIsDisplayed("//input[@placeholder='Enter plan fee value']",
														"placeholder - Enter plan fee value");

												verifyIsDisplayed("//span[text()='Service Charge']",
														"label - Service Charge"); verifyIsDisplayed("//input[@id='Service Charge']",
																"Service Charge input"); verifyIsEnabled("//input[@id='Service Charge']",
																		"Service Charge input");
																verifyIsDisplayed("//input[@placeholder='Enter service charge']",
																		"placeholder - Enter service charge");


																verifyIsDisplayed("//p[text()='Redemption Rules']",
																		"heading - Redemption Rules");

																verifyIsDisplayed("//p[text()='Redemption Rules']/..//span[text()='Coverage Percent']"
																		, "label - Coverage Percent in Redemption Rules");
																verifyIsDisplayed("//p[text()='Redemption Rules']/..//input[@id='Coverage Percent']"
																		, "Coverage Percent input in Redemption Rules");
																verifyIsEnabled("//p[text()='Redemption Rules']/..//input[@id='Coverage Percent']"
																		, "Coverage Percent input in Redemption Rules");
																verifyIsDisplayed("//p[text()='Redemption Rules']/..//input[@placeholder='Enter coverage percent']"
																		, "placeholder - Enter coverage percent in Redemption Rules");

																verifyIsDisplayed("//p[text()='Redemption Rules']/..//span[text()='Instant Pay Percent']"
																		, "label - Instant Pay Percent in Redemption Rules");
																verifyIsDisplayed("//p[text()='Redemption Rules']/..//input[@id='Instant Pay Percent']"
																		, "Instant Pay Percent input in Redemption Rules");
																verifyIsEnabled("//p[text()='Redemption Rules']/..//input[@id='Instant Pay Percent']"
																		, "Instant Pay Percent input in Redemption Rules");
																verifyIsDisplayed("//p[text()='Redemption Rules']/..//input[@placeholder='Enter instant pay percent']"
																		, "placeholder - Enter instant pay percent in Redemption Rules");

																verifyIsDisplayed("//p[text()='Redemption Rules']/..//span[text()='Pay On Next Purchase Percent']"
																		, "label - Pay On Next Purchase Percent in Redemption Rules");
																verifyIsDisplayed("//p[text()='Redemption Rules']/..//p[text()='Redemption Rules']/..//input[@id='Pay On Next Purchase Percent']"
																		, "Pay On Next Purchase Percent input in Redemption Rules");
																verifyIsEnabled("//p[text()='Redemption Rules']/..//input[@id='Pay On Next Purchase Percent']"
																		, "Pay On Next Purchase Percent input in Redemption Rules");
																verifyIsDisplayed("//p[text()='Redemption Rules']/..//input[@placeholder='Enter pay on next purchase percent']"
																		, "placeholder - Enter pay on next purchase percent in Redemption Rules");

																verifyIsDisplayed("//p[text()='Redemption Rules']/..//span[text()='Max Buyback Value']"
																		, "label - Max Buyback Value in Redemption Rules");
																verifyIsDisplayed("//p[text()='Redemption Rules']/..//input[@id='Max Buyback Value']"
																		, "Max Buyback Value input in Redemption Rules");
																verifyIsEnabled("//p[text()='Redemption Rules']/..//input[@id='Max Buyback Value']"
																		, "Max Buyback Value input in Redemption Rules");
																verifyIsDisplayed("//p[text()='Redemption Rules']/..//input[@placeholder='Enter max buyback value']"
																		, "placeholder - Enter max buyback value in Redemption Rules");

																verifyIsDisplayed("//p[text()='Redemption Rules']/..//span[text()='Waiting Period (Days)']"
																		, "label - Waiting Period (Days) in Redemption Rules");
																verifyIsDisplayed("//p[text()='Redemption Rules']/..//input[@id='Waiting Period (Days)']"
																		, "Waiting Period (Days) input in Redemption Rules");
																verifyIsEnabled("//p[text()='Redemption Rules']/..//input[@id='Waiting Period (Days)']"
																		, "Waiting Period (Days) input in Redemption Rules");
																verifyIsDisplayed("//p[text()='Redemption Rules']/..//input[@placeholder='Enter waiting period']"
																		, "placeholder - Enter waiting period in Redemption Rules");

																verifyIsDisplayed("//p[text()='Redemption Rules']/..//span[text()='Applicable After (Days)']"
																		, "label - Applicable After (Days) in Redemption Rules");
																verifyIsDisplayed("//p[text()='Redemption Rules']/..//input[@id='Applicable After (Days)']"
																		, "Applicable After (Days) input in Redemption Rules");
																verifyIsEnabled("//p[text()='Redemption Rules']/..//input[@id='Applicable After (Days)']"
																		, "Applicable After (Days) input in Redemption Rules");
																verifyIsDisplayed("//p[text()='Redemption Rules']/..//input[@placeholder='Enter applicable after days']"
																		, "placeholder - Enter applicable after days in Redemption Rules");



																verifyIsDisplayed("//p[text()='Next Purchase Rules']",
																		"heading - Next Purchase Rules");

																verifyIsDisplayed("//p[text()='Next Purchase Rules']/..//span[text()='Coverage Percent']"
																		, "label - Coverage Percent in Next Purchase Rules");
																verifyIsDisplayed("//p[text()='Next Purchase Rules']/..//input[@id='Coverage Percent']"
																		, "Coverage Percent input in Next Purchase Rules");
																verifyIsEnabled("//p[text()='Next Purchase Rules']/..//input[@id='Coverage Percent']"
																		, "Coverage Percent input in Next Purchase Rules");
																verifyIsDisplayed("//p[text()='Next Purchase Rules']/..//input[@placeholder='Enter coverage percent']"
																		, "placeholder - Enter coverage percent in Next Purchase Rules");

																verifyIsDisplayed("//p[text()='Next Purchase Rules']/..//span[text()='Min Purchase Value']"
																		, "label - Min Purchase Value in Next Purchase Rules");
																verifyIsDisplayed("//p[text()='Next Purchase Rules']/..//input[@id='Min Purchase Value']"
																		, "Min Purchase Value input in Next Purchase Rules");
																verifyIsEnabled("//p[text()='Next Purchase Rules']/..//input[@id='Min Purchase Value']"
																		, "Min Purchase Value input in Next Purchase Rules");
																verifyIsDisplayed("//p[text()='Next Purchase Rules']/..//input[@placeholder='Enter minimum purchase value']"
																		, "placeholder - Enter minimum purchase value in Next Purchase Rules");

																verifyIsDisplayed("//p[text()='Next Purchase Rules']/..//span[text()='Min Purchase Weight']"
																		, "label - Min Purchase Weight in Next Purchase Rules");
																verifyIsDisplayed("//p[text()='Next Purchase Rules']/..//input[@id='Min Purchase Weight']"
																		, "Min Purchase Weight input in Next Purchase Rules");
																verifyIsEnabled("//p[text()='Next Purchase Rules']/..//input[@id='Min Purchase Weight']"
																		, "Min Purchase Weight input in Next Purchase Rules");
																verifyIsDisplayed("//p[text()='Next Purchase Rules']/..//input[@placeholder='Enter minimum purchase weight']"
																		, "placeholder - Enter minimum purchase weight in Next Purchase Rules");

																verifyIsDisplayed("//p[text()='Next Purchase Rules']/..//span[text()='Validity']"
																		, "label - Validity in Next Purchase Rules");
																verifyIsDisplayed("//p[text()='Next Purchase Rules']/..//input[@id='next-purchase-validity']"
																		, "Validity input in Next Purchase Rules");
																verifyIsEnabled("//p[text()='Next Purchase Rules']/..//input[@id='next-purchase-validity']"
																		, "Validity input in Next Purchase Rules");
																verifyIsDisplayed("//p[text()='Next Purchase Rules']/..//div[text()='Select...']"
																		, "placeholder - Select...");

																// Select Metal Category
																verifyIsDisplayed("//p[text()='Next Purchase Rules']/..//span[text()='Select Metal Category']"
																		, "Label - Select Metal Category Next Purchase Rules");
																verifyIsDisplayed("//p[text()='Next Purchase Rules']/..//input[@id='next-purchase-metal-category']"
																		, "Select Metal Category input Next Purchase Rules");
																verifyIsEnabled("//p[text()='Next Purchase Rules']/..//input[@id='next-purchase-metal-category']"
																		, "Select Metal Category input Next Purchase Rules");
																verifyIsDisplayed("//p[text()='Next Purchase Rules']/..//div[text()='Search Metal Categories']"
																		, "Placeholder - Select Metal Categories Next Purchase Rules");

																// Select Metal Type
																verifyIsDisplayed("//p[text()='Next Purchase Rules']/..//span[text()='Select Metal Type']"
																		, "Label - Select Metal Type Next Purchase Rules");
																verifyIsDisplayed("//p[text()='Next Purchase Rules']/..//input[@id='next-purchase-metal-type']"
																		, "Select Metal Type input Next Purchase Rules");
																verifyIsEnabled("//p[text()='Next Purchase Rules']/..//input[@id='next-purchase-metal-type']"
																		, "Select Metal Type input Next Purchase Rules");
																verifyIsDisplayed("//p[text()='Next Purchase Rules']/..//div[text()='Search Metal Types']"
																		, "Placeholder - Select Metal Types Next Purchase Rules");

																// Select Category
																verifyIsDisplayed("//p[text()='Next Purchase Rules']/..//span[text()='Select Category']"
																		, "Label - Select Category Next Purchase Rules");
																verifyIsDisplayed("//p[text()='Next Purchase Rules']/..//input[@id='next-purchase-category']"
																		, "Select Category input Next Purchase Rules");
																verifyIsEnabled("//p[text()='Next Purchase Rules']/..//input[@id='next-purchase-category']"
																		, "Select Category input Next Purchase Rules");
																verifyIsDisplayed("//p[text()='Next Purchase Rules']/..//div[text()='Search Categories']"
																		, "Placeholder - Select Categories Next Purchase Rules");

																// Select sub Category
																verifyIsDisplayed("//p[text()='Next Purchase Rules']/..//span[text()='Select Sub Category']"
																		, "Label - Select Sub Category Next Purchase Rules");
																verifyIsDisplayed("//p[text()='Next Purchase Rules']/..//input[@id='next-purchase-sub-category']"
																		, "Select Sub Category input Next Purchase Rules");
																verifyIsEnabled("//p[text()='Next Purchase Rules']/..//input[@id='next-purchase-sub-category']"
																		, "Select Sub Category input Next Purchase Rules");
																verifyIsDisplayed("//p[text()='Next Purchase Rules']/..//div[text()='Search Sub Categories']"
																		, "Placeholder - Select Sub Categories Next Purchase Rules");


																verifyIsDisplayed("//p[text()='Next Purchase Additional Offers']",
																		"heading - Next Purchase Additional Offers");

																verifyIsDisplayed("//p[text()='Next Purchase Additional Offers']/..//span[text()='Offer Type']"
																		, "Label - Offer Type");
																verifyIsDisplayed("//p[text()='Next Purchase Additional Offers']/..//span[text()='Offer Type']/..//span[text()='*']"
																		, "Mandatory - Offer Type");

																verifyIsDisplayed("//p[text()='Next Purchase Additional Offers']/..//input[@id='offerType flat']"
																		, "Flat radio button");
																verifyIsDisplayed("//p[text()='Next Purchase Additional Offers']/..//label[text()='Flat']"
																		, "Flat radio option");
																verifyIsEnabled("//p[text()='Next Purchase Additional Offers']/..//input[@id='offerType flat']"
																		, "Flat radio button");

																verifyIsDisplayed("//p[text()='Next Purchase Additional Offers']/..//input[@id='offerType percentage']"
																		, "Percentage radio buttton");
																verifyIsDisplayed("//p[text()='Next Purchase Additional Offers']/..//label[text()='Percentage']"
																		, "Percentage radio label");
																verifyIsEnabled("//p[text()='Next Purchase Additional Offers']/..//input[@id='offerType percentage']"
																		, "Percentage radio buttton");



																verifyIsDisplayed("//p[text()='Next Purchase Additional Offers']/..//span[text()='Offer Value']"
																		, "label - Offer Value in Next Purchase Additional Offers");
																verifyIsDisplayed("//p[text()='Next Purchase Additional Offers']/..//input[@id='Offer Value']"
																		, "Offer Value input in Next Purchase Additional Offers");
																verifyIsEnabled("//p[text()='Next Purchase Additional Offers']/..//input[@id='Offer Value']"
																		, "Offer Value input in Next Purchase Additional Offers");
																verifyIsDisplayed("//p[text()='Next Purchase Additional Offers']/..//input[@placeholder='Enter offer value']"
																		, "placeholder - Enter offer value in Next Purchase Additional Offers");

																verifyIsDisplayed("//p[text()='Next Purchase Additional Offers']/..//span[text()='Making Charge']"
																		, "label - Making Charge in Next Purchase Additional Offers");
																verifyIsDisplayed("//p[text()='Next Purchase Additional Offers']/..//input[@id='Making Charge']"
																		, "Making Charge input in Next Purchase Additional Offers");
																verifyIsEnabled("//p[text()='Next Purchase Additional Offers']/..//input[@id='Making Charge']"
																		, "Making Charge input in Next Purchase Additional Offers");
																verifyIsDisplayed("//p[text()='Next Purchase Additional Offers']/..//input[@placeholder='Enter making charge']"
																		, "placeholder - Enter making charge in Next Purchase Additional Offers");

																verifyIsDisplayed("//p[text()='Next Purchase Additional Offers']/..//span[text()='Making Charge Type']"
																		, "Label - Making Charge Type in Next Purchase Additional Offers");
																verifyIsDisplayed("//p[text()='Next Purchase Additional Offers']/..//span[text()='Making Charge Type']/..//span[text()='*']"
																		, "Mandatory - Making Charge Type in Next Purchase Additional Offers");

																verifyIsDisplayed("//p[text()='Next Purchase Additional Offers']/..//input[@id='makingChargeType flat']"
																		, "makingChargeType Flat radio button in Next Purchase Additional Offers");
																verifyIsDisplayed("//p[text()='Next Purchase Additional Offers']/..//input[@id='makingChargeType flat']/../../../..//label[text()='Flat']"
																		, "makingChargeType Flat radio option in Next Purchase Additional Offers");
																verifyIsEnabled("//p[text()='Next Purchase Additional Offers']/..//input[@id='makingChargeType flat']"
																		, "makingChargeTypeFlat radio button in Next Purchase Additional Offers");

																verifyIsDisplayed("//p[text()='Next Purchase Additional Offers']/..//input[@id='makingChargeType percentage']"
																		,
																		"makingChargeType Percentage radio buttton in Next Purchase Additional Offers"
																		);
																verifyIsDisplayed("//p[text()='Next Purchase Additional Offers']/..//input[@id='makingChargeType percentage']/../../../..//label[text()='Percentage']"
																		,
																		"makingChargeType Percentage radio label in Next Purchase Additional Offers"
																		);
																verifyIsEnabled("//p[text()='Next Purchase Additional Offers']/..//input[@id='makingChargeType percentage']"
																		,
																		"makingChargeType Percentage radio buttton in Next Purchase Additional Offers"
																		);

																verifyIsDisplayed("//p[text()='Next Purchase Additional Offers']/..//span[text()='Validity']"
																		,
																		"label - Validity in Next Purchase Rules in Next Purchase Additional Offers"
																		);
																verifyIsDisplayed("//p[text()='Next Purchase Additional Offers']/..//input[@id='additional-offers-validity']"
																		, "Validity input in Next Purchase Additional Offers");
																verifyIsEnabled("//p[text()='Next Purchase Additional Offers']/..//input[@id='additional-offers-validity']"
																		, "Validity input in Next Purchase Additional Offers");
																verifyIsDisplayed("//p[text()='Next Purchase Additional Offers']/..//div[text()='Select...']"
																		, "placeholder - Select... in Next Purchase Additional Offers");








																verifyIsDisplayed("//p[text()='Benefits']", "heading - Benefits");

																verifyIsDisplayed("//p[text()='Benefits']/..//span[text()='Exchange Discount Percent']"
																		, "label - Exchange Discount Percent in Benefits");
																verifyIsDisplayed("//p[text()='Benefits']/..//input[@id='Exchange Discount Percent']"
																		, "Exchange Discount Percent input in Benefits");
																verifyIsEnabled("//p[text()='Benefits']/..//input[@id='Exchange Discount Percent']"
																		, "Exchange Discount Percent input in Benefits");
																verifyIsDisplayed("//p[text()='Benefits']/..//input[@placeholder='Enter exchange discount percent']"
																		, "placeholder -Enter exchange discount percent in Benefits");

																verifyIsDisplayed("//p[text()='Benefits']/..//span[text()='Min Buy Value']",
																		"label - Min Buy Valuein Benefits");
																verifyIsDisplayed("//p[text()='Benefits']/..//input[@id='Min Buy Value']",
																		"Min Buy Value input in Benefits");
																verifyIsEnabled("//p[text()='Benefits']/..//input[@id='Min Buy Value']",
																		"Min Buy Value input in Benefits");
																verifyIsDisplayed("//p[text()='Benefits']/..//input[@placeholder='Enter min buy value']"
																		, "placeholder -Enter min buy value in Benefits");

																verifyIsDisplayed("//p[text()='Benefits']/..//span[text()='Min Buy Value']",
																		"label - Min Buy Value in Benefits");
																verifyIsDisplayed("//p[text()='Benefits']/..//input[@id='Min Buy Value']",
																		"Min Buy Value input in Benefits");
																verifyIsEnabled("//p[text()='Benefits']/..//input[@id='Min Buy Value']",
																		"Min Buy Value input in Benefits");
																verifyIsDisplayed("//p[text()='Benefits']/..//input[@placeholder='Enter min buy value']"
																		, "placeholder -Enter min buy value in Benefits");

																verifyIsDisplayed("//p[text()='Benefits']/..//span[text()='Min Buy Weight']",
																		"label - Min Buy Weight in Benefits");
																verifyIsDisplayed("//p[text()='Benefits']/..//input[@id='Min Buy Weight']",
																		"Min Buy Weight input in Benefits");
																verifyIsEnabled("//p[text()='Benefits']/..//input[@id='Min Buy Weight']",
																		"Min Buy Value input in Benefits");
																verifyIsDisplayed("//p[text()='Benefits']/..//input[@placeholder='Enter min buy weight']"
																		, "placeholder -Enter min buy weight in Benefits");

																verifyIsDisplayed("//p[text()='Benefits']/..//span[text()='Validity']",
																		"label - Validity in Benefits"); verifyIsDisplayed(
																				"//p[text()='Benefits']/..//input[@id='benefits-validity']",
																				"Validity input in Benefits");
																		verifyIsEnabled("//p[text()='Benefits']/..//input[@id='benefits-validity']",
																				"Validity input in Benefits");
																		verifyIsDisplayed("//p[text()='Benefits']/..//div[text()='Select...']",
																				"placeholder - Select... in Benefits");

																		verifyIsDisplayed("//p[text()='Benefits']/..//label[text()='Price Protection Mandatory']"
																				, "label - Price Protection Mandatory in Benefits");
																		verifyIsDisplayed("//p[text()='Benefits']/..//label[text()='Price Protection Mandatory']/../..//input[@type='checkbox']"
																				, "Price Protection Mandatory checkbox in Benefits");
																		verifyIsEnabled("//p[text()='Benefits']/..//label[text()='Price Protection Mandatory']/../..//input[@type='checkbox']"
																				, "Price Protection Mandatory checkbox in Benefits");

																		verifyIsDisplayed("//p[text()='Benefits']/..//span[text()='Maintenance Period (Months)']"
																				, "label - Maintenance Period (Months) in Benefits");
																		verifyIsDisplayed("//p[text()='Benefits']/..//input[@id='Maintenance Period (Months)']"
																				, "Maintenance Period (Months) input in Benefits");
																		verifyIsEnabled("//p[text()='Benefits']/..//input[@id='Maintenance Period (Months)']"
																				, "Min Buy Value input in Benefits");
																		verifyIsDisplayed("//p[text()='Benefits']/..//input[@placeholder='Enter maintenance period']"
																				, "placeholder -Enter maintenance period in Benefits");


																		verifyIsDisplayed("//p[text()='Benefits']/..//span[text()='Maintenance Type']"
																				, "Label - Maintenance Type in Benefits");
																		verifyIsDisplayed("//p[text()='Benefits']/..//span[text()='Maintenance Type']/..//span[text()='*']"
																				, "Mandatory - Maintenance Type in Benefits");

																		verifyIsDisplayed("//p[text()='Benefits']/..//input[@id='maintenanceType limited']"
																				, "Limited radio button in Benefits");
																		verifyIsDisplayed("//p[text()='Benefits']/..//label[text()='Limited']",
																				"Limited radio option in Benefits");
																		verifyIsEnabled("//p[text()='Benefits']/..//input[@id='maintenanceType limited']"
																				, "Limited radio button in Benefits");

																		verifyIsDisplayed("//p[text()='Benefits']/..//input[@id='maintenanceType lifetime']"
																				, "Lifetime radio buttton in Benefits");
																		verifyIsDisplayed("//p[text()='Benefits']/..//label[text()='Lifetime']",
																				"Lifetime radio label in Benefits");
																		verifyIsEnabled("//p[text()='Benefits']/..//input[@id='maintenanceType lifetime']"
																				, "Lifetime radio buttton in Benefits");

																		verifyIsDisplayed("//p[text()='Benefits']/..//label[text()='Service Charge Applicable']"
																				, "label - Service Charge Applicable in Benefits");
																		verifyIsDisplayed("//p[text()='Benefits']/..//label[text()='Service Charge Applicable']/../..//input[@type='checkbox']"
																				, "Service Charge Applicable checkbox in Benefits");
																		verifyIsEnabled("//p[text()='Benefits']/..//label[text()='Service Charge Applicable']/../..//input[@type='checkbox']"
																				, "Service Charge Applicable checkbox in Benefits");

																		check("//p[text()='Benefits']/..//label[text()='Service Charge Applicable']/../..//input[@type='checkbox']",
																				"Service Charge Applicable checkbox in Benefits");
																		
																		verifyIsDisplayed(
																				"//p[text()='Benefits']/..//span[text()='Service Charge Percent']",
																				"label - Service Charge Percent");
																		verifyIsDisplayed("//p[text()='Benefits']/..//input[@id='Service Charge Percent']",
																				"Service Charge Percent input");
																		verifyIsEnabled("//p[text()='Benefits']/..//input[@id='Service Charge Percent']",
																				"Service Charge Percent input");
																		verifyIsDisplayed("//p[text()='Benefits']/..//input[@placeholder='Enter service charge percent']",
																				"placeholder - Enter service charge percent");

																		verifyIsDisplayed("//p[text()='Renewal Offers']",
																				"heading - Renewal Offers");

																		verifyIsDisplayed("//p[text()='Renewal Offers']/..//span[text()='Offer Type']"
																				, "Label - Offer Type in Renewal Offers");
																		verifyIsDisplayed("//p[text()='Renewal Offers']/..//span[text()='Offer Type']/..//span[text()='*']"
																				, "Mandatory - Offer Type in Renewal Offers");

																		verifyIsDisplayed("//p[text()='Renewal Offers']/..//input[@id='renewalOfferType next_renewal']"
																				, "Next Renewal radio buttonin Renewal Offers");
																		verifyIsDisplayed("//p[text()='Renewal Offers']/..//label[text()='Next Renewal']"
																				, "Next Renewal radio optionin Renewal Offers");
																		verifyIsEnabled("//p[text()='Renewal Offers']/..//input[@id='renewalOfferType next_renewal']"
																				, "Next Renewal radio buttonin Renewal Offers");

																		verifyIsDisplayed("//p[text()='Renewal Offers']/..//span[text()='Discount Percent']"
																				, "label - Discount Percent in Renewal Offers");
																		verifyIsDisplayed("//p[text()='Renewal Offers']/..//input[@id='Discount Percent']"
																				, "Discount Percent input in Renewal Offers");
																		verifyIsEnabled("//p[text()='Renewal Offers']/..//input[@id='Discount Percent']"
																				, "Discount Percent input in Renewal Offers");
																		verifyIsDisplayed("//p[text()='Renewal Offers']/..//input[@placeholder='Enter discount percent']"
																				, "placeholder -Enter discount percent in Renewal Offers");

																		verifyIsDisplayed("//p[text()='Renewal Offers']/..//span[text()='Min Buy Value']"
																				, "label - Min Buy Value in Renewal Offers");
																		verifyIsDisplayed("//p[text()='Renewal Offers']/..//input[@id='Min Buy Value']"
																				, "Min Buy Value input in Renewal Offers");
																		verifyIsEnabled("//p[text()='Renewal Offers']/..//input[@id='Min Buy Value']"
																				, "Min Buy Value input in Renewal Offers");
																		verifyIsDisplayed("//p[text()='Renewal Offers']/..//input[@placeholder='Enter min buy value']"
																				, "placeholder -Enter min buy value in Renewal Offers");

																		verifyIsDisplayed("//p[text()='Renewal Offers']/..//span[text()='Min Buy Weight']"
																				, "label - Min Buy Weight in Renewal Offers");
																		verifyIsDisplayed("//p[text()='Renewal Offers']/..//input[@id='Min Buy Weight']"
																				, "Min Buy Weight input in Renewal Offers");
																		verifyIsEnabled("//p[text()='Renewal Offers']/..//input[@id='Min Buy Weight']"
																				, "Min Buy Value input in Renewal Offers");
																		verifyIsDisplayed("//p[text()='Renewal Offers']/..//input[@placeholder='Enter min buy weight']"
																				, "placeholder -Enter min buy weight in Renewal Offers");

																		verifyIsDisplayed("//p[text()='Renewal Offers']/..//span[text()='Validity']",
																				"label - Validity in Renewal Offers");
																		verifyIsDisplayed("//p[text()='Renewal Offers']/..//input[@id='renewal-validity']"
																				, "Validity input in Renewal Offers");
																		verifyIsEnabled("//p[text()='Renewal Offers']/..//input[@id='renewal-validity']"
																				, "Validity input in Renewal Offers");
																		verifyIsDisplayed("//p[text()='Renewal Offers']/..//div[text()='Select...']",
																				"placeholder - Select... in Renewal Offers");

																		verifyIsDisplayed("//p[text()='Renewal Offers']/..//label[text()='Price Protection Mandatory']"
																				, "label - Price Protection Mandatory in Renewal Offers");
																		verifyIsDisplayed("//p[text()='Renewal Offers']/..//label[text()='Price Protection Mandatory']/../..//input[@type='checkbox']"
																				, "Price Protection Mandatory checkbox in Renewal Offers");
																		verifyIsEnabled("//p[text()='Renewal Offers']/..//label[text()='Price Protection Mandatory']/../..//input[@type='checkbox']"
																				, "Price Protection Mandatory checkbox in Renewal Offers");


																		verifyIsDisplayed("//button[text()='Reset']", "Reset button");
																		verifyIsEnabled("//button[text()='Reset']", "Reset button");

																		verifyIsDisplayed("//button[text()='Create Plan']", "Create Plan button");
																		verifyIsEnabled("//button[text()='Create Plan']", "Create Plan button");

																		logScreenshot();


	}
	Excel_Reader reader = new Excel_Reader("Pricing Plan.xlsx");

	@Test(priority = 3)
	public void fillForm() {


		int cellCount=Excel_Reader.getCellCount("PricingPlan","Field Name");
		//System.out.println(cellCount);
		for (int i = 2; i <cellCount; i++) {
			String planTitle=Excel_Reader.getCellData("PricingPlan", i, 1);

			startTest("Filling fom for - "+planTitle);


			int rowCount=Excel_Reader.getRowCount("PricingPlan");
			//System.out.println(rowCount); 
			for (int j = 2; j <= rowCount; j++) {

				String fieldId=Excel_Reader.getCellData("PricingPlan", 1, j);

				if(fieldId.equals("1")) {
					clearText("//input[@id='Plan Name']", "Plan Name input");
					clearNenterText("//input[@id='Plan Name']", Excel_Reader.getCellData("PricingPlan", i, j),"Plan Name input"); 
					sleep(1);
				}
				else if(fieldId.equals("2")) {
					clearText("//input[@id='plan-branches']", "Select Branches input");	
					clearNenterText("//input[@id='plan-branches']", Excel_Reader.getCellData("PricingPlan", i, j),"Select Branches input");
					sleep(1);
					click("//div[text()='"+Excel_Reader.getCellData("PricingPlan", i, j)+"']", "option - Making & VA Details");

				}
				else if(fieldId.equals("3")) {
					clearText("//input[@id='Plan Code']", "Plan Code input");
					clearNenterText("//input[@id='Plan Code']", Excel_Reader.getCellData("PricingPlan", i, j),"Plan Code input"); 
					sleep(1);

				}
				else if(fieldId.equals("4")) {
					clickWithOutWait("//input[@id='plan-tenure']", "Tenure input");
					clearNenterTextWithOutWait("//input[@id='plan-tenure']", Excel_Reader.getCellData("PricingPlan", i, j),"Tenure input"); 
					sleep(1);
					click("//div[text()='"+Excel_Reader.getCellData("PricingPlan", i, j)+"']", "Tenure option - "+Excel_Reader.getCellData("PricingPlan", i, j));
				}
				else if(fieldId.equals("5")) {
					click("//input[@placeholder='Start date']", "Start date input");
					if(Excel_Reader.getCellData("PricingPlan", i, j).equals("0")) {
						click("//div[@role='option' and contains(@class,'today')]", "Today date");
					}
					else {
						click("//div[@role='option' and contains(@class,'today')]//following::div[@role='option']["+Excel_Reader.getCellData("PricingPlan", i, j)+"]", 
								"today date + "+Excel_Reader.getCellData("PricingPlan", i, j)+" day/days");
					}
				} 
				else if(fieldId.equals("6")) {
					click("//input[@placeholder='End date']", "End date input");
					if(Excel_Reader.getCellData("PricingPlan", i, j).equals("0")) {
						click("//div[@role='option' and contains(@class,'today')]", "Today date");
					}
					else {
						click("//div[@role='option' and contains(@class,'today')]//following::div[@role='option']["+Excel_Reader.getCellData("PricingPlan", i, j)+"]", 
								"today date + "+Excel_Reader.getCellData("PricingPlan", i, j)+" day/days");
					}
				}
				else if(fieldId.equals("7")) {
					clearText("//input[@id='Description']", "Description input");
					clearNenterText("//input[@id='Description']", Excel_Reader.getCellData("PricingPlan", i, j),"Description input"); 

				}
				else if(fieldId.equals("8")) {
					clearText("//input[@id='Terms & Conditions']", "Terms & Conditions input");
					clearNenterText("//input[@id='Terms & Conditions']", Excel_Reader.getCellData("PricingPlan", i, j),"Terms & Conditions input");
				}
				else if (fieldId.equals("9")) {
					if (Excel_Reader.getCellData("PricingPlan", i, j).equalsIgnoreCase("yes")) {
						check("//label[text()='Monthly Risk Limit']/../..//input[@type='checkbox']"
								, "Monthly Risk Limit checkbox");
					}
					if (Excel_Reader.getCellData("PricingPlan", i, j).equalsIgnoreCase("no")) {
						uncheck("//label[text()='Monthly Risk Limit']/../..//input[@type='checkbox']"
								, "Monthly Risk Limit checkbox");
					}
				}
				else if(fieldId.equals("10")) {
					if (Excel_Reader.getCellData("PricingPlan", i-1, j).equalsIgnoreCase("yes")) {
						clearText("//input[@id='Max Risk Amount']","Max Risk Amount input");
						clearNenterText("//input[@id='Max Risk Amount']", Excel_Reader.getCellData("PricingPlan", i, j),"Max Risk Amount input");
					}
				}
				else if(fieldId.equals("11")) {
					scrollToElement("//input[@id='Terms & Conditions']", "Terms & Conditions input");
					clickWithOutWait("//input[@id='plan-metal-category']", "Select Metal Category input");
					clearNenterTextWithOutWait("//input[@id='plan-metal-category']", Excel_Reader.getCellData("PricingPlan", i, j),"Select Metal Category input"); 
					sleep(1);
					click("//div[text()='"+Excel_Reader.getCellData("PricingPlan", i, j)+"']", "Select Metal Category option - "+Excel_Reader.getCellData("PricingPlan", i, j));
				}
				else if(fieldId.equals("12")) {
					scrollToElement("//input[@id='Terms & Conditions']", "Terms & Conditions input");
					clickWithOutWait("//input[@id='plan-metal-type']", "Select Metal Type input");
					clearNenterTextWithOutWait("//input[@id='plan-metal-type']", Excel_Reader.getCellData("PricingPlan", i, j),"Select Metal Type input"); 
					sleep(1);
					click("//div[text()='"+Excel_Reader.getCellData("PricingPlan", i, j)+"']", "Select Metal Type option - "+Excel_Reader.getCellData("PricingPlan", i, j));
				}
				else if(fieldId.equals("13")) {
					scrollToElement("//input[@id='Terms & Conditions']", "Terms & Conditions input");
					clickWithOutWait("//input[@id='plan-category']", "Select Category input");
					clearNenterTextWithOutWait("//input[@id='plan-category']", Excel_Reader.getCellData("PricingPlan", i, j),"Select Category input"); 
					sleep(1);
					click("//div[text()='"+Excel_Reader.getCellData("PricingPlan", i, j)+"']", "Select Category option - "+Excel_Reader.getCellData("PricingPlan", i, j));
				}
				else if(fieldId.equals("14")) {
					scrollToElement("//input[@id='Terms & Conditions']", "Terms & Conditions input");
					clearText("//input[@id='plan-sub-category']", "Select Sub Category input");	
					clearNenterText("//input[@id='plan-sub-category']", Excel_Reader.getCellData("PricingPlan", i, j),"Select Sub Category input");
					sleep(2);
					click("//div[text()='"+Excel_Reader.getCellData("PricingPlan", i, j)+"']", "Select Sub Category option - Making & VA Details");

				}
				else if(fieldId.equals("15")) {
					scrollToElement("//input[@id='Terms & Conditions']", "Terms & Conditions input");
					clearText("//input[@id='plan-karates']", "Select Types of Karates input");	
					clearNenterText("//input[@id='plan-karates']", Excel_Reader.getCellData("PricingPlan", i, j),"Select Types of Karates input");
					sleep(1);
					click("//div[text()='"+Excel_Reader.getCellData("PricingPlan", i, j)+"']", "Select Types of Karates option - Making & VA Details");

				}
				else if(fieldId.equals("16")) {
					scrollToElement("//input[@id='Terms & Conditions']", "Terms & Conditions input");
					clearText("//input[@id='plan-demographics']", "Select Demographics input");	
					clearNenterText("//input[@id='plan-demographics']", Excel_Reader.getCellData("PricingPlan", i, j),"Select Demographics input");
					sleep(1);
					click("//div[text()='"+Excel_Reader.getCellData("PricingPlan", i, j)+"']", "Select Demographics option - Making & VA Details");

				}
				else if (fieldId.equals("17")) {
					scrollToElement("//p[text()='Pricing Rules']", "label - Pricing Rules");
					if (Excel_Reader.getCellData("PricingPlan", i, j).equalsIgnoreCase("net")) {
						click("//input[@id='weightType net']", "Net radio button");
					} else if (Excel_Reader.getCellData("PricingPlan", i, j).equalsIgnoreCase("gross")) {
						click("//input[@id='weightType gross']", "Gross radio buttton");
					}
				} else if (fieldId.equals("18")) {
					scrollToElement("//p[text()='Pricing Rules']", "label - Pricing Rules");
					clearText("//input[@id='Min Weight']", "Min Weight input");
					clearNenterText("//input[@id='Min Weight']", Excel_Reader.getCellData("PricingPlan", i, j),
							"Min Weight input");
				} else if (fieldId.equals("19")) {
					scrollToElement("//p[text()='Pricing Rules']", "label - Pricing Rules");
					clearText("//input[@id='Max Weight']", "Max Weight input");
					clearNenterText("//input[@id='Max Weight']", Excel_Reader.getCellData("PricingPlan", i, j),
							"Max Weight input");
				} else if (fieldId.equals("20")) {
					scrollToElement("//p[text()='Pricing Rules']", "label - Pricing Rules");
					if (Excel_Reader.getCellData("PricingPlan", i, j).equalsIgnoreCase("flat")) {
						click("//input[@id='planFeeType flat']", "Flat radio button");
					} else if (Excel_Reader.getCellData("PricingPlan", i, j).equalsIgnoreCase("percentage")) {
						click("//input[@id='planFeeType percentage']", "Percentage radio buttton");
					}
				} else if (fieldId.equals("21")) {
					scrollToElement("//p[text()='Pricing Rules']", "label - Pricing Rules");
					clearText("//input[@id='Plan Fee Value']", "Plan Fee Value input");
					clearNenterText("//input[@id='Plan Fee Value']", Excel_Reader.getCellData("PricingPlan", i, j),
							"Plan Fee Value input");
				} else if (fieldId.equals("22")) {
					scrollToElement("//p[text()='Pricing Rules']", "label - Pricing Rules");
					clearText("//input[@id='Service Charge']", "Service Charge input");
					clearNenterText("//input[@id='Service Charge']", Excel_Reader.getCellData("PricingPlan", i, j),
							"Service Charge input");
				}
				else if (fieldId.equals("23")) {
					scrollToElement("//p[text()='Redemption Rules']", "heading - Redemption Rules");
					clearText("//p[text()='Redemption Rules']/..//input[@id='Coverage Percent']",
							"Coverage Percent input in Redemption Rules");
					clearNenterText("//p[text()='Redemption Rules']/..//input[@id='Coverage Percent']",
							Excel_Reader.getCellData("PricingPlan", i, j), "Coverage Percent input in Redemption Rules");

				}
				else if (fieldId.equals("24")) {
					scrollToElement("//p[text()='Redemption Rules']", "heading - Redemption Rules");
					clearText("//p[text()='Redemption Rules']/..//input[@id='Instant Pay Percent']",
							"Instant Pay Percent input in Redemption Rules");
					clearNenterText("//p[text()='Redemption Rules']/..//input[@id='Instant Pay Percent']",
							Excel_Reader.getCellData("PricingPlan", i, j), "Instant Pay Percent input in Redemption Rules");

				}
				else if (fieldId.equals("25")) {
					/*
					 * scrollToElement("//p[text()='Redemption Rules']",
					 * "heading - Redemption Rules");
					 * clearText("//p[text()='Redemption Rules']/..//input[@id='Pay On Next Purchase Percent']"
					 * , "Pay On Next Purchase Percent input in Redemption Rules");
					 * clearNenterText("//p[text()='Redemption Rules']/..//input[@id='Pay On Next Purchase Percent']"
					 * , Excel_Reader.getCellData("PricingPlan", i, j),
					 * "Pay On Next Purchase Percent input in Redemption Rules"); 
					 */
				}
				else if (fieldId.equals("26")) {

					scrollToElement("//p[text()='Redemption Rules']",
							"heading - Redemption Rules");
					clearText("//p[text()='Redemption Rules']/..//input[@id='Max Buyback Value']"
							, "Max Buyback Value input in Redemption Rules");
					clearNenterText("//p[text()='Redemption Rules']/..//input[@id='Max Buyback Value']"
							, Excel_Reader.getCellData("PricingPlan", i, j),
							"Max Buyback Value input in Redemption Rules"); 

				} else if (fieldId.equals("27")) {

					scrollToElement("//p[text()='Redemption Rules']",
							"heading - Redemption Rules");
					clearText("//p[text()='Redemption Rules']/..//input[@id='Waiting Period (Days)']"
							, "Waiting Period (Days) input in Redemption Rules");
					clearNenterText("//p[text()='Redemption Rules']/..//input[@id='Waiting Period (Days)']"
							, Excel_Reader.getCellData("PricingPlan", i, j),
							"Waiting Period (Days) input in Redemption Rules"); 

				} else if (fieldId.equals("28")) {

					scrollToElement("//p[text()='Redemption Rules']",
							"heading - Redemption Rules");
					clearText("//p[text()='Redemption Rules']/..//input[@id='Applicable After (Days)']"
							, "Applicable After (Days) input in Redemption Rules");
					clearNenterText("//p[text()='Redemption Rules']/..//input[@id='Applicable After (Days)']"
							, Excel_Reader.getCellData("PricingPlan", i, j),
							"Applicable After (Days) input in Redemption Rules"); 

				}

				else if (fieldId.equals("29")) {
					scrollToElement("//p[text()='Next Purchase Rules']",
							"heading - Next Purchase Rules");
					clearText("//p[text()='Next Purchase Rules']/..//input[@id='Coverage Percent']"
							, "Coverage Percent input in Next Purchase Rules");
					clearNenterText("//p[text()='Next Purchase Rules']/..//input[@id='Coverage Percent']"
							, Excel_Reader.getCellData("PricingPlan", i, j),
							"Coverage Percent input in Next Purchase Rules"); 
				}
				else if(fieldId.equals("30")) {
					scrollToElement("//p[text()='Next Purchase Rules']",
							"heading - Next Purchase Rules");
					clearText("//p[text()='Next Purchase Rules']/..//input[@id='Min Purchase Value']"
							, "Min Purchase Value input in Next Purchase Rules");
					clearNenterText("//p[text()='Next Purchase Rules']/..//input[@id='Min Purchase Value']"
							, Excel_Reader.getCellData("PricingPlan", i, j),
							"Min Purchase Value input in Next Purchase Rules");

				}
				else if(fieldId.equals("31")) {
					scrollToElement("//p[text()='Next Purchase Rules']",
							"heading - Next Purchase Rules");
					clearText("//p[text()='Next Purchase Rules']/..//input[@id='Min Purchase Weight']"
							, "Min Purchase Weight input in Next Purchase Rules");
					clearNenterText("//p[text()='Next Purchase Rules']/..//input[@id='Min Purchase Weight']"
							, Excel_Reader.getCellData("PricingPlan", i, j),
							"Min Purchase Weight input in Next Purchase Rules");

				}




				else if(fieldId.equals("32")) {
					scrollToElement("//p[text()='Next Purchase Rules']",
							"heading - Next Purchase Rules");
					clickWithOutWait("//p[text()='Next Purchase Rules']/..//input[@id='next-purchase-validity']"
							, "Validity input in Next Purchase Rules");
					clearNenterText("//p[text()='Next Purchase Rules']/..//input[@id='next-purchase-validity']"
							, Excel_Reader.getCellData("PricingPlan", i, j),
							"Validity input in Next Purchase Rules");
					sleep(1);
					click("//p[text()='Next Purchase Rules']/..//div[text()='"+Excel_Reader.getCellData("PricingPlan", i, j)+"']", "Validity option - "+Excel_Reader.getCellData("PricingPlan", i, j));


				}

				else if(fieldId.equals("33")) {
					scrollToElement("//p[text()='Next Purchase Rules']",
							"heading - Next Purchase Rules");
					clickWithOutWait("//p[text()='Next Purchase Rules']/..//input[@id='next-purchase-metal-category']"
							, "Select Metal Category input in Next Purchase Rules");
					clearNenterTextWithOutWait("//p[text()='Next Purchase Rules']/..//input[@id='next-purchase-metal-category']"
							, Excel_Reader.getCellData("PricingPlan", i, j),
							"Select Metal Category input in Next Purchase Rules"); 
					click("//p[text()='Next Purchase Rules']/..//div[text()='"+Excel_Reader.getCellData("PricingPlan", i, j)+"']", "Validity option - "+Excel_Reader.getCellData("PricingPlan", i, j));

				}
				else if(fieldId.equals("34")) {
					scrollToElement("//p[text()='Next Purchase Rules']",
							"heading - Next Purchase Rules");
					clickWithOutWait("//p[text()='Next Purchase Rules']/..//input[@id='next-purchase-metal-type']"
							, "Select Metal Type input in Next Purchase Rules");
					clearNenterTextWithOutWait("//p[text()='Next Purchase Rules']/..//input[@id='next-purchase-metal-type']"
							, Excel_Reader.getCellData("PricingPlan", i, j),
							"Select Metal Type input in Next Purchase Rules"); 
					click("//p[text()='Next Purchase Rules']/..//div[text()='"+Excel_Reader.getCellData("PricingPlan", i, j)+"']", "Validity option - "+Excel_Reader.getCellData("PricingPlan", i, j));
				}
				else if(fieldId.equals("35")) {
					scrollToElement("//p[text()='Next Purchase Rules']",
							"heading - Next Purchase Rules");
					clickWithOutWait("//p[text()='Next Purchase Rules']/..//input[@id='next-purchase-category']"
							, "Select Category input in Next Purchase Rules");
					clearNenterTextWithOutWait("//p[text()='Next Purchase Rules']/..//input[@id='next-purchase-category']"
							, Excel_Reader.getCellData("PricingPlan", i, j),
							"Select Category input in Next Purchase Rules"); 
					click("//p[text()='Next Purchase Rules']/..//div[text()='"+Excel_Reader.getCellData("PricingPlan", i, j)+"']", "Validity option - "+Excel_Reader.getCellData("PricingPlan", i, j));
				}
				else if(fieldId.equals("36")) {
					scrollToElement("//p[text()='Next Purchase Rules']",
							"heading - Next Purchase Rules");
					clearText("//p[text()='Next Purchase Rules']/..//input[@id='next-purchase-sub-category']"
							, "Select Sub Category input in Next Purchase Rules");
					clearNenterText("//p[text()='Next Purchase Rules']/..//input[@id='next-purchase-sub-category']"
							, Excel_Reader.getCellData("PricingPlan", i, j),
							"Select Sub Category input in Next Purchase Rules"); 
					click("//p[text()='Next Purchase Rules']/..//div[text()='"+Excel_Reader.getCellData("PricingPlan", i, j)+"']", "Validity option - "+Excel_Reader.getCellData("PricingPlan", i, j));
				}
				else if(fieldId.equals("37")) {
					scrollToElement("//p[text()='Next Purchase Additional Offers']",
							"heading - Next Purchase Additional Offers");
					if (Excel_Reader.getCellData("PricingPlan", i, j).equalsIgnoreCase("flat")) {
						click("//p[text()='Next Purchase Additional Offers']/..//input[@id='offerType flat']", "Flat radio button");
					} else if (Excel_Reader.getCellData("PricingPlan", i, j).equalsIgnoreCase("percentage")) {
						click("//p[text()='Next Purchase Additional Offers']/..//input[@id='offerType percentage']", "Percentage radio buttton");
					}

				}
				else if(fieldId.equals("38")) {
					scrollToElement("//p[text()='Next Purchase Additional Offers']",
							"heading - Next Purchase Additional Offers");
					clearText("//p[text()='Next Purchase Additional Offers']/..//input[@id='Offer Value']",
							"Offer Value input in Next Purchase Additional Offers");
					clearNenterText("//p[text()='Next Purchase Additional Offers']/..//input[@id='Offer Value']",
							Excel_Reader.getCellData("PricingPlan", i, j),
							"Offer Value input in Next Purchase Additional Offers");
				}
				else if(fieldId.equals("39")) {
					scrollToElement("//p[text()='Next Purchase Additional Offers']",
							"heading - Next Purchase Additional Offers");
					clearText("//p[text()='Next Purchase Additional Offers']/..//input[@id='Making Charge']",
							"Making Charge input in Next Purchase Additional Offers");
					clearNenterText("//p[text()='Next Purchase Additional Offers']/..//input[@id='Making Charge']",
							Excel_Reader.getCellData("PricingPlan", i, j),
							"Making Charge input in Next Purchase Additional Offers");
				}
				else if(fieldId.equals("40")) {
					scrollToElement("//p[text()='Next Purchase Additional Offers']",
							"heading - Next Purchase Additional Offers");
					if (Excel_Reader.getCellData("PricingPlan", i, j).equalsIgnoreCase("flat")) {
						click("//p[text()='Next Purchase Additional Offers']/..//input[@id='makingChargeType flat']", "Flat radio button in Next Purchase Additional Offers");
					} else if (Excel_Reader.getCellData("PricingPlan", i, j).equalsIgnoreCase("percentage")) {
						click("//p[text()='Next Purchase Additional Offers']/..//input[@id='makingChargeType percentage']", "Percentage radio buttton in Next Purchase Additional Offers");
					}
				}
				else if(fieldId.equals("41")) {
					scrollToElement("//p[text()='Next Purchase Additional Offers']",
							"heading - Next Purchase Additional Offers");
					clickWithOutWait("//p[text()='Next Purchase Additional Offers']/..//input[@id='additional-offers-validity']",
							"Validity input in Next Purchase Additional Offers");
					clearNenterTextWithOutWait("//p[text()='Next Purchase Additional Offers']/..//input[@id='additional-offers-validity']",
							Excel_Reader.getCellData("PricingPlan", i, j),
							"Validity input in Next Purchase Additional Offers");

				}
				else if(fieldId.equals("42")) {
					scrollToElement("//p[text()='Benefits']", "label - Benefits");

					clearText("//p[text()='Benefits']/..//input[@id='Exchange Discount Percent']"
							, "Exchange Discount Percent input in Benefits");

					clearNenterText("//p[text()='Benefits']/..//input[@id='Exchange Discount Percent']"
							, Excel_Reader.getCellData("PricingPlan", i, j),
							"Exchange Discount Percent input in Benefits");

				}
				else if(fieldId.equals("43")) {
					scrollToElement("//p[text()='Benefits']", "label - Benefits");
					clearText("//p[text()='Benefits']/..//input[@id='Min Buy Value']",
							"Min Buy Value input in Benefits");
					clearNenterText("//p[text()='Benefits']/..//input[@id='Min Buy Value']",
							Excel_Reader.getCellData("PricingPlan", i, j),
							"Min Buy Value input in Benefits");
				} else if (fieldId.equals("44")) {
					scrollToElement("//p[text()='Benefits']", "label - Benefits");
					clearText("//p[text()='Benefits']/..//input[@id='Min Buy Weight']",
							"Min Buy Weight input in Benefits");
					clearNenterText("//p[text()='Benefits']/..//input[@id='Min Buy Weight']",
							Excel_Reader.getCellData("PricingPlan", i, j), "Min Buy Weight input in Benefits");
				}
				else if (fieldId.equals("45")) {
					scrollToElement("//p[text()='Benefits']", "label - Benefits");
					clickWithOutWait("//p[text()='Benefits']/..//input[@id='benefits-validity']",
							"Validity input in Benefits");
					clearNenterTextWithOutWait("//p[text()='Benefits']/..//input[@id='benefits-validity']",
							Excel_Reader.getCellData("PricingPlan", i, j), "Validity input in Benefits");
					sleep(1);
					click("//p[text()='Benefits']/..//div[text()='"+Excel_Reader.getCellData("PricingPlan", i, j)+"']", 
							"Validity option - "+Excel_Reader.getCellData("PricingPlan", i, j));




				}

				else if (fieldId.equals("46")) {
					scrollToElement("//p[text()='Benefits']", "label - Benefits");
					if (Excel_Reader.getCellData("PricingPlan", i, j).equalsIgnoreCase("yes")) {
						check("//p[text()='Benefits']/..//label[text()='Price Protection Mandatory']/../..//input[@type='checkbox']",
								"Price Protection Mandatory checkbox in Benefits");
					}
					if (Excel_Reader.getCellData("PricingPlan", i, j).equalsIgnoreCase("no")) {
						uncheck("//p[text()='Benefits']/..//label[text()='Price Protection Mandatory']/../..//input[@type='checkbox']",
								"Price Protection Mandatory checkbox in Benefits");
					}
				} 
				else if (fieldId.equals("47")) {
					scrollToElement("//p[text()='Benefits']", "label - Benefits");
					clearText("//p[text()='Benefits']/..//input[@id='Maintenance Period (Months)']",
							"Maintenance Period (Months) input in Benefits");
					clearNenterText("//p[text()='Benefits']/..//input[@id='Maintenance Period (Months)']",
							Excel_Reader.getCellData("PricingPlan", i, j),
							"Maintenance Period (Months) input in Benefits");
				} 
				else if (fieldId.equals("48")) {
					scrollToElement("//p[text()='Benefits']", "label - Benefits");
					if (Excel_Reader.getCellData("PricingPlan", i, j).equalsIgnoreCase("limited")) {
						click("//p[text()='Benefits']/..//input[@id='maintenanceType limited']",
								"Limited radio button in Benefits");
					}
					else if (Excel_Reader.getCellData("PricingPlan", i, j).equalsIgnoreCase("lifetime")) {
						click("//p[text()='Benefits']/..//input[@id='maintenanceType lifetime']"
								, "Lifetime radio buttton in Benefits");
					}
				}

				else if (fieldId.equals("49")) {
					scrollToElement("//p[text()='Benefits']", "label - Benefits");
					if (Excel_Reader.getCellData("PricingPlan", i, j).equalsIgnoreCase("yes")) {
						check("//p[text()='Benefits']/..//label[text()='Service Charge Applicable']/../..//input[@type='checkbox']"
								, "Service Charge Applicable checkbox in Benefits");
					}
					if (Excel_Reader.getCellData("PricingPlan", i, j).equalsIgnoreCase("no")) {
						uncheck("//p[text()='Benefits']/..//label[text()='Service Charge Applicable']/../..//input[@type='checkbox']"
								, "Service Charge Applicable checkbox in Benefits");
					}
				}
				else if(fieldId.equals("50")) {
					if (Excel_Reader.getCellData("PricingPlan", i-1, j).equalsIgnoreCase("yes")) {
						clearText("//p[text()='Benefits']/..//input[@id='Service Charge Percent']",
								"Service Charge Percent input");
						clearNenterText("//p[text()='Benefits']/..//input[@id='Service Charge Percent']", Excel_Reader.getCellData("PricingPlan", i, j),"Service Charge Percent input");
					}
				}
				else if (fieldId.equals("51")) {
					scrollToElement("//p[text()='Renewal Offers']",
							"heading - Renewal Offers");
					if (Excel_Reader.getCellData("PricingPlan", i, j).equalsIgnoreCase("limited")) {
						click("//p[text()='Renewal Offers']/..//input[@id='renewalOfferType next_renewal']"
								, "Next Renewal radio buttonin Renewal Offers");
					}
				}
				else if (fieldId.equals("52")) {
					scrollToElement("//p[text()='Benefits']", "label - Benefits");
					clearText("//p[text()='Renewal Offers']/..//input[@id='Discount Percent']"
							, "Discount Percent input in Renewal Offers");
					clearNenterText("//p[text()='Renewal Offers']/..//input[@id='Discount Percent']",
							Excel_Reader.getCellData("PricingPlan", i, j),
							"Discount Percent input in Renewal Offers");
				} 
				else if (fieldId.equals("53")) {
					scrollToElement("//p[text()='Benefits']", "label - Benefits");
					clearText("//p[text()='Renewal Offers']/..//input[@id='Min Buy Value']"
							, "Min Buy Value input in Renewal Offers");
					clearNenterText("//p[text()='Renewal Offers']/..//input[@id='Min Buy Value']",
							Excel_Reader.getCellData("PricingPlan", i, j),
							"Min Buy Value input in Renewal Offers");
				}
				else if (fieldId.equals("54")) {
					scrollToElement("//p[text()='Benefits']", "label - Benefits");
					clearText("//p[text()='Renewal Offers']/..//input[@id='Min Buy Weight']"
							, "Min Buy Weight input in Renewal Offers");
					clearNenterText("//p[text()='Renewal Offers']/..//input[@id='Min Buy Weight']",
							Excel_Reader.getCellData("PricingPlan", i, j),
							"Min Buy Weight input in Renewal Offers");
				}
				else if (fieldId.equals("55")) {
					scrollToElement("//p[text()='Benefits']", "label - Benefits");
					clickWithOutWait("//p[text()='Renewal Offers']/..//input[@id='renewal-validity']"
							, "Validity input in Renewal Offers");
					clearNenterTextWithOutWait("//p[text()='Renewal Offers']/..//input[@id='renewal-validity']",
							Excel_Reader.getCellData("PricingPlan", i, j),
							"Validity input in Renewal Offers");
					sleep(1);
					click("//p[text()='Renewal Offers']/..//div[text()='"+Excel_Reader.getCellData("PricingPlan", i, j)+"']",
							"Validity option - "+Excel_Reader.getCellData("PricingPlan", i, j));
				}
				else if (fieldId.equals("56")) {
					scrollToElement("//p[text()='Benefits']", "label - Benefits");
					if (Excel_Reader.getCellData("PricingPlan", i, j).equalsIgnoreCase("yes")) {
						check("//p[text()='Renewal Offers']/..//label[text()='Price Protection Mandatory']/../..//input[@type='checkbox']"
								, "Price Protection Mandatory checkbox in Renewal Offers");
					}
					if (Excel_Reader.getCellData("PricingPlan", i, j).equalsIgnoreCase("no")) {
						uncheck("//p[text()='Renewal Offers']/..//label[text()='Price Protection Mandatory']/../..//input[@type='checkbox']"
								, "Price Protection Mandatory checkbox in Renewal Offers");
					}


				}


			}

			//click("//button[text()='Create Plan']", "Create Plan button");
			logScreenshot();
			endTest();
		}

		/*


		 * 
		 * verifyIsDisplayed("//button[text()='Reset']", "Reset button");
		 * verifyIsEnabled("//button[text()='Reset']", "Reset button");
		 * 
		 * verifyIsDisplayed("//button[text()='Create Plan']", "Create Plan button");
		 * verifyIsEnabled("//button[text()='Create Plan']", "Create Plan button");
		 */

		//


	}



}
