package be.intecbrussel.messageboard.controller;

import be.intecbrussel.messageboard.service.AuthenticationException;
import be.intecbrussel.messageboard.service.DuplicateUserException;
import be.intecbrussel.messageboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration( Model model) {
        UserDto user = new UserDto();
        model.addAttribute(user);
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(@ModelAttribute("user") UserDto user, Model model) throws AuthenticationException {
        if(!user.getPassword().equals(user.getMatchingPassword())){
            return "mismatchedPasswords";
        }
        try {
            userService.registerUser(user);
        } catch (DuplicateUserException e) {
            return "duplicateUser";
        }
        return "redirect:login";
    }
}