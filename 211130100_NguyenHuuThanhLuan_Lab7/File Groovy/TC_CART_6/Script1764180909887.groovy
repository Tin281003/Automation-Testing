import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling as FailureHandling

// Định nghĩa thời gian chờ mặc định (bằng giây)
int timeout = 10

/* ================================
   MỞ TRÌNH DUYỆT & ĐIỀU HƯỚNG
================================ */
WebUI.openBrowser('')

WebUI.maximizeWindow()

WebUI.navigateToUrl('https://nest.botble.com/vi')

/* ================================
   XỬ LÝ POP-UP QUẢNG CÁO (Tùy chọn)
================================ */
TestObject popupClose = findTestObject('Object Repository/Page_Nest - Tp lnh thng mi in t a nng ca Laravel/button_Thm_btn-close')

// Chờ tối đa 12s cho popup xuất hiện. Nếu không thấy thì bỏ qua (OPTIONAL).
if (WebUI.waitForElementVisible(popupClose, 12, FailureHandling.OPTIONAL)) {
    WebUI.click(popupClose)

    WebUI.delay(1)
}

/* ================================
   VÀO TRANG ĐĂNG NHẬP
================================ */
TestObject accountBtn = findTestObject('Object Repository/Page_Nest - Tp lnh thng mi in t a nng ca Laravel/span_Ti khon')

// Chờ nút "Tài khoản" có thể click được
WebUI.waitForElementClickable(accountBtn, timeout)

WebUI.click(accountBtn)

/* ================================
   THỰC HIỆN ĐĂNG NHẬP (FIX: Thêm click nút Đăng nhập)
================================ */
TestObject emailInput = findTestObject('Object Repository/Page_ng nhp/input_Email_email')

TestObject passwordInput = findTestObject('Object Repository/Page_ng nhp/input_Mt khu_password')

TestObject loginButton = findTestObject('Object Repository/Page_ng nhp/button_ng nhp')

WebUI.waitForElementVisible(emailInput, timeout)

WebUI.setText(emailInput, 'luannguyenhuuthanh@gmail.com')

WebUI.setEncryptedText(passwordInput, '6VVFoV+eHaKgZHR+STFlaw==')

// *** BƯỚC THIẾU TRONG MÃ CŨ ***
WebUI.click(loginButton)

WebUI.waitForPageLoad(timeout)

/* ================================
   THÊM SẢN PHẨM VÀO GIỎ
================================ */
TestObject productLink = findTestObject('Object Repository/Page_Thng tin ti khon/a_Sn phm')

WebUI.waitForElementClickable(productLink, timeout)

WebUI.click(productLink // Chuyển đến trang chi tiết sản phẩm
    )

// Chờ nút Thêm vào giỏ hàng
TestObject addToCartBtn = findTestObject('Object Repository/Page_Ht ging thay i Quinoa hu c/button_Thm vo gi hng')

WebUI.waitForElementClickable(addToCartBtn, timeout)

// Thực hiện click Add to Cart
WebUI.click(addToCartBtn)

WebUI.delay(2 // Đợi Ajax cập nhật giỏ hàng
    )

/* ================================
   ĐĂNG XUẤT
================================ */
// Click vào tên tài khoản hoặc menu người dùng (span_Nguyn Hu)
TestObject userMenuSpan = findTestObject('Object Repository/Page_Ht ging thay i Quinoa hu c/span_Nguyn Hu')

WebUI.waitForElementClickable(userMenuSpan, timeout)

WebUI.click(userMenuSpan)

WebUI.delay(1 // Đợi menu dropdown xổ xuống
    )

// Click vào nút Đăng xuất
TestObject logoutLink = findTestObject('Object Repository/Page_Thng tin ti khon/a_ng xut')

WebUI.waitForElementClickable(logoutLink, timeout)

WebUI.click(logoutLink)

WebUI.waitForPageLoad(timeout)

/* ================================
   ĐÓNG TRÌNH DUYỆT
================================ */
WebUI.closeBrowser()