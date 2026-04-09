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

    @Column(nullable = false)
    private String creator;   //  Neu Anpassung

    @OneToMany(
            mappedBy = "chat",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @OrderBy("id ASC")
    private List<Message> messages = new ArrayList<>();

    public Chat() {
    }

    public Chat(String topic, String creator) {   // Neur Konstruktor
        this.topic = topic;
        this.creator = creator;
    }

    public Long getId() {
        return id;
    }

    public String getTopic() {
        return topic;
    }
    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getCreator() {          // GETTER
        return creator;
    }

    public void setCreator(String creator) {   // SETTER
        this.creator = creator;
    }

    public List<Message> getMessages() {
        return messages;
    }
}