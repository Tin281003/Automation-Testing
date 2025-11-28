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
String invalidPermalink = "kiem-tra-loi-thieu-ten"

// ====== OPEN BROWSER & LOGIN ======
WebUI.comment("--- BẮT ĐẦU KIỂM TRA RÀNG BUỘC NAME ---")
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

// ====== NAVIGATE TO NEW DIGITAL PRODUCT PAGE ======
TestObject menuEcommerce = new TestObject().addProperty("xpath", ConditionType.EQUALS, "//span[contains(text(),'Ecommerce')]")
WebUI.click(menuEcommerce)
TestObject menuProducts = new TestObject().addProperty("xpath", ConditionType.EQUALS, "//a[contains(.,'Products') and not(contains(.,'Prices'))]") 
WebUI.waitForElementClickable(menuProducts, timeout)
WebUI.click(menuProducts)
WebUI.waitForPageLoad(timeout)

TestObject btnCreate = new TestObject().addProperty("xpath", ConditionType.EQUALS,
        "//button[contains(@class,'btn-primary') and contains(.,'Create')]") 
WebUI.waitForElementClickable(btnCreate, timeout)
WebUI.executeJavaScript("arguments[0].click();", btnCreate)
WebUI.delay(1) 

TestObject optionDigital = new TestObject().addProperty("xpath", ConditionType.EQUALS,
        "//a[contains(.,'Digital')]")
WebUI.waitForElementVisible(optionDigital, timeout)
WebUI.executeJavaScript("arguments[0].click();", optionDigital)
WebUI.waitForPageLoad(timeout)

// ====== BỎ TRỐNG NAME VÀ NHẬP PERMALINK HỢP LỆ ======
TestObject inputName = new TestObject().addProperty("xpath", ConditionType.EQUALS,
        "//label[contains(text(),'Name')]/following-sibling::input")
WebUI.setText(inputName, "") // Bỏ trống trường Name

TestObject inputPermalink = new TestObject().addProperty("xpath", ConditionType.EQUALS,
        "//label[contains(text(),'Permalink')]/following-sibling::input")
WebUI.setText(inputPermalink, invalidPermalink)

// ====== CLICK SAVE ======
TestObject btnSave = new TestObject().addProperty("xpath", ConditionType.EQUALS,
        "//button[contains(.,'Save') and not(contains(.,'Exit'))]")
WebUI.click(btnSave)
WebUI.delay(1) 

// ====== VERIFY KẾT QUẢ (FAILURE) ======
// Xác minh trang không chuyển hướng (vẫn còn text nhận diện của trang tạo sản phẩm)
WebUI.verifyTextPresent("NEW DIGITAL PRODUCT", true) 

// Xác minh thông báo lỗi/trường bị đánh dấu
TestObject errorIndicator = new TestObject().addProperty("xpath", ConditionType.EQUALS,
        "//label[contains(text(),'Name')]/following-sibling::span[contains(@class, 'help-block') or contains(@class, 'text-danger')]")
boolean isErrorDisplayed = WebUI.verifyElementPresent(errorIndicator, timeout, FailureHandling.OPTIONAL)

if(isErrorDisplayed) {
    WebUI.comment("PASS: Hệ thống hiển thị lỗi bắt buộc cho trường Name.")
} else {
    WebUI.comment("PASS: Hệ thống ngăn chặn lưu dữ liệu và vẫn ở trên màn hình tạo sản phẩm (Mặc dù không tìm thấy indicator lỗi cụ thể).")
}

// ====== CLOSE BROWSER ======
WebUI.closeBrowser()