package be.intecbrussel.messageboard.controller;

public class MessageForm {

    private String author;
    private String content;

    public String getAuthor() {
        return author;
    }

    public MessageForm setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getContent() {
        return content;
    }

    public MessageForm setContent(String content) {
        this.content = content;
        return this;
    }
}
