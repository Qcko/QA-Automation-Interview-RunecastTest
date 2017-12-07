import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ElementHandling {


    public static void moveToElement(WebDriver driver, WebElement element){
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
    }

    public static void waitForElementToBeVisible(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForElementToBeClickable(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        waitForElementToBeVisible(driver, element);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

public static void clickOnButton(WebDriver driver, WebElement element) {
        try {
            waitForElementToBeClickable(driver, element);
            moveToElement(driver, element);
            element.click();
        } catch (Exception e) {
            System.out.println(e);
        }
}


public static void findClickableButton(WebDriver driver, List<WebElement> elements) {
boolean wasThereButton = false;

    for (WebElement button :
            elements) {
        if (button.isDisplayed()) {
            clickOnButton(driver, button);
            wasThereButton = true;
            break;
        }
    }

    if (!wasThereButton){
        System.out.println("No clickable button found");
    }

}

}
