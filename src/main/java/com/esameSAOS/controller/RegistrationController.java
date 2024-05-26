package com.esameSAOS.controller;

import com.esameSAOS.dto.UserDTO;
import com.esameSAOS.dto.User;
import com.esameSAOS.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());

        Optional<User> existingUser = registrationService.findByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            return new ResponseEntity<>("Username già esistente nel database", HttpStatus.BAD_REQUEST);
        }

        registrationService.registerNewUser(user);
        return new ResponseEntity<>("Registrazione avvenuta con successo!", HttpStatus.OK);
    }
}
