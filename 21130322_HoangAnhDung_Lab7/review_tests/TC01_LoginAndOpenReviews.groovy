import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.testobject.SelectorMethod
import org.openqa.selenium.Keys as Keys
import org.testng.asserts.SoftAssert

// TC01: Đăng nhập và mở tab Reviews của sản phẩm (định dạng WebUI + TestObject)
SoftAssert softAssertion = new SoftAssert()

// Mở trình duyệt và điều hướng đến trang login
WebUI.openBrowser('')
WebUI.navigateToUrl('https://nest.botble.com/vi/login')

// Lấy credential từ biến môi trường (khuyến nghị) hoặc dùng fallback
String email = System.getenv('E2E_EMAIL') ?: 'dung@gmail.com'
String pwd = System.getenv('E2E_PASS') ?: '123456'

// Tạo TestObject cho ô email và mật khẩu (sử dụng id nếu có)
TestObject txtEmail = new TestObject().addProperty('id', ConditionType.EQUALS, 'email')
TestObject txtPassword = new TestObject().addProperty('id', ConditionType.EQUALS, 'password')

// Nhập thông tin và submit form
WebUI.setText(txtEmail, email)
WebUI.setText(txtPassword, pwd)
TestObject btnLogin = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//form[@id='botble-ecommerce-forms-fronts-auth-login-form']/div[4]/button")
WebUI.click(btnLogin)

// Mở trang sản phẩm và click tab Reviews
WebUI.navigateToUrl('https://nest.botble.com/vi/products/angies-boomchickapop-sweet-salty-kettle-corn')
TestObject tabReviews = new TestObject().addProperty('id', ConditionType.EQUALS, 'Reviews-tab')
WebUI.click(tabReviews)

// Kiểm tra Reviews tab có hiện lên
boolean isPresent = WebUI.verifyElementPresent(tabReviews, 5, FailureHandling.OPTIONAL)
softAssertion.assertTrue(isPresent, 'Reviews tab should be present')
softAssertion.assertAll()
