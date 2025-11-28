import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import org.openqa.selenium.Keys as Keys

// --- 1. Khởi động Trình duyệt và Điều hướng ---
WebUI.openBrowser('')
WebUI.navigateToUrl('https://nest.botble.com/admin/login')
WebUI.waitForPageLoad(10)

// --- 2. ĐĂNG NHẬP (Đã loại bỏ các thao tác thừa) ---
WebUI.setText(findTestObject('Object Repository/Page_Admin  Botble Technologies/input_EmailUsername_username'), 'admin')

// Chỉ giữ lại mật khẩu cuối cùng hợp lệ
WebUI.setEncryptedText(findTestObject('Object Repository/Page_Admin  Botble Technologies/input_Lost your password_password'), 
    'RigbBhfdqOBGNlJIWM1ClA==') 

// Click nút Sign in (Đã sửa từ button_Sign in)
WebUI.click(findTestObject('Object Repository/Page_Admin  Botble Technologies/button_Sign in'))
WebUI.waitForPageLoad(15) 

// --- 3. Điều hướng đến Discounts ---
// Click vào menu cha: Ecommerce
WebUI.click(findTestObject('Object Repository/Page_Dashboard  Botble Technologies/a_Ecommerce 24')) 

// Click vào menu con: Discounts
WebUI.click(findTestObject('Object Repository/Page_Dashboard  Botble Technologies/span_Discounts'))
WebUI.waitForPageLoad(10)

// --- 4. Tìm kiếm và Xóa mã giảm giá ---
String codeToDelete = 'A'

// Chỉ nhập mã giảm giá một lần
WebUI.setText(findTestObject('Object Repository/Page_Discounts  Botble Technologies/input_Delete_form-control input-sm'), 
    codeToDelete)

// Thực hiện tìm kiếm bằng cách nhấn ENTER
WebUI.sendKeys(findTestObject('Object Repository/Page_Discounts  Botble Technologies/input_Delete_form-control input-sm'), 
    Keys.chord(Keys.ENTER))

// Chờ và click vào icon XÓA (Sử dụng đối tượng svg_Edit_icon svg-icon-ti-ti-trash đã được cung cấp)
WebUI.waitForElementPresent(findTestObject('Object Repository/Page_Discounts  Botble Technologies/svg_Edit_icon svg-icon-ti-ti-trash'), 10) 
WebUI.click(findTestObject('Object Repository/Page_Discounts  Botble Technologies/svg_Edit_icon svg-icon-ti-ti-trash'))

// Click vào nút "Delete" trong cửa sổ xác nhận modal
WebUI.waitForElementPresent(findTestObject('Object Repository/Page_Discounts  Botble Technologies/button_Delete'), 5)
WebUI.click(findTestObject('Object Repository/Page_Discounts  Botble Technologies/button_Delete'))

// --- 5. Đóng Trình duyệt ---

// Loại bỏ lệnh click vào thông báo "Deleted successfully"
WebUI.closeBrowser()