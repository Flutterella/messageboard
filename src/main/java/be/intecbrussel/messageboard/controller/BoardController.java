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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class BoardController {

    @Autowired
    MessageService messageService;

    @GetMapping("/board")
    public String board(Model model) {
        List<Message> messages;
        try {
            messages = messageService.getAllMessages();
            Collections.reverse(messages);
            model.addAttribute("messages", messages);
            model.addAttribute("messageForm", new MessageForm());
            return "board";
        } catch (NoMessageFoundException e) {
            messages = new ArrayList<>();
            model.addAttribute("messages", messages);
            model.addAttribute("messageForm", new MessageForm());
            return "board";
        }
    }

    @PostMapping("/board")
    public String board(@ModelAttribute("messageForm") MessageForm messageForm, Model model){
        messageService.addMessage(messageForm);
        return "redirect:board";
    }
}
