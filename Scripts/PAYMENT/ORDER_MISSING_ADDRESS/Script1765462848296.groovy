import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.WebElement
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.JavascriptExecutor

// --- Hàm hỗ trợ JavaScript Click ---
def performJSClick(TestObject object) {
    WebUI.waitForElementPresent(object, 10)
    WebElement element = WebUI.findWebElement(object)
    WebUI.executeJavaScript('arguments[0].click();', [element])
    WebUI.delay(0.5)
}

// --- 1. Khởi động Trình duyệt và Thêm sản phẩm ---
WebUI.openBrowser('')
WebUI.navigateToUrl('https://nest.botble.com/')
WebUI.waitForPageLoad(10)

// Đóng popup quảng cáo (sử dụng OPTIONAL)
WebUI.click(findTestObject('Object Repository/Page_Nest - Laravel Multipurpose eCommerce Script/button_Add_btn-close'), FailureHandling.OPTIONAL)
WebUI.delay(1)

// Click vào nút "Add" - SỬ DỤNG JS CLICK
TestObject addButton = findTestObject('Object Repository/Page_Nest - Laravel Multipurpose eCommerce Script/a_Add')
performJSClick(addButton)
WebUI.delay(3)

// Click vào nút "Checkout" (từ Mini-Cart) - SỬ DỤNG JS CLICK
TestObject checkoutButtonFromCart = findTestObject('Object Repository/Page_Nest - Laravel Multipurpose eCommerce Script/a_Checkout')
performJSClick(checkoutButtonFromCart)
WebUI.waitForPageLoad(10)

// --- 2. Nhập thông tin thanh toán (CỐ TÌNH BỎ TRỐNG TRƯỜNG ĐỊA CHỈ) ---
WebUI.setText(findTestObject('Object Repository/Page_Checkout/input_Login_address_name'), 'Tran Van Hien')

WebUI.setText(findTestObject('Object Repository/Page_Checkout/input_Full Name_address_email'), 'hien123@gmail.com')

WebUI.setText(findTestObject('Object Repository/Page_Checkout/input_land Islands_address_phone'), '334430049')

// Chọn Quốc gia
WebUI.selectOptionByValue(findTestObject('Object Repository/Page_Checkout/select_Select country.                     _771f1e'), 
    'VN', true)

// Nhập State
WebUI.setText(findTestObject('Object Repository/Page_Checkout/input_Country_address_state'), 'xyz')

// Nhập City
WebUI.setText(findTestObject('Object Repository/Page_Checkout/input_State_address_city'), '123')

// BỎ QUA BƯỚC: Nhập Address

// --- 3. Checkout và Xác minh lỗi ---
// Nút Checkout cuối cùng - SỬ DỤNG JS CLICK
TestObject finalCheckoutButton = findTestObject('Object Repository/Page_Checkout/button_Checkout')
performJSClick(finalCheckoutButton)

WebUI.waitForPageLoad(5) 

// XÁC MINH LỖI: Địa chỉ là bắt buộc (FIXED: Thêm timeout và loại bỏ chuỗi thông báo tùy chỉnh)
TestObject addressRequiredMessage = findTestObject('Object Repository/Page_Checkout/div_The Address field is required') 
WebUI.waitForElementPresent(addressRequiredMessage, 10)
WebUI.verifyElementPresent(addressRequiredMessage, 
    10, // Thêm timeout (cần thiết cho chữ ký này)
    FailureHandling.STOP_ON_FAILURE) 

WebUI.closeBrowser()