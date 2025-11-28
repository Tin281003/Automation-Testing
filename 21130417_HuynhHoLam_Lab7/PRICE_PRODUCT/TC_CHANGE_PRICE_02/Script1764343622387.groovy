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
String targetProductID = "56"
String newPrice = "3500"
String newSalePrice = "3000"

// ====== OPEN BROWSER & LOGIN ======
WebUI.comment("---BẮT ĐẦU THAY ĐỔI GIÁ SẢN PHẨM ---")
WebUI.openBrowser('')
WebUI.maximizeWindow()
WebUI.navigateToUrl('https://nest.botble.com/admin/login')

TestObject user = new TestObject().addProperty("xpath", ConditionType.EQUALS, "//input[@type='text' or @type='email']")
TestObject pass = new TestObject().addProperty("xpath", ConditionType.EQUALS, "//input[@type='password']")
TestObject btnLogin = new TestObject().addProperty("xpath", ConditionType.EQUALS, "//button[contains(.,'Sign in') or contains(.,'Login')]")

WebUI.waitForElementVisible(user, timeout)
WebUI.setText(user, "admin")
WebUI.setText(pass, "12345678")
WebUI.click(btnLogin)
WebUI.waitForPageLoad(timeout)

// ====== NAVIGATE TO PRODUCT PRICES (Dùng code mẫu) ======
TestObject menuEcommerce = new TestObject().addProperty("xpath", ConditionType.EQUALS, "//span[contains(text(),'Ecommerce')]")
WebUI.click(menuEcommerce)

TestObject productPrices = new TestObject().addProperty("xpath", ConditionType.EQUALS, "//a[contains(.,'Product Prices')]")
WebUI.waitForElementClickable(productPrices, timeout)
WebUI.click(productPrices)
WebUI.waitForPageLoad(timeout)

// ====== CHỌN INPUT GIÁ CỦA SẢN PHẨM TARGET (ID 56) ======
// Giả sử các input giá nằm trong hàng có ID 56.
TestObject priceInput = new TestObject().addProperty("xpath", ConditionType.EQUALS,
        "//tr[td[contains(text(),'" + targetProductID + "')]]//input[@placeholder='Price']")
TestObject saleInput = new TestObject().addProperty("xpath", ConditionType.EQUALS,
        "//tr[td[contains(text(),'" + targetProductID + "')]]//input[@placeholder='Price Sale']")

// ====== CHANGE PRODUCT PRICE USING JS (Dùng code mẫu) ======
WebUI.waitForElementVisible(priceInput, timeout)
WebUI.waitForElementVisible(saleInput, timeout)

// Click và set value bằng JavaScript
WebUI.executeJavaScript("arguments[0].click(); arguments[0].value='" + newPrice + "';", 
        Arrays.asList(WebUI.findWebElement(priceInput, timeout)))
WebUI.executeJavaScript("arguments[0].click(); arguments[0].value='" + newSalePrice + "';", 
        Arrays.asList(WebUI.findWebElement(saleInput, timeout)))

// Click Reload để áp dụng thay đổi
TestObject reload = new TestObject().addProperty("xpath", ConditionType.EQUALS, "//button[contains(.,'Reload')]")
WebUI.waitForElementClickable(reload, timeout)
WebUI.click(reload)
WebUI.delay(2)

// ====== VERIFY CHANGES (Tích cực) ======
WebUI.verifyElementAttributeValue(priceInput, 'value', newPrice, 3)
WebUI.verifyElementAttributeValue(saleInput, 'value', newSalePrice, 3)
WebUI.comment("THÀNH CÔNG: Giá sản phẩm ID " + targetProductID + " đã được cập nhật và xác minh.")

// ====== CLOSE BROWSER ======
WebUI.closeBrowser()