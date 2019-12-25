package com.spammachine.task.pageobjects;

import com.google.common.io.Resources;
import com.spammachine.task.customobjects.Attachment;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MessageEditorPage {

    private final WebDriver driver;

    public MessageEditorPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(name = "to")
    private WebElement toRecepInput;

    @FindBy(className = "mail-Compose-Attach-Button")
    private WebElement attachmentButton;

    @FindBy(css = "button[class*='js-send-button']")
    private WebElement sendButton;

    @FindBy(className = "b-file__text")
    private List<WebElement> attachmentNameElements;

    @FindBy(className = "mail-Done-Title")
    private WebElement confirmation;

    private List<String> getAttachedFileNames() {
        List<String> names = new ArrayList<>();

        for (WebElement element : attachmentNameElements) {
            names.add(element.getText());
        }

        return names;
    }

    private void addFileAttachment(String attachment) throws InterruptedException, AWTException {

        String filename = attachment.split("\\.")[0];

        String path = Resources.getResource("attachments/" + attachment).getPath()
                .replaceAll("^/", "")
                .replaceAll("/", "\\\\");

        attachmentButton.click();

        Thread.sleep(5000);
        StringSelection ss = new StringSelection(path);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

        Robot robot = new Robot();
        //robot.keyPress(KeyEvent.VK_ENTER);
        //robot.keyRelease(KeyEvent.VK_ENTER);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.delay(1000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.delay(100);
        robot.keyRelease(KeyEvent.VK_ENTER);

        List<String> attachedFileNames = getAttachedFileNames();
        assertThat(attachedFileNames).contains(filename);
    }

    public void putRecipientsAndHeader(String recipients, String header) {
        toRecepInput.sendKeys(recipients, Keys.TAB, header);
    }

    public void attachData(Attachment attachment) throws AWTException, InterruptedException {

        switch (attachment.getType()) {
            case LOCALFILE:
                for (String doc : attachment.getData()) {
                    addFileAttachment(doc);
                }
                break;

            default:
                System.out.println("Not implemented");
                break;
        }
    }

    public void sendMessage() {
        sendButton.click();
        assertThat(confirmation.getText()).contains("Письмо отправлено.");
    }
}
