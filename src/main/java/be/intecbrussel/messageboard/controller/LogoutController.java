package be.intecbrussel.messageboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LogoutController {

    @GetMapping("/logout")
    public String logout( Model model, HttpSession session) {
        session.setAttribute("loggedIn", false);
        return "logout";
    }
}
