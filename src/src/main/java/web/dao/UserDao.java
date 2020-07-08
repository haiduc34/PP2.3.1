package web.dao;
import web.model.*;


import java.util.List;

public interface UserDao {
    public void addUser(User user);

    public void updateUser(User user);

    public void removeUser(Long id);

    public User getUserById(Long id);

    public List<User> listUsers();
}
