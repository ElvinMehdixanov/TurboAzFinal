package turboaz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import turboaz.entity.SearchCategoryEntity;

@Repository
public interface SearchRepository extends JpaRepository<SearchCategoryEntity, Long> {
}
