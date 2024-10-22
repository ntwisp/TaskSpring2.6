package mvc.service;

import mvc.model.User;
import java.util.List;

public interface UserService {
    List<User> readAllUsers();
    User readUser(Integer id);
    void createUser(User user);
    void updateUser(User user);
    void deleteUser(Integer id);
}