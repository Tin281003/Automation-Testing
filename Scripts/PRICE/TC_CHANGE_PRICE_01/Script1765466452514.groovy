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
def newPrice = '3333'           
def newSalePrice = '2111'      

// ===== BƯỚC 1: OPEN BROWSER & LOGIN =====
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

// ===== BƯỚC 2: NAVIGATE TỚI PRODUCT PRICES =====
TestObject menu = new TestObject().addProperty("xpath", ConditionType.EQUALS, "//span[contains(text(),'Ecommerce')]")
WebUI.waitForElementClickable(menu, timeout)
WebUI.click(menu)

TestObject productPrices = new TestObject().addProperty("xpath", ConditionType.EQUALS, "//a[contains(.,'Product Prices')]")
WebUI.waitForElementClickable(productPrices, timeout)
WebUI.click(productPrices)
WebUI.waitForPageLoad(timeout)

// =========================================================================
WebUI.comment("--- BƯỚC 3: CẬP NHẬT GIÁ (ID " + targetProductID + ") ---")

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

// Đặt Price = 3333
WebUI.executeJavaScript("arguments[0].value='" + newPrice + "';", 
        Arrays.asList(WebUI.findWebElement(priceInput, timeout)))
// Đặt Sale Price = 21111
WebUI.executeJavaScript("arguments[0].value='" + newSalePrice + "';", 
        Arrays.asList(WebUI.findWebElement(saleInput, timeout)))
        
// Chờ 1 giây để đảm bảo sự kiện nhập liệu hoàn tất trước khi click ra ngoài
WebUI.delay(1) 

// =========================================================================
WebUI.comment("--- BƯỚC 4: CLICK VÀO TIÊU ĐỀ TRANG ĐỂ KÍCH HOẠT LƯU TỰ ĐỘNG ---")

// Tìm một phần tử tĩnh (Tiêu đề trang "Product Prices") để click vào
// Giả định tiêu đề trang là H1 hoặc H2, nằm trong thẻ có chứa "Product Prices"
TestObject pageHeader = new TestObject().addProperty("xpath", ConditionType.EQUALS, 
    "//h1[contains(.,'Product Prices') or contains(.,'Bảng Giá Sản Phẩm')]")

// Thực hiện click ra khỏi ô input
WebUI.waitForElementClickable(pageHeader, timeout)
WebUI.click(pageHeader)

WebUI.delay(2) // Chờ 2 giây để hệ thống có thời gian thực hiện thao tác lưu ngầm

// ===== BƯỚC 5: CLOSE BROWSER =====
WebUI.closeBrowser()
WebUI.comment("✅ HOÀN TẤT: Đã cập nhật giá và click ra khỏi ô nhập liệu để kích hoạt lưu tự động.")