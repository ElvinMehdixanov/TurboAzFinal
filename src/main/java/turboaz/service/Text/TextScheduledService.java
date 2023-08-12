package turboaz.service.Text;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import turboaz.dto.SearchTextChangeDto;
import turboaz.entity.SearchTextChangeEntity;
import turboaz.repository.SearchTextChangeRepository;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TextScheduledService {
    private final SearchTextChangeRepository searchTextChangeRepository;
    private final TextChangeTrackerService textChangeTrackerService;


    @Scheduled(fixedRate = 60000)
    public void checkTextChangesAndNotify() throws IOException, InterruptedException {
        List<SearchTextChangeEntity> allTextChangeEntities = searchTextChangeRepository.findAll();
        for (SearchTextChangeEntity entity : allTextChangeEntities) {

            SearchTextChangeDto searchTextChangeDto = SearchTextChangeDto.builder().url(entity.getUrl()).mail(entity.getMail()).build();

            textChangeTrackerService.searchForCarTextChanges(searchTextChangeDto);
        }
    }
}



