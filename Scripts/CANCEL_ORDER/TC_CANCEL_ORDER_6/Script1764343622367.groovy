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

// --- BƯỚC 1: ĐĂNG NHẬP (Bắt buộc để vào được trang Admin) ---
WebUI.openBrowser('https://nest.botble.com/admin/login')
WebUI.maximizeWindow()

// Nhập Username
TestObject txtUser = new TestObject().addProperty("id", ConditionType.EQUALS, "username")
WebUI.setText(txtUser, "admin") // <--- Thay bằng Username của bạn

// Nhập Password
TestObject txtPass = new TestObject().addProperty("id", ConditionType.EQUALS, "password")
WebUI.setText(txtPass, "12345678") // <--- Thay bằng Password của bạn

// Click Login
TestObject btnLogin = new TestObject().addProperty("xpath", ConditionType.EQUALS, "//button[@type='submit']")
WebUI.click(btnLogin)
WebUI.waitForPageLoad(10)

// --- BƯỚC 2: THAO TÁC TRÊN TRANG ORDERS ---

// Điều hướng đến trang Orders
WebUI.navigateToUrl("https://nest.botble.com/admin/ecommerce/orders")

// Click vào ô tìm kiếm
// Code cũ: selenium.click("xpath=//input[@type='search']")
TestObject inputSearch = new TestObject().addProperty("xpath", ConditionType.EQUALS, "//input[@type='search']")
WebUI.click(inputSearch)

// Click vào vùng main app (có thể để đóng dropdown hoặc focus out)
// Code cũ: selenium.click("xpath=//div[@id='app']/div/div/main/div/div/div[2]/div/div/div")
TestObject mainDiv = new TestObject().addProperty("xpath", ConditionType.EQUALS, "//div[@id='app']/div/div/main/div/div/div[2]/div/div/div")
WebUI.click(mainDiv)

// Chờ 1 chút (tùy chọn)
WebUI.delay(1)

// Click vào icon Global Store để mở chi tiết (hoặc action)
// Code cũ: xpath=(.//*[normalize-space(text()) and normalize-space(.)='Global Store'])[1]/following::*[name()='svg'][1]
TestObject iconGlobalStore = new TestObject().addProperty("xpath", ConditionType.EQUALS, "(.//*[normalize-space(text()) and normalize-space(.)='Global Store'])[1]/following::*[name()='svg'][1]")
WebUI.click(iconGlobalStore)
// --- PHẦN 3: XÓA ĐƠN HÀNG ĐÃ HỦY (3 BƯỚC MỚI THÊM TỪ HÌNH ẢNH) ---

// Bước 1: Mở lại trang danh sách Orders
// Url: https://nest.botble.com/admin/ecommerce/orders
WebUI.navigateToUrl("https://nest.botble.com/admin/ecommerce/orders")
WebUI.delay(2) // Chờ trang load

// Bước 2: Click vào icon xóa (bên cạnh nút Edit)
// XPath từ hình ảnh: xpath=(.//*[normalize-space(text()) and normalize-space(.)='Edit'])[1]/following::*[name()='svg'][1]
TestObject iconDelete = new TestObject().addProperty("xpath", ConditionType.EQUALS, "(.//*[normalize-space(text()) and normalize-space(.)='Edit'])[1]/following::*[name()='svg'][1]")
WebUI.click(iconDelete)

// Bước 3: Click nút xác nhận xóa
// XPath từ hình ảnh: xpath=//div[@id='stack-footer']/div[8]/div/div/div[3]/div/div/div/button
TestObject btnConfirmDeleteAction = new TestObject().addProperty("xpath", ConditionType.EQUALS, "//div[@id='stack-footer']/div[8]/div/div/div[3]/div/div/div/button")
WebUI.click(btnConfirmDeleteAction)

// Đóng trình duyệt
WebUI.closeBrowser()