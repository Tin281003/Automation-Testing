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

WebUI.navigateToUrl('https://nest.botble.com/vi')

WebUI.click(findTestObject('Object Repository/Page_Nest - Tp lnh thng mi in t a nng ca Laravel/div_Giao dch trong ngyAngies Boomchickapop _f4cfa6'))

WebUI.click(findTestObject('Object Repository/Page_Nest - Tp lnh thng mi in t a nng ca Laravel/button_Thm_btn-close'))

WebUI.click(findTestObject('Object Repository/Page_Nest - Tp lnh thng mi in t a nng ca Laravel/span_Ti khon'))

WebUI.setText(findTestObject('Object Repository/Page_ng nhp/input_Email_email'), 'admin@gmail.com')

WebUI.setEncryptedText(findTestObject('Object Repository/Page_ng nhp/input_Mt khu_password'), 'aeHFOx8jV/A=')

WebUI.click(findTestObject('Object Repository/Page_ng nhp/button_ng nhp'))

WebUI.click(findTestObject('Object Repository/Page_Thng tin ti khon/span_Minh Huy'))

WebUI.click(findTestObject('Object Repository/Page_Thng tin ti khon/a_Chnh sa ti khon'))

WebUI.click(findTestObject('Object Repository/Page_Ci t ti khon/input_Ngy sinh_date_of_birth'))

WebUI.click(findTestObject('Object Repository/Page_Ci t ti khon/td_17'))

WebUI.click(findTestObject('Object Repository/Page_Ci t ti khon/div_Ngy sinh'))

WebUI.click(findTestObject('Object Repository/Page_Ci t ti khon/button_Cp nht'))

