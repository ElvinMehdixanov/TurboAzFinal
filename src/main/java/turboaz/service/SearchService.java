package turboaz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import turboaz.dto.SearchCategoryDto;
import turboaz.entity.SearchCategoryEntity;
import turboaz.repository.SearchRepository;


@Service
@RequiredArgsConstructor
public class SearchService {

    private final SearchRepository searchRepository;


    public void saveDbAndEmail(SearchCategoryDto searchCategoryDto) {


        searchRepository.save(SearchCategoryEntity.builder().url(searchCategoryDto.getUrl()).mail(searchCategoryDto.getMail()).build());


    }
}
