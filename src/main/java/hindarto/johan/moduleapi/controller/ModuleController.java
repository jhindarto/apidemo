package hindarto.johan.moduleapi.controller;

import hindarto.johan.moduleapi.entity.GroupModule;
import hindarto.johan.moduleapi.entity.User;
import hindarto.johan.moduleapi.exception.UserNotFoundException;
import hindarto.johan.moduleapi.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/0.1/modules")
public class ModuleController {

    private static Logger LOGGER = LoggerFactory.getLogger(ModuleController.class);

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public GroupModule findGroupModuleByUserId(@PathVariable("userId") long userId) {

        LOGGER.info("findGroupModuleByUserId() with userId=" + userId);
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()) {
            GroupModule gm = user.get().getGroupModule();
            return gm;
        } else {
            throw new UserNotFoundException(userId);
        }
    }
}
