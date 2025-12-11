import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.model.FailureHandling as FailureHandling

/* ================================
   OPEN BROWSER
================================ */
WebUI.openBrowser('')

WebUI.maximizeWindow()

WebUI.navigateToUrl('https://nest.botble.com/')

/* ================================
   FIX POPUP QUẢNG CÁO 10 GIÂY
================================ */
TestObject popupClose = findTestObject('Object Repository/Page_Nest - Laravel Multipurpose eCommerce Script/button_Add_btn-close')

// Chờ tối đa 12s cho popup xuất hiện
if (WebUI.waitForElementVisible(popupClose, 12, FailureHandling.OPTIONAL)) {
    WebUI.click(popupClose)

    WebUI.delay(1)
}

/* ================================
   VÀO TRANG LOGIN
================================ */
TestObject accountBtn = findTestObject('Object Repository/Page_Nest - Laravel Multipurpose eCommerce Script/a_Account')

WebUI.waitForElementClickable(accountBtn, 10)

WebUI.click(accountBtn)

/* ================================
   LOGIN
================================ */
WebUI.waitForElementVisible(findTestObject('Object Repository/Page_Login/input_Email_email'), 10)

WebUI.setText(findTestObject('Object Repository/Page_Login/input_Email_email'), 'luannguyenhuuthanh@gmail.com')

WebUI.setEncryptedText(findTestObject('Object Repository/Page_Login/input_Password_password'), '6VVFoV+eHaKgZHR+STFlaw==')

WebUI.click(findTestObject('Object Repository/Page_Login/button_Login'))

/* ================================
   ADD TO CART
================================ */
WebUI.waitForPageLoad(10)

// Chờ element "Product" xuất hiện
WebUI.waitForElementClickable(findTestObject('Object Repository/Page_Account information/a_Product'), 10)

WebUI.click(findTestObject('Object Repository/Page_Account information/a_Product'))

// Chờ nút Add to Cart
WebUI.waitForElementClickable(findTestObject('Object Repository/Page_Seeds of Change Organic Quinoe/button_Add to cart'), 
    10)

WebUI.click(findTestObject('Object Repository/Page_Seeds of Change Organic Quinoe/button_Add to cart'))

/* ================================
   CLOSE
================================ */
WebUI.delay(1)

WebUI.closeBrowser()