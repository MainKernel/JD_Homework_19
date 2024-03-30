package com.simplenotes.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Table(name = "users")
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_icon")
    private String userIcon;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "password")
    private String password;
    @Enumerated(value = EnumType.STRING)
    @Builder.Default
    private ServerRole role = ServerRole.USER;
    @Column(name = "is_non_expired")
    @Builder.Default
    private boolean isAccountNonExpired = true;
    @Column(name = "is_account_non_locked")
    @Builder.Default
    private boolean isAccountNonLocked = true;
    @Column(name = "is_credentials_non_expired")
    @Builder.Default
    private boolean isCredentialsNonExpired = true;
    @Column(name = "is_enabled")
    @Builder.Default
    private boolean isEnabled = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    public enum ServerRole {
        USER,
        ADMIN
    }
}
