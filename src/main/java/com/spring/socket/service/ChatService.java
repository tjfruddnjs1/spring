package com.spring.socket.service;


import com.spring.socket.domain.ChatRoom;
import com.spring.socket.repository.ChatRoomRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ChatService {

    @NonNull
    private final ChatRoomRepository chatRoomRepository;

    @Transactional
    public List<ChatRoom> findAllRoom() {
        List chatRooms = new ArrayList<>(chatRoomRepository.findAll());
        Collections.reverse(chatRooms);
        return chatRooms;
    }

    @Transactional
    public void save(ChatRoom chatRoom) {
        if(chatRoom.getUuid().isEmpty()){
            chatRoom.setUuid(UUID.randomUUID().toString());
        }
         chatRoomRepository.save(chatRoom);
    }

    public ChatRoom findRoomByRoomName(String roomName) {
        return chatRoomRepository.findRoomByRoomName(roomName);
    }
}
