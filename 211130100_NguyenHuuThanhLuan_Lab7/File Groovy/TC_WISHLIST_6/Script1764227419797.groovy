import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling as FailureHandling

// Định nghĩa thời gian chờ mặc định (bằng giây)
int timeout = 10 

/* ================================
   MỞ TRÌNH DUYỆT & XỬ LÝ POP-UP
================================ */
WebUI.openBrowser('')
WebUI.maximizeWindow()
WebUI.navigateToUrl('https://nest.botble.com/') 

TestObject popupClose = findTestObject(
	'Object Repository/Page_Nest - Laravel Multipurpose eCommerce Script/button_Add_btn-close'
)

if (WebUI.waitForElementVisible(popupClose, 12, FailureHandling.OPTIONAL)) {
	WebUI.click(popupClose)
	WebUI.delay(1)
}

/* ================================
   KỊCH BẢN 1: ĐĂNG NHẬP, THÊM WISHLIST, ĐĂNG XUẤT
================================ */
// --- Chuẩn bị Login ---
TestObject accountBtn = findTestObject(
	'Object Repository/Page_Nest - Laravel Multipurpose eCommerce Script/span_Account'
)
WebUI.waitForElementClickable(accountBtn, timeout)
WebUI.click(accountBtn)

TestObject emailInput = findTestObject('Object Repository/Page_Login/input_Email_email')
TestObject passwordInput = findTestObject('Object Repository/Page_Login/input_Password_password')
TestObject loginButton = findTestObject('Object Repository/Page_Login/button_Login')

WebUI.waitForElementVisible(emailInput, timeout)
WebUI.setText(emailInput, 'luannguyenhuuthanh@gmail.com')

// *** ĐÃ XÓA: WebUI.click(findTestObject('Object Repository/Page_Login/div_Password')) ***

WebUI.setEncryptedText(passwordInput, '6VVFoV+eHaKgZHR+STFlaw==')
WebUI.click(loginButton) 
WebUI.waitForPageLoad(timeout) 

// --- Thêm sản phẩm vào Wishlist ---
TestObject productLink = findTestObject('Object Repository/Page_Account information/a_Product')
WebUI.waitForElementClickable(productLink, timeout)
WebUI.click(productLink) 

TestObject wishlistIcon = findTestObject('Object Repository/Page_Seeds of Change Organic Quinoe/i_Buy Now_fi-rs-heart')
WebUI.waitForElementClickable(wishlistIcon, timeout)
WebUI.click(wishlistIcon)
WebUI.delay(2) 

// --- Đăng xuất ---
// Click vào Menu (a_Nguyn Hu) để mở dropdown (Object này có thể là tên người dùng hoặc menu)
TestObject userMenuLink = findTestObject('Object Repository/Page_Seeds of Change Organic Quinoe/a_Nguyn Hu')
WebUI.waitForElementClickable(userMenuLink, timeout)
WebUI.click(userMenuLink)
WebUI.delay(1) // Đợi menu dropdown xuất hiện

// Click Logout
TestObject logoutLink = findTestObject('Object Repository/Page_Account information/span_Logout')
WebUI.waitForElementClickable(logoutLink, timeout)
WebUI.click(logoutLink)
WebUI.waitForPageLoad(timeout) // Chờ trang được tải lại sau khi đăng xuất

/* ================================
   KỊCH BẢN 2: ĐĂNG NHẬP LẠI & ĐI ĐẾN WISHLIST
================================ */
// --- Login lại ---
WebUI.click(accountBtn) // Click Account để mở form Login
WebUI.waitForElementVisible(emailInput, timeout) // Chờ form Login xuất hiện lại

WebUI.setText(emailInput, 'luannguyenhuuthanh@gmail.com')
WebUI.setEncryptedText(passwordInput, '6VVFoV+eHaKgZHR+STFlaw==')
WebUI.click(loginButton)
WebUI.waitForPageLoad(timeout)

// --- Đi đến Wishlist ---
TestObject wishlistLink = findTestObject('Object Repository/Page_Account information/span_Wishlist')
WebUI.waitForElementClickable(wishlistLink, timeout)
WebUI.click(wishlistLink)

WebUI.waitForPageLoad(timeout)

/* ================================
   ĐÓNG TRÌNH DUYỆT
================================ */
WebUI.closeBrowser()