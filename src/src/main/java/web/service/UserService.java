package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    void addUser(User user);

    void updateUser(User user);

    void removeUser(Long id);

    User getUserById(Long id);

    public List<User> listUsers();
}
