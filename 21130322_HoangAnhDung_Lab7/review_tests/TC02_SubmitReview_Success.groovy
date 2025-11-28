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

// TC02: Đăng nhập và gửi review 5 sao (sử dụng TestObject tạo tại chỗ)
SoftAssert softAssertion = new SoftAssert()

WebUI.openBrowser('')
String baseUrl = 'https://nest.botble.com/vi'

// Dùng biến môi trường cho credential để tránh commit secrets
String email = System.getenv('E2E_EMAIL') ?: 'dung@gmail.com'
String pwd = System.getenv('E2E_PASS') ?: '123456'

// Mở trang login và nhập thông tin
WebUI.navigateToUrl(baseUrl + '/login')
TestObject txtEmail = new TestObject().addProperty('id', ConditionType.EQUALS, 'email')
TestObject txtPassword = new TestObject().addProperty('id', ConditionType.EQUALS, 'password')
WebUI.setText(txtEmail, email)
WebUI.setText(txtPassword, pwd)
TestObject btnLogin = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//form[@id='botble-ecommerce-forms-fronts-auth-login-form']/div[4]/button")
WebUI.click(btnLogin)

// Mở sản phẩm và gửi review
WebUI.navigateToUrl(baseUrl + '/products/angies-boomchickapop-sweet-salty-kettle-corn')
TestObject tabReviews = new TestObject().addProperty('id', ConditionType.EQUALS, 'Reviews-tab')
WebUI.click(tabReviews)

TestObject star5 = new TestObject().addProperty('id', ConditionType.EQUALS, 'rating-star-5')
WebUI.click(star5)

TestObject txtComment = new TestObject().addProperty('name', ConditionType.EQUALS, 'comment')
String comment = 'Sản phẩm chất lượng, rất hài lòng'
WebUI.setText(txtComment, comment)

TestObject btnSubmit = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//div[@id='Reviews']/div/div/div[2]/form/button")
WebUI.click(btnSubmit)

// Kiểm tra xuất hiện nội dung vừa gửi (best-effort)
boolean present = WebUI.verifyTextPresent(comment, false, FailureHandling.OPTIONAL)
softAssertion.assertTrue(present, 'Submitted review text should appear')
softAssertion.assertAll()
