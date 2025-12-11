import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling as FailureHandling

// Thời gian chờ mặc định cho các bước (giúp mã gọn hơn)
int timeout = 10

/* ================================
   OPEN BROWSER & NAVIGATE
================================ */
WebUI.openBrowser('')

WebUI.maximizeWindow()

WebUI.navigateToUrl('https://nest.botble.com/')

/* ================================
   FIX POPUP QUẢNG CÁO (Sử dụng FailureHandling.OPTIONAL)
================================ */
TestObject popupClose = findTestObject('Object Repository/Page_Nest - Laravel Multipurpose eCommerce Script/button_Add_btn-close')

// Chờ tối đa 12s cho popup xuất hiện. Nếu không thấy thì bỏ qua.
if (WebUI.waitForElementVisible(popupClose, 12, FailureHandling.OPTIONAL)) {
    WebUI.click(popupClose)

    WebUI.delay(1)
}

/* ================================
   VÀO TRANG LOGIN
================================ */
TestObject accountBtn = findTestObject('Object Repository/Page_Nest - Laravel Multipurpose eCommerce Script/a_Account')

// Đảm bảo nút "Account" có thể click được trước khi click
WebUI.waitForElementClickable(accountBtn, timeout)

WebUI.click(accountBtn)

/* ================================
   LOGIN
================================ */
TestObject emailInput = findTestObject('Object Repository/Page_Login/input_Email_email')

TestObject passwordInput = findTestObject('Object Repository/Page_Login/input_Password_password')

TestObject loginButton = findTestObject('Object Repository/Page_Login/button_Login')

WebUI.waitForElementVisible(emailInput, timeout)

WebUI.setText(emailInput, 'luannguyenhuuthanh@gmail.com')

// Giữ nguyên chuỗi mã hóa cho bảo mật
WebUI.setEncryptedText(passwordInput, '6VVFoV+eHaKgZHR+STFlaw==')

WebUI.click(loginButton)

/* ================================
   ADD TO CART
================================ */
// Chờ tải trang sau khi đăng nhập
WebUI.waitForPageLoad(timeout)

// Click vào liên kết "Product" 
TestObject productLink = findTestObject('Object Repository/Page_Account information/a_Product')

WebUI.waitForElementClickable(productLink, timeout)

WebUI.click(productLink)

// *Lưu ý: Đã loại bỏ dòng click thừa (li_Product...) trong code cũ*
// Chờ nút Add to Cart trên trang sản phẩm
TestObject addToCartBtn = findTestObject('Object Repository/Page_Seeds of Change Organic Quinoe/button_Add to cart')

WebUI.waitForElementClickable(addToCartBtn, timeout)

// Thực hiện click Add to Cart lần 1
WebUI.click(addToCartBtn)

// Thực hiện click Add to Cart lần 2 (nếu đây là ý định của bạn)
// Cần thêm một độ trễ ngắn để đảm bảo click thứ hai hoạt động (tùy thuộc vào ứng dụng)
WebUI.delay(1)

WebUI.click(addToCartBtn)

/* ================================
   CLOSE
================================ */
WebUI.delay(1)

WebUI.closeBrowser()