package ru.itsinfo.service;

import ru.itsinfo.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User readUser(long id);

    void deleteUser(long parseUnsignedInt);

    void createOrUpdateUser(User user);
}
