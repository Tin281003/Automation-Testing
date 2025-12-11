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

// --- CÁC IMPORT QUAN TRỌNG ĐỂ SỬA LỖI DROPDOWN ---
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import org.openqa.selenium.WebElement
import java.util.Arrays

// Khởi tạo SoftAssert
SoftAssert softAssertion = new SoftAssert()

// =================================================================
// BƯỚC 1: ĐĂNG NHẬP (Bắt buộc)
// =================================================================
WebUI.openBrowser('https://nest.botble.com/admin/login')
WebUI.maximizeWindow()

TestObject txtUser = new TestObject().addProperty("id", ConditionType.EQUALS, "username")
WebUI.setText(txtUser, "admin")

TestObject txtPass = new TestObject().addProperty("id", ConditionType.EQUALS, "password")
WebUI.setText(txtPass, "12345678")

TestObject btnLogin = new TestObject().addProperty("xpath", ConditionType.EQUALS, "//button[@type='submit']")
WebUI.click(btnLogin)
WebUI.waitForPageLoad(10)

// =================================================================
// BƯỚC 2: TÌM KIẾM VÀ VÀO CHI TIẾT ĐƠN HÀNG
// =================================================================
WebUI.navigateToUrl("https://nest.botble.com/admin/ecommerce/orders")

// Nhập "abc"
TestObject inputSearch = new TestObject().addProperty("xpath", ConditionType.EQUALS, "//input[@type='search']")
WebUI.setText(inputSearch, "abc")
WebUI.sendKeys(inputSearch, Keys.chord(Keys.ENTER))
WebUI.delay(2)

// Click vào icon Global Store
TestObject iconGlobalStore = new TestObject().addProperty("xpath", ConditionType.EQUALS, "(.//*[normalize-space(text()) and normalize-space(.)='Global Store'])[1]/following::*[name()='svg'][1]")
WebUI.click(iconGlobalStore)

// =================================================================
// BƯỚC 3: CẬP NHẬT TRẠNG THÁI (FIX LỖI HIDDEN ELEMENT)
// =================================================================

// 1. Mở menu Action (nếu cần)
TestObject btnActionUpdate = new TestObject().addProperty("xpath", ConditionType.EQUALS, "//div[@id='main-order-content']/div/div/div/div[3]/div[5]/button")
if (WebUI.verifyElementPresent(btnActionUpdate, 2, FailureHandling.OPTIONAL)) {
    WebUI.click(btnActionUpdate)
    WebUI.delay(1)
}

// 2. Xử lý Dropdown bị ẩn bằng JavaScript
TestObject ddlStatus = new TestObject().addProperty("id", ConditionType.EQUALS, "status")
try {
    WebElement element = WebUiCommonHelper.findWebElement(ddlStatus, 5)
    WebUI.executeJavaScript("arguments[0].style.display = 'block'; arguments[0].style.visibility = 'visible';", Arrays.asList(element))
} catch (Exception e) {
    println("Dropdown đã hiển thị hoặc không tìm thấy, tiếp tục...")
}

// 3. Chọn trạng thái
WebUI.selectOptionByLabel(ddlStatus, "Ready to be shipped out", false)

// 4. Confirm Update
TestObject btnConfirmUpdate = new TestObject().addProperty("id", ConditionType.EQUALS, "confirm-update-shipping-status-button")
WebUI.click(btnConfirmUpdate)
WebUI.delay(2) // Chờ lưu xong

// =================================================================
// BƯỚC 4: HỦY ĐƠN HÀNG
// =================================================================
TestObject btnCancel = new TestObject().addProperty("xpath", ConditionType.EQUALS, "//div[@id='main-order-content']/div/div[2]/div/div[3]/div/button")
WebUI.click(btnCancel)

TestObject btnConfirmCancel = new TestObject().addProperty("id", ConditionType.EQUALS, "confirm-cancel-order-button")
WebUI.click(btnConfirmCancel)
WebUI.delay(2) // Chờ hủy xong

// =================================================================
// BƯỚC 5: XÓA ĐƠN HÀNG (3 bước bạn yêu cầu thêm)
// =================================================================

// 1. Quay lại trang danh sách Orders
WebUI.navigateToUrl("https://nest.botble.com/admin/ecommerce/orders")
WebUI.waitForPageLoad(10)

// 2. [QUAN TRỌNG] Tìm lại "abc" để chắc chắn xóa đúng đơn vừa hủy
WebUI.setText(inputSearch, "abc")
WebUI.sendKeys(inputSearch, Keys.chord(Keys.ENTER))
WebUI.delay(2)

// 3. Click icon Xóa (Delete) bên cạnh nút Edit
TestObject iconDelete = new TestObject().addProperty("xpath", ConditionType.EQUALS, "(.//*[normalize-space(text()) and normalize-space(.)='Edit'])[1]/following::*[name()='svg'][1]")
WebUI.click(iconDelete)

// 4. Click Confirm Delete (Nút ở footer)
TestObject btnConfirmDeleteAction = new TestObject().addProperty("xpath", ConditionType.EQUALS, "//div[@id='stack-footer']/div[8]/div/div/div[3]/div/div/div/button")
WebUI.click(btnConfirmDeleteAction)

// Đóng trình duyệt
WebUI.closeBrowser()