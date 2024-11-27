package toy.repository;

import org.springframework.stereotype.Repository;
import toy.domain.User;

@Repository
public interface UserRepository {
    public String save(User user);
    public User update(String id, User updateParam);
    public User findById(String id);
    public void delete(String id);
}
