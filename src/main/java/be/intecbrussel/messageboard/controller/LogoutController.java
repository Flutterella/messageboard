package be.intecbrussel.messageboard.controller;

import be.intecbrussel.messageboard.model.UserDto;
import be.intecbrussel.messageboard.service.InvalidLoginException;
import be.intecbrussel.messageboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LogoutController {

    @GetMapping("/logout")
    public String logout( Model model, HttpSession session) {
        session.setAttribute("loggedIn", false);
        return "logout";
    }
}
