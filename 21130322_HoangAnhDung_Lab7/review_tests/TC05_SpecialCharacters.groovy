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

// TC05: Gửi review có dấu tiếng Việt và ký tự đặc biệt (định dạng WebUI + TestObject)
SoftAssert softAssertion = new SoftAssert()

WebUI.openBrowser('')
String baseUrl = 'https://nest.botble.com/vi'

String email = System.getenv('E2E_EMAIL') ?: 'dung@gmail.com'
String pwd = System.getenv('E2E_PASS') ?: '123456'

// Đăng nhập
WebUI.navigateToUrl(baseUrl + '/login')
TestObject txtEmail = new TestObject().addProperty('id', ConditionType.EQUALS, 'email')
TestObject txtPassword = new TestObject().addProperty('id', ConditionType.EQUALS, 'password')
WebUI.setText(txtEmail, email)
WebUI.setText(txtPassword, pwd)
TestObject btnLogin = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//form[@id='botble-ecommerce-forms-fronts-auth-login-form']/div[4]/button")
WebUI.click(btnLogin)

// Gửi review
WebUI.navigateToUrl(baseUrl + '/products/angies-boomchickapop-sweet-salty-kettle-corn')
TestObject tabReviews = new TestObject().addProperty('id', ConditionType.EQUALS, 'Reviews-tab')
WebUI.click(tabReviews)
TestObject star4 = new TestObject().addProperty('id', ConditionType.EQUALS, 'rating-star-4')
WebUI.click(star4)

String comment = 'Sản phẩm tuyệt vời — ngon, rẻ, dễ dùng! @#€%&*()'
TestObject txtComment = new TestObject().addProperty('name', ConditionType.EQUALS, 'comment')
WebUI.setText(txtComment, comment)
TestObject btnSubmit = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//div[@id='Reviews']/div/div/div[2]/form/button")
WebUI.click(btnSubmit)

// Kiểm tra xuất hiện nội dung (best-effort)
boolean shown = WebUI.verifyTextPresent('Sản phẩm tuyệt vời', false, FailureHandling.OPTIONAL)
softAssertion.assertTrue(shown, 'Special-character review should appear')
softAssertion.assertAll()
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

// TC05: Submit review with Vietnamese accents and special characters (WebUI style)
WebUI.openBrowser('')
String baseUrl = "https://nest.botble.com/vi"

String email = System.getenv('E2E_EMAIL') ?: 'dung@gmail.com'
String pwd = System.getenv('E2E_PASS') ?: '123456'

WebUI.navigateToUrl(baseUrl + '/login')
WebUI.setText(findTestObject('Page_Login/input_Email'), email)
WebUI.setEncryptedText(findTestObject('Page_Login/input_Password'), pwd)
WebUI.click(findTestObject('Page_Login/button_Login'))

WebUI.navigateToUrl(baseUrl + '/products/angies-boomchickapop-sweet-salty-kettle-corn')
WebUI.click(findTestObject('Page_Product/tab_Reviews'))
WebUI.click(findTestObject('Page_Product/star_4'))
String comment = 'Sản phẩm tuyệt vời — ngon, rẻ, dễ dùng! @#€%&*()'
WebUI.setText(findTestObject('Page_Product/textarea_Comment'), comment)
WebUI.click(findTestObject('Page_Product/button_SubmitReview'))

WebUI.verifyTextPresent('Sản phẩm tuyệt vời', false)
