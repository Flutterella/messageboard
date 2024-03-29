package be.intecbrussel.messageboard.controller;

import be.intecbrussel.messageboard.service.DuplicateUserException;
import be.intecbrussel.messageboard.service.MismatchedPasswordsException;
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
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(@ModelAttribute("userDto") UserDto userDto, Model model) {
        try {
            userService.registerUser(userDto);
        } catch (MismatchedPasswordsException e) {
            return "mismatchedPasswords";
        }
        catch (DuplicateUserException e){
            return "duplicateUser.html";
        }
        return "redirect:login";

    }
}
