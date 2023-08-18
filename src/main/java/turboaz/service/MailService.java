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

//    void sendEmailWithAttachment() throws MessagingException, IOException {
//        MimeMessage msg = javaMailSender.createMimeMessage();
//
//        // true = multipart message
//        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
//        helper.setTo("elvinmehdikhanov@gmail.com");
//        helper.setFrom("ellvinmehdixanov@gmail.com");
//        helper.setSubject("Testing from Spring Boot");
//
//        // default = text/plain
//        //helper.setText("Check attachment for image!");
//
//        // true = text/html
//        helper.setText("<h1>Check attachment for image!</h1>", true);
//
//        //FileSystemResource file = new FileSystemResource(new File("classpath:android.png"));
//
//        //Resource resource = new ClassPathResource("android.png");
//        //InputStream input = resource.getInputStream();
//
//        //ResourceUtils.getFile("classpath:android.png");
//
//        helper.addAttachment("my_photo.png", new ClassPathResource("android.png"));
//
//        javaMailSender.send(msg);
//    }
}