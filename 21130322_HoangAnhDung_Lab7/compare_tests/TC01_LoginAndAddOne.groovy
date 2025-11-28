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

// TC01: Đăng nhập và thêm 1 sản phẩm vào so sánh (WebUI + inline TestObject)
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

// Trên trang chủ, cố click nút thêm so sánh của sản phẩm đầu tiên (best-effort)
WebUI.navigateToUrl(baseUrl)
TestObject addFirst = new TestObject().addProperty('xpath', ConditionType.EQUALS, "(//a[contains(@class,'compare')])[1]")
if (WebUI.verifyElementPresent(addFirst, 2, FailureHandling.OPTIONAL)) {
    WebUI.click(addFirst)
} else {
    // fallback: mở trang sản phẩm cụ thể và click nút thêm so sánh
    WebUI.navigateToUrl(baseUrl + '/products/angies-boomchickapop-sweet-salty-kettle-corn')
    TestObject btnAddOnProduct = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//a[contains(@class,'compare')]")
    WebUI.click(btnAddOnProduct)
}

// Mở trang compare và verify
WebUI.navigateToUrl(baseUrl + '/compare')
TestObject compareTable = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//table[contains(@class,'compare')]")
boolean present = WebUI.verifyElementPresent(compareTable, 5, FailureHandling.OPTIONAL)
soft.assertTrue(present, 'Compare table should be present after adding one product')
soft.assertAll()
