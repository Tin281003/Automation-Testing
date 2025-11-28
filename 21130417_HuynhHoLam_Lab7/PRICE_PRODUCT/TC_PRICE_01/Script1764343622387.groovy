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
String invalidPrice = "2000"
String invalidSalePrice = "2500" // Giá Sale lớn hơn Giá gốc

// [THỰC HIỆN ĐĂNG NHẬP VÀ ĐIỀU HƯỚNG TỚI PRODUCT PRICES]
// Giả định các bước Login và Navigate to Product Prices đã được thực hiện

WebUI.comment("--- KIỂM TRA LỖI GIÁ SALE > GIÁ GỐC ---")

// ====== CHỌN INPUT GIÁ CỦA SẢN PHẨM MỤC TIÊU ======
TestObject priceInput = new TestObject().addProperty("xpath", ConditionType.EQUALS,
        "//tr[td[contains(text(),'" + targetProductID + "')]]//input[@placeholder='Price']")
TestObject saleInput = new TestObject().addProperty("xpath", ConditionType.EQUALS,
        "//tr[td[contains(text(),'" + targetProductID + "')]]//input[@placeholder='Price Sale']")

WebUI.waitForElementVisible(priceInput, timeout)
WebUI.waitForElementVisible(saleInput, timeout)

// ====== NHẬP DỮ LIỆU KHÔNG HỢP LỆ ======
// Đặt Giá gốc (2000)
WebUI.executeJavaScript("arguments[0].value='" + invalidPrice + "';", 
        Arrays.asList(WebUI.findWebElement(priceInput, timeout)))
// Đặt Giá Sale cao hơn (2500)
WebUI.executeJavaScript("arguments[0].value='" + invalidSalePrice + "';", 
        Arrays.asList(WebUI.findWebElement(saleInput, timeout)))

// Click Reload để áp dụng và kích hoạt validation
TestObject reload = new TestObject().addProperty("xpath", ConditionType.EQUALS, "//button[contains(.,'Reload')]")
WebUI.waitForElementClickable(reload, timeout)
WebUI.click(reload)
WebUI.delay(2)

// ====== VERIFY LỖI (Tiêu cực) ======
// Xác minh thông báo lỗi/indicator (Giả định thông báo lỗi chung hoặc thông báo lỗi cụ thể cho trường Sale)
TestObject errorMessage = new TestObject().addProperty("xpath", ConditionType.EQUALS,
        "//div[@role='alert' or contains(@class, 'alert-danger') or contains(text(), 'Price sale cannot be higher')]")

boolean isErrorDisplayed = WebUI.verifyElementPresent(errorMessage, timeout, FailureHandling.OPTIONAL)

if (isErrorDisplayed) {
    WebUI.comment("PASS: Hệ thống hiển thị thông báo lỗi khi Giá Sale cao hơn Giá Price.")
} else {
    // Nếu không tìm thấy thông báo, kiểm tra xem giá trị có được lưu không hợp lệ không
    WebUI.verifyElementAttributeValue(saleInput, 'value', invalidSalePrice, 3, FailureHandling.OPTIONAL)
    WebUI.comment("FAIL: Hệ thống cho phép lưu Giá Sale cao hơn Giá Price mà không báo lỗi.")
}

// [CLOSE BROWSER]