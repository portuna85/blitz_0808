package com.blitz.springboot.controller;

import com.blitz.springboot.service.UserService;
import com.blitz.springboot.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class UserApiController {

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    @PutMapping("/user")
    public ResponseEntity<String> modify(@RequestBody UserDto.Request dto) {
        userService.modify(dto);

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
