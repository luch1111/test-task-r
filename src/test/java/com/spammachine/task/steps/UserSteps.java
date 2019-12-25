package com.spammachine.task.steps;

import com.spammachine.task.customobjects.Attachment;
import com.spammachine.task.pageobjects.MessageEditorPage;
import com.spammachine.task.pageobjects.PostMainPage;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.WebDriver;

import java.awt.*;

import static org.assertj.core.api.Assertions.assertThat;

public class UserSteps {

    private static PostMainPage postMainPage;
    private static MessageEditorPage messageEditorPage;
    private static WebDriver webDriver;

    @Step
    public void userOpensPageInBrowser(WebDriver driver, String url) {
        webDriver = driver;
        driver.get(url);
    }

    @Step
    public void userLogsIn(String user, String psw) {
        postMainPage = new PostMainPage(webDriver);
        postMainPage.login(user, psw);
    }

    @Step
    public void userOpensNewMessagePage() {
        postMainPage.startNewMessage();
        messageEditorPage = new MessageEditorPage(webDriver);
    }

    @Step
    public void userFulfilsRecipientsFieldAndHeader(String recipients, String header) {
        messageEditorPage.putRecipientsAndHeader(recipients, header);
    }

    @Step
    public void userAttachesDocument(Attachment attachment) throws InterruptedException, AWTException {
        messageEditorPage.attachData(attachment);
    }

    @Step
    public void userClicksSend() {
        messageEditorPage.sendMessage();
    }

    @Step
    public void userSeeMessageInSentFolder(String header) {
        postMainPage.openSentMessages();
        assertThat(postMainPage.getMessages().get(0).getText()).contains(header);
    }
}
