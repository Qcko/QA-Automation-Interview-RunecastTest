import org.openqa.selenium.WebDriver;

public class BrowserSelection {
    /** lets you choose which browser you want to run it in **/
    public static WebDriver getBrowser(char whichBrowser) {
        switch (whichBrowser) {
            case 'F':
                System.out.println("Firefox chosen");
                return FirefoxDriverBuilder.inFirefox();
            case 'f':
                System.out.println("Firefox chosen");
                return FirefoxDriverBuilder.inFirefox();
            case 'C':
                System.out.println("Chrome chosen");
                return ChromeDriveBuilder.inChrome();
            case 'c':
                System.out.println("Chrome chosen");
                return ChromeDriveBuilder.inChrome();
            case 'S':
                System.out.println("Safari chosen");
                return SafariDriverBuilder.inSafari();
            case 's':
                System.out.println("Safari chosen");
                return SafariDriverBuilder.inSafari();
            case 'I':
                System.out.println("Internet Explorer chosen");
                return IEDriverBuilder.inIE();
            case 'i':
                System.out.println("Internet Explorer chosen");
                return IEDriverBuilder.inIE();
        }
        System.out.println("Chosen browser not recognized");
        return null;
    }
}
