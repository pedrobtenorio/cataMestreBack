package com.p3p.catamestre.Service;

import com.p3p.catamestre.Domain.AuthResponse;
import com.p3p.catamestre.Domain.Studies;
import com.p3p.catamestre.Domain.User;
import com.p3p.catamestre.Repository.UserRepository;
import com.p3p.catamestre.Security.Credential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.ResponseEntity;
import com.p3p.catamestre.Security.JwtTokenUtil;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;


import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    final private PasswordEncoder passwordEncoder;
    final private UserRepository userRepository;

    final private AuthenticationManager authManager;
    final private JwtTokenUtil jwtTokenUtil;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, AuthenticationManager authManager, JwtTokenUtil jwtTokenUtil) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.authManager = authManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getLoggedUser(Principal principal) {


        Optional<User> userOptional = userRepository.findByEmail(principal.getName());
        if(userOptional.isPresent()){
           return userOptional.get();
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    public User getUserByEmail(String email) {
        Optional<User> userOpt = this.userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            return userOpt.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    public User createUser(User user) throws Exception {
        if (userRepository.findByUsername(user.getUsername()).isPresent() || userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        if (!validateEmail(user.getEmail())) {
            throw new Exception("Invalid email address: " + user.getEmail());
        }
        user.setPassword(this.encodePassword(user.getPassword()));


        return userRepository.save(user);
    }


    public User updateUser(Long id, User user) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(this.encodePassword(user.getPassword()));
        existingUser.setRole(user.getRole());
        return userRepository.save(existingUser);
    }


    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User AddClasses(Long id, Set<Studies> studies) {
        Optional<User> userOptional = this.userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.getStudies().addAll(studies);
            return this.userRepository.save(user);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

    }

    public ResponseEntity<AuthResponse> authenticate(Credential credential) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            credential.getEmail(), credential.getPassword())
            );

            User user = (User) authentication.getPrincipal();
            String accessToken = jwtTokenUtil.generateAccessToken(user);
            AuthResponse response = new AuthResponse(user.getEmail(), accessToken);

            return ResponseEntity.ok().body(response);

        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    private static boolean validateEmail(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
