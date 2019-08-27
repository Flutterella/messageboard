package be.intecbrussel.messageboard.controller;

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
        System.out.println("In registration controller!");
        userService.registerUser(userDto);
        return "redirect:login";

    }
}
