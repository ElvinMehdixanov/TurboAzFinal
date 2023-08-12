package turboaz.service.Category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import turboaz.dto.SearchCategoryDto;
import turboaz.entity.SearchCategoryEntity;
import turboaz.repository.SearchCategoryRepository;


@Service
@RequiredArgsConstructor
public class SearchService {

    private final SearchCategoryRepository searchCategoryRepository;


    public void saveDbAndEmail(SearchCategoryDto searchCategoryDto) {


        searchCategoryRepository.save(SearchCategoryEntity.builder().url(searchCategoryDto.getUrl()).mail(searchCategoryDto.getMail()).build());


    }
}
