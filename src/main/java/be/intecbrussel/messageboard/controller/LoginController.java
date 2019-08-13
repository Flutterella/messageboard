package be.intecbrussel.messageboard.controller;

import be.intecbrussel.messageboard.model.UserDto;
import be.intecbrussel.messageboard.service.DuplicateUserException;
import be.intecbrussel.messageboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String login( Model model) {
        UserDto user = new UserDto();
        model.addAttribute(user);
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute("user") UserDto user, Model model, HttpServletRequest request){
        return "login";
    }
}
