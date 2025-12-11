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

// Khởi tạo SoftAssert
SoftAssert softAssertion = new SoftAssert()

// --- BƯỚC 1: MỞ TRÌNH DUYỆT TẠI TRANG LOGIN ---
// Code cũ mở Google rồi mới mở trang Login, tôi sửa lại mở thẳng trang Login cho nhanh
WebUI.openBrowser('https://nest.botble.com/admin/login')
WebUI.maximizeWindow()

// --- BƯỚC 2: NHẬP THÔNG TIN ---

// Nhập Username "tin"
// Code cũ: selenium.type("id=username", "tin")
TestObject txtUser = new TestObject().addProperty("id", ConditionType.EQUALS, "username")
WebUI.setText(txtUser, "tin")

// Nhập Password "123456"
// Code cũ: selenium.type("id=password", "123456")
TestObject txtPass = new TestObject().addProperty("id", ConditionType.EQUALS, "password")
WebUI.setText(txtPass, "123456")

// --- BƯỚC 3: CLICK NÚT ĐĂNG NHẬP ---

// Click nút login (được xác định là icon SVG nằm sau chữ "Remember me?")
// Code cũ: selenium.click("xpath=(.//*[normalize-space(text()) and normalize-space(.)='Remember me?'])[1]/following::*[name()='svg'][1]")
TestObject btnLogin = new TestObject().addProperty("xpath", ConditionType.EQUALS, "(.//*[normalize-space(text()) and normalize-space(.)='Remember me?'])[1]/following::*[name()='svg'][1]")
WebUI.click(btnLogin)

// Chờ trang xử lý đăng nhập
WebUI.waitForPageLoad(20)

// Đóng trình duyệt (tùy chọn)
 WebUI.closeBrowser()