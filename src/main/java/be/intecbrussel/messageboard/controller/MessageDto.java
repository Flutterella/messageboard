package be.intecbrussel.messageboard.controller;

public class MessageDto {

    private String content;

    public String getContent() {
        return content;
    }

    public MessageDto setContent(String content) {
        this.content = content;
        return this;
    }
}
