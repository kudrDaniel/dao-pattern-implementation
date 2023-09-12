package ru.kudrdaniel.dao.pattern.implementation;

import lombok.extern.slf4j.Slf4j;
import ru.kudrdaniel.dao.pattern.implementation.db.Initialize;
import ru.kudrdaniel.dao.pattern.implementation.entity.User;
import ru.kudrdaniel.dao.pattern.implementation.entity.dao.UserDao;
import java.util.List;

@Slf4j
public final class Application {
    public static void main(String[] args) {
        Initialize.initDB();

        try (UserDao dao = new UserDao()) {
            dao.create(new User("John", "Blame"));
            dao.create(new User("Andy", "Blame"));
            dao.create(new User("Robert", "Fall"));
            dao.create(new User("Clint", "Lawson"));
            dao.create(new User("Ann", "Craft"));
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        List<User> users = null;
        try (UserDao dao = new UserDao()) {
            users = dao.getAll();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        if (users != null) {
            users.forEach(user -> log.info(user.toString()));
        } else {
            log.info("No users");
        }
    }
}
