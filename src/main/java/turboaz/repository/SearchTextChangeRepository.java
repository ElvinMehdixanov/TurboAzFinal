package turboaz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import turboaz.entity.SearchTextChangeEntity;

public interface SearchTextChangeRepository extends JpaRepository<SearchTextChangeEntity, Long> {

    SearchTextChangeEntity findByMailAndUrl(String mail, String url);
}
