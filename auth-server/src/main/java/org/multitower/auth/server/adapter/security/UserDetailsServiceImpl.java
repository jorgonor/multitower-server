package org.multitower.auth.server.adapter.security;

import lombok.RequiredArgsConstructor;
import org.multitower.user.domain.application.service.UserRepository;
import org.multitower.user.domain.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return new CustomUserDetails(user.getEmail(), user.getPassword(), user.getNickName(), user.getType());
    }

    private static List<GrantedAuthority> getAuthorities() {
        return List.of();
    }
}
