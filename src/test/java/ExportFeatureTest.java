import com.google.common.base.Verify;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ExportFeatureTest {
    /** test all feature of Export Feature **/
    public WebDriver driver;

    @Parameters("browser")
    @BeforeMethod
    public void setUp(String browser) {
        char whichBrowser = browser.split("")[0].charAt(0);
        driver = BrowserSelection.getBrowser(whichBrowser);
        OpenApplication.goToPage(driver);
        OpenApplication.logIn(driver);

        ApplicationPage.closeDemoModalDialog(driver);
        ApplicationPage.navigateToDISA(driver);
        ApplicationPage.closeSTIGComplianceModalDialog(driver);
    }

    @Test
    public void testIfExportFeatureExistsReturnIt() {
        Verify.verify(false);
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }
}