package be.intecbrussel.messageboard.repository;

import be.intecbrussel.messageboard.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    public List<User> findUsersByUserName(String userName);

    public User save(User user);
}
