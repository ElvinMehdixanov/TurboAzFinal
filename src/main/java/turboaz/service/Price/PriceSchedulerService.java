package turboaz.service.Price;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import turboaz.dto.SearchCarDto;
import turboaz.entity.SearchCarEntity;
import turboaz.repository.SearchCarRepository;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PriceSchedulerService {
    private final SearchCarRepository searchRepository;
    private final PriceJsoupService priceJsoupService;

    @Scheduled(fixedRate = 5000)
    public void searchForCars() throws IOException, InterruptedException {
        List<SearchCarEntity> allSearchEntities = searchRepository.findAll();
        for (SearchCarEntity e : allSearchEntities) {

            SearchCarDto carDto = SearchCarDto.builder().url(e.getUrl()).mail(e.getMail()).build();

            priceJsoupService.searchPriceForCar(carDto);
        }
    }
}