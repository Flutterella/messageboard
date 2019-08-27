package be.intecbrussel.messageboard.repository;

import be.intecbrussel.messageboard.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Long> {

    public Role findByName(String name);
}
