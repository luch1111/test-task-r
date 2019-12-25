package com.spammachine.task.pageobjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PostMainPage {

    private final WebDriver driver;
    private WebDriverWait wait;

    public PostMainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 5);
    }

    @FindBy(className = "HeadBanner-Button-Enter")
    private WebElement enterButton;

    @FindBy(id = "passp-field-login")
    private WebElement userInput;

    @FindBy(id = "passp-field-passwd")
    private WebElement pswdInput;

    @FindBy(className = "mail-User-Name")
    private WebElement userNameHolder;

    @FindBy(className = "mail-ComposeButton-Text")
    private WebElement newLetterButton;

    @FindBy(name = "to")
    private WebElement toRecepInput;

    @FindBy(className = "mail-Compose-Attach-Button")
    private WebElement attachmentButton;

    @FindBy(css = ".ns-view-folders>a")
    private List<WebElement> messageFolders;

    @FindBy(className = "mail-MessageSnippet-Content")
    private List<WebElement> messagesList;

    private WebElement getFolderElement(String name) {

        for (int i = 0; i < messageFolders.size(); i++) {
            if (messageFolders.get(i).getText().equals(name)) {
                return messageFolders.get(i);

            }
        }
        return null;
    }


    public void login(String user, String pswd) {
        enterButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(userInput));
        userInput.sendKeys(user, Keys.ENTER);
        wait.until(ExpectedConditions.elementToBeClickable(pswdInput));
        pswdInput.sendKeys(pswd, Keys.ENTER);

        assertThat(userNameHolder.getText()).contains(user);
    }

    public void startNewMessage() {
        newLetterButton.click();
    }

    public void openSentMessages() {
        WebElement element = getFolderElement("Отправленные");
        assertThat(element).isNotNull();
        element.click();
    }

    public List<WebElement> getMessages() {
        return messagesList;
    }
}
