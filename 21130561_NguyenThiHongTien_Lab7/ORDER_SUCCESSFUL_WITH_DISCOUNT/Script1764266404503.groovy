import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.TestObject as TestObject
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory

def ENCRYPTED_PASSWORD = 'RigbBhfdqODKcAsiUrg+1Q=='

// 1. Khởi động Trình duyệt và Điều hướng
WebUI.openBrowser('')

WebUI.navigateToUrl('https://nest.botble.com/')

// 2. Click vào Deal of the Day (Có thể là mở modal hoặc chuyển trang)
WebUI.click(findTestObject('Object Repository/Page_Nest - Laravel Multipurpose eCommerce Script/div_Deal of the DayAngies Boomchickapop Swe_1d8720'))

// 3. Tắt Pop-up (Modal lớn)
WebUI.click(findTestObject('Object Repository/Page_Nest - Laravel Multipurpose eCommerce Script/button_Add_btn-close'), 
    FailureHandling.OPTIONAL)

// 4. THÊM SẢN PHẨM VÀO GIỎ HÀNG (SỬ DỤNG JS FORCE CLICK)
TestObject addButton = findTestObject('Object Repository/Page_Nest - Laravel Multipurpose eCommerce Script/a_Add')

WebUI.scrollToElement(addButton, 5)

WebUI.waitForElementPresent(addButton, 10)

WebElement addElement = WebUI.findWebElement(addButton)

WebUI.executeJavaScript('arguments[0].click();', [addElement])

// ************************************************************
// 5. KHẮC PHỤC LỖI CHECKOUT MINI-CART (LOẠI BỎ OBJECT BỊ NULL):
// 
// BỎ BƯỚC 5a GÂY LỖI NULL.
// 5b. Chờ nút Checkout hiển thị sau khi sản phẩm được thêm vào mini-cart
TestObject checkoutObject = findTestObject('Object Repository/Page_Nest - Laravel Multipurpose eCommerce Script/a_Checkout')

WebUI.waitForElementClickable(checkoutObject, 10)

// 5c. Buộc click nút Checkout trong mini-cart
WebElement checkoutElement = WebUI.findWebElement(checkoutObject)

WebUI.executeJavaScript('arguments[0].click();', [checkoutElement])

// ************************************************************
// --- Điền thông tin Người dùng và Địa chỉ Giao hàng ---
WebUI.waitForPageLoad(10)

WebUI.setText(findTestObject('Object Repository/Page_Checkout/input_Login_address_name'), 'Tran Van Hien')

WebUI.setText(findTestObject('Object Repository/Page_Checkout/input_Full Name_address_email'), 'hien123@gmail.com')

WebUI.setText(findTestObject('Object Repository/Page_Checkout/input_land Islands_address_phone'), '334430049')

WebUI.selectOptionByValue(findTestObject('Object Repository/Page_Checkout/select_Select country.                     _771f1e'), 
    'VN', true)

WebUI.setText(findTestObject('Object Repository/Page_Checkout/input_Country_address_state'), 'abc')

WebUI.setText(findTestObject('Object Repository/Page_Checkout/input_State_address_city'), 'xyz')

WebUI.setText(findTestObject('Object Repository/Page_Checkout/input_City_address_address'), '123')

// --- Xử lý Mã giảm giá (Coupon) ---
WebUI.click(findTestObject('Object Repository/Page_Checkout/button_Apply'), FailureHandling.OPTIONAL)

// --- Thông tin Thanh toán (Billing) ---
WebUI.click(findTestObject('Object Repository/Page_Checkout/input_Billing information_billing_address_s_0a25ad'))

// 6. Hoàn tất Đơn hàng
WebUI.click(findTestObject('Object Repository/Page_Checkout/button_Checkout'))

WebUI.closeBrowser()

