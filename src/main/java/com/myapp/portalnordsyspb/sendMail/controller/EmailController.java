package com.myapp.portalnordsyspb.sendMail.controller;

import com.myapp.portalnordsyspb.sendMail.service.SendEmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
@RequiredArgsConstructor
public class EmailController {

    private final SendEmailService emailService;

    @GetMapping("/send-email")
    public String sendEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String body) {
        emailService.sendEmail(to, subject, body);
        return "Email sent successfully!";
    }

    @GetMapping("/send-email-second")
    public String sendEmailSec()
            throws MessagingException, UnsupportedEncodingException {
        emailService.sendEmailWithSenderName(
                "levanovroman2016@yandex.ru",
                "Test Subject",
                "Hello, this is a test email!",
                "PORTAL"
        );
        return "Email sent successfully!";
    }
}
