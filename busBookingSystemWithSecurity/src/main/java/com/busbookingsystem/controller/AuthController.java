package com.busbookingsystem.controller;

import com.busbookingsystem.dto.AuthRequest;
import com.busbookingsystem.dto.AuthResponse;
import com.busbookingsystem.exception.NotFoundException;
import com.busbookingsystem.utility.JwtUtility;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name="AuthController")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtility jwtUtility;
    public AuthController(AuthenticationManager authenticationManager,JwtUtility jwtUtility){
        this.jwtUtility=jwtUtility;
        this.authenticationManager=authenticationManager;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> genrateToken(@RequestBody AuthRequest authRequest){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
             String token=jwtUtility.generateToken(authRequest.getUsername());
             AuthResponse response= new AuthResponse(token,"User Authenticated Successfully");
             return ResponseEntity.ok(response);

        }catch (Exception e){
            throw new NotFoundException("does not authenticate "+e);
        }

    }
}
