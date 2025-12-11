import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import java.util.Arrays as Arrays

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

if (WebUI.waitForElementVisible(popupClose, 12, FailureHandling.OPTIONAL)) {
    WebUI.click(popupClose)

    WebUI.delay(1)
}

/* ================================
   VÀO TRANG ĐĂNG NHẬP
================================ */
TestObject accountBtn = findTestObject('Object Repository/Page_Nest - Tp lnh thng mi in t a nng ca Laravel/span_Ti khon')

WebUI.waitForElementClickable(accountBtn, timeout)

WebUI.click(accountBtn)

/* ================================
   THỰC HIỆN ĐĂNG NHẬP
================================ */
TestObject emailInput = findTestObject('Object Repository/Page_ng nhp/input_Email_email')

TestObject passwordInput = findTestObject('Object Repository/Page_ng nhp/input_Mt khu_password')

TestObject loginButton = findTestObject('Object Repository/Page_ng nhp/button_ng nhp')

WebUI.waitForElementVisible(emailInput, timeout)

WebUI.setText(emailInput, 'luannguyenhuuthanh@gmail.com')

WebUI.setEncryptedText(passwordInput, '6VVFoV+eHaKgZHR+STFlaw==')

WebUI.click(loginButton)

/* ================================
   THÊM SẢN PHẨM 1
================================ */
WebUI.waitForPageLoad(timeout)

TestObject productLink = findTestObject('Object Repository/Page_Thng tin ti khon/a_Sn phm')

WebUI.waitForElementClickable(productLink, timeout)

WebUI.click(productLink)

TestObject addToCartBtn1 = findTestObject('Object Repository/Page_Ht ging thay i Quinoa hu c/button_Thm vo gi hng')

WebUI.waitForElementClickable(addToCartBtn1, timeout)

WebUI.click(addToCartBtn1)

WebUI.delay(1)

/* ================================
   THÊM SẢN PHẨM 2 (FIX LỖI MissingMethodException)
================================ */
TestObject productImgLink = findTestObject('Object Repository/Page_Ht ging thay i Quinoa hu c/img_Bn c th cng thch_hover-img')

// Đảm bảo phần tử được tải
WebUI.waitForElementVisible(productImgLink, timeout)

// 1. Lăn chuột để đảm bảo phần tử ở trong tầm nhìn
WebUI.scrollToElement(productImgLink, 5)

// 2. TÌM WebElement VÀ BUỘC CLICK BẰNG JAVASCRIPT
org.openqa.selenium.WebElement elementToClick = WebUI.findWebElement(productImgLink)

WebUI.executeJavaScript('arguments[0].click();', Arrays.asList(elementToClick))

WebUI.waitForPageLoad(timeout)

// Chờ nút Thêm vào giỏ hàng của SP2
TestObject addToCartBtn2 = findTestObject('Object Repository/Page_Encore Hi Sn Nhi Alaska (K Thut S)/button_Thm vo gi hng')

WebUI.waitForElementClickable(addToCartBtn2, timeout)

WebUI.click(addToCartBtn2)

WebUI.delay(1)

/* ================================
   ĐI ĐẾN GIỎ HÀNG & ĐÓNG TRÌNH DUYỆT
================================ */
TestObject cartLink = findTestObject('Object Repository/Page_Encore Hi Sn Nhi Alaska (K Thut S)/span_Gi hng')

WebUI.waitForElementClickable(cartLink, timeout)

WebUI.click(cartLink)

WebUI.waitForPageLoad(timeout)

WebUI.delay(1)

WebUI.closeBrowser()