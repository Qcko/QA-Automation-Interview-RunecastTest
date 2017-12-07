import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ApplicationPage {
    /**
     * handles all actions on application page
     **/
        //todo Unit test for this

    private static final String DEMO_MODALDIALOG_CLASSNAME = "poplist";
    private static final String MENU_BUTTON_CLASSNAME = "responsive-toggler";
    private static final String SECURITY_BUTTON_PARTIALTEXT = "Security";
    private static final String DISA_BUTTON_PARTIALTEXT = "DISA";
    private static final String STIGCOMPLIANCE_MODALDIALOG_ID = "settings_page";
    private static final String STIGCOMPLIANCE_MODALDIALOG_TEXT = "STIG compliance";
    private static final String STIGCOMPLIANCE_MODALDIALOG_BUTTON_XPATH = "//*[contains(text(), 'Get Started')]";

public static void closeDemoModalDialog(WebDriver driver) {
    ElementHandling.waitForElementToBeVisible(driver, driver.findElement(By.className(DEMO_MODALDIALOG_CLASSNAME)));
    ElementHandling.findClickableButton(driver, driver.findElements(By.className(DEMO_MODALDIALOG_CLASSNAME)));
}

public static void navigateToDISA(WebDriver driver) {
    WebElement buttonMenu = driver.findElement(By.className(MENU_BUTTON_CLASSNAME));
    if (buttonMenu.isDisplayed()) {
        ElementHandling.clickOnButton(driver, buttonMenu); //when width of browser is small, menu packs in menu button
    }

    WebElement buttonSec = driver.findElement(By.partialLinkText(SECURITY_BUTTON_PARTIALTEXT));
    ElementHandling.clickOnButton(driver, buttonSec);

    WebElement buttonDISA = driver.findElement(By.partialLinkText(DISA_BUTTON_PARTIALTEXT));
    ElementHandling.clickOnButton(driver, buttonDISA);
}

public static void closeSTIGComplianceModalDialog(WebDriver driver) {
    WebDriverWait wait = new WebDriverWait(driver, 20);

    wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.id(STIGCOMPLIANCE_MODALDIALOG_ID)), (STIGCOMPLIANCE_MODALDIALOG_TEXT)));
    ElementHandling.findClickableButton(driver, driver.findElements(By.xpath(STIGCOMPLIANCE_MODALDIALOG_BUTTON_XPATH)));
}
}
