package com.spring.socket.controller;

import com.spring.socket.domain.ChatMessage;
import com.spring.socket.enumerate.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class ChatController {

    private final SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/chat/message")
    public void message(ChatMessage message) {
        if (Message.ENTER.equals(message.getType())) {
            message.setMessage(message.getUser().getName() + "님이 입장하셨습니다.");
        }
        messagingTemplate
            .convertAndSend("/sub/chat/room/" + message.getRoomName().getUuid(), message);
    }
}
