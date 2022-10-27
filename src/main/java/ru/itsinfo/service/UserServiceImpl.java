package ru.itsinfo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itsinfo.repository.UserRepository;
import ru.itsinfo.model.User;

import java.util.List;

@Service

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Transactional
    @Override
    public void createOrUpdateUser(User user) {
        if (0 == user.getId()) {
            createUser(user);
        } else {
            updateUser(user);
        }
    }

    private void createUser(User user) {
        userRepository.createUser(user);
    }

    private void updateUser(User user) {
        userRepository.updateUser(user);
    }


    @Override
    public User readUser(long id) {
        return userRepository.readUser(id);
    }

    @Transactional
    @Override
    public void deleteUser(long id) {
        try {
            userRepository.deleteUser(id);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
