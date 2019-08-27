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

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BoardController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/board")
    public String board(@RequestParam(value="offset", required=false, defaultValue="0") String offset, Model model,
                        Principal principal){
        model.addAttribute("username", principal.getName());
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
