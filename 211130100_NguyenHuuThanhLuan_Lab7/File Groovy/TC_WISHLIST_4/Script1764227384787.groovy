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
   THỰC HIỆN ĐĂNG NHẬP
================================ */
// Click vào Account để mở form Login
TestObject accountBtn = findTestObject(
	'Object Repository/Page_Nest - Laravel Multipurpose eCommerce Script/span_Account'
)
WebUI.waitForElementClickable(accountBtn, timeout)
WebUI.click(accountBtn)

// Nhập thông tin và Login
TestObject emailInput = findTestObject('Object Repository/Page_Login/input_Email_email')
TestObject passwordInput = findTestObject('Object Repository/Page_Login/input_Password_password')
TestObject loginButton = findTestObject('Object Repository/Page_Login/button_Login')

WebUI.waitForElementVisible(emailInput, timeout)

WebUI.setText(emailInput, 'luannguyenhuuthanh@gmail.com')
WebUI.setEncryptedText(passwordInput, '6VVFoV+eHaKgZHR+STFlaw==')
WebUI.click(loginButton) 
WebUI.waitForPageLoad(timeout) 

/* ================================
   1. THÊM SẢN PHẨM VÀO WISHLIST
================================ */
TestObject productLink = findTestObject('Object Repository/Page_Account information/a_Product')
WebUI.waitForElementClickable(productLink, timeout)
WebUI.click(productLink) // Chuyển đến trang chi tiết sản phẩm

// Chờ nút Wishlist (trái tim)
TestObject wishlistIcon = findTestObject('Object Repository/Page_Seeds of Change Organic Quinoe/i_Buy Now_fi-rs-heart')
WebUI.waitForElementClickable(wishlistIcon, timeout)

// Thực hiện click Add to Wishlist
WebUI.click(wishlistIcon)
WebUI.delay(2) // Đợi phản hồi AJAX: sản phẩm được thêm vào

/* ================================
   2. ĐI ĐẾN TRANG WISHLIST
================================ */
// Sử dụng liên kết Wishlist trên trang chi tiết sản phẩm
TestObject wishlistLink = findTestObject('Object Repository/Page_Seeds of Change Organic Quinoe/a_Wishlist')
WebUI.waitForElementClickable(wishlistLink, timeout)
WebUI.click(wishlistLink)

WebUI.waitForPageLoad(timeout)

/* ================================
   3. XÓA SẢN PHẨM KHỎI WISHLIST
================================ */
TestObject deleteIcon = findTestObject('Object Repository/Page_Wishlist/i_In stock_fi-rs-trash')

// Lăn chuột đến phần tử xóa để đảm bảo nó hiển thị
WebUI.scrollToElement(deleteIcon, 3) 

WebUI.waitForElementClickable(deleteIcon, timeout)
WebUI.click(deleteIcon)

// Chờ trang cập nhật (hoặc hộp thoại xác nhận nếu có)
WebUI.waitForPageLoad(timeout)
WebUI.delay(2)

/* ================================
   ĐÓNG TRÌNH DUYỆT
================================ */
WebUI.closeBrowser()