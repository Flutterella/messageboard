package be.intecbrussel.messageboard.repository;

import be.intecbrussel.messageboard.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User save(User user);
}
