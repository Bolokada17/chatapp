# ChatApp - LCSS Lab 0

A chat application built with Java, Spring Boot, Thymeleaf, H2 (in-memory database) and WebSockets.

## Requirements
- Java 17+
- Maven 3.6+

## Run the application

```bash
mvn spring-boot:run
```

Then open your browser at: http://localhost:8080/chats

## Features
- Create new chats with a topic
- Send messages (with sender name and content)
- Real-time updates via WebSocket (STOMP/SockJS) — no page reload needed
- Messages persisted in H2 in-memory database
- H2 console available at: http://localhost:8080/h2-console (JDBC URL: jdbc:h2:mem:chatappdb)

## Git Setup & Submission

```bash
# Initialize repo (only once)
git init
git add .
git commit -m "Initial homework implementation"

# Tag for submission
git tag homework

git remote add origin ssh://git@gitlab.nt.fh-koeln.de:10022/gitlab/mconde/chatapp.git
git push
git push --tag


```
