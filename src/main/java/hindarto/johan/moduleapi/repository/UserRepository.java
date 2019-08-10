package hindarto.johan.moduleapi.repository;

import hindarto.johan.moduleapi.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}