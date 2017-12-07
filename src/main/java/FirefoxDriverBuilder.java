import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxDriverBuilder {
    /** Builds firefox driver **/

    public static WebDriver inFirefox() {
        try {
            WebDriver driver = new FirefoxDriver();
            return driver;
        } catch (IllegalStateException e) {
            System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage() + "\n !!Path to FirefoxDriver is not set in system variables!!");
        }
        catch (WebDriverException e) {
            System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage() + "\n !!Path to Firefox is not set in system variables!!");
        }
        return null;
    }
}
