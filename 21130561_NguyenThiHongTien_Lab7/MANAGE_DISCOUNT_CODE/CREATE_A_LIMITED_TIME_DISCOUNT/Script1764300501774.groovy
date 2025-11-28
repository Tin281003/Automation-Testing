import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.JavascriptExecutor
import com.kms.katalon.core.testobject.TestObject

// --- 1. Khởi động Trình duyệt và Điều hướng ---
WebUI.openBrowser('')
WebUI.navigateToUrl('https://nest.botble.com/admin/login')
WebUI.waitForPageLoad(10)

// --- 2. ĐĂNG NHẬP ---
WebUI.setText(findTestObject('Object Repository/Page_Admin  Botble Technologies/input_EmailUsername_username'), 'admin')
WebUI.setEncryptedText(findTestObject('Object Repository/Page_Admin  Botble Technologies/input_Lost your password_password'), 
    'RigbBhfdqOBGNlJIWM1ClA==')

WebUI.click(findTestObject('Object Repository/Page_Admin  Botble Technologies/button_Sign in'))
WebUI.waitForPageLoad(10) 

// --- 3. Điều hướng đến Discounts ---
TestObject discountsObject = findTestObject('Object Repository/Page_Dashboard  Botble Technologies/span_Discounts')
WebUI.waitForElementClickable(discountsObject, 15)

try {
    WebUI.click(discountsObject)
} catch (Exception e) {
    // Sử dụng JS Force Click nếu click thông thường thất bại
    WebElement discountsElement = WebUI.findWebElement(discountsObject)
    WebUI.executeJavaScript('arguments[0].click();', [discountsElement])
}
WebUI.waitForPageLoad(10)

// --- 4. Tạo Discount ---
WebUI.click(findTestObject('Object Repository/Page_Discounts  Botble Technologies/span_Create'))
WebUI.waitForPageLoad(10)

// Coupon code
WebUI.setText(findTestObject('Object Repository/Page_Create discount  Botble Technologies/input_Create coupon code_code'), 
    'SUMMER20')

// Chọn loại giảm giá (Percentage discount)
WebUI.selectOptionByValue(findTestObject('Object Repository/Page_Create discount  Botble Technologies/select_Percentage discount ()Free shippingS_df6a97'), 
    'percentage', true)

// Giá trị giảm giá
WebUI.setText(findTestObject('Object Repository/Page_Create discount  Botble Technologies/input_Discount_value'), '20')

// --- 5. Thiết lập thời gian (FIX LỖI DATE PICKER BẰNG JAVASCRIPT CLICK) ---

// Bỏ chọn "Unlimited time"
WebUI.click(findTestObject('Object Repository/Page_Create discount  Botble Technologies/input_End date_unlimited_time'))

// Start date
WebUI.click(findTestObject('Object Repository/Page_Create discount  Botble Technologies/input_Start date_start_date'))
WebUI.delay(1) // Đợi Date Picker hiển thị

// Chọn ngày 28 (Start Date) - SỬ DỤNG JAVASCRIPT CLICK
TestObject startDateObject = findTestObject('Object Repository/Page_Create discount  Botble Technologies/span_28')
WebUI.waitForElementPresent(startDateObject, 10) 
WebElement startDateElement = WebUI.findWebElement(startDateObject)
WebUI.executeJavaScript('arguments[0].click();', [startDateElement])

// End date
WebUI.click(findTestObject('Object Repository/Page_Create discount  Botble Technologies/input_End date_end_date'))
WebUI.delay(1) // Đợi Date Picker hiển thị

// Chọn ngày 28 tiếp theo (End Date) - SỬ DỤNG JAVASCRIPT CLICK
TestObject endDateObject = findTestObject('Object Repository/Page_Create discount  Botble Technologies/span_28_1')
WebUI.waitForElementPresent(endDateObject, 10)
WebElement endDateElement = WebUI.findWebElement(endDateObject)
WebUI.executeJavaScript('arguments[0].click();', [endDateElement])


// --- 6. Lưu và Đóng Trình duyệt ---
WebUI.click(findTestObject('Object Repository/Page_Create discount  Botble Technologies/button_Save'))
WebUI.waitForPageLoad(10)

WebUI.closeBrowser()