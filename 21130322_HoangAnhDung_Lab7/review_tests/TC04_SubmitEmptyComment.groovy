import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import org.testng.asserts.SoftAssert
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.testobject.ConditionType as ConditionType

// TC04: Đăng nhập, chọn rating nhưng gửi bình luận trống -> kỳ vọng validation
SoftAssert softAssertion = new SoftAssert()

WebUI.openBrowser('')
String baseUrl = 'https://nest.botble.com/vi'

String email = System.getenv('E2E_EMAIL') ?: 'dung@gmail.com'
String pwd = System.getenv('E2E_PASS') ?: '123456'

// Login
WebUI.navigateToUrl(baseUrl + '/login')
TestObject txtEmail = new TestObject().addProperty('id', ConditionType.EQUALS, 'email')
TestObject txtPassword = new TestObject().addProperty('id', ConditionType.EQUALS, 'password')
WebUI.setText(txtEmail, email)
WebUI.setText(txtPassword, pwd)
TestObject btnLogin = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//form[@id='botble-ecommerce-forms-fronts-auth-login-form']/div[4]/button")
WebUI.click(btnLogin)

// Mở product và chọn rating, để comment trống
WebUI.navigateToUrl(baseUrl + '/products/angies-boomchickapop-sweet-salty-kettle-corn')
TestObject tabReviews = new TestObject().addProperty('id', ConditionType.EQUALS, 'Reviews-tab')
WebUI.click(tabReviews)
TestObject star5 = new TestObject().addProperty('id', ConditionType.EQUALS, 'rating-star-5')
WebUI.click(star5)
TestObject btnSubmit = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//div[@id='Reviews']/div/div/div[2]/form/button")
WebUI.click(btnSubmit)

// Kiểm tra validation (best-effort)
boolean hasValidation = WebUI.verifyTextPresent('bắt buộc', false, FailureHandling.OPTIONAL) || WebUI.verifyTextPresent('required', false, FailureHandling.OPTIONAL) || WebUI.verifyTextPresent('Vui lòng nhập', false, FailureHandling.OPTIONAL)
softAssertion.assertTrue(hasValidation, 'Submitting empty comment should show validation')
softAssertion.assertAll()
