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

WebUI.click(findTestObject('Object Repository/Page_Admin  Botble Technologies/svg_Sign in_icon icon-left svg-icon-ti-ti-login-2'))

WebUI.click(findTestObject('Object Repository/Page_Dashboard  Botble Technologies/a_Ecommerce 24'))

WebUI.click(findTestObject('Object Repository/Page_Dashboard  Botble Technologies/a_Product categories'))

WebUI.setText(findTestObject('Object Repository/Page_Product categories  Botble Technologies/input_Name_name'), '')

WebUI.click(findTestObject('Object Repository/Page_Product categories  Botble Technologies/input_It will replace Icon Font if it is pr_f132af'))

WebUI.click(findTestObject('Object Repository/Page_Product categories  Botble Technologies/button_Save'))

