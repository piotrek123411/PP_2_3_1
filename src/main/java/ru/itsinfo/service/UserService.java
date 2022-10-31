package ru.itsinfo.service;

import org.springframework.transaction.annotation.Transactional;
import ru.itsinfo.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    @Transactional
    void createUser(User user);

    @Transactional
    void updateUser(User user);

    User readUser(long id);

    void deleteUser(long parseUnsignedInt);

    void createOrUpdateUser(User user);
}
