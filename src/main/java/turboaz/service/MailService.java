package turboaz.service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender javaMailSender;

    @Scheduled(fixedRate = 600000)

    public void mailSender() throws MessagingException, IOException {
        sendEmail();
        //       sendEmailWithAttachment();
        log.info("Email sent successfully!");
    }

    void sendEmail() {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("elvinmehdikhanov@gmail.com");
        msg.setFrom("ellvinmehdixanov@gmail.com");
        msg.setSubject("Testing from Spring Boot");
        msg.setText("Salam. necəsən? \n Spring Boot Email");
        javaMailSender.send(msg);
    }
}