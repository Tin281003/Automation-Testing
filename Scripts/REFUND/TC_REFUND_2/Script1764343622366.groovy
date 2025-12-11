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
import org.testng.asserts.SoftAssert

// Khởi tạo SoftAssert (nếu bạn cần dùng để verify sau này)
SoftAssert softAssertion = new SoftAssert()

// 1. Mở trình duyệt (Nên mở trang Login trước thay vì Google)
WebUI.openBrowser('https://nest.botble.com/admin/login')
WebUI.maximizeWindow()

// --- BẮT ĐẦU PHẦN LOGIN (Bắt buộc phải có để vào được trang Admin) ---
// Bạn hãy cập nhật ID/XPath username password của bạn vào đây
TestObject txtUser = new TestObject().addProperty("id", ConditionType.EQUALS, "username")
WebUI.setText(txtUser, "admin") // <--- Nhập User của bạn

TestObject txtPass = new TestObject().addProperty("id", ConditionType.EQUALS, "password")
WebUI.setText(txtPass, "12345678") // <--- Nhập Pass của bạn

TestObject btnLogin = new TestObject().addProperty("xpath", ConditionType.EQUALS, "//button[@type='submit']")
WebUI.click(btnLogin)

// Chờ đăng nhập xong
WebUI.waitForPageLoad(10)
// --- KẾT THÚC PHẦN LOGIN ---


// 2. Điều hướng đến trang Orders
WebUI.navigateToUrl("https://nest.botble.com/admin/ecommerce/orders")

// 3. Click icon SVG (xpath từ code cũ)
TestObject iconSvg = new TestObject().addProperty("xpath", ConditionType.EQUALS, "(.//*[normalize-space(text()) and normalize-space(.)='Global Store'])[1]/following::*[name()='svg'][1]")
WebUI.click(iconSvg)

// 4. Click nút mở Refund modal (hoặc nút action tương ứng)
// Selenium cũ: xpath=//div[@id='main-order-content']/div/div/div/div[3]/div[2]/div[2]/button
TestObject btnAction = new TestObject().addProperty("xpath", ConditionType.EQUALS, "//div[@id='main-order-content']/div/div/div/div[3]/div[2]/div[2]/button")
WebUI.click(btnAction)

// 5. Click vào ô nhập tiền hoàn lại (refund_amount)
// Selenium cũ: name=refund_amount
TestObject inputRefundAmount = new TestObject().addProperty("name", ConditionType.EQUALS, "refund_amount")
WebUI.click(inputRefundAmount) 
// Nếu bạn muốn NHẬP TIỀN thay vì chỉ click, hãy dùng lệnh dưới:
// WebUI.setText(inputRefundAmount, "100000") 

// 6. Click vào vùng form (có thể để focus hoặc thao tác gì đó trên giao diện)
// Selenium cũ: xpath=//div[@id='confirm-refund-modal']/div/div/div[2]/form/div[3]
TestObject formArea = new TestObject().addProperty("xpath", ConditionType.EQUALS, "//div[@id='confirm-refund-modal']/div/div/div[2]/form/div[3]")
WebUI.click(formArea)

// 7. Click nút Xác nhận hoàn tiền
// Selenium cũ: id=confirm-refund-payment-button
TestObject btnConfirm = new TestObject().addProperty("id", ConditionType.EQUALS, "confirm-refund-payment-button")
WebUI.click(btnConfirm)

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