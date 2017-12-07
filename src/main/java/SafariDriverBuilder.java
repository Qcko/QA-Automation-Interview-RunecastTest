import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.safari.SafariDriver;

public class SafariDriverBuilder {
    /** Builds Safari driver **/

public static WebDriver inSafari() {
    try {
        WebDriver driver = new SafariDriver();
        return driver;
    } catch (IllegalStateException e) {
        System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage() + "\n !!Path to SafariDriver is not set in system variables!!");
    }
    catch (WebDriverException e) {
        System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage() + "\n !!Path to Safari is not set in system variables!!");
    }
    return null;
}
}
