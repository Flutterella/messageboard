package be.intecbrussel.messageboard.controller;

import be.intecbrussel.messageboard.service.InvalidLoginException;
import be.intecbrussel.messageboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login( Model model) {
        UserDto user = new UserDto();
        model.addAttribute(user);
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute("user") UserDto user, Model model,
                            HttpSession session){
        try{
            userService.loginUser(user);
            session.setAttribute("loggedIn", true);
            session.setAttribute("userName", user.getUserName());
        }
        catch(InvalidLoginException e){
            return "invalidLogin";
        }
        return "redirect:board";
    }
}
