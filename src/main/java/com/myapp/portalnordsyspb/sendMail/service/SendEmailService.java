package com.myapp.portalnordsyspb.sendMail.service;

import com.myapp.portalnordsyspb.sendMail.dto.MailRequestDto;
import com.myapp.portalnordsyspb.trainingStatistics.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.io.UnsupportedEncodingException;

@Service
@RequiredArgsConstructor
public class SendEmailService {

    private final JavaMailSender mailSender;
    private final PersonRepository personRepository;

    @Value("$(spring.mail.username)")
    private String fromEmailId;

    public void sendEmail(String to, String subject, String body, String senderName)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body, true); // Установите true, если содержимое письма в формате HTML
        helper.setFrom("noreply@nordsy.spb.ru", senderName); // Укажите email и имя отправителя

        mailSender.send(mimeMessage);
    }

    public void sendEmailToBoss(MailRequestDto mailRequestDto) {
    }
}
