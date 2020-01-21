package pl.lg.charity.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import pl.lg.charity.services.EmailService;

import javax.transaction.Transactional;

@Service
@Transactional
@Slf4j
public class DefaultEmailService implements EmailService {

    private final JavaMailSender emailSender;

    public DefaultEmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            emailSender.send(message);
            log.debug("Email was sending: {}", message);
        } catch (MailException exception) {
            exception.printStackTrace();
        }
    }
}
