package mvc.repository;

import mvc.model.User;
import java.util.List;

public interface UserRepository {
    List<User> findAll();
    User findById(Integer id);
    void save(User user);
    void delete(User user);
}