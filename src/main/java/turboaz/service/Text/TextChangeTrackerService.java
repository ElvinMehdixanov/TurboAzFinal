package turboaz.service.Text;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import turboaz.dto.SearchTextChangeDto;
import turboaz.entity.SearchTextChangeEntity;
import turboaz.repository.SearchTextChangeRepository;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class TextChangeTrackerService {
    private final JavaMailSender javaMailSender;
    private final SearchTextChangeRepository textRepository;

    public void searchForCarTextChanges(SearchTextChangeDto searchTextChangeDto) throws IOException, InterruptedException {
        String mail = searchTextChangeDto.getMail();
        String url = searchTextChangeDto.getUrl();

        SearchTextChangeEntity byMailAndUrl = textRepository.findByMailAndUrl(mail, url);

        Document doc = Jsoup.connect(url).get();
        Elements elementsByClass = doc.getElementsByClass("product-description__content");

        Element element = elementsByClass.get(0);
        String newText = element.text();

        if (byMailAndUrl == null) {
            saveNewTextChange(mail, url, newText);
        } else {
            String oldText = byMailAndUrl.getDescriptionText();
            if (!newText.isEmpty() && !newText.equals(oldText)) {
                updateTextChangeAndSend(byMailAndUrl, oldText, newText);
            }
        }
    }

    private void saveNewTextChange(String mail, String url, String text) {
        SearchTextChangeEntity textChangeEntity = SearchTextChangeEntity.builder()
                .mail(mail)
                .url(url)
                .descriptionText(text)
                .build();

        textRepository.save(textChangeEntity);
    }

    private void updateTextChangeAndSend(
            SearchTextChangeEntity textChangeEntity, String oldText, String newText) {

        textChangeEntity.setDescriptionText(newText);
        textChangeEntity.setDescriptionText(oldText);
        textRepository.save(textChangeEntity);

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(textChangeEntity.getMail());
        msg.setFrom("ellvinmehdixanov@gmail.com");
        msg.setSubject("Text deyisdi");
        msg.setText("Kohne Text:\n" + newText + "\n\nYeni Text:\n" + oldText);
        javaMailSender.send(msg);
    }
}

