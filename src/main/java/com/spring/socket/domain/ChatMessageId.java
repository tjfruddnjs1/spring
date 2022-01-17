package com.spring.socket.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ChatMessageId implements Serializable {

    @EqualsAndHashCode.Include
    @Column(name = "id")
    private Integer id;

    @EqualsAndHashCode.Include
    @Column(name = "roomName")
    private String roomName;

    @EqualsAndHashCode.Include
    @Column(name = "user_id")
    private Integer user;
}
