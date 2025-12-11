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

WebUI.openBrowser('')

WebUI.navigateToUrl('https://nest.botble.com/admin/login')

WebUI.maximizeWindow()

WebUI.setText(findTestObject('Admin Login Page/login_username_input'), 'admin')

WebUI.setEncryptedText(findTestObject('Admin Login Page/login_password_input'), 'RigbBhfdqOBGNlJIWM1ClA==')

WebUI.click(findTestObject('Admin Login Page/login_submit_btn'))

WebUI.click(findTestObject('Admin Home Page/ecommerce_menu'))

WebUI.click(findTestObject('Admin Home Page/product_tags_menu_item'))

WebUI.click(findTestObject('Product Tags Page/button_Create'))

WebUI.setText(findTestObject('Product Tags Form/tag_name_input'), name)

WebUI.setText(findTestObject('Product Tags Form/tag_slug_input'), permalink)

WebUI.setText(findTestObject('Page_New product tag  Botble Technologies/textarea_Description_description'), description)

WebUI.selectOptionByValue(findTestObject('Product Tags Form/tag_status_select'), status, true)

WebUI.click(findTestObject('Product Tags Form/tag_submit_btn'))

WebUI.click(findTestObject('Product Tags Page/product_tags_link'))

WebUI.delay(3)

WebUI.verifyElementPresent(findTestObject('Product Tags Page/tag_list_item', [('name') : name]), 5)

WebUI.click(findTestObject('Product Tags Page/tag_list_item', [('name') : name]), FailureHandling.STOP_ON_FAILURE)

// Verify form values
WebUI.verifyElementAttributeValue(findTestObject('Product Tags Form/tag_name_input'), 'value', name, 5)

String slugValue = WebUI.getAttribute(findTestObject('Product Tags Form/tag_slug_input'), 'value')

WebUI.verifyNotEqual(slugValue.trim(), '')

WebUI.verifyElementText(findTestObject('Product Tags Form/tag_description_input'), description)

WebUI.verifyOptionSelectedByValue(findTestObject('Product Tags Form/tag_status_select'), status, true, 5)

WebUI.closeBrowser()

