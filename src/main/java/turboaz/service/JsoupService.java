package turboaz.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import turboaz.dto.OrderDto;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
@RequiredArgsConstructor
public class JsoupService {

    private final JavaMailSender javaMailSender;

    public void searchForCar(String mail, String url) throws IOException, InterruptedException {


        OrderDto orderDto = new OrderDto();

        Document doc = Jsoup.connect(url).get();

        Elements elementsByClass = doc.getElementsByClass("products-i");

        for (Element element : elementsByClass) {
            Elements date = element.getElementsByClass("products-i__datetime");

            String dateString = date.text();
            if (dateString.contains("bugün") || dateString.contains("сегодня")) {
                String[] s = dateString.split(" ");
                String timesString = s[2];

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                LocalTime desiredTime = LocalTime.parse(timesString, formatter);

                LocalTime now = LocalTime.now();
                Duration elapsedTime;
                elapsedTime = Duration.between(desiredTime, now);
                long minutesElapsed = elapsedTime.getSeconds() / 60;


                if (minutesElapsed <= 5) {

                    Elements links = element.getElementsByClass("products-i__link");
                    Element link = links.get(0);
                    String message = "https://turbo.az" + link.attr("href");

                    SimpleMailMessage msg = new SimpleMailMessage();
                    msg.setTo(mail);
                    msg.setFrom("ellvinmehdixanov@gmail.com");
                    msg.setSubject("Mashin tapildi");
                    msg.setText(message);
                    javaMailSender.send(msg);


                }

            }

        }

    }

}
