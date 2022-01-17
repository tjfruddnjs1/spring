package com.spring.socket.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.socket.util.DateUtil;
import com.sun.istack.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@Entity
@Table(name = "chatRoom")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ChatRoom {
    @Id
    @NotNull
    @Column(unique = true, nullable = false, updatable = false)
    private String roomName;

    @NotNull
    @Column(unique = true, nullable = false, updatable = false)
    private String uuid;

    @JsonIgnore
    @DateTimeFormat(pattern = DateUtil.PATTERN_YMDHMS)
    @Column(nullable = false, insertable = false, updatable = false, columnDefinition = "timestamp(6) default current_timestamp(6)")
    private LocalDateTime createDate;

    @JsonIgnore
    @DateTimeFormat(pattern = DateUtil.PATTERN_YMDHMS)
    @Column(nullable = false, insertable = false, updatable = false, columnDefinition = "timestamp(6) default current_timestamp(6) on update current_timestamp(6)")
    private LocalDateTime updateDate;
}
