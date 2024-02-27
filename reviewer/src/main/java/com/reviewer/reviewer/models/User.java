package com.reviewer.reviewer.models;

import com.reviewer.reviewer.dto.users.RegisterDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "users")
@Entity(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private String user;
    private String type;
    private String gkz;
    private String manager;

    public User(RegisterDto data) {
        this.name = data.name();
        this.email = data.email();
        this.password = data.password();
        this.user = data.user();
        this.type = data.type();
        this.gkz = data.gkz();
        this.manager = data.manager();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        String role = "ROLE_USER";

        if ("admin".equals(getType())) {
            role = "ROLE_ADMIN";
        }

        return List.of(new SimpleGrantedAuthority(role));

    }

    @Override
    public String getUsername() {
        return email;
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