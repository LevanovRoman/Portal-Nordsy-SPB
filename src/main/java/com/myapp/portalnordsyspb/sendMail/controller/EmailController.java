package com.myapp.portalnordsyspb.sendMail.controller;

import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.MessageDto;
import com.myapp.portalnordsyspb.sendMail.dto.MailRequestDto;
import com.myapp.portalnordsyspb.sendMail.dto.MailResponseDto;
import com.myapp.portalnordsyspb.sendMail.service.SendEmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("api/send-email")
@RequiredArgsConstructor
public class EmailController {

    private final SendEmailService emailService;

    @GetMapping("/test")
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

    @GetMapping("/{tabnumber}")
    public ResponseEntity<MailResponseDto> getPersonData(@PathVariable("tabnumber") String tabnumber){
        return ResponseEntity.ok(emailService.getPersonData(tabnumber));
    }

    @PostMapping
    public ResponseEntity<MessageDto> sendEmailToBoss(@RequestBody MailRequestDto mailRequestDto){
        emailService.sendEmailToBoss(mailRequestDto);
        return ResponseEntity.ok(new MessageDto("Email has been sent successfully"));
    }
}
