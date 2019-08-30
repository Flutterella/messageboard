package be.intecbrussel.messageboard.controller;

import be.intecbrussel.messageboard.model.Message;
import be.intecbrussel.messageboard.model.Role;
import be.intecbrussel.messageboard.model.User;
import be.intecbrussel.messageboard.repository.RoleRepository;
import be.intecbrussel.messageboard.repository.UserRepository;
import be.intecbrussel.messageboard.service.MessageService;
import be.intecbrussel.messageboard.service.NoMessageFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class BoardController {

    @Autowired
    private MessageService messageService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String entry(){
        User tempUser = userRepository.findByUsername("admin");
        if(tempUser == null){
            //If the admin user does not exist yet, make a new one.
            User admin = new User()
                    .setUsername("admin")
                    .setPassword(passwordEncoder.encode("admin"));
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

    @GetMapping("/board")
    public String board(@RequestParam(value="offset", required=false, defaultValue="0") String offset, Model model,
                        Principal principal){
        if(principal != null){
            model.addAttribute("username", principal.getName());
        }
        List<Message> messages;
        int intOffset = Integer.parseInt(offset);
        try {
            messages = messageService.getAllMessagesWithOffset(intOffset);
            model.addAttribute("messages", messages);
            model.addAttribute("messageDto", new MessageDto());
            model.addAttribute("offset", intOffset);
            return "board";
        } catch (NoMessageFoundException e) {
            messages = new ArrayList<>();
            model.addAttribute("messages", messages);
            model.addAttribute("messageDto", new MessageDto());
            model.addAttribute("offset", intOffset);
            return "board";
        }
    }

    @PostMapping("/board")
    public String board(@ModelAttribute("messageDto") MessageDto messageDto, Principal principal){
        messageDto.setAuthor(principal.getName());
        messageService.addMessage(messageDto);
        return "redirect:board";
    }
}
