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

// TC03: Attempt to submit a review as guest (not logged in)
SoftAssert softAssertion = new SoftAssert();
WebUI.openBrowser('about:blank')
def driver = DriverFactory.getWebDriver()
String baseUrl = "https://nest.botble.com/vi"
selenium = new WebDriverBackedSelenium(driver, baseUrl)

// Open product page without login
selenium.open(baseUrl + "/products/angies-boomchickapop-sweet-salty-kettle-corn")
selenium.click("id=Reviews-tab")
selenium.click("id=rating-star-5")
selenium.type("name=comment", ("Thử gửi review khi chưa đăng nhập").toString())
selenium.click("xpath=//div[@id='Reviews']/div/div/div[2]/form/button")

// Expected behavior: either redirect to login or show a message requiring login
boolean redirectedToLogin = selenium.getLocation().contains("/login")
boolean loginMsg = selenium.isTextPresent("Vui lòng đăng nhập") || selenium.isTextPresent("Please login")

softAssertion.assertTrue(redirectedToLogin || loginMsg, "Guest submit should require login")
softAssertion.assertAll()
