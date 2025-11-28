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

// TC06: Submit a review and immediately verify it appears in the Reviews list (WebUI style)
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
String uniqueComment = 'E2E Auto comment ' + System.currentTimeMillis()
WebUI.setText(findTestObject('Page_Product/textarea_Comment'), uniqueComment)
WebUI.click(findTestObject('Page_Product/star_5'))
WebUI.click(findTestObject('Page_Product/button_SubmitReview'))

// wait a moment for page update (best-effort)
WebUI.delay(2)

WebUI.verifyTextPresent(uniqueComment, false)
