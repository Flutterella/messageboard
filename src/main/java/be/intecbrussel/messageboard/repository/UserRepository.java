package be.intecbrussel.messageboard.repository;

import be.intecbrussel.messageboard.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    public List<User> findUsersByUserName(String userName);

    @Transactional
    public User save(User user);
}
