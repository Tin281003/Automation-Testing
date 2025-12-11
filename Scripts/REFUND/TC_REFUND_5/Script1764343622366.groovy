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

// --- BƯỚC 1: ĐĂNG NHẬP (Bắt buộc) ---
WebUI.openBrowser('https://nest.botble.com/admin/login')
WebUI.maximizeWindow()

// Nhập Username
TestObject txtUser = new TestObject().addProperty("id", ConditionType.EQUALS, "username")
WebUI.setText(txtUser, "admin") // <--- Thay Username của bạn

// Nhập Password
TestObject txtPass = new TestObject().addProperty("id", ConditionType.EQUALS, "password")
WebUI.setText(txtPass, "12345678") // <--- Thay Password của bạn

// Click Login
TestObject btnLogin = new TestObject().addProperty("xpath", ConditionType.EQUALS, "//button[@type='submit']")
WebUI.click(btnLogin)
WebUI.waitForPageLoad(10)

// --- BƯỚC 2: TÌM KIẾM VÀ HOÀN TIỀN ---

// Điều hướng đến trang Orders
WebUI.navigateToUrl("https://nest.botble.com/admin/ecommerce/orders")

// Nhập "abc" vào ô tìm kiếm
TestObject inputSearch = new TestObject().addProperty("xpath", ConditionType.EQUALS, "//input[@type='search']")
WebUI.setText(inputSearch, "abc")
WebUI.sendKeys(inputSearch, Keys.chord(Keys.ENTER))

// Chờ tìm kiếm xong
WebUI.delay(2)

// Click icon Global Store (dùng index 1)
TestObject iconGlobalStore = new TestObject().addProperty("xpath", ConditionType.EQUALS, "(.//*[normalize-space(text()) and normalize-space(.)='Global Store'])[1]/following::*[name()='svg'][1]")
WebUI.click(iconGlobalStore)

// Click nút mở modal Refund
TestObject btnRefundOption = new TestObject().addProperty("xpath", ConditionType.EQUALS, "//div[@id='main-order-content']/div/div/div/div[3]/div[2]/div[2]/button")
WebUI.click(btnRefundOption)

// --- BƯỚC 3: NHẬP SỐ TIỀN HOÀN (REFUND AMOUNT) ---
// Code cũ: selenium.type("xpath=//input[@type='refund_amount']", "10000") -> Sai type
// Sửa lại: Dùng name='refund_amount' vì input tiền thường dùng name
TestObject inputAmount = new TestObject().addProperty("name", ConditionType.EQUALS, "refund_amount")

// Xóa giá trị cũ (nếu có) và nhập 10000
WebUI.clearText(inputAmount) 
WebUI.setText(inputAmount, "100000")

// Click ra ngoài form để đảm bảo giá trị được nhận (tùy chọn)
TestObject formArea = new TestObject().addProperty("xpath", ConditionType.EQUALS, "//div[@id='confirm-refund-modal']/div/div/div[2]/form/div[3]")
WebUI.click(formArea)

// Click nút Xác nhận (Confirm Refund Payment)
TestObject btnConfirm = new TestObject().addProperty("id", ConditionType.EQUALS, "confirm-refund-payment-button")
WebUI.click(btnConfirm)

WebUI.delay(15)

// --- PHẦN 3: XÓA ĐƠN HÀNG ĐÃ HỦY (3 BƯỚC MỚI THÊM TỪ HÌNH ẢNH) ---

// Bước 1: Mở lại trang danh sách Orders
// Url: https://nest.botble.com/admin/ecommerce/orders
WebUI.navigateToUrl("https://nest.botble.com/admin/ecommerce/orders")
WebUI.delay(15) // Chờ trang load

// Đóng trình duyệt
WebUI.closeBrowser()