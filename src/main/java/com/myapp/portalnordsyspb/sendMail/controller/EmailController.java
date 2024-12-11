package com.myapp.portalnordsyspb.sendMail.controller;

import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.MessageDto;
import com.myapp.portalnordsyspb.sendMail.dto.MailRequestDto;
import com.myapp.portalnordsyspb.sendMail.service.SendEmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController("/send-email")
@RequiredArgsConstructor
public class EmailController {

    private final SendEmailService emailService;

    @GetMapping
    public String sendEmail()
            throws MessagingException, UnsupportedEncodingException {
        emailService.sendEmail(
                "levanovroman2016@yandex.ru",
                "Test Subject",
                "Hello, this is a test email!",
                "PORTAL"
        );
        return "Email sent successfully!";
    }

    @PostMapping
    public ResponseEntity<MessageDto> sendEmailToBoss(@RequestBody MailRequestDto mailRequestDto){
        emailService.sendEmailToBoss(mailRequestDto);
        return ResponseEntity.ok(new MessageDto("Email has been sent successfully"));
    }
}
