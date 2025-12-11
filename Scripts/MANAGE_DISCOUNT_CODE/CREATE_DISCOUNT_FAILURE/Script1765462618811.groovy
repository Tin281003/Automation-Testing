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

// Mật khẩu đã mã hóa
WebUI.setEncryptedText(findTestObject('Object Repository/Page_Admin  Botble Technologies/input_Lost your password_password'), 
    'RigbBhfdqOBGNlJIWM1ClA==')

WebUI.click(findTestObject('Object Repository/Page_Admin  Botble Technologies/button_Sign in'))
WebUI.waitForPageLoad(15) 

// --- 3. Điều hướng đến Discounts (Sử dụng cách click qua menu cha) ---

// Click vào menu cha: Ecommerce
WebUI.click(findTestObject('Object Repository/Page_Dashboard  Botble Technologies/a_Ecommerce 23')) 

// Click vào menu con: Discounts
// Đảm bảo đối tượng này có thể tương tác được (có thể cần wait/JS click nếu lỗi ElementNotInteractableException xảy ra)
WebUI.click(findTestObject('Object Repository/Page_Dashboard  Botble Technologies/a_Discounts'))
WebUI.waitForPageLoad(10)

// --- 4. Tạo Discount và Điền thông tin ---
WebUI.click(findTestObject('Object Repository/Page_Discounts  Botble Technologies/span_Create'))
WebUI.waitForPageLoad(10)

// Coupon code
WebUI.setText(findTestObject('Object Repository/Page_Create discount  Botble Technologies/input_Create coupon code_code'), 
    'SALE10')

// Chọn loại giảm giá (Percentage discount)
WebUI.selectOptionByValue(findTestObject('Object Repository/Page_Create discount  Botble Technologies/select_Percentage discount ()Free shippingS_df6a97'), 
    'percentage', true)

// Giá trị giảm giá
WebUI.setText(findTestObject('Object Repository/Page_Create discount  Botble Technologies/input_Discount_value'), '10')

// --- 5. Thiết lập Ngày Hợp lệ (End Date > Start Date) ---

// Bỏ chọn "Never expired" (Nếu cần chọn ngày)
// Loại bỏ click thừa vào div_Start dateEnd dateNever expired
WebUI.click(findTestObject('Object Repository/Page_Create discount  Botble Technologies/input_End date_unlimited_time'))

// Start date
WebUI.click(findTestObject('Object Repository/Page_Create discount  Botble Technologies/input_Start date_start_date'))
WebUI.click(findTestObject('Object Repository/Page_Create discount  Botble Technologies/span_29')) // Chọn ngày 29 (Start)

// End date
WebUI.click(findTestObject('Object Repository/Page_Create discount  Botble Technologies/input_End date_end_date'))
// Giả định ngày 28 là ngày của tháng tiếp theo (lớn hơn 29)
WebUI.click(findTestObject('Object Repository/Page_Create discount  Botble Technologies/span_28')) 

// --- 6. Lưu và Đóng Trình duyệt ---
// Loại bỏ các lệnh click thừa vào thông báo lỗi không cần thiết
WebUI.click(findTestObject('Object Repository/Page_Create discount  Botble Technologies/button_Save'))
WebUI.waitForPageLoad(10)

WebUI.closeBrowser()