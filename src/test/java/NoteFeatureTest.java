import com.google.common.base.Verify;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class NoteFeatureTest {
    /**
     * test all features of Note Feature
     **/
    private static final String NOTE_TAB_CLASSNAME = "kb-comments";
    private static final String NOTE_FEATURE_BUTTON_TEXT = "Note";
    private static final int NOTE_FEATURE_VISIBLE_BUTTONS_BEFORE_EDIT = 1;
    private static final int NOTE_FEATURE_VISIBLE_BUTTONS_AFTER_EDIT = 2;
    private static final String NOTE_FEATURE_TEXTAREA_MAXLENGTH_ATTRIBUTE = "maxlength";
    private static final int NOTE_FEATURE_TEXTAREA_MAXLENGTH_NUMBER = 512;
    private static final String NOTE_FEATURE_TEXTAREA_MAXLENGTH_SHOW_LEFT_CLASSNAME = "maxlength";
    private static final int NOTE_FEATURE_TEXTAREA_MAXLENGTH_NUMBER_SUBTRACTED = 503;
    private static final String SPLIT_BY_SPACE = "\\s+";
    private static final String NOTE_FEATURE_TEXTAREA_SENDKEYS = "123456789";
    private static final String NOTE_FEATURE_MODAL_DIALOG_BODY_CLASSNAME = "modal-body";
    private static final String NOTE_FEATURE_MODAL_DIALOG_CONTENT_CLASSNAME = "modal-content";
    private static final String NOTE_FEATURE_MODAL_DIALOG_BODY_TEXT = "This action is not available in the demo";
    private static final String NOTE_FEATURE_MODAL_DIALOG_BODY_LINK_CSSSELECTOR = "a";
    private static final String NOTE_FEATURE_MODAL_DIALOG_BODY_LINK_URL = "https://www.runecast.biz/";
    private static final String NOTE_FEATURE_MODAL_DIALOG_BODY_BUTTON_TEXT = "Close";
    public WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, 20);

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
    public void NoteFeature_ifNoteFeatureExistsReturnIt_featureExists() {
        Verify.verify(NoteFeature.ifNoteFeatureExistsReturnIt(driver).getText().contains(NOTE_FEATURE_BUTTON_TEXT));
    }

    @Test(dependsOnMethods = "NoteFeature_ifNoteFeatureExistsReturnIt_featureExists")
    public void NoteFeature_clickOnNoteFeature_newElements() {
        NoteFeature.clickOnNoteFeature(driver, NoteFeature.ifNoteFeatureExistsReturnIt(driver));
        ElementHandling.waitForElementToBeVisible(driver, driver.findElement(By.className(NOTE_TAB_CLASSNAME)));
        Verify.verify(driver.findElement(By.className(NOTE_TAB_CLASSNAME)).isDisplayed());
    }

    @Test(dependsOnMethods = "NoteFeature_clickOnNoteFeature_newElements")
    public void NoteFeature_noteFeatureTextArea_elementExists() {
        NoteFeature.clickOnNoteFeature(driver, NoteFeature.ifNoteFeatureExistsReturnIt(driver));
        Verify.verify(NoteFeature.noteFeatureTextArea(driver).isDisplayed());
    }

    @Test(dependsOnMethods = "NoteFeature_clickOnNoteFeature_newElements")
    public void NoteFeature_noteFeatureVisibleButtons_oneElementIsDisplayed() {
        NoteFeature.clickOnNoteFeature(driver, NoteFeature.ifNoteFeatureExistsReturnIt(driver));
        Assert.assertEquals(NoteFeature.noteFeatureVisibleButtons(driver).size(), NOTE_FEATURE_VISIBLE_BUTTONS_BEFORE_EDIT);
    }

    @Test(dependsOnMethods = "NoteFeature_noteFeatureVisibleButtons_oneElementIsDisplayed")
    public void NoteFeature_clickOnEditButton_newButtonsAreDisplayed() {
        NoteFeature.clickOnNoteFeature(driver, NoteFeature.ifNoteFeatureExistsReturnIt(driver));
        NoteFeature.clickOnEditButton(driver, NoteFeature.noteFeatureVisibleButtons(driver));
        Assert.assertEquals(NoteFeature.noteFeatureVisibleButtons(driver).size(), NOTE_FEATURE_VISIBLE_BUTTONS_AFTER_EDIT);
    }

    @Test(dependsOnMethods = "NoteFeature_clickOnEditButton_newButtonsAreDisplayed")
    public void NoteFeature_noteFeatureTextArea_maximalLengthSetUp() {
        NoteFeature.clickOnNoteFeature(driver, NoteFeature.ifNoteFeatureExistsReturnIt(driver));
        NoteFeature.clickOnEditButton(driver, NoteFeature.noteFeatureVisibleButtons(driver));
        Assert.assertEquals(Integer.parseInt(NoteFeature.noteFeatureTextArea(driver).getAttribute(NOTE_FEATURE_TEXTAREA_MAXLENGTH_ATTRIBUTE)), NOTE_FEATURE_TEXTAREA_MAXLENGTH_NUMBER);
    }

    @Test(dependsOnMethods = "NoteFeature_clickOnEditButton_newButtonsAreDisplayed")
    public void NoteFeature_noteFeatureTextArea_charactersLeftShown() {
        NoteFeature.clickOnNoteFeature(driver, NoteFeature.ifNoteFeatureExistsReturnIt(driver));
        NoteFeature.clickOnEditButton(driver, NoteFeature.noteFeatureVisibleButtons(driver));
        Assert.assertEquals(Integer.parseInt(driver.findElement(By.className(NOTE_FEATURE_TEXTAREA_MAXLENGTH_SHOW_LEFT_CLASSNAME)).getText().split(SPLIT_BY_SPACE)[0]), NOTE_FEATURE_TEXTAREA_MAXLENGTH_NUMBER);
    }

    @Test(dependsOnMethods = "NoteFeature_noteFeatureTextArea_charactersLeftShown")
    public void NoteFeature_noteFeatureTextArea_charactersLeftSubtracting() {
        NoteFeature.clickOnNoteFeature(driver, NoteFeature.ifNoteFeatureExistsReturnIt(driver));
        NoteFeature.clickOnEditButton(driver, NoteFeature.noteFeatureVisibleButtons(driver));
        NoteFeature.noteFeatureTextArea(driver).sendKeys(NOTE_FEATURE_TEXTAREA_SENDKEYS); //9 chars
        Assert.assertEquals(Integer.parseInt(driver.findElement(By.className(NOTE_FEATURE_TEXTAREA_MAXLENGTH_SHOW_LEFT_CLASSNAME)).getText().split(SPLIT_BY_SPACE)[0]), NOTE_FEATURE_TEXTAREA_MAXLENGTH_NUMBER_SUBTRACTED);
    }

    @Test(dependsOnMethods = "NoteFeature_clickOnEditButton_newButtonsAreDisplayed")
    public void NoteFeature_clickOnCancelButton_onlyOneButtonIsDisplayed() {
        NoteFeature.clickOnNoteFeature(driver, NoteFeature.ifNoteFeatureExistsReturnIt(driver));
        NoteFeature.clickOnEditButton(driver, NoteFeature.noteFeatureVisibleButtons(driver));
        NoteFeature.clickOnCancelButton(driver, NoteFeature.noteFeatureVisibleButtons(driver));
        Assert.assertEquals(NoteFeature.noteFeatureVisibleButtons(driver).size(), 1);
    }

    @Test(dependsOnMethods = "NoteFeature_clickOnEditButton_newButtonsAreDisplayed")
    public void NoteFeature_clickOnCancelButton_noteFeatureTextArea_textIsBackToDefault() {
        NoteFeature.clickOnNoteFeature(driver, NoteFeature.ifNoteFeatureExistsReturnIt(driver));
        String beforeEdit = (NoteFeature.noteFeatureTextArea(driver).getText());
        NoteFeature.clickOnEditButton(driver, NoteFeature.noteFeatureVisibleButtons(driver));
        NoteFeature.noteFeatureTextArea(driver).sendKeys(NOTE_FEATURE_TEXTAREA_SENDKEYS); //9 chars
        NoteFeature.clickOnCancelButton(driver, NoteFeature.noteFeatureVisibleButtons(driver));
        String afterEdit = (NoteFeature.noteFeatureTextArea(driver).getText());
        Assert.assertEquals(beforeEdit, afterEdit);
    }

    @Test(dependsOnMethods = "NoteFeature_clickOnEditButton_newButtonsAreDisplayed")
    public void NoteFeature_clickOnSaveButton_modalDialogIsDisplayed() {
        NoteFeature.clickOnNoteFeature(driver, NoteFeature.ifNoteFeatureExistsReturnIt(driver));
        NoteFeature.clickOnEditButton(driver, NoteFeature.noteFeatureVisibleButtons(driver));
        NoteFeature.clickOnSaveButton(driver, NoteFeature.noteFeatureVisibleButtons(driver));
        wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.className(NOTE_FEATURE_MODAL_DIALOG_BODY_CLASSNAME)), (NOTE_FEATURE_MODAL_DIALOG_BODY_TEXT)));
        Verify.verify(driver.findElement(By.className(NOTE_FEATURE_MODAL_DIALOG_BODY_CLASSNAME)).getText().contains(NOTE_FEATURE_MODAL_DIALOG_BODY_TEXT));
    }

    @Test(dependsOnMethods = "NoteFeature_clickOnSaveButton_modalDialogIsDisplayed")
    public void NoteFeature_clickOnSaveButton_modalDialogHasLink() {
        WebElement modalDialogBody = null;
        NoteFeature.clickOnNoteFeature(driver, NoteFeature.ifNoteFeatureExistsReturnIt(driver));
        NoteFeature.clickOnEditButton(driver, NoteFeature.noteFeatureVisibleButtons(driver));
        NoteFeature.clickOnSaveButton(driver, NoteFeature.noteFeatureVisibleButtons(driver));
        wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.className(NOTE_FEATURE_MODAL_DIALOG_BODY_CLASSNAME)), (NOTE_FEATURE_BUTTON_TEXT)));
        for (WebElement element :
                driver.findElements(By.className(NOTE_FEATURE_MODAL_DIALOG_CONTENT_CLASSNAME))) {
            modalDialogBody = element.findElement(By.className(NOTE_FEATURE_MODAL_DIALOG_BODY_CLASSNAME));
            break;
        }

        if (modalDialogBody != null) {
            for (WebElement element :
                    modalDialogBody.findElements(By.cssSelector(NOTE_FEATURE_MODAL_DIALOG_BODY_LINK_CSSSELECTOR))) {
                element.click();
            }
        }
        Verify.verify(driver.getCurrentUrl().contains(NOTE_FEATURE_MODAL_DIALOG_BODY_LINK_URL));
    }

    @Test(dependsOnMethods = "NoteFeature_clickOnSaveButton_modalDialogIsDisplayed")
    public void NoteFeature_clickOnSaveButton_modalDialogHasCloseButton() {
        WebElement modalDialogButton = null;
        NoteFeature.clickOnNoteFeature(driver, NoteFeature.ifNoteFeatureExistsReturnIt(driver));
        NoteFeature.clickOnEditButton(driver, NoteFeature.noteFeatureVisibleButtons(driver));
        NoteFeature.clickOnSaveButton(driver, NoteFeature.noteFeatureVisibleButtons(driver));
        wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.className(NOTE_FEATURE_MODAL_DIALOG_BODY_CLASSNAME)), (NOTE_FEATURE_MODAL_DIALOG_BODY_TEXT)));
        for (WebElement element :
                driver.findElements(By.className(NOTE_FEATURE_MODAL_DIALOG_CONTENT_CLASSNAME))) {
            modalDialogButton = element.findElement(By.partialLinkText(NOTE_FEATURE_MODAL_DIALOG_BODY_BUTTON_TEXT));
            break;
        }
        ElementHandling.clickOnButton(driver, modalDialogButton);
        Verify.verify(!driver.findElement(By.className(NOTE_FEATURE_MODAL_DIALOG_BODY_CLASSNAME)).getText().contains(NOTE_FEATURE_MODAL_DIALOG_BODY_TEXT));
    }

    @Test(dependsOnMethods = "NoteFeature_clickOnSaveButton_modalDialogHasCloseButton")
    public void NoteFeature_clickOnSaveButton_noteFeatureTextArea_textIsBackToDefault() {
        WebElement modalDialogButton = null;
        NoteFeature.clickOnNoteFeature(driver, NoteFeature.ifNoteFeatureExistsReturnIt(driver));
        String beforeEdit = (NoteFeature.noteFeatureTextArea(driver).getText());
        NoteFeature.clickOnEditButton(driver, NoteFeature.noteFeatureVisibleButtons(driver));
        NoteFeature.noteFeatureTextArea(driver).sendKeys(NOTE_FEATURE_TEXTAREA_SENDKEYS); //9 chars
        NoteFeature.clickOnSaveButton(driver, NoteFeature.noteFeatureVisibleButtons(driver));
        wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.className(NOTE_FEATURE_MODAL_DIALOG_BODY_CLASSNAME)), (NOTE_FEATURE_MODAL_DIALOG_BODY_TEXT)));
        for (WebElement element :
                driver.findElements(By.className(NOTE_FEATURE_MODAL_DIALOG_CONTENT_CLASSNAME))) {
            modalDialogButton = element.findElement(By.partialLinkText(NOTE_FEATURE_MODAL_DIALOG_BODY_BUTTON_TEXT));
            break;
        }
        ElementHandling.clickOnButton(driver, modalDialogButton);
        String afterEdit = (NoteFeature.noteFeatureTextArea(driver).getText());
        Assert.assertEquals(beforeEdit, afterEdit);
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }
}