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

// TC03: Thử gửi review khi chưa đăng nhập -> kỳ vọng bắt login hoặc hiển thị yêu cầu đăng nhập
SoftAssert softAssertion = new SoftAssert()

WebUI.openBrowser('')
String baseUrl = 'https://nest.botble.com/vi'

// Mở trang sản phẩm (không login)
WebUI.navigateToUrl(baseUrl + '/products/angies-boomchickapop-sweet-salty-kettle-corn')
TestObject tabReviews = new TestObject().addProperty('id', ConditionType.EQUALS, 'Reviews-tab')
WebUI.click(tabReviews)

TestObject star5 = new TestObject().addProperty('id', ConditionType.EQUALS, 'rating-star-5')
WebUI.click(star5)

TestObject txtComment = new TestObject().addProperty('name', ConditionType.EQUALS, 'comment')
WebUI.setText(txtComment, 'Thử gửi review khi chưa đăng nhập')

TestObject btnSubmit = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//div[@id='Reviews']/div/div/div[2]/form/button")
WebUI.click(btnSubmit)

// Kiểm tra: redirect đến login hoặc hiển thị thông báo yêu cầu đăng nhập
boolean redirected = WebUI.getUrl().contains('/login')
boolean showMsg = WebUI.verifyTextPresent('Vui lòng đăng nhập', false, FailureHandling.OPTIONAL) || WebUI.verifyTextPresent('Please login', false, FailureHandling.OPTIONAL)

softAssertion.assertTrue(redirected || showMsg, 'Guest submit should require login')
softAssertion.assertAll()
