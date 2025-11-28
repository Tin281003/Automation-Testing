import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

int timeout = 10
String searchKeyword = "Banana"

// ====== OPEN BROWSER & LOGIN ======
WebUI.comment("--- BẮT ĐẦU TÌM KIẾM SẢN PHẨM ---")
WebUI.openBrowser('')
WebUI.maximizeWindow()
WebUI.navigateToUrl('https://nest.botble.com/admin/login')

// Đối tượng Login
TestObject user = new TestObject().addProperty("xpath", ConditionType.EQUALS, "//input[@type='text' or @type='email']")
TestObject pass = new TestObject().addProperty("xpath", ConditionType.EQUALS, "//input[@type='password']")
TestObject btnLogin = new TestObject().addProperty("xpath", ConditionType.EQUALS, "//button[contains(.,'Sign in') or contains(.,'Login')]")

WebUI.waitForElementVisible(user, timeout)
WebUI.setText(user, "admin")
WebUI.setText(pass, "12345678")
WebUI.click(btnLogin)
WebUI.waitForPageLoad(timeout)

// ====== NAVIGATE TO PRODUCTS ======
TestObject menuEcommerce = new TestObject().addProperty("xpath", ConditionType.EQUALS, "//span[contains(text(),'Ecommerce')]")
WebUI.click(menuEcommerce)
TestObject menuProducts = new TestObject().addProperty("xpath", ConditionType.EQUALS, "//a[contains(.,'Products') and not(contains(.,'Prices'))]") 
WebUI.waitForElementClickable(menuProducts, timeout)
WebUI.click(menuProducts)
WebUI.waitForPageLoad(timeout)

// ====== SEARCH PRODUCT "Banana" ======
TestObject searchInput = new TestObject().addProperty("xpath", ConditionType.EQUALS,
        "//input[@placeholder='Search']")
WebUI.waitForElementVisible(searchInput, timeout)
WebUI.setText(searchInput, searchKeyword)
WebUI.sendKeys(searchInput, org.openqa.selenium.Keys.ENTER) 
WebUI.delay(2) 

// ====== VERIFY KẾT QUẢ (SUCCESS) ======
// Xác minh sản phẩm "Colorful Banana" xuất hiện
TestObject productInList = new TestObject().addProperty("xpath", ConditionType.EQUALS,
        "//table//td//a[contains(text(),'Colorful Banana')]")

WebUI.verifyElementPresent(productInList, timeout)
WebUI.comment("THÀNH CÔNG: Sản phẩm 'Colorful Banana' đã được tìm thấy bằng từ khóa '${searchKeyword}'.")

// ====== CLOSE BROWSER ======
WebUI.closeBrowser()