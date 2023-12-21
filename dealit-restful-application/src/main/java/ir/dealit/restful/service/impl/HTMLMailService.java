package ir.dealit.restful.service.impl;

import ir.dealit.restful.service.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class HTMLMailService implements MailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private @Email String usernameMail;

    @Override
    @Async
    public void send(@Email String to, String subject, String text) throws MessagingException {
        MimeMessage msg = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true, "UTF-8");
        helper.setFrom(usernameMail);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, true);
        mailSender.send(msg);
    }

    @Override
    @Async
    public void sendAll(List<@Email String> to, String subject, String text) throws MessagingException {
        MimeMessage msg = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true, "UTF-8");
        helper.setFrom(usernameMail);
        helper.setTo((String[]) to.toArray());
        helper.setSubject(subject);
        helper.setText(text, true);
        mailSender.send(msg);
    }
}
