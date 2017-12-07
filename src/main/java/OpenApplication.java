import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OpenApplication {
    /**
     * opens application page and logs in
     **/
    private static final String START_PAGE = "https://demo.runecast.biz/rc2/login";
    private static final String USERNAME = "rcuser";
    private static final String PASSWORD = "Runecast!";
    private static final String FIELD_USERNAME = "j_username";
    private static final String FIELD_PASSWORD = "j_password";


    public static void goToPage(WebDriver driver) {
                driver.get(START_PAGE);
    }

    public static void logIn(WebDriver driver) {
        WebElement userField = driver.findElement(By.name(FIELD_USERNAME));
        WebElement passwordField = driver.findElement(By.name(FIELD_PASSWORD));
        ElementHandling.waitForElementToBeVisible(driver, userField);
        ElementHandling.waitForElementToBeVisible(driver, passwordField);
        userField.sendKeys(USERNAME);
        passwordField.sendKeys(PASSWORD);
        passwordField.submit();
    }
}
