package com.chatapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String sender;

    @Column(nullable = false, length = 2000)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id", nullable = false)
    private Chat chat;

    public Message() {}

    public Message(String sender, String content, Chat chat) {
        this.sender = sender;
        this.content = content;
        this.chat = chat;
    }

    public Long getId() { return id; }
    public String getSender() { return sender; }
    public void setSender(String sender) { this.sender = sender; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public Chat getChat() { return chat; }
    public void setChat(Chat chat) { this.chat = chat; }
}
