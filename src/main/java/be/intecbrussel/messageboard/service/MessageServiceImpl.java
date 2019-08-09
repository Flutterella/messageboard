package be.intecbrussel.messageboard.service;

import be.intecbrussel.messageboard.controller.MessageForm;
import be.intecbrussel.messageboard.model.Message;
import be.intecbrussel.messageboard.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageRepository messageRepository;

    @Override
    public List<Message> getAllMessages() throws NoMessageFoundException {
        List<Message> messages = messageRepository.findAll();
        if(messages.size() >= 1){
            return messages;
        }
        else{
            throw new NoMessageFoundException();
        }
    }

    @Override
    public void addMessage(MessageForm messageForm) {
        Message message = new Message();
        message.setAuthor(messageForm.getAuthor());
        message.setContent(messageForm.getContent());
        message.setDate(LocalDateTime.now());
        messageRepository.save(message);
    }
}
