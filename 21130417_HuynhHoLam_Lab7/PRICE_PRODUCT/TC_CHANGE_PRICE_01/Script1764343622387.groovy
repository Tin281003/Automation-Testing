import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

int timeout = 10

// ====== OPEN BROWSER ======
WebUI.openBrowser('')
WebUI.maximizeWindow()
WebUI.navigateToUrl('https://nest.botble.com/admin/login')

// ====== LOGIN ======
TestObject user = new TestObject().addProperty("xpath", ConditionType.EQUALS,
        "//input[@type='text' or @type='email']")
TestObject pass = new TestObject().addProperty("xpath", ConditionType.EQUALS,
        "//input[@type='password']")
TestObject btnLogin = new TestObject().addProperty("xpath", ConditionType.EQUALS,
        "//button[contains(.,'Sign in') or contains(.,'Login')]")

WebUI.waitForElementVisible(user, timeout)
WebUI.setText(user, "admin")
WebUI.setText(pass, "12345678")
WebUI.click(btnLogin)
WebUI.waitForPageLoad(timeout)

// ====== NAVIGATE TO PRODUCT PRICES ======
TestObject menu = new TestObject().addProperty("xpath", ConditionType.EQUALS,
        "//span[contains(text(),'Ecommerce')]")
WebUI.click(menu)

TestObject productPrices = new TestObject().addProperty("xpath", ConditionType.EQUALS,
        "//a[contains(.,'Product Prices')]")
WebUI.waitForElementClickable(productPrices, timeout)
WebUI.click(productPrices)

// ====== CHANGE PRODUCT PRICE USING JS ======
// Chọn input Price và Sale Price dựa vào placeholder
TestObject price = new TestObject().addProperty("xpath", ConditionType.EQUALS,
        "//input[@placeholder='Price']")
TestObject sale = new TestObject().addProperty("xpath", ConditionType.EQUALS,
        "//input[@placeholder='Sale Price']")

// Click và set value bằng JavaScript để tránh lỗi click nhầm
WebUI.executeJavaScript("arguments[0].click(); arguments[0].value='2000';", 
        Arrays.asList(WebUI.findWebElement(price, timeout)))
WebUI.executeJavaScript("arguments[0].click(); arguments[0].value='1500';", 
        Arrays.asList(WebUI.findWebElement(sale, timeout)))

// Click Reload để áp dụng thay đổi
TestObject reload = new TestObject().addProperty("xpath", ConditionType.EQUALS,
        "//button[contains(.,'Reload')]")
WebUI.waitForElementClickable(reload, timeout)
WebUI.click(reload)
WebUI.delay(2)

// ====== VERIFY CHANGES ======
WebUI.verifyElementAttributeValue(price, 'value', '2000', 3)
WebUI.verifyElementAttributeValue(sale, 'value', '1500', 3)

// ====== CLOSE BROWSER ======
WebUI.closeBrowser()
