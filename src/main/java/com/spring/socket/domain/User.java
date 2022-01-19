package com.spring.socket.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.socket.enumerate.Role;
import com.spring.socket.util.DateUtil;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
@Entity
@Table(name = "user", indexes = {@Index(columnList = "username")})
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @JsonIgnore
    @DateTimeFormat(pattern = DateUtil.PATTERN_YMDHMS)
    @Column(nullable = false, insertable = false, updatable = false, columnDefinition = "timestamp(6) default current_timestamp(6)")
    private LocalDateTime createDate;

    @JsonIgnore
    @DateTimeFormat(pattern = DateUtil.PATTERN_YMDHMS)
    @Column(nullable = false, insertable = false, updatable = false, columnDefinition = "timestamp(6) default current_timestamp(6) on update current_timestamp(6)")
    private LocalDateTime updateDate;

    @NonNull
    @Column(unique = true, nullable = false, updatable = false, length = 38)
    private String username;

    @Size(min = 2, max = 10)
    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @NotBlank
    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>(0);
        authorities.add(new SimpleGrantedAuthority(role.name()));
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
