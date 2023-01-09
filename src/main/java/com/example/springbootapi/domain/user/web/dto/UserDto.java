package com.example.springbootapi.domain.user.web.dto;

import com.example.springbootapi.domain.user.entity.Role;
import com.example.springbootapi.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserDto {

    private String userName;

    private String email;

    private String birth;

    private String nickName;

    private String callNumber;

    private Role roles;

    public UserDto(User entity) {
        this.email = entity.getEmail();
        this.birth = entity.getBirth();
        this.nickName = entity.getNickName();
        this.callNumber = entity.getCallNumber();
        this.roles = entity.getRole();
    }

    public User toEntity() {
        return User
                .builder()
                .email(email)
                .userName(userName)
                .birth(birth)
                .nickName(nickName)
                .callNumber(callNumber)
                .role(Role.USER)
                .build();
    }
}
