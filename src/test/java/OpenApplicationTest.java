import com.google.common.base.Verify;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class OpenApplicationTest {
    /** **/
    private static final String START_PAGE = "https://demo.runecast.biz/rc2/login";
    private static final String FIELD_USERNAME = "j_username";
    private static final String FIELD_PASSWORD = "j_password";
    private static final String FIELD_USERNAME_DEAFULT_MSG = "rcuser or username@domain.com";
    private static final String FIELD_PASSWORD_DEAFULT_MSG = "Password";
    private static final String ATTRIBUTE = "placeholder";
    private static final String LOGIN_PAGE = "https://demo.runecast.biz/rc2/index.html";
    public WebDriver driver;

    @Parameters("browser")
    @BeforeClass
    public void setUp(String browser) {
        char whichBrowser = browser.split("")[0].charAt(0);
        driver = BrowserSelection.getBrowser(whichBrowser);
    }

    @Test
    public void openApplication_goToPage_pageLoaded() {
        OpenApplication.goToPage(driver);
        Verify.verify(driver.getCurrentUrl().contains(START_PAGE));
    }



    @Test(dependsOnMethods = "openApplication_goToPage_pageLoaded", groups = "logInForm")
    public void openApplication_logIn_usernameFieldExists() {
        OpenApplication.goToPage(driver);
        WebElement userField = driver.findElement(By.name(FIELD_USERNAME));
        Verify.verify(userField.getAttribute(ATTRIBUTE).contains(FIELD_USERNAME_DEAFULT_MSG));
    }

    @Test(dependsOnMethods = "openApplication_goToPage_pageLoaded", groups = "logInForm")
    public void openApplication_logIn_passwordFieldExists() {
        OpenApplication.goToPage(driver);
        WebElement passwordField = driver.findElement(By.name(FIELD_PASSWORD));
        Verify.verify(passwordField.getAttribute(ATTRIBUTE).contains(FIELD_PASSWORD_DEAFULT_MSG));
    }


    @Test(dependsOnGroups = "logInForm")
    public void openApplication_logIn_successfulLogIn() {
        OpenApplication.goToPage(driver);
        OpenApplication.logIn(driver);
        Verify.verify(driver.getCurrentUrl().contains(LOGIN_PAGE));
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }
}