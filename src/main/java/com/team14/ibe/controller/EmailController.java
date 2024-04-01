package com.team14.ibe.controller;

import com.team14.ibe.dto.Request.EmailRequest;
import com.team14.ibe.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class to handle email sending functionality.
 */
@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @Value("${email.sender}")
    public String senderEmail;

    @Value("${email.subject}")
    private String emailSubject;

    @Value("${email.body}")
    private String emailBody;

    /**
     * Endpoint to send an email.
     *
     * @param request The recipient email address.
     * @return ResponseEntity indicating the status of the email sending operation.
     */
    @PostMapping("/sendemail")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest request) {
        emailService.sendEmail(senderEmail, request.getEmail(), request.getRoomid(), request.getPropertyId(), emailSubject, emailBody);
        return new ResponseEntity<>("Email sent successfully!", HttpStatus.OK);
    }
}
