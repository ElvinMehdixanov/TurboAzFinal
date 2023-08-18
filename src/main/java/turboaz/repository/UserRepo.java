package turboaz.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import turboaz.entity.UserEntity;

@Repository
public interface UserRepo extends CrudRepository<UserEntity, Long>{
    UserEntity findUsersEntityByEmail(String email);
}
