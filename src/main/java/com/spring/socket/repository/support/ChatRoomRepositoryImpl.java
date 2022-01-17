package com.spring.socket.repository.support;

import com.spring.socket.domain.QChatRoom;
import com.spring.socket.repository.custom.CustomChatRoomRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class ChatRoomRepositoryImpl extends QuerydslRepositorySupport implements
    CustomChatRoomRepository {

    private final QChatRoom chatRoom = QChatRoom.chatRoom;

    public ChatRoomRepositoryImpl() {
        super(QChatRoom.class);
    }

}
