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

WebUI.click(findTestObject('Product Tags Page/delete_btn', [('name') : name]))

WebUI.click(findTestObject('Product Tags Page/delete_conform_btn'))

WebUI.delay(3)

WebUI.verifyElementNotPresent(findTestObject('Product Tags Page/tag_list_item', [('name') : name]), 5)

WebUI.closeBrowser()

