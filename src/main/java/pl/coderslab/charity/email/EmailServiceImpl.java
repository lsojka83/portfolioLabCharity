package pl.coderslab.charity.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender emailSender;


    @Override
    public void sendSimpleMessage(
        String to, String subject, String text) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("appsender@o2.pl");
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            emailSender.send(message);

    }

    @Override
    public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment) {

        MimeMessage message = emailSender.createMimeMessage();

        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setFrom("appsender@o2.pl");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);

            FileSystemResource file
                    = new FileSystemResource(new File(pathToAttachment));
            helper.addAttachment("<document_name>", file);

            emailSender.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}