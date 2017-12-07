import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class IEDriverBuilder {
    /** Builds Internet Explorer driver **/

public static WebDriver inIE() {
    try {
        WebDriver driver = new InternetExplorerDriver();
        return driver;
    } catch (IllegalStateException e) {
        System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage() + "\n !!Path to IEDriver is not set in system variables!!");
    }
    catch (WebDriverException e) {
        System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage() + "\n !!Path to Internet Explorer is not set in system variables!!");
    }
    return null;
}
}
