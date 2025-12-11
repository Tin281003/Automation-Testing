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
// Đảm bảo Test Object tồn tại: input_EmailUsername_username
WebUI.setText(findTestObject('Object Repository/Page_Admin  Botble Technologies/input_EmailUsername_username'), 'admin')

// Đảm bảo Test Object tồn tại: input_Lost your password_password
WebUI.setEncryptedText(findTestObject('Object Repository/Page_Admin  Botble Technologies/input_Lost your password_password'), 
    'RigbBhfdqOBGNlJIWM1ClA==')

WebUI.click(findTestObject('Object Repository/Page_Admin  Botble Technologies/button_Sign in'))
WebUI.waitForPageLoad(10) 

// --- 3. Điều hướng đến Discounts (FIX ElementNotInteractableException) ---

// Xác định đối tượng Discounts
TestObject discountsObject = findTestObject('Object Repository/Page_Dashboard  Botble Technologies/span_Discounts')

// Chờ phần tử sẵn sàng để click
WebUI.waitForElementClickable(discountsObject, 15)

try {
    // Thử Click thông thường
    WebUI.click(discountsObject)
} catch (Exception e) {
    // Nếu click thất bại, sử dụng JS Force Click (Giải pháp mạnh)
    System.out.println("Click thông thường thất bại, thử sử dụng JS Executor.")
    WebElement discountsElement = WebUI.findWebElement(discountsObject)
    WebUI.executeJavaScript('arguments[0].click();', [discountsElement])
}

WebUI.waitForPageLoad(10)

// --- 4. Tạo Discount ---
WebUI.click(findTestObject('Object Repository/Page_Discounts  Botble Technologies/span_Create'))
WebUI.waitForPageLoad(10)

// Coupon code
WebUI.setText(findTestObject('Object Repository/Page_Create discount  Botble Technologies/input_Create coupon code_code'), 
    'SALE20')

// Chọn loại giảm giá (Percentage discount)
WebUI.selectOptionByValue(findTestObject('Object Repository/Page_Create discount  Botble Technologies/select_Percentage discount ()Free shippingS_df6a97'), 
    'percentage', true)

// Giá trị giảm giá
WebUI.setText(findTestObject('Object Repository/Page_Create discount  Botble Technologies/input_Discount_value'), '20')

// Thiết lập thời gian: Chọn Không giới hạn thời gian (Unlimited time)
WebUI.click(findTestObject('Object Repository/Page_Create discount  Botble Technologies/input_End date_unlimited_time'))

// --- 5. Lưu và Đóng Trình duyệt ---
WebUI.click(findTestObject('Object Repository/Page_Create discount  Botble Technologies/button_Save'))
WebUI.waitForPageLoad(10)

WebUI.closeBrowser()