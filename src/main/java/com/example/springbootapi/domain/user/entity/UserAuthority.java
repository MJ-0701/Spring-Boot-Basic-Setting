package com.example.springbootapi.domain.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@IdClass(UserAuthority.class)
@Data
public class UserAuthority implements GrantedAuthority {

    @Id
    private Long id;

    @Id
    private String authority;
}
