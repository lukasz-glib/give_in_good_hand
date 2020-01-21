package pl.lg.charity.services;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);
}
