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
import com.kms.katalon.core.testobject.SelectorMethod as SelectorMethod
import org.openqa.selenium.Keys as Keys
import org.testng.asserts.SoftAssert as SoftAssert

SoftAssert softAssertion = new SoftAssert()

// 1. Mở trình duyệt và đi đến trang login
WebUI.openBrowser('https://nest.botble.com/admin/login')

// 2. Điền Username (id=username)
TestObject txtUsername = new TestObject().addProperty("id", ConditionType.EQUALS, "username")
WebUI.setText(txtUsername, "admin") // <-- BẠN HÃY ĐIỀN USERNAME CỦA BẠN VÀO ĐÂY

// 3. Điền Password (id=password)
TestObject txtPassword = new TestObject().addProperty("id", ConditionType.EQUALS, "password")
WebUI.setText(txtPassword, "12345678") // <-- BẠN HÃY ĐIỀN PASSWORD CỦA BẠN VÀO ĐÂY

// LƯU Ý: Dòng 'selenium.click("css=path")' trong code cũ có vẻ là lỗi do recorder, tôi đã bỏ qua.

// 4. Click nút Submit (xpath=//button[@type='submit'])
TestObject btnSubmit = new TestObject().addProperty("xpath", ConditionType.EQUALS, "//button[@type='submit']")
WebUI.click(btnSubmit)

// 5. Chờ trang admin tải xong hoặc điều hướng thẳng đến đó
WebUI.waitForPageLoad(10)
// Nếu cần thiết ép buộc chuyển trang:
// WebUI.navigateToUrl('https://nest.botble.com/admin')

// 6. Click menu Plugins Ecommerce (id=cms-plugins-ecommerce)
TestObject menuEcommerce = new TestObject().addProperty("id", ConditionType.EQUALS, "cms-plugins-ecommerce")
WebUI.click(menuEcommerce)

// 7. Click menu Order (id=cms-plugins-ecommerce-order)
TestObject menuOrder = new TestObject().addProperty("id", ConditionType.EQUALS, "cms-plugins-ecommerce-order")
WebUI.click(menuOrder)

// 8. Click icon SVG (xpath từ code cũ)
TestObject iconSvg = new TestObject().addProperty("xpath", ConditionType.EQUALS, "(.//*[normalize-space(text()) and normalize-space(.)='Global Store'])[1]/following::*[name()='svg'][1]")
WebUI.click(iconSvg)

// 9. Click nút Refund (xpath từ code cũ)
TestObject btnRefundOption = new TestObject().addProperty("xpath", ConditionType.EQUALS, "//div[@id='main-order-content']/div/div/div/div[3]/div[2]/div[2]/button")
WebUI.click(btnRefundOption)

// 10. Confirm Refund (id=confirm-refund-payment-button)
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