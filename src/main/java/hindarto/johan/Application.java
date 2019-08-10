package hindarto.johan;

import hindarto.johan.moduleapi.entity.GroupModule;
import hindarto.johan.moduleapi.entity.Module;
import hindarto.johan.moduleapi.entity.User;
import hindarto.johan.moduleapi.repository.GroupModuleRepository;
import hindarto.johan.moduleapi.repository.ModuleRepository;
import hindarto.johan.moduleapi.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner bootstrapData(ModuleRepository mRepository, GroupModuleRepository gmRepository,
                                           UserRepository userRepository) {
        return (args) -> {

            User user1 = new User();
            user1.setName("Alex");
            User user2 = new User();
            user2.setName("Bob");
            User user3 = new User();
            user3.setName("Marvel");

            GroupModule gmod1 = new GroupModule();
            gmod1.setName("Young&Adventurous");
            GroupModule gmod2 = new GroupModule();
            gmod2.setName("Family");
            GroupModule gmod3 = new GroupModule();
            gmod3.setName("Hobby Centric");

            user1.setGroupModule(gmod1);
            user2.setGroupModule(gmod2);
            user3.setGroupModule(gmod3);

            gmod1.addModule(createModule("PromoCard",1));
            gmod1.addModule(createModule("CategoryCard",2));
            gmod1.addModule(createModule("FlashSaleCard",3));
            gmod1.addModule(createModule("HistoryCard",4));
            gmod1.addModule(createModule("NewsCard",5));

            gmod2.addModule(createModule("PromoCard",1));
            gmod2.addModule(createModule("NewsCard",2));
            gmod2.addModule(createModule("FlashSaleCard",3));
            gmod2.addModule(createModule("CategoryCard",4));
            gmod2.addModule(createModule("HistoryCard",5));

            gmod3.addModule(createModule("PromoCard",1));
            gmod3.addModule(createModule("FlashSaleCard",2));
            gmod3.addModule(createModule("CategoryCard",3));
            gmod3.addModule(createModule("NewsCard",4));
            gmod3.addModule(createModule("HistoryCard",5));


            gmRepository.save(gmod1);
            gmRepository.save(gmod2);
            gmRepository.save(gmod3);

            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);

            for (User user : userRepository.findAll()) {
                LOGGER.info(user.toString());
            }
            LOGGER.info("");

            for (GroupModule gModule : gmRepository.findAll()) {
                LOGGER.info(gModule.toString());
            }
            LOGGER.info("");

            for (Module module : mRepository.findAll()) {
                LOGGER.info(module.toString());
            }
            LOGGER.info("");
        };
    }

    private Module createModule(String name, int order) {
        Module mod = new Module();
        mod.setModuleName(name);
        mod.setModuleOrder(order);
        return mod;
    }
}
