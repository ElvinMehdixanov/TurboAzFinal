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
import turboaz.dto.SearchPriceDto;
import turboaz.entity.SearchPriceEntity;
import turboaz.repository.SearchPriceRepository;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class PriceJsoupService {

    private final JavaMailSender javaMailSender;
    private final SearchPriceRepository carRepository;

    public void searchPriceForCar(SearchPriceDto searchPriceDto) throws IOException, InterruptedException {

        String mail = searchPriceDto.getMail();
        String url = searchPriceDto.getUrl();


        SearchPriceEntity byMailAndUrl = carRepository.findByMailAndUrl(mail, url);


        Document doc = Jsoup.connect(url).get();
        Elements elementsByClass = doc.getElementsByClass("product-price");

        Element element = elementsByClass.get(0);
        System.out.println(element.text());

        String price = element.getElementsByClass("product-price__i").text();

        String priceToDb;
        if (byMailAndUrl == null) {
            priceToDb = price;
            SearchPriceEntity searchPriceEntity = SearchPriceEntity.builder()
                    .mail(mail)
                    .url(url)
                    .productPrice(price)
                    .build();

            carRepository.save(searchPriceEntity);

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
            msg.setText("Avtomobilin yeni qiymeti: " + priceToDb);
            this.javaMailSender.send(msg);
        }
    }

}


