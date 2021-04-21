package com.marius.vila.security;

import com.marius.vila.authentication.model.DbUser;
import com.marius.vila.authentication.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        DbUser dbUser = userRepository.getByEmail(email)
                        .orElseThrow(() -> new UsernameNotFoundException("Email: " + email + " not found!" ));

        return new User(dbUser.getEmail(), dbUser.getPassword(),
                        dbUser.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
    }
}
