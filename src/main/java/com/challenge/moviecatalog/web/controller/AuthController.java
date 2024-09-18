package com.challenge.moviecatalog.web.controller;

import com.challenge.moviecatalog.model.domain.User;
import com.challenge.moviecatalog.security.JwtUtil;
import com.challenge.moviecatalog.service.SecurityUserDetailsService;
import com.challenge.moviecatalog.service.UserService;
import com.challenge.moviecatalog.web.requests.JwtRquest;
import com.challenge.moviecatalog.web.requests.UserRequest;
import com.challenge.moviecatalog.web.responses.JwtResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final JwtUtil jwtUtil;

    private final SecurityUserDetailsService userDetailsService;

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody JwtRquest jwtRquest) throws Exception {
        authenticate(jwtRquest.getUsername(), jwtRquest.getPassword());

        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRquest.getUsername());

        String token = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.register(userRequest));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }


}
