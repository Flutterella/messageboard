package be.intecbrussel.messageboard.controller;

import be.intecbrussel.messageboard.model.Message;
import be.intecbrussel.messageboard.service.MessageService;
import be.intecbrussel.messageboard.service.NoMessageFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BoardController {

    @Autowired
    MessageService messageService;

    @GetMapping("/board")
    public String board(@RequestParam(value="offset", required=false, defaultValue="0") String offset, Model model,
                        HttpServletRequest request) {
        List<Message> messages;
        Boolean loggedIn = ((Boolean)request.getSession().getAttribute("loggedIn"));
        int intOffset = Integer.parseInt(offset);
        try {
            messages = messageService.getAllMessagesWithOffset(intOffset);
            model.addAttribute("messages", messages);
            model.addAttribute("messageForm", new MessageForm());
            model.addAttribute("offset", intOffset);
            if(loggedIn != null){
                model.addAttribute("loggedIn", loggedIn);
            }
            else{
                model.addAttribute("loggedIn", false);
            }
            return "board";
        } catch (NoMessageFoundException e) {
            messages = new ArrayList<>();
            model.addAttribute("messages", messages);
            model.addAttribute("messageForm", new MessageForm());
            model.addAttribute("offset", intOffset);
            if(loggedIn != null){
                model.addAttribute("loggedIn", loggedIn);
            }
            else{
                model.addAttribute("loggedIn", false);
            }
            return "board";
        }
    }

    @PostMapping("/board")
    public String board(@ModelAttribute("messageForm") MessageForm messageForm, Model model,
                        HttpServletRequest request){
        String author = ((String)request.getSession().getAttribute("user"));
        messageService.addMessage(messageForm, author);
        return "redirect:board";
    }
}
