package com.marius.vila.authentication.service;

import com.marius.vila.authentication.dto.LoginDto;
import com.marius.vila.authentication.dto.RegisterDto;
import com.marius.vila.authentication.model.DbUser;
import com.marius.vila.authentication.model.UserType;
import com.marius.vila.authentication.repository.UserRepository;
import com.marius.vila.authentication.repository.UserTypeRepository;
import com.marius.vila.security.JwtTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationService {
    private final UserRepository userRepository;
    private final UserTypeRepository userTypeRepository;
    private final JwtTokenService jwtTokenService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public DbUser getUserById(Long id) {
        return userRepository.getOne(id);
    }

    public ResponseEntity<String> register(RegisterDto req) {
        ResponseEntity<String> validation = validateRegistration(req);
        if (validation.getStatusCode().equals(HttpStatus.OK)) {
            DbUser newAppUser = createUserFromRequest(req);

            userRepository.save(newAppUser);
        }
        return validation;
    }

    public ResponseEntity<?> login(LoginDto req, HttpServletResponse res) {
        try {
            String email = req.getEmail();
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, req.getPassword()));

            List<String> roles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
            Optional<DbUser> user = userRepository.getByEmail(email);

            String token = jwtTokenService.createToken(email, roles);

            Map<Object, Object> model = new HashMap<>();
            model.put("email", email);
            model.put("roles", roles);
            model.put("token", token);
            if(user.isPresent()) {
                model.put("id", user.get().getId());
                model.put("firstName", user.get().getFirstName());
                model.put("userTypeName", user.get().getUserType().getName());
            }
            Cookie cookie = new Cookie("token", token);
            cookie.setMaxAge(606024);
            cookie.setHttpOnly(true);
            res.addCookie(cookie);

            return ResponseEntity.ok(model);
        } catch (UsernameNotFoundException | BadCredentialsException e) {
            return new ResponseEntity<>("Invalid username/password supplied!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private ResponseEntity<String> validateRegistration(RegisterDto req) {
        Optional<DbUser> userOptional = userRepository.getByEmail(req.getEmail());

        return userOptional.isPresent() ? new ResponseEntity<>("Email already exists!", HttpStatus.INTERNAL_SERVER_ERROR)
                : new ResponseEntity<>("Registration successful", HttpStatus.OK);
    }

    private DbUser createUserFromRequest(RegisterDto req) {
        DbUser user = new DbUser();
        UserType userType = userTypeRepository.getOne(Long.parseLong(req.getUserTypeId()));
        user.setEmail(req.getEmail());
        user.setPhoneNumber(req.getPhoneNumber());
        user.setFirstName(req.getFirstName());
        user.setLastName(req.getLastName());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setUserType(userType);
        return user;
    }
}
