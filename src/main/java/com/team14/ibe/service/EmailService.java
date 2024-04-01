package com.team14.ibe.service;

import com.azure.communication.email.EmailClient;
import com.azure.communication.email.models.EmailMessage;
import com.azure.communication.email.models.EmailSendResult;
import com.azure.core.util.polling.PollResponse;
import com.azure.core.util.polling.SyncPoller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {

    @Autowired
    private EmailClient emailClient;

    public void sendEmail(String sender, String recipient, String roomid, String propertyId, String subject, String body) {
        String feedbackLink = body + "?roomid=" + roomid + "&propertyid=" + propertyId;

        EmailMessage message = new EmailMessage().setSenderAddress(sender).setToRecipients(recipient).setSubject(subject).setBodyPlainText(feedbackLink);

        SyncPoller<EmailSendResult, EmailSendResult> poller = emailClient.beginSend(message);
        PollResponse<EmailSendResult> response = poller.waitForCompletion();

        log.info("Operation Id: {}", response.getValue().getId());
    }

}
