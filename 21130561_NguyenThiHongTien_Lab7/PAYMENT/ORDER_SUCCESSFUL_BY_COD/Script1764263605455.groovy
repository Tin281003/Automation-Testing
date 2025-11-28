import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling as FailureHandling
// --- BỔ SUNG CÁC IMPORT THIẾU ---
import com.kms.katalon.core.testobject.TestObject as TestObject
import org.openqa.selenium.WebElement as WebElement

// ---------------------------------
// 1. Khởi động Trình duyệt và Điều hướng
WebUI.openBrowser('')

WebUI.navigateToUrl('https://nest.botble.com/')

// 2. Tắt cửa sổ Pop-up (Modal lớn)
WebUI.click(findTestObject('Object Repository/Page_Nest - Laravel Multipurpose eCommerce Script/button_Add_btn-close'), 
    FailureHandling.OPTIONAL)

// 2.1. Ẩn SITE NOTICE bằng JavaScript
WebUI.waitForPageLoad(10)

WebUI.executeJavaScript('document.getElementsByClassName("site-notice__inner")[0].style.display="none";', null)

// 3. Thêm sản phẩm vào giỏ hàng
WebUI.scrollToElement(findTestObject('Object Repository/Page_Nest - Laravel Multipurpose eCommerce Script/a_Add'), 5)

WebUI.waitForElementClickable(findTestObject('Object Repository/Page_Nest - Laravel Multipurpose eCommerce Script/a_Add'), 
    10)

WebUI.click(findTestObject('Object Repository/Page_Nest - Laravel Multipurpose eCommerce Script/a_Add'))

// 4. Mở Giỏ hàng/Chuyển đến trang Shopping Cart
WebUI.click(findTestObject('Object Repository/Page_Nest - Laravel Multipurpose eCommerce Script/div_All CategoriesAll CategoriesMilks and D_8f9977'))

// 5. KHẮC PHỤC LỖI CHECKOUT Bằng JavaScript Force Click và WebElement Resolution
WebUI.waitForPageLoad(10)

// 5a. Lấy Test Object
TestObject checkoutObject = findTestObject('Object Repository/Page_Shopping Cart/a_Checkout')

// 5b. Cuộn tới phần tử
WebUI.scrollToElement(checkoutObject, 5)

// 5c. Tìm kiếm WebElement thực tế (Giải quyết lỗi IllegalArgumentException)
WebElement checkoutElement = WebUI.findWebElement(checkoutObject)

// 5d. Buộc click bằng JavaScript (truyền WebElement vào List)
WebUI.executeJavaScript('arguments[0].click();', [checkoutElement])

// 6. Điền thông tin Người nhận (Trang Checkout)
WebUI.waitForPageLoad(10)

WebUI.setText(findTestObject('Object Repository/Page_Checkout/input_Login_address_name'), 'Tran Van Hien')

WebUI.setText(findTestObject('Object Repository/Page_Checkout/input_Full Name_address_email'), 'hien123@gmail.com')

WebUI.setText(findTestObject('Object Repository/Page_Checkout/input_land Islands_address_phone'), '33344330049')

// 7. Chọn Quốc gia
WebUI.selectOptionByValue(findTestObject('Object Repository/Page_Checkout/select_Select country.                     _771f1e'), 
    'VN', true)

// 8. Điền thông tin Địa chỉ
WebUI.setText(findTestObject('Object Repository/Page_Checkout/input_Country_address_state'), 'abc')

WebUI.setText(findTestObject('Object Repository/Page_Checkout/input_State_address_city'), 'xyz')

WebUI.setText(findTestObject('Object Repository/Page_Checkout/input_City_address_address'), '123')

// 11. Chấp nhận Điều khoản & Điều kiện
WebUI.click(findTestObject('Object Repository/Page_Checkout/div_By placing an order, you agree to our T_2111d3'))

// 12. Hoàn tất Thanh toán (Nhấn nút Checkout)
WebUI.click(findTestObject('Object Repository/Page_Checkout/button_Checkout'))

// 13. Đóng Trình duyệt
WebUI.closeBrowser()

