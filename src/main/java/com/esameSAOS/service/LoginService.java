package com.esameSAOS.service;

import com.esameSAOS.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import com.esameSAOS.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.esameSAOS.dto.User;
import org.springframework.security.crypto.password.PasswordEncoder;
@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<Integer> login(UserDTO userDTO) {
        Optional<User> userOptional = userRepository.findByUsername(userDTO.getUsername());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(userDTO.getPassword(), user.getPassword())) {
                return Optional.of(user.getId().intValue());
            }
        }
        return Optional.of(-1);
    }

}