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

// TC05: Kiểm tra cấu trúc trang so sánh và các label chính
WebUI.openBrowser('')
String baseUrl = 'https://nest.botble.com/vi'

// Mở trang compare trực tiếp
WebUI.navigateToUrl(baseUrl + '/compare')
boolean hasTitle = WebUI.verifyTextPresent('So sánh', false, FailureHandling.OPTIONAL) || WebUI.verifyTextPresent('Compare', false, FailureHandling.OPTIONAL)
TestObject compareTable = new TestObject().addProperty('xpath', ConditionType.EQUALS, "//table[contains(@class,'compare')]")
boolean hasTable = WebUI.verifyElementPresent(compareTable, 2, FailureHandling.OPTIONAL)

assert hasTitle || hasTable
