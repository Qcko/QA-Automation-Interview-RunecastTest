import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class NoteFeature {
    private static final String PAGE_BODY_ID = "grid";
    private static final String TR_ELEMENTS_TAGNAME = "tr";
    private static final String DETAILS_ELEMENTS_CLASSNAME = "details";
    private static final String TD_ELEMENTS_TAGNAME = "td";
    private static final String LI_ELEMENTS_TAGNAME = "li";
    private static final String LI_ELEMENTS_TEXT = "Note";
    private static final String NOTE_TAB_CLASSNAME = "kb-comments";
    private static final String NOTE_TAB_TEXTAREA_TAGNAME = "textarea";
    private static final String NOTE_TAB_BUTTON_TAGNAME = "button";
    private static final String NOTE_TAB_BUTTON_EDIT_TEXT = "Edit";
    private static final String NOTE_TAB_BUTTON_SAVE_TEXT = "Save";
    private static final String NOTE_TAB_BUTTON_CANCEL_TEXT = "Cancel";

    public static WebElement ifNoteFeatureExistsReturnIt(WebDriver driver) {
        List<WebElement> elementsWithTr = driver.findElement(By.id(PAGE_BODY_ID)).findElements(By.tagName(TR_ELEMENTS_TAGNAME));
        if (elementsWithTr.size() == 0) {
            System.out.println("There is problem with the code");
        } else {
            elementsWithTr.remove(0); //removes dummy tr
            if (elementsWithTr.size() == 0) {
                System.out.println("No vulnerabilities in the cloud");
            } else {
                WebElement issue = elementsWithTr.get(0);
                ElementHandling.clickOnButton(driver, issue); //expands first issue

                //finds if there is Note button
                List<WebElement> elementsWithDetails = driver.findElements(By.className(DETAILS_ELEMENTS_CLASSNAME));
                findNote:
                for (WebElement elementWithDetail :
                        elementsWithDetails) {
                    List<WebElement> elementsWithTD = elementWithDetail.findElements(By.tagName(TD_ELEMENTS_TAGNAME));
                    for (WebElement elementWithTD :
                            elementsWithTD) {
                        List<WebElement> elementsWithLi = elementWithTD.findElements(By.tagName(LI_ELEMENTS_TAGNAME));
                        for (WebElement elementWithLi :
                                elementsWithLi) {
                            if (elementWithLi.getText().contains(LI_ELEMENTS_TEXT)) {
                                return elementWithLi;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    public static void clickOnNoteFeature(WebDriver driver, WebElement note) {
        ElementHandling.clickOnButton(driver, note);
    }

    public static WebElement noteFeatureTextArea(WebDriver driver) {
        WebElement noteTab = driver.findElement(By.className(NOTE_TAB_CLASSNAME)); // all elements in note
        ElementHandling.waitForElementToBeVisible(driver, noteTab);
        WebElement noteTextArea = noteTab.findElement(By.tagName(NOTE_TAB_TEXTAREA_TAGNAME));
        return noteTextArea;
    }

    public static List<WebElement> noteFeatureVisibleButtons(WebDriver driver) {
        WebElement noteTab = driver.findElement(By.className(NOTE_TAB_CLASSNAME)); // all elements in note
        ElementHandling.waitForElementToBeVisible(driver, noteTab);
        List<WebElement> noteButtons = noteTab.findElements(By.tagName(NOTE_TAB_BUTTON_TAGNAME));
        List<WebElement> noteDisplayedButtons = new ArrayList<>();
        for (WebElement button :
                noteButtons) {
            if (button.isDisplayed()) {
                noteDisplayedButtons.add(button);
            }
        }
        return noteDisplayedButtons;
    }

    public static void clickOnEditButton(WebDriver driver, List<WebElement> noteDisplayedButtons) {
        for (WebElement button :
                noteDisplayedButtons) {
            if (button.getText().contains(NOTE_TAB_BUTTON_EDIT_TEXT)) {
                ElementHandling.clickOnButton(driver, button);
                break;
            }
        }
    }

    public static void clickOnSaveButton(WebDriver driver, List<WebElement> noteDisplayedButtons) {
        for (WebElement button :
                noteDisplayedButtons) {
            if (button.getText().contains(NOTE_TAB_BUTTON_SAVE_TEXT)) {
                ElementHandling.clickOnButton(driver, button);
                break;
            }
        }
    }

    public static void clickOnCancelButton(WebDriver driver, List<WebElement> noteDisplayedButtons) {
        for (WebElement button :
                noteDisplayedButtons) {
            if (button.getText().contains(NOTE_TAB_BUTTON_CANCEL_TEXT)) {
                ElementHandling.clickOnButton(driver, button);
                break;
            }
        }
    }


}
