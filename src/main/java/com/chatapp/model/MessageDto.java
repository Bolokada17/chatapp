package com.chatapp.model;

public class MessageDto {
    private Long id;
    private String sender;
    private String content;
    private Long chatId;

    public MessageDto() {}

    public MessageDto(Long id, String sender, String content, Long chatId) {
        this.id = id;
        this.sender = sender;
        this.content = content;
        this.chatId = chatId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getSender() { return sender; }
    public void setSender(String sender) { this.sender = sender; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public Long getChatId() { return chatId; }
    public void setChatId(Long chatId) { this.chatId = chatId; }
}
