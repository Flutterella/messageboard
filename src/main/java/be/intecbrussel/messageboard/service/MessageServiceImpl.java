package be.intecbrussel.messageboard.service;

import be.intecbrussel.messageboard.controller.MessageForm;
import be.intecbrussel.messageboard.model.Message;
import be.intecbrussel.messageboard.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageRepository messageRepository;

    @Override
    public List<Message> getAllMessages() throws NoMessageFoundException {
        List<Message> messages = messageRepository.findAll();
        if(messages.size() >= 1){
            Collections.reverse(messages);
            return messages;
        }
        else{
            throw new NoMessageFoundException();
        }
    }

    @Override
    public List<Message> getAllMessagesWithOffset(int offset) throws NoMessageFoundException {
        List<Message> messages = messageRepository.findAll();
        Collections.reverse(messages);
        if(messages.size() >= 1 + offset){
            List<Message> finalMessages = new ArrayList<>();
            for(int i = offset; i < offset + 10; i++){
                if(i >= 0 && i < messages.size()){
                    finalMessages.add(messages.get(i));
                }
            }
            Collections.reverse(finalMessages);
            return finalMessages;
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
