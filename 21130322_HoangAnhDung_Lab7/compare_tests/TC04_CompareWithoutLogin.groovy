import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import org.testng.asserts.SoftAssert

// TC03: Thêm hai sản phẩm, xóa một sản phẩm khỏi so sánh và kiểm tra đã xóa
SoftAssert soft = new SoftAssert()
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

// Thêm 2 sản phẩm
WebUI.navigateToUrl(baseUrl)
TestObject add1 = new TestObject().addProperty('xpath', ConditionType.EQUALS, "(//a[contains(@class,'compare')])[1]")
TestObject add2 = new TestObject().addProperty('xpath', ConditionType.EQUALS, "(//a[contains(@class,'compare')])[2]")
if (WebUI.verifyElementPresent(add1, 2, FailureHandling.OPTIONAL)) WebUI.click(add1)
if (WebUI.verifyElementPresent(add2, 2, FailureHandling.OPTIONAL)) WebUI.click(add2)

// Mở trang compare
WebUI.navigateToUrl(baseUrl + '/compare')
// Xóa sản phẩm đầu tiên (selector best-effort)
TestObject removeFirst = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//table//tr[1]//a[contains(@class,'remove')]")
if (WebUI.verifyElementPresent(removeFirst, 2, FailureHandling.OPTIONAL)) {
    WebUI.click(removeFirst)
}

// Kiểm tra phần tử hàng đầu tiên không còn (best-effort)
TestObject firstRow = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//table//tr[1]")
boolean notPresent = WebUI.verifyElementNotPresent(firstRow, 5, FailureHandling.OPTIONAL)
soft.assertTrue(notPresent, 'First compare row should be removed')
soft.assertAll()
