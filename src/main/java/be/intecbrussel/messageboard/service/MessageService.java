package be.intecbrussel.messageboard.service;

import be.intecbrussel.messageboard.controller.MessageForm;
import be.intecbrussel.messageboard.model.Message;

import java.util.List;

public interface MessageService {

    public List<Message> getAllMessages() throws NoMessageFoundException;

    public List<Message> getAllMessagesWithOffset(int offset) throws NoMessageFoundException;

    public void addMessage(MessageForm messageForm, String author);
}
