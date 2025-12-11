import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

int timeout = 10
String productNameValue = " "
String permalinkValue = "khoang trang"

// ====== OPEN BROWSER ======
WebUI.comment("--- BẮT ĐẦU: MỞ TRÌNH DUYỆT VÀ ĐI ĐẾN TRANG LOGIN ---")
WebUI.openBrowser('')
WebUI.maximizeWindow()
WebUI.navigateToUrl('https://nest.botble.com/admin/login')

// ====== LOGIN ======
WebUI.comment("--- THỰC HIỆN ĐĂNG NHẬP ---")
TestObject user = new TestObject().addProperty("xpath", ConditionType.EQUALS,
        "//input[@type='text' or @type='email']")
TestObject pass = new TestObject().addProperty("xpath", ConditionType.EQUALS,
        "//input[@type='password']")
TestObject btnLogin = new TestObject().addProperty("xpath", ConditionType.EQUALS,
        "//button[contains(.,'Sign in') or contains(.,'Login')]")

WebUI.waitForElementVisible(user, timeout)
WebUI.setText(user, "admin")
WebUI.setText(pass, "12345678")
WebUI.click(btnLogin)
WebUI.waitForPageLoad(timeout)

// ====== NAVIGATE TO PRODUCTS ======
WebUI.comment("--- ĐIỀU HƯỚNG ĐẾN DANH SÁCH SẢN PHẨM (Products) ---")
// Nhấp vào Ecommerce
TestObject menuEcommerce = new TestObject().addProperty("xpath", ConditionType.EQUALS,
        "//span[contains(text(),'Ecommerce')]")
WebUI.click(menuEcommerce)

// Nhấp vào Products
TestObject menuProducts = new TestObject().addProperty("xpath", ConditionType.EQUALS,
        "//a[contains(.,'Products') and not(contains(.,'Prices'))]") 
WebUI.waitForElementClickable(menuProducts, timeout)
WebUI.click(menuProducts)
WebUI.waitForPageLoad(timeout)

// ====== CREATE NEW DIGITAL PRODUCT======
WebUI.comment("--- TẠO SẢN PHẨM KỸ THUẬT SỐ MỚI ---")

// SỬ DỤNG XPATH CỤ THỂ ĐỂ KHẮC PHỤC LỖI NHẬN DIỆN NÚT "Create"
TestObject btnCreate = new TestObject().addProperty("xpath", ConditionType.EQUALS,
        "//button[contains(@class,'btn-primary') and contains(.,'Create')]") 

WebUI.waitForElementClickable(btnCreate, timeout)
WebUI.click(btnCreate)

// Nhấp vào tùy chọn Digital
TestObject optionDigital = new TestObject().addProperty("xpath", ConditionType.EQUALS,
        "//a[contains(.,'Digital')]")
WebUI.waitForElementVisible(optionDigital, timeout)
WebUI.click(optionDigital)
WebUI.waitForPageLoad(timeout)

// ====== NHẬP DỮ LIỆU SẢN PHẨM "táo" ======
WebUI.comment("--- NHẬP TÊN VÀ PERMALINK ---")
// Input Name
TestObject inputName = new TestObject().addProperty("xpath", ConditionType.EQUALS,
        "//label[contains(text(),'Name')]/following-sibling::input")
WebUI.waitForElementVisible(inputName, timeout)
WebUI.setText(inputName, productNameValue)

// ====== SAVE & EXIT ======
WebUI.comment("--- LƯU VÀ THOÁT ---")
TestObject btnSaveExit = new TestObject().addProperty("xpath", ConditionType.EQUALS,
        "//button[contains(.,'Save & Exit')]")
WebUI.click(btnSaveExit)
WebUI.waitForPageLoad(timeout)

// ====== VERIFY KẾT QUẢ CUỐI CÙNG ======
WebUI.comment("--- XÁC MINH SẢN PHẨM ĐƯỢC TẠO ---")
// Xác minh sản phẩm "táo" đã xuất hiện trong danh sách Products
TestObject productInList = new TestObject().addProperty("xpath", ConditionType.EQUALS,
        "//table//td//a[contains(text(),'" + productNameValue + "')]")

boolean isCreated = WebUI.verifyElementPresent(productInList, timeout, FailureHandling.OPTIONAL)

if(isCreated) {
    WebUI.comment("THÀNH CÔNG: Sản phẩm Kỹ thuật số '${productNameValue}' đã được tạo và tìm thấy trong danh sách.")
} else {
    WebUI.comment("THẤT BẠI: Sản phẩm Kỹ thuật số '${productNameValue}' KHÔNG được tìm thấy trong danh sách.")
}

// ====== CLOSE BROWSER ======
WebUI.closeBrowser()