package turboaz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import turboaz.entity.SearchCategoryEntity;
import turboaz.repository.SearchRepository;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SchedulerService {
    private final SearchRepository searchRepository;

    private final JsoupService jsoupService;


    @Scheduled(fixedRate = 1000)
    public void searchForCars() throws IOException, InterruptedException {
        List<SearchCategoryEntity> all = searchRepository.findAll();

        for (SearchCategoryEntity e: all) {
            jsoupService.searchForCar(e.getMail(), e.getUrl());

        }

    }
}
