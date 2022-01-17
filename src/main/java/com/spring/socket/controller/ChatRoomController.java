package com.spring.socket.controller;

import com.spring.socket.domain.ChatRoom;
import com.spring.socket.service.ChatService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
@RequestMapping("/chat")
public class ChatRoomController {

    private final ChatService chatService;

    // 채팅 리스트 화면
    @GetMapping("/room")
    public String rooms(Model model) {
        return "chat/room";
    }

    // 모든 채팅방 목록 반환
    @GetMapping("/rooms")
    @ResponseBody
    public List<ChatRoom> room() {
        return chatService.findAllRoom();
    }

    // 채팅방 생성
    @PostMapping("/room")
    @ResponseBody
    public ChatRoom createRoom(@RequestParam String roomName) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setRoomName(roomName);
        chatRoom.setUuid(UUID.randomUUID().toString());

        return chatService.save(chatRoom);
    }

    // 채팅방 입장 화면
    @GetMapping("/room/enter/{RoomName}")
    public String roomDetail(Model model, @PathVariable String RoomName) {
        model.addAttribute("RoomName", RoomName);
        return "chat/roomDetail";
    }

    // 특정 채팅방 조회
    @GetMapping("/room/{RoomName}")
    @ResponseBody
    public ChatRoom roomInfo(@PathVariable String RoomName) {
        return chatService.findRoomByRoomName(RoomName);
    }
}
