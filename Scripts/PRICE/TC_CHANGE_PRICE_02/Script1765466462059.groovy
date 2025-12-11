import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.util.KeywordUtil
import org.openqa.selenium.Keys

int timeout = 10
String searchProduct = "encore"

// ===== OPEN =====
WebUI.openBrowser('')
WebUI.maximizeWindow()
WebUI.navigateToUrl("https://nest.botble.com/admin/login")

// ===== LOGIN =====
TestObject user = new TestObject().addProperty("xpath", ConditionType.EQUALS,
    "//form//input[@type='email' or @type='text']")
TestObject pass = new TestObject().addProperty("xpath", ConditionType.EQUALS,
    "//form//input[@type='password']")
TestObject btnLogin = new TestObject().addProperty("xpath", ConditionType.EQUALS,
    "//button[contains(.,'Sign in') or contains(.,'Login')]")

WebUI.waitForElementClickable(user, timeout)
WebUI.setText(user, "admin")
WebUI.waitForElementClickable(pass, timeout)
WebUI.setText(pass, "12345678")
WebUI.click(btnLogin)
WebUI.waitForPageLoad(timeout)

// ===== NAVIGATE =====
TestObject menu = new TestObject().addProperty("xpath", ConditionType.EQUALS,
    "//span[contains(text(),'Ecommerce')]")
WebUI.waitForElementClickable(menu, timeout)
WebUI.click(menu)

TestObject productPrices = new TestObject().addProperty("xpath", ConditionType.EQUALS,
    "//a[contains(.,'Product Prices')]")
WebUI.waitForElementClickable(productPrices, timeout)
WebUI.click(productPrices)

// ===== SEARCH (CLICK ĐÚNG Ô DƯỚI) =====
import java.util.Arrays

TestObject searchInput = new TestObject().addProperty("xpath", ConditionType.EQUALS,
    "(//input[@placeholder='Search...'])[last()]")

WebUI.waitForElementPresent(searchInput, timeout)
WebUI.scrollToElement(searchInput, timeout)

// FORCE SET VALUE bằng JavaScript
WebUI.executeJavaScript(
    "arguments[0].style.display='block';" +
    "arguments[0].value='encore';" +
    "arguments[0].dispatchEvent(new Event('input'));" +
    "arguments[0].dispatchEvent(new Event('change'));",
    Arrays.asList(WebUI.findWebElement(searchInput, timeout))
)

WebUI.delay(2)



// ===== VERIFY (CASE-INSENSITIVE, BẮT ĐÚNG TD TÊN) =====
boolean found = false

try {
    TestObject productEncore = new TestObject().addProperty('xpath', ConditionType.EQUALS,
        "//table//td[span[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'encore')]"
      + " or contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'encore')]")

    found = WebUI.waitForElementPresent(productEncore, 8, FailureHandling.OPTIONAL)
} catch(Exception e) {}

if(found){
    KeywordUtil.markPassed("PASS - FOUND encore")
}else{
    KeywordUtil.markFailed("FAIL - encore NOT FOUND")
}

WebUI.closeBrowser()
