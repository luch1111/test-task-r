package com.spammachine.task;

import com.spammachine.task.customobjects.Attachment;
import com.spammachine.task.steps.UserSteps;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.Qualifier;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.awt.*;
import java.io.IOException;
import java.time.Instant;

@RunWith(SerenityParameterizedRunner.class)
@UseTestDataFrom(value = "testdata/send-message.csv")
public class MessagesRelatedTest extends AbstractTest {

    private String header;
    private String attachmentRaw;
    private String recipient;

    public void setHeader(String header) {
        this.header = header + " " + Instant.now();
    }

    public void setAttachmentRaw(String attachmentRaw) {
        this.attachmentRaw = attachmentRaw.replaceAll("\"", "");
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    @Qualifier
    public String qualifier() {
        return header + "=>" + recipient + "=>" + attachmentRaw;
    }

    @Steps
    private UserSteps userSteps;

    @Test
    public void userSendsMessage() throws InterruptedException, AWTException, IOException {

        //prepare test data
        Attachment[] attachments = null;

        try {
            attachments = objectMapper.readValue(attachmentRaw, Attachment[].class);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        //given
        userSteps.userOpensPageInBrowser(driver, postUrl);
        userSteps.userLogsIn(user, psw);

        //when
        userSteps.userOpensNewMessagePage();
        userSteps.userFulfilsRecipientsFieldAndHeader(recipient, header);

        if (attachments.length > 0) {
            for (Attachment attachment : attachments) {
                userSteps.userAttachesDocument(attachment);
            }
        }

        userSteps.userClicksSend();

        //then
        userSteps.userSeeMessageInSentFolder(header);
    }
}
