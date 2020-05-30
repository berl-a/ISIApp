package com.berla.pwrapps.isiapp.security.jwt

import com.berla.pwrapps.isiapp.model.Status
import com.berla.pwrapps.isiapp.model.User
import com.berla.pwrapps.isiapp.model.Role
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

import java.util.stream.Collectors

public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                mapToGrantedAuthorities(new ArrayList<>(user.getRoles())),
                user.getStatus().equals(Status.ACTIVE),
                user.getUpdated()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> userRoles) {
        return userRoles.stream()
                .map({ role ->
                    new SimpleGrantedAuthority(role.getName())
                }
                ).collect(Collectors.toList());
    }
}
