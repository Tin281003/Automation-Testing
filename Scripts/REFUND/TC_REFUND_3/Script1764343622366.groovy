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

// --- BƯỚC 1: MỞ TRÌNH DUYỆT & ĐĂNG NHẬP ---
// (Bạn phải đăng nhập trước mới vào được trang Orders)
WebUI.openBrowser('https://nest.botble.com/admin/login')
WebUI.maximizeWindow()

// Nhập Username
TestObject txtUser = new TestObject().addProperty("id", ConditionType.EQUALS, "username")
WebUI.setText(txtUser, "admin") // <--- Thay bằng Username của bạn

// Nhập Password
TestObject txtPass = new TestObject().addProperty("id", ConditionType.EQUALS, "password")
WebUI.setText(txtPass, "12345678") // <--- Thay bằng Password của bạn

// Click nút Login
TestObject btnLogin = new TestObject().addProperty("xpath", ConditionType.EQUALS, "//button[@type='submit']")
WebUI.click(btnLogin)

// Chờ trang Admin tải xong
WebUI.waitForPageLoad(10)


// --- BƯỚC 2: THAO TÁC TRÊN TRANG ORDERS ---

// Điều hướng đến trang danh sách đơn hàng
WebUI.navigateToUrl("https://nest.botble.com/admin/ecommerce/orders")

// Nhập "abc" vào ô tìm kiếm và nhấn Enter
// XPath cũ: //input[@type='search']
TestObject inputSearch = new TestObject().addProperty("xpath", ConditionType.EQUALS, "//input[@type='search']")
WebUI.setText(inputSearch, "abc")
WebUI.sendKeys(inputSearch, Keys.chord(Keys.ENTER))

// Chờ kết quả tìm kiếm load xong (quan trọng)
WebUI.delay(2)

//  Click icon SVG (xpath từ code cũ)
TestObject iconSvg = new TestObject().addProperty("xpath", ConditionType.EQUALS, "(.//*[normalize-space(text()) and normalize-space(.)='Global Store'])[1]/following::*[name()='svg'][1]")
WebUI.click(iconSvg)

// Click nút Refund (hoặc nút chức năng tương ứng trong menu)
// XPath cũ: //div[@id='main-order-content']/div/div/div/div[3]/div[2]/div[2]/button
TestObject btnRefundOption = new TestObject().addProperty("xpath", ConditionType.EQUALS, "//div[@id='main-order-content']/div/div/div/div[3]/div[2]/div[2]/button")
WebUI.click(btnRefundOption)

// Click nút Xác nhận Refund
// ID cũ: confirm-refund-payment-button
TestObject btnConfirmRefund = new TestObject().addProperty("id", ConditionType.EQUALS, "confirm-refund-payment-button")
WebUI.click(btnConfirmRefund)

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