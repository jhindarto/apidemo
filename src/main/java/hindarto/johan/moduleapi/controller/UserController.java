package hindarto.johan.moduleapi.controller;

import hindarto.johan.moduleapi.entity.User;
import hindarto.johan.moduleapi.exception.UserNotFoundException;
import hindarto.johan.moduleapi.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/0.1/users")
public class UserController {

    private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserRepository userRepository;

    @RequestMapping("")
    public List<User> findAllUsers() {

        LOGGER.info("findAllUsers()");
        Iterable<User> users = userRepository.findAll();
        List<User> list = new ArrayList<>();
        Iterator<User> it = users.iterator();
        while(it.hasNext()) {
            list.add(it.next());
        }
        return list;
    }

    @RequestMapping("/{userId}")
    public User findUserByUserId(@PathVariable("userId") long userId) {

        LOGGER.info("findUserByUserId() with userId=" + userId);
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()) {
            return user.get();
        } else {
            throw new UserNotFoundException(userId);
        }
    }
}
