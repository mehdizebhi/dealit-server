package ir.dealit.restful.service;

import jakarta.mail.MessagingException;
import jakarta.validation.constraints.Email;

import java.util.List;

public interface MailService {
    void send(@Email String to, String subject, String text);
    void sendAll(List<@Email String> to, String subject, String text);
}
