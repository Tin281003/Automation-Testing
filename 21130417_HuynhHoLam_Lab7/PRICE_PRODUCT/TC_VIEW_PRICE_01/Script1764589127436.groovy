import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.ConditionType
import java.util.Arrays

// =========================================================================
// CẤU HÌNH BIẾN
// =========================================================================
int timeout = 10
def targetProductID = '2'       
def invalidPrice = '2000'       // Giá gốc (Price)
def invalidSalePrice = '2500'   // Giá Sale (Sale Price > Price)

// Chuỗi JavaScript mạnh mẽ để gán giá trị và kích hoạt sự kiện lưu:
def jsTrigger = """
    var input = arguments[0];
    input.value = arguments[1]; // Gán giá trị mới
    
    // Tạo và kích hoạt sự kiện 'change'
    var event = new Event('change');
    input.dispatchEvent(event);
    
    // Tùy chọn: Kích hoạt sự kiện 'blur'
    var blurEvent = new Event('blur');
    input.dispatchEvent(blurEvent);
"""

// ===== BƯỚC 1 & 2: LOGIN & NAVIGATE (Giữ nguyên) =====
WebUI.openBrowser('')
WebUI.maximizeWindow()
WebUI.navigateToUrl("https://nest.botble.com/admin/login")

TestObject user = new TestObject().addProperty("xpath", ConditionType.EQUALS, "//form//input[@type='email' or @type='text']")
TestObject pass = new TestObject().addProperty("xpath", ConditionType.EQUALS, "//form//input[@type='password']")
TestObject btnLogin = new TestObject().addProperty("xpath", ConditionType.EQUALS, "//button[contains(.,'Sign in') or contains(.,'Login')]")

WebUI.waitForElementClickable(user, timeout)
WebUI.setText(user, "admin")
WebUI.waitForElementClickable(pass, timeout)
WebUI.setText(pass, "12345678")
WebUI.click(btnLogin)
WebUI.waitForPageLoad(timeout)

TestObject menu = new TestObject().addProperty("xpath", ConditionType.EQUALS, "//span[contains(text(),'Ecommerce')]")
WebUI.waitForElementClickable(menu, timeout)
WebUI.click(menu)

TestObject productPrices = new TestObject().addProperty("xpath", ConditionType.EQUALS, "//a[contains(.,'Product Prices')]")
WebUI.waitForElementClickable(productPrices, timeout)
WebUI.click(productPrices)
WebUI.waitForPageLoad(timeout)

// =========================================================================
WebUI.comment("--- BƯỚC 3: CỐ GẮNG NHẬP GIÁ SALE > GIÁ GỐC (ID " + targetProductID + ") ---")

// SỬ DỤNG XPath ỔN ĐỊNH
def productRowBaseXPath = "//tr[td[normalize-space()='" + targetProductID + "']]"

// Ô PRICE nằm ở CỘT THỨ 5 (td[5])
TestObject priceInput = new TestObject().addProperty("xpath", ConditionType.EQUALS,
    productRowBaseXPath + "/td[5]//input") 
// Ô SALE PRICE nằm ở CỘT THỨ 6 (td[6])
TestObject saleInput = new TestObject().addProperty("xpath", ConditionType.EQUALS,
    productRowBaseXPath + "/td[6]//input") 

WebUI.waitForElementVisible(priceInput, timeout) 
WebUI.waitForElementVisible(saleInput, timeout) 

// 1. CẬP NHẬT GIÁ GỐC (PRICE = 2000) và KÍCH HOẠT LƯU
WebUI.comment("Đặt Price = " + invalidPrice)
WebUI.executeJavaScript(jsTrigger, 
    Arrays.asList(WebUI.findWebElement(priceInput, timeout), invalidPrice)) 

WebUI.delay(1) 

// 2. CẬP NHẬT GIÁ SALE (SALE PRICE = 2500) và KÍCH HOẠT LƯU
WebUI.comment("Đặt Sale Price = " + invalidSalePrice + " (Sale > Price)")
WebUI.executeJavaScript(jsTrigger, 
    Arrays.asList(WebUI.findWebElement(saleInput, timeout), invalidSalePrice)) 
        
WebUI.delay(3) // Chờ 3 giây để hệ thống có thời gian xử lý sự kiện lưu ngầm

// =========================================================================
WebUI.comment("--- BƯỚC 4: ĐÓNG TRÌNH DUYỆT ---")

// ===== CLOSE BROWSER =====
WebUI.closeBrowser()
WebUI.comment("✅ HOÀN TẤT: Đã cập nhật Price < Sale Price và dùng JavaScript để buộc kích hoạt lưu tự động.")