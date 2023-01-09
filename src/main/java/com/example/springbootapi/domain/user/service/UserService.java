package com.example.springbootapi.domain.user.service;

import com.example.springbootapi.domain.user.entity.User;
import com.example.springbootapi.domain.user.entity.UserAuthority;
import com.example.springbootapi.domain.user.entity.repository.UserRepository;
import com.example.springbootapi.domain.user.web.dto.UserDto;
import com.example.springbootapi.global.config.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

@RequiredArgsConstructor
@Service
public class UserService {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;



    @Transactional
    public User userSignUp(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public void addAuthority(Long id, String authority){
        userRepository.findById(id).ifPresent(user -> {
            UserAuthority newRole = new UserAuthority(user.getId(), authority);
            if(user.getAuthorities() == null){
                HashSet<UserAuthority> authorities = new HashSet<>();
                authorities.add(newRole);
                user.setAuthorities(authorities);
//                User.builder().authorities(user.getAuthorities());
                userSignUp(user);
            }else if(!user.getAuthorities().contains(newRole)){
                HashSet<UserAuthority> authorities = new HashSet<>(user.getAuthorities());
                authorities.add(newRole);
                user.setAuthorities(authorities);
//                User.builder().authorities(user.getAuthorities());
                userSignUp(user);
            }
        });
    }

    @Transactional
    public String login(UserDto dto) {
        User user = userRepository.findByCallNumber(dto.getCallNumber()).orElseThrow(() -> new IllegalArgumentException("없는 번호 입니다."));
        return jwtTokenProvider.createToken(dto.getCallNumber(), dto.getRoles());
    }


}
