package be.intecbrussel.messageboard.controller;

import be.intecbrussel.messageboard.model.Role;
import be.intecbrussel.messageboard.model.User;
import be.intecbrussel.messageboard.repository.RoleRepository;
import be.intecbrussel.messageboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
public class EntryController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/")
    public String entry(){
        User tempUser = userRepository.findByUsername("admin");
        if(tempUser == null){
            //If the admin user does not exist yet, make a new one.
            User admin = new User().setUsername("admin").setPassword("admin");
            Set<Role> roles = new HashSet<>();
            Role roleUser = roleRepository.findByName("ROLE_USER");
            Role roleAdmin = roleRepository.findByName("ROLE_ADMIN");
            roles.add(roleUser);
            roles.add(roleAdmin);
            admin.setRoles(roles);
            userRepository.save(admin);
        }
        return "index";
    }
}
