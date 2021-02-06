package web.service;

import web.models.User;

import java.util.List;

public interface UserService {

    void saveUser(User user);

    List<User> getAllUsers();

    User getUser(int id);

    void editUser(User user);

    void deleteUser(int id);
}
