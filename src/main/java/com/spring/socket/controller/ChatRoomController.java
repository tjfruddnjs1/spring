package com.spring.socket.controller;

import com.spring.socket.domain.ChatRoom;
import com.spring.socket.service.ChatService;
import java.util.UUID;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/chat/room")
public class ChatRoomController {

    private final ChatService chatService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("roomList", chatService.findAllRoom());
        return "chat/list";
    }

    @GetMapping({"/edit", "/edit/{roomName}"})
    public String edit(@PathVariable(name = "roomName", required = false) String roomName,
        Model model) {
        ChatRoom chatRoom =
            (!roomName.isEmpty()) ? chatService.findRoomByRoomName(roomName) : new ChatRoom();

        model.addAttribute("chatRoom", chatRoom);
        return "chat/edit";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("chatRoom") ChatRoom chatRoom, BindingResult result) {
        if (!result.hasErrors()) {
            chatService.save(chatRoom);
            return "redirect:/chat/room/list";
        }

        return "chat/edit";
    }

    // 채팅방 입장 화면
    @GetMapping("/room/enter/{RoomName}")
    public String roomDetail(Model model, @PathVariable String RoomName) {
        model.addAttribute("RoomName", RoomName);
        return "chat/detail";
    }
}
