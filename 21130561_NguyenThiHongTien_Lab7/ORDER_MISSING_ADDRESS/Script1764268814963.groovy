import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.TestObject as TestObject
import org.openqa.selenium.WebElement as WebElement
// Thêm các import cần thiết cho các phương thức nâng cao
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import org.openqa.selenium.JavascriptExecutor as JavascriptExecutor

// Định nghĩa mật khẩu đã mã hóa (hoặc dùng chuỗi rõ cho JS)
def ENCRYPTED_PASSWORD = 'RigbBhfdqODKcAsiUrg+1Q=='

// Sử dụng mật khẩu rõ ràng cho JavaScript để tránh lỗi giải mã và tương tác
// Bạn cần thay thế chuỗi này bằng mật khẩu thực tế (không cần mã hóa)
String PLAIN_PASSWORD = 'TestPassword123'

// --- 1. Khởi động Trình duyệt và Điều hướng ---
WebUI.openBrowser('')

// Thêm lệnh chờ để Driver ổn định sau khi mở trình duyệt
WebUI.waitForPageLoad(5)

WebUI.navigateToUrl('https://nest.botble.com/')

// 2. Tắt Pop-up/Modal
WebUI.click(findTestObject('Object Repository/Page_Nest - Laravel Multipurpose eCommerce Script/button_Add_btn-close'), 
    FailureHandling.OPTIONAL)

// --- 3. Thêm sản phẩm vào Giỏ hàng (JS Force Click) ---
TestObject addButton = findTestObject('Object Repository/Page_Nest - Laravel Multipurpose eCommerce Script/a_Add')

WebUI.scrollToElement(addButton, 5)

WebUI.waitForElementPresent(addButton, 10)

WebElement addElement = WebUI.findWebElement(addButton)

// Buộc click nút 'Add'
WebUI.executeJavaScript('arguments[0].click();', [addElement])

// --- 4. Chuyển đến Checkout (JS Force Click cho Mini-Cart) ---
TestObject checkoutObjectMiniCart = findTestObject('Object Repository/Page_Nest - Laravel Multipurpose eCommerce Script/a_Checkout')

WebUI.waitForElementClickable(checkoutObjectMiniCart, 10)

WebElement checkoutElementMiniCart = WebUI.findWebElement(checkoutObjectMiniCart)

// Buộc click nút 'Checkout'
WebUI.executeJavaScript('arguments[0].click();', [checkoutElementMiniCart])

// --- 5. Điền thông tin Người dùng và Địa chỉ Giao hàng ---
WebUI.waitForPageLoad(10)

WebUI.setText(findTestObject('Object Repository/Page_Checkout/input_Login_address_name'), 'Tran Van Hien')

WebUI.setText(findTestObject('Object Repository/Page_Checkout/input_Full Name_address_email'), 'hien123@gmail.com')

WebUI.setText(findTestObject('Object Repository/Page_Checkout/input_land Islands_address_phone'), '334430049')

WebUI.selectOptionByValue(findTestObject('Object Repository/Page_Checkout/select_Select country.                     _771f1e'), 
    'VN', true)

// --- 6. Đăng ký Tài khoản (JS Force Set cho trường mật khẩu) ---
WebUI.click(findTestObject('Object Repository/Page_Checkout/input_Address_create_account'))

// Xác định các đối tượng mật khẩu
TestObject passwordInput = findTestObject('Object Repository/Page_Checkout/input_Register an account with above inform_3b85ff')

TestObject confirmPasswordInput = findTestObject('Object Repository/Page_Checkout/input_Password_password-confirm')

// Chờ và Cuộn tới trường Mật khẩu để đảm bảo hiển thị
WebUI.waitForElementVisible(passwordInput, 10)

WebUI.scrollToElement(passwordInput, 2)

// Buộc nhập giá trị bằng JavaScript
WebElement passwordElement = WebUI.findWebElement(passwordInput)

WebUI.executeJavaScript("arguments[0].value = '$PLAIN_PASSWORD';", [passwordElement])

WebElement confirmPasswordElement = WebUI.findWebElement(confirmPasswordInput)

WebUI.executeJavaScript("arguments[0].value = '$PLAIN_PASSWORD';", [confirmPasswordElement])

// --- 7. Hoàn tất Đơn hàng ---
WebUI.click(findTestObject('Object Repository/Page_Checkout/button_Checkout'))

WebUI.closeBrowser()

