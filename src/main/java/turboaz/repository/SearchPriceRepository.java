package turboaz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import turboaz.entity.SearchPriceEntity;

@Repository
public interface SearchPriceRepository extends JpaRepository<SearchPriceEntity, Long> {
    SearchPriceEntity findByMailAndUrl(String mail, String url);
}
