package turboaz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import turboaz.entity.CarEntity;

import java.util.ArrayList;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {
    ArrayList<CarEntity> findAllByPriceBetween(Integer minPrice, Integer maxPrice);

    ArrayList<CarEntity> findAllByMakeAndModel(String make, String model);
}
