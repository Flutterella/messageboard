package be.intecbrussel.messageboard.controller;

public class MessageDto {

    private String content;
    private String author;

    public String getContent() {
        return content;
    }

    public MessageDto setContent(String content) {
        this.content = content;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public MessageDto setAuthor(String author) {
        this.author = author;
        return this;
    }
}
