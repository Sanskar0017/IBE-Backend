package com.team14.ibe.controller;

import com.team14.ibe.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class to handle email sending functionality.
 */
@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @Value("${email.sender}")
    private String senderEmail;

    @Value("${email.subject}")
    private String emailSubject;

    @Value("${email.body}")
    private String emailBody;

    /**
     * Endpoint to send an email.
     * @param email The recipient email address.
     * @return ResponseEntity indicating the status of the email sending operation.
     */
    @PostMapping("/sendemail")
    public ResponseEntity<String> sendEmail(@RequestParam String email) {
        emailService.sendEmail(senderEmail, email, emailSubject, emailBody);
        return new ResponseEntity<>("Email sent successfully!", HttpStatus.OK);
    }
}
