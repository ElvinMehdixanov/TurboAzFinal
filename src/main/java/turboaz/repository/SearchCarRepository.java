package turboaz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import turboaz.entity.SearchCarEntity;

@Repository
public interface SearchCarRepository extends JpaRepository<SearchCarEntity, Long> {
    SearchCarEntity findByMailAndUrl(String mail, String url);
}
