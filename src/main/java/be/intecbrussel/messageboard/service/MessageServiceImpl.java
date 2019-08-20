package be.intecbrussel.messageboard.service;

import be.intecbrussel.messageboard.controller.MessageDto;
import be.intecbrussel.messageboard.model.Message;
import be.intecbrussel.messageboard.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    ReadWriteLock lock = new ReentrantReadWriteLock();

    @Override
    public List<Message> getAllMessages() throws NoMessageFoundException {
        List<Message> messages;
        try{
            lock.readLock().lock();
            messages = messageRepository.findAll();
        }
        finally{
            lock.readLock().unlock();
        }
        if(messages != null && messages.size() >= 1){
            Collections.reverse(messages);
            return messages;
        }
        else{
            throw new NoMessageFoundException();
        }
    }

    @Override
    public List<Message> getAllMessagesWithOffset(int offset) throws NoMessageFoundException {
        List<Message> messages;
        try{
            lock.readLock().lock();
            messages = messageRepository.findAll();
        }
        finally{
            lock.readLock().unlock();
        }
        Collections.reverse(messages);
        if(messages != null && messages.size() >= 1 + offset){
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
    public void addMessage(MessageDto messageDto, String author) {
        Message message = new Message();
        message.setAuthor(author);
        message.setContent(messageDto.getContent());
        message.setDate(LocalDateTime.now());
        try{
            lock.writeLock().lock();
            messageRepository.save(message);
        }
        finally{
            lock.writeLock().unlock();
        }
    }
}
