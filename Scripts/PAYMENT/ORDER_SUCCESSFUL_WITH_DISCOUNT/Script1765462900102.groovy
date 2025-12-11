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

// Đóng popup quảng cáo
WebUI.click(findTestObject('Object Repository/Page_Nest - Laravel Multipurpose eCommerce Script/button_Add_btn-close'), FailureHandling.OPTIONAL)
WebUI.delay(1)

// Click vào nút "Add" - SỬ DỤNG JS CLICK
TestObject addButton = findTestObject('Object Repository/Page_Nest - Laravel Multipurpose eCommerce Script/a_Add')
performJSClick(addButton)
WebUI.delay(3) // Đợi Mini-Cart hiển thị

// Click vào nút "Checkout" (từ Mini-Cart) - SỬ DỤNG JS CLICK
TestObject checkoutButtonFromCart = findTestObject('Object Repository/Page_Nest - Laravel Multipurpose eCommerce Script/a_Checkout')
performJSClick(checkoutButtonFromCart)
WebUI.waitForPageLoad(10)

// --- 2. Nhập thông tin thanh toán ---
WebUI.setText(findTestObject('Object Repository/Page_Checkout/input_Login_address_name'), 'Tran Van Hien')

WebUI.setText(findTestObject('Object Repository/Page_Checkout/input_Full Name_address_email'), 'hien123@gmail.com')

WebUI.setText(findTestObject('Object Repository/Page_Checkout/input_land Islands_address_phone'), '334430049')

WebUI.selectOptionByValue(findTestObject('Object Repository/Page_Checkout/select_Select country.                     _771f1e'), 
    'VN', true)

WebUI.setText(findTestObject('Object Repository/Page_Checkout/input_Country_address_state'), 'abc')

WebUI.setText(findTestObject('Object Repository/Page_Checkout/input_State_address_city'), 'xyz')

WebUI.setText(findTestObject('Object Repository/Page_Checkout/input_City_address_address'), '123')

// --- 3. XỬ LÝ COUPON (SỬA LỖI LOGIC: Mở trường coupon và áp dụng rỗng) ---
// Bước MỚI: Mở trường nhập coupon
WebUI.click(findTestObject('Object Repository/Page_Checkout/a_You have a coupon code'))
WebUI.delay(1) 

// Nút Apply (Áp dụng mã coupon rỗng) - SỬ DỤNG JS CLICK
TestObject applyButton = findTestObject('Object Repository/Page_Checkout/button_Apply')
performJSClick(applyButton)
WebUI.delay(1)

// Kiểm tra thông báo lỗi/thông báo
TestObject errorCouponMessage = findTestObject('Object Repository/Page_Checkout/span_This coupon code is invalid or has exp_7e6371')
WebUI.waitForElementPresent(errorCouponMessage, 5, FailureHandling.OPTIONAL)

// --- 4. Hoàn thành Checkout ---
// Nút Checkout cuối cùng - SỬ DỤNG JS CLICK
TestObject finalCheckoutButton = findTestObject('Object Repository/Page_Checkout/button_Checkout')
performJSClick(finalCheckoutButton)

WebUI.waitForPageLoad(15)

// Đã loại bỏ click thừa vào div_Success
WebUI.closeBrowser()