package turboaz.service.Price;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import turboaz.dto.SearchPriceDto;
import turboaz.entity.SearchPriceEntity;
import turboaz.repository.SearchPriceRepository;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PriceSchedulerService {
    private final SearchPriceRepository searchRepository;
    private final PriceJsoupService priceJsoupService;

    @Scheduled(fixedRate = 60000)
    public void searchForCars() throws IOException, InterruptedException {
        List<SearchPriceEntity> allSearchEntities = searchRepository.findAll();
        for (SearchPriceEntity e : allSearchEntities) {

            SearchPriceDto carDto = SearchPriceDto.builder().url(e.getUrl()).mail(e.getMail()).build();

            priceJsoupService.searchPriceForCar(carDto);
        }
    }
}