package com.team14.ibe.controller;

import com.team14.ibe.dto.Request.EmailRequest;
import com.team14.ibe.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

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

    @Value("${email.body}")
    private String bookingEmailId;

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

    @GetMapping("/sendotpemail")
    public ResponseEntity<String> sendStringEmail() {
        String message = generateOTP();
        emailService.sendOTPEmail(senderEmail, "sarafsanskar468@gmail.com", emailSubject, message);
        return ResponseEntity.ok(message);
    }

    @PostMapping("/sendbookingemail")
    public ResponseEntity<String> sendBookingEmail(@RequestParam String bookingId) {
        emailService.sendbookingEmail(senderEmail, "sarafsanskar468@gmail.com", emailSubject, bookingId);
        return new ResponseEntity<>("Booking email sent successfully!", HttpStatus.OK);
    }


    private String generateOTP() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }
}
