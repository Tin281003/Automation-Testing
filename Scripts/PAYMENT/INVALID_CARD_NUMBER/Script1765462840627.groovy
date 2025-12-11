import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.webui.driver.DriverFactory 
import org.openqa.selenium.WebElement 
import com.kms.katalon.core.testobject.TestObject as TestObject 

// --- 1. MỞ TRÌNH DUYỆT TRƯỚC ---
WebUI.openBrowser('')
WebUI.maximizeWindow() 
WebUI.navigateToUrl('https://nest.botble.com/')
WebUI.waitForPageLoad(10)
WebUI.delay(2) 

// --- 2. LƯU CỬA SỔ GỐC (Chỉ dùng khi đóng) ---
String originalWindow = DriverFactory.getWebDriver().getWindowHandle() 

// 3. Tắt Pop-up/Modal (Optional)
WebUI.click(findTestObject('Object Repository/Page_Nest - Laravel Multipurpose eCommerce Script/button_Add_btn-close'), FailureHandling.OPTIONAL)
WebUI.delay(1) 

// --- 4. Thêm sản phẩm vào Giỏ hàng (JavaScript Click) ---
TestObject addButton = findTestObject('Object Repository/Page_Nest - Laravel Multipurpose eCommerce Script/a_Add')
WebUI.waitForElementPresent(addButton, 10) 
WebElement addElement = WebUI.findWebElement(addButton) 
WebUI.executeJavaScript('arguments[0].click();', [addElement])

WebUI.delay(3) // Đợi giỏ hàng cập nhật

// --- 5. Chuyển đến Checkout (JavaScript Click) ---
TestObject checkoutButton = findTestObject('Object Repository/Page_Nest - Laravel Multipurpose eCommerce Script/a_Checkout')
WebUI.waitForElementPresent(checkoutButton, 10) 
WebElement checkoutElement = WebUI.findWebElement(checkoutButton) 
WebUI.executeJavaScript('arguments[0].click();', [checkoutElement])

// --- 6. Điền thông tin Người dùng ---
WebUI.waitForPageLoad(10) 
WebUI.setText(findTestObject('Object Repository/Page_Checkout/input_Login_address_name'), 'Tran Van Hien')
WebUI.setText(findTestObject('Object Repository/Page_Checkout/input_Full Name_address_email'), 'hien123@gmail.com')
WebUI.setText(findTestObject('Object Repository/Page_Checkout/input_land Islands_address_phone'), '334430049')
WebUI.selectOptionByValue(findTestObject('Object Repository/Page_Checkout/select_Select country.                     _771f1e'), 
    'VN', true)
WebUI.setText(findTestObject('Object Repository/Page_Checkout/input_Country_address_state'), 'abc')
WebUI.setText(findTestObject('Object Repository/Page_Checkout/input_State_address_city'), 'xyz')
WebUI.setText(findTestObject('Object Repository/Page_Checkout/input_City_address_address'), '123')

// --- 7. Chọn Thanh toán qua PayPal ---
WebUI.click(findTestObject('Object Repository/Page_Checkout/label_Fast and safe online payment via PayPal'))

// --- 8. Click Checkout, mở cửa sổ PayPal/Chuyển hướng ---
WebUI.click(findTestObject('Object Repository/Page_Checkout/button_Checkout'))

// --- 9. XỬ LÝ CHUYỂN ĐỔI CỬA SỔ/TAB (Kiểm tra xem có cửa sổ pop-up nào được mở không) ---
WebUI.delay(15) // Chờ 15 giây cho trang PayPal tải/chuyển hướng
int currentWindows = DriverFactory.getWebDriver().getWindowHandles().size()

if (currentWindows > 1) {
    WebUI.comment('Phát hiện Pop-up PayPal. Tiến hành Switch Window.')
    WebUI.switchToWindowIndex(1) // Chuyển sang cửa sổ thứ hai
} else {
    WebUI.comment('Trang web đã chuyển hướng trong cùng 1 tab. Tiếp tục tương tác.')
}

WebUI.waitForPageLoad(30) // Tăng thời gian chờ load trang PayPal

// --- 10. Tương tác với trang Đăng nhập PayPal ---
WebUI.setText(findTestObject('Object Repository/Page_ng nhp vo ti khon PayPal ca bn/input_VN_email'), 'hien123@gmail.com')

WebUI.click(findTestObject('Object Repository/Page_ng nhp vo ti khon PayPal ca bn/button_Tip theo')) 

WebUI.waitForElementPresent(findTestObject('Object Repository/Page_ng nhp vo ti khon PayPal ca bn/input_Tip theo_password'), 10) 
WebUI.setEncryptedText(findTestObject('Object Repository/Page_ng nhp vo ti khon PayPal ca bn/input_Tip theo_password'), 
    'RigbBhfdqODKcAsiUrg+1Q==')

WebUI.click(findTestObject('Object Repository/Page_ng nhp vo ti khon PayPal ca bn/button_ng nhp')) 

// Xác nhận thông báo lỗi.
WebUI.waitForElementPresent(findTestObject('Object Repository/Page_ng nhp vo ti khon PayPal ca bn/p_Mt s thng tin ca bn khng khp. Vui lng th _df8374'), 10) 

// --- 11. Kết thúc ---
if (currentWindows > 1) {
    WebUI.closeWindow()
    WebUI.switchToWindow(originalWindow) 
}

WebUI.closeBrowser()