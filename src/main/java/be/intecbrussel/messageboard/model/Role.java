package be.intecbrussel.messageboard.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToMany(mappedBy="roles")
    private Set<User> users;

    public long getId() {
        return id;
    }

    public Role setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Role setName(String name) {
        this.name = name;
        return this;
    }

    public Set<User> getUsers() {
        return users;
    }

    public Role setUsers(Set<User> users) {
        this.users = users;
        return this;
    }
}
