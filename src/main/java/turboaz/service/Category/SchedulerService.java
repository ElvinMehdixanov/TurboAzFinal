package turboaz.service.Category;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import turboaz.entity.SearchCategoryEntity;
import turboaz.repository.SearchCategoryRepository;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SchedulerService {
    private final SearchCategoryRepository searchCategoryRepository;

    private final JsoupService jsoupService;


    @Scheduled(fixedRate = 600000)
    public void searchForCars() throws IOException, InterruptedException {
        List<SearchCategoryEntity> all = searchCategoryRepository.findAll();

        for (SearchCategoryEntity e: all) {
            jsoupService.searchForCar(e.getMail(), e.getUrl());

        }

    }
}
