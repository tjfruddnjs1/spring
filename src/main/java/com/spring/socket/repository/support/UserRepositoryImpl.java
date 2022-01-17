package com.spring.socket.repository.support;

import com.spring.socket.domain.QUser;
import com.spring.socket.repository.custom.CustomUserRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class UserRepositoryImpl extends QuerydslRepositorySupport implements
    CustomUserRepository {

    private final QUser user = QUser.user;

    public UserRepositoryImpl() {
        super(QUser.class);
    }
}
