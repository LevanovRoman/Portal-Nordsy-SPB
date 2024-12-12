package com.myapp.portalnordsyspb.sendMail.service;

import com.myapp.portalnordsyspb.exceptions.ObjectNotFoundException;
import com.myapp.portalnordsyspb.sendMail.dto.MailRequestDto;
import com.myapp.portalnordsyspb.trainingStatistics.entity.Person;
import com.myapp.portalnordsyspb.trainingStatistics.repository.PersonRepository;
import com.myapp.portalnordsyspb.trainingStatistics.service.DirectionService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSendException;
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
    private final DirectionService directionService;

    private static final Logger logger = LoggerFactory.getLogger(SendEmailService.class);

    @Value("${MAIL_EMAIL}")
    private String email;

    public void sendEmail(String to, String subject, String body, String senderName)
            throws MessagingException, UnsupportedEncodingException {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true); // Установите true, если содержимое письма в формате HTML
            helper.setFrom("noreply@nordsy.spb.ru", senderName); // Укажите email и имя отправителя

            mailSender.send(mimeMessage);
        } catch (MailSendException e) {
            // Логирование информации об ошибке
            e.getFailedMessages().forEach((msg, ex) -> {
                System.err.println("Failed to send to: " + msg.toString());
                ex.printStackTrace();
            });
        }

    }

    public void sendEmailToBoss(MailRequestDto mailRequestDto) {
        Person person = personRepository.findByTabNumber(mailRequestDto.tabNumber())
                .orElseThrow(() -> new ObjectNotFoundException("Person not found."));

        String message = "Сотрудник " + person.getFullName() +
                ", табельный номер " + person.getTabNumber() +
                ", хочет пройти обучение по направлению "
                + directionService.getDirectionById(mailRequestDto.directionId()).getName();
        try {
            sendEmail(
                    email,
                    "Заявка на обучение",
                    message,
                    "Производственная система");
        } catch (MessagingException | UnsupportedEncodingException e) {
            logger.error("Error when sending an email: ", e);
        }

    }
}
