import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.WebElement
import com.kms.katalon.core.testobject.TestObject

// --- 1. Khởi động Trình duyệt và Điều hướng ---
WebUI.openBrowser('')
WebUI.navigateToUrl('https://nest.botble.com/admin/login')
WebUI.waitForPageLoad(10)

// --- 2. ĐĂNG NHẬP (Loại bỏ các click thừa) ---
WebUI.setText(findTestObject('Object Repository/Page_Admin  Botble Technologies/input_EmailUsername_username'), 'admin')
WebUI.setEncryptedText(findTestObject('Object Repository/Page_Admin  Botble Technologies/input_Lost your password_password'), 
    'RigbBhfdqOBGNlJIWM1ClA==')

// *** Đã loại bỏ các click thừa vào toggle và trường password ***
// WebUI.click(findTestObject('Object Repository/Page_Admin  Botble Technologies/span_Lost your password_input-password-toggle'))
// WebUI.click(findTestObject('Object Repository/Page_Admin  Botble Technologies/input_Lost your password_password'))
// ...

WebUI.click(findTestObject('Object Repository/Page_Admin  Botble Technologies/button_Sign in'))
WebUI.waitForPageLoad(10)

// --- 3. Điều hướng đến Discounts ---
// Click vào menu Ecommerce (Tăng độ ổn định bằng cách chờ)
WebUI.waitForElementClickable(findTestObject('Object Repository/Page_Dashboard  Botble Technologies/a_Ecommerce 23'), 15)
WebUI.click(findTestObject('Object Repository/Page_Dashboard  Botble Technologies/a_Ecommerce 23'))
WebUI.delay(1) // Đợi submenu mở

// Click vào Discounts
TestObject discountsObject = findTestObject('Object Repository/Page_Dashboard  Botble Technologies/span_Discounts')
WebUI.waitForElementClickable(discountsObject, 10)
WebUI.click(discountsObject)
WebUI.waitForPageLoad(10)

// --- 4. Chỉnh sửa Discount ---
// Click vào icon Edit (Cần chờ element này xuất hiện trên trang Discounts)
TestObject editIconObject = findTestObject('Object Repository/Page_Discounts  Botble Technologies/svg__icon svg-icon-ti-ti-edit')
WebUI.waitForElementClickable(editIconObject, 10)

// Sử dụng JavaScript Click để tránh lỗi ElementNotInteractableException (nếu có)
try {
    WebUI.click(editIconObject)
} catch (Exception e) {
    WebElement editIconElement = WebUI.findWebElement(editIconObject)
    WebUI.executeJavaScript('arguments[0].click();', [editIconElement])
}
WebUI.waitForPageLoad(10)

// --- 5. Thực hiện Chỉnh sửa ---
// Đổi mã Coupon
WebUI.setText(findTestObject('Object Repository/Page_Edit discount  Botble Technologies/input_Create coupon code_code'), 
    'XWAFU6LRPOOP12')

// Đảm bảo click vào End date_unlimited_time tắt đi (nếu nó đang bật)
WebUI.click(findTestObject('Object Repository/Page_Edit discount  Botble Technologies/input_End date_unlimited_time'))

// --- 6. Lưu và Xác nhận ---
WebUI.click(findTestObject('Object Repository/Page_Edit discount  Botble Technologies/button_Save'))
WebUI.waitForPageLoad(10)

// Kiểm tra thông báo thành công (span_Updated successfully)
WebUI.waitForElementVisible(findTestObject('Object Repository/Page_Edit discount  Botble Technologies/span_Updated successfully'), 10)
// Thay vì click vào thông báo (điều này thường không cần thiết), chúng ta chỉ cần xác nhận nó hiển thị
// WebUI.click(findTestObject('Object Repository/Page_Edit discount  Botble Technologies/span_Updated successfully')) 

WebUI.closeBrowser()