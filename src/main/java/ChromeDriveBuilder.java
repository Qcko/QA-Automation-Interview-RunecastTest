import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriveBuilder {
    /** Builds chrome driver **/

    public static WebDriver inChrome() {
        try {
            WebDriver driver = new ChromeDriver();
            return driver;
        } catch (IllegalStateException e) {
            System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage() + "\n !!Path to ChromeDriver is not set in system variables!!");
        }
        catch (WebDriverException e) {
            System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage() + "\n !!Path to Chrome is not set in system variables!!");
        }
        return null;
    }
}
