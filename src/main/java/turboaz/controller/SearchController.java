package turboaz.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import turboaz.dto.SearchPriceDto;
import turboaz.dto.SearchCategoryDto;
import turboaz.dto.SearchTextChangeDto;
import turboaz.service.Price.PriceJsoupService;
import turboaz.service.Category.SearchService;
import turboaz.service.Text.TextChangeTrackerService;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/search")
public class SearchController {

    private final SearchService searcService;
    private final PriceJsoupService priceJsoupService;
    private final TextChangeTrackerService textChangeTrackerService;

    @PostMapping("/category")
    public void sendSearch(@Valid @RequestBody SearchCategoryDto searchCategoryDto) {
        searcService.saveDbAndEmail(searchCategoryDto);

    }

    @PostMapping("/price")
    public void sendPriceSearch(@Valid @RequestBody SearchPriceDto carDto) throws IOException, InterruptedException {
        priceJsoupService.searchPriceForCar(carDto);
    }


    @PostMapping("/text")
    public String startTextChangeTracking(@Valid @RequestBody SearchTextChangeDto searchTextChangeDto) throws IOException, InterruptedException {
        textChangeTrackerService.searchForCarTextChanges(searchTextChangeDto);
        return "Text change tracking starteMd successfully.";
    }
}
