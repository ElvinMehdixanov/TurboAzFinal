package turboaz.service.Price;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import turboaz.dto.SearchCarDto;
import turboaz.entity.SearchCarEntity;
import turboaz.repository.SearchCarRepository;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class PriceJsoupService {

    private final JavaMailSender javaMailSender;
    private final SearchCarRepository carRepository;

    public void searchPriceForCar(SearchCarDto searchCarDto) throws IOException, InterruptedException {

        String mail = searchCarDto.getMail();
        String url = searchCarDto.getUrl();


        SearchCarEntity byMailAndUrl = carRepository.findByMailAndUrl(mail, url);


        Document doc = Jsoup.connect(url).get();
        Elements elementsByClass = doc.getElementsByClass("product-price");

        Element element = elementsByClass.get(0);
        System.out.println(element.text());

        String price = element.getElementsByClass("product-price__i").text();

        String priceToDb;
        if (byMailAndUrl == null) {
            priceToDb = price;
            SearchCarEntity searchCarEntity = SearchCarEntity.builder()
                    .mail(mail)
                    .url(url)
                    .productPrice(price)
                    .build();

            carRepository.save(searchCarEntity);

        } else {
            priceToDb = byMailAndUrl.getProductPrice();
        }

        if (!price.isEmpty() && !price.equals(priceToDb)) {

            byMailAndUrl.setProductPrice(price);
            carRepository.save(byMailAndUrl);

            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(mail);
            msg.setFrom("ellvinmehdixanov@gmail.com");
            msg.setSubject("Avtomobilin qiymeti dustu");
            msg.setText("Avtomobilin yeni qiymeti: " + price);
            this.javaMailSender.send(msg);
        }
    }

}


