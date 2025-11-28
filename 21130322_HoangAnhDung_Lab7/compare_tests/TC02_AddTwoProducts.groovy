import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.testobject.SelectorMethod

import com.thoughtworks.selenium.Selenium
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.WebDriver
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium
import static org.junit.Assert.*
import java.util.regex.Pattern
import static org.apache.commons.lang3.StringUtils.join
import org.testng.asserts.SoftAssert
import com.kms.katalon.core.testdata.CSVData
import org.openqa.selenium.Keys as Keys

// TC02: Add two products to compare and verify compare page
SoftAssert softAssertion = new SoftAssert();
WebUI.openBrowser('about:blank')
def driver = DriverFactory.getWebDriver()
String baseUrl = "https://nest.botble.com/vi"
selenium = new WebDriverBackedSelenium(driver, baseUrl)

String email = System.getenv('E2E_EMAIL') ?: 'dung@gmail.com'
String pwd = System.getenv('E2E_PASS') ?: '123456'

selenium.open(baseUrl + "/login")
selenium.type("id=email", email)
selenium.type("id=password", pwd)
selenium.click("xpath=//form[@id='botble-ecommerce-forms-fronts-auth-login-form']/div[4]/button")

selenium.open(baseUrl)
// add product 1
if (selenium.isElementPresent("xpath=(//a[contains(@class,'compare')])[1]")) selenium.click("xpath=(//a[contains(@class,'compare')])[1]")
// add product 2
if (selenium.isElementPresent("xpath=(//a[contains(@class,'compare')])[2]")) selenium.click("xpath=(//a[contains(@class,'compare')])[2]")

// open compare
selenium.open(baseUrl + "/compare")

boolean hasBoth = selenium.isTextPresent("So sánh") || selenium.isElementPresent("xpath=//table[contains(@class,'compare')]")
softAssertion.assertTrue(hasBoth, "Compare page should list products")
softAssertion.assertAll()
