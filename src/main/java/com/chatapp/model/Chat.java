package com.chatapp.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "chats")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String topic;

    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy("id ASC")
    private List<Message> messages = new ArrayList<>();

    public Chat() {}

    public Chat(String topic) {
        this.topic = topic;
    }

    public Long getId() { return id; }
    public String getTopic() { return topic; }
    public void setTopic(String topic) { this.topic = topic; }
    public List<Message> getMessages() { return messages; }
}
