package be.intecbrussel.messageboard.service;

import be.intecbrussel.messageboard.controller.MessageDto;
import be.intecbrussel.messageboard.model.Message;

import java.util.List;

public interface MessageService {

    List<Message> getAllMessages() throws NoMessageFoundException;

    List<Message> getAllMessagesWithOffset(int offset) throws NoMessageFoundException;

    void addMessage(MessageDto messageDto);
}
