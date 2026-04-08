package com.chatapp.controller;

import com.chatapp.model.*;
import com.chatapp.repository.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ChatController {

    private final ChatRepository chatRepository;
    private final MessageRepository messageRepository;
    private final SimpMessagingTemplate messagingTemplate;

    public ChatController(ChatRepository chatRepository,
                          MessageRepository messageRepository,
                          SimpMessagingTemplate messagingTemplate) {
        this.chatRepository = chatRepository;
        this.messageRepository = messageRepository;
        this.messagingTemplate = messagingTemplate;
    }

    // ---- Home page ----

    @GetMapping("/chats")
    public String home(Model model) {
        List<Chat> chats = chatRepository.findAll();
        model.addAttribute("chats", chats);
        return "home";
    }

    @PostMapping("/chats")
    public String createChat(@RequestParam String topic) {
        if (topic != null && !topic.trim().isEmpty()) {
            chatRepository.save(new Chat(topic.trim()));
        }
        return "redirect:/chats";
    }

    // ---- Chat page ----

    @GetMapping("/chats/{id}")
    public String chatPage(@PathVariable Long id, Model model) {
        Chat chat = chatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chat not found"));
        List<Message> messages = messageRepository.findByChatIdOrderByIdAsc(id);
        model.addAttribute("chat", chat);
        model.addAttribute("messages", messages);
        return "chat";
    }

    @PostMapping("/chats/{id}/messages")
    public String sendMessage(@PathVariable Long id,
                              @RequestParam String sender,
                              @RequestParam String content) {
        Chat chat = chatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chat not found"));

        if (sender != null && !sender.trim().isEmpty()
                && content != null && !content.trim().isEmpty()) {
            Message msg = new Message(sender.trim(), content.trim(), chat);
            messageRepository.save(msg);

            // Broadcast via WebSocket to all clients watching this chat
            MessageDto dto = new MessageDto(msg.getId(), msg.getSender(), msg.getContent(), id);
            messagingTemplate.convertAndSend("/topic/chats/" + id, dto);
        }
        return "redirect:/chats/" + id;
    }
}
