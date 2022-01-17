package com.spring.socket.enumerate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Message {
    ENTER("참가"),
    TALK("채팅");

    private String value;
}
