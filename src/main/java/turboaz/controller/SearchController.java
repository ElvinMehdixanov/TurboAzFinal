package turboaz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import turboaz.dto.SearchCarDto;
import turboaz.dto.SearchCategoryDto;
import turboaz.service.Price.PriceJsoupService;
import turboaz.service.SearchService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {

    private final SearchService searcService;
    private final PriceJsoupService priceJsoupService;

    @PostMapping("/category")
    public void sendSearch(@RequestBody SearchCategoryDto searchCategoryDto) {

        searcService.saveDbAndEmail(searchCategoryDto);

    }

    @PostMapping("/car")
    public void sendPriceSearch(@RequestBody SearchCarDto carDto) throws IOException, InterruptedException {
        priceJsoupService.searchPriceForCar(carDto);
    }
}
