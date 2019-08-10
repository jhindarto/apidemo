package hindarto.johan.moduleapi.repository;

import hindarto.johan.moduleapi.entity.Module;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ModuleRepository extends CrudRepository<Module, Long> {

}