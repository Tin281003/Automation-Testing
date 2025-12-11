import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling as FailureHandling

// Định nghĩa thời gian chờ mặc định (bằng giây)
int timeout = 10 

/* ================================
   MỞ TRÌNH DUYỆT & ĐIỀU HƯỚNG
================================ */
WebUI.openBrowser('')
WebUI.maximizeWindow()
WebUI.navigateToUrl('https://nest.botble.com/') // URL mặc định (Tiếng Anh?)

/* ================================
   XỬ LÝ POP-UP QUẢNG CÁO (Tùy chọn)
================================ */
TestObject popupClose = findTestObject(
	'Object Repository/Page_Nest - Laravel Multipurpose eCommerce Script/button_Add_btn-close'
)

// Chờ tối đa 12s cho popup xuất hiện. Nếu không thấy thì bỏ qua (OPTIONAL).
if (WebUI.waitForElementVisible(popupClose, 12, FailureHandling.OPTIONAL)) {
	WebUI.click(popupClose)
	WebUI.delay(1)
}

/* ================================
   VÀO TRANG ĐĂNG NHẬP
================================ */
TestObject accountBtn = findTestObject(
	'Object Repository/Page_Nest - Laravel Multipurpose eCommerce Script/span_Account'
)

// Chờ nút "Account" có thể click được
WebUI.waitForElementClickable(accountBtn, timeout)
WebUI.click(accountBtn)

/* ================================
   THỰC HIỆN ĐĂNG NHẬP
================================ */
TestObject emailInput = findTestObject('Object Repository/Page_Login/input_Email_email')
TestObject passwordInput = findTestObject('Object Repository/Page_Login/input_Password_password')
TestObject loginButton = findTestObject('Object Repository/Page_Login/button_Login')

WebUI.waitForElementVisible(emailInput, timeout)

WebUI.setText(emailInput, 'luannguyenhuuthanh@gmail.com')
WebUI.setEncryptedText(passwordInput, '6VVFoV+eHaKgZHR+STFlaw==')

// *** BƯỚC THIẾU TRONG MÃ CŨ ***
WebUI.click(loginButton) 
WebUI.waitForPageLoad(timeout) 

/* ================================
   VÀO TRANG SẢN PHẨM & THÊM VÀO WISHLIST
================================ */
TestObject productLink = findTestObject('Object Repository/Page_Account information/a_Product')
WebUI.waitForElementClickable(productLink, timeout)
WebUI.click(productLink) // Chuyển đến trang chi tiết sản phẩm

// Chờ nút Wishlist (trái tim)
TestObject wishlistIcon = findTestObject('Object Repository/Page_Seeds of Change Organic Quinoe/i_Buy Now_fi-rs-heart')
WebUI.waitForElementClickable(wishlistIcon, timeout)

// Thực hiện click Add to Wishlist
WebUI.click(wishlistIcon)
WebUI.delay(2) // Đợi phản hồi AJAX/thông báo thành công

/* ================================
   ĐÓNG TRÌNH DUYỆT
================================ */
WebUI.closeBrowser()