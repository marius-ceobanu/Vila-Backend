package com.marius.vila.authentication.controller;

import com.marius.vila.authentication.dto.LoginDto;
import com.marius.vila.authentication.dto.RegisterDto;
import com.marius.vila.authentication.model.DbUser;
import com.marius.vila.authentication.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/vila/v1/authentication")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterDto req) {
        return authenticationService.register(req);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDto req, HttpServletResponse res) {
        return authenticationService.login(req, res);
    }

    @GetMapping("/logout")
    public void logoutUser(HttpSession session, HttpServletResponse res) throws IOException {
        session.removeAttribute("user");
        res.sendRedirect("/");
    }

    @GetMapping("/get-user/{email}")
    public Optional<DbUser> getUserByEmail(@PathVariable String email) {
        return authenticationService.getUserByEmail(email);
    }

    @GetMapping("/is-loged-in")
    public boolean isLoggedIn(HttpSession session) {
        return session.getAttribute("user") != null;
    }
}
