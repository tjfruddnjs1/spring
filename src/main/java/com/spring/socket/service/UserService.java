package com.spring.socket.service;

import com.spring.socket.domain.User;
import com.spring.socket.enumerate.Role;
import com.spring.socket.repository.UserRepository;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    @NonNull
    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return userRepository.findByUsername(userName)
            .orElseThrow(() -> new UsernameNotFoundException("일치하는 사용자를 찾을 수 없습니다."));
    }

    @Transactional
    public void save(User user) {
        if (user.getPassword() != null) {
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        }
        user.setRole(Role.ROLE_USER);
        userRepository.save(user);
    }
}
