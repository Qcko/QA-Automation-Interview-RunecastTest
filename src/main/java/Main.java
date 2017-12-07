import org.openqa.selenium.WebDriver;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Which browser should i use? Chrome, Safari, Firefox or Internet Explorer");
        Scanner keyboardInput = new Scanner(System.in);
        char whichBrowser = keyboardInput.nextLine().charAt(0);
        keyboardInput.close();

        WebDriver driver = BrowserSelection.getBrowser(whichBrowser);
        OpenApplication.goToPage(driver);
        OpenApplication.logIn(driver);

        ApplicationPage.closeDemoModalDialog(driver);
        ApplicationPage.navigateToDISA(driver);
        ApplicationPage.closeSTIGComplianceModalDialog(driver);

        NoteFeature.clickOnNoteFeature(driver, NoteFeature.ifNoteFeatureExistsReturnIt(driver));
        NoteFeature.noteFeatureTextArea(driver); //test text
        NoteFeature.clickOnEditButton(driver, NoteFeature.noteFeatureVisibleButtons(driver));

    }
}