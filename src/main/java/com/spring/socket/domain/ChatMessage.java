package com.spring.socket.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.socket.enumerate.Message;
import com.spring.socket.util.DateUtil;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@Entity
@Table(name = "chatMessage")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ChatMessage {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private ChatMessageId chatMessageId;

    @MapsId("roomName")
    @ManyToOne
    @JoinColumn(name = "roomName")
    private ChatRoom roomName;

    @MapsId("user")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Message type;

    private String message;

    @JsonIgnore
    @DateTimeFormat(pattern = DateUtil.PATTERN_YMDHMS)
    @Column(nullable = false, insertable = false, updatable = false, columnDefinition = "timestamp(6) default current_timestamp(6)")
    private LocalDateTime createDate;

    @JsonIgnore
    @DateTimeFormat(pattern = DateUtil.PATTERN_YMDHMS)
    @Column(nullable = false, insertable = false, updatable = false, columnDefinition = "timestamp(6) default current_timestamp(6) on update current_timestamp(6)")
    private LocalDateTime updateDate;
}
