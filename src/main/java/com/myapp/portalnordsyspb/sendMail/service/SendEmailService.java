package com.myapp.portalnordsyspb.sendMail.service;

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

    @Value("$(spring.mail.username)")
    private String fromEmailId;

    public void sendEmail(String recipient, String body, String subject){

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setFrom("PORTAL");
        simpleMailMessage.setTo(recipient);
        simpleMailMessage.setText(body);
        simpleMailMessage.setSubject(subject);

        mailSender.send(simpleMailMessage);
    }

    public void sendEmailWithSenderName(String to, String subject, String body, String senderName)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body, false); // Установите true, если содержимое письма в формате HTML
        helper.setFrom("your_email@gmail.com", senderName); // Укажите email и имя отправителя

        mailSender.send(mimeMessage);
    }
}
