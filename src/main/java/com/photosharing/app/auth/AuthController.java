package com.photosharing.app.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
//@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthService authService;

    @PostMapping("/auth/signin")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody Login login, BindingResult bindingResult) {
        Role role = new Role();
        role.setAuthority("ROLE_USER");
        List<Role> authorities = new ArrayList<>();
        authorities.add(role);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword(), authorities));
        //SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtService.createToken(login.getUsername());
        JwtResponse response = new JwtResponse(token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/auth/register")
    public ResponseEntity<JwtResponse> register(@Valid @RequestBody Register register, BindingResult bindingResult) {
        authService.register(register);
        String token = jwtService.createToken(register.getUsername());
        JwtResponse response = new JwtResponse(token);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    private void authenticate(String username, String password) throws RuntimeException {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (DisabledException e) {
            throw new RuntimeException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new RuntimeException("INVALID_CREDENTIALS", e);
        }
    }

}