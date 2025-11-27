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
WebUI.navigateToUrl('https://nest.botble.com/') 

// --- Xử lý Pop-up (Giữ nguyên logic của bạn) ---
TestObject popupClose = findTestObject(
	'Object Repository/Page_Nest - Laravel Multipurpose eCommerce Script/button_Add_btn-close'
)

if (WebUI.waitForElementVisible(popupClose, 12, FailureHandling.OPTIONAL)) {
	WebUI.click(popupClose)
	WebUI.delay(1)
}

/* ================================
   VÀO TRANG ĐĂNG NHẬP
================================ */
// Click vào Account để mở form Login
TestObject accountBtn = findTestObject(
	'Object Repository/Page_Nest - Laravel Multipurpose eCommerce Script/span_Account'
)
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

WebUI.click(loginButton) 
WebUI.waitForPageLoad(timeout) 

/* ================================
   ĐI ĐẾN TRANG WISHLIST
================================ */
// Sau khi đăng nhập, click vào liên kết Wishlist trên Dashboard hoặc menu
TestObject wishlistLink = findTestObject('Object Repository/Page_Account information/span_Wishlist')

WebUI.waitForElementClickable(wishlistLink, timeout)
WebUI.click(wishlistLink)

WebUI.waitForPageLoad(timeout)

/* ================================
   ĐÓNG TRÌNH DUYỆT
================================ */
WebUI.closeBrowser()