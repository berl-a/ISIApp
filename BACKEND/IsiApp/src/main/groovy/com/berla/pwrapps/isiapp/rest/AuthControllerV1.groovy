package com.berla.pwrapps.isiapp.rest

import com.berla.pwrapps.isiapp.dto.AuthenticationRequestDto
import com.berla.pwrapps.isiapp.dto.RegistrationRequestDto
import com.berla.pwrapps.isiapp.model.Role
import com.berla.pwrapps.isiapp.model.User
import com.berla.pwrapps.isiapp.repository.RoleRepository
import com.berla.pwrapps.isiapp.security.jwt.JwtTokenProvider
import com.berla.pwrapps.isiapp.service.UserService
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import java.util.stream.Collectors

@RestController
@Slf4j
@RequestMapping(value = "/auth/")
public class AuthControllerV1 {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final RoleRepository roleRepository;

    @Autowired
    AuthControllerV1(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService, RoleRepository roleRepository) {
        this.authenticationManager = authenticationManager
        this.jwtTokenProvider = jwtTokenProvider
        this.userService = userService
        this.roleRepository = roleRepository
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            String username = requestDto.getUsername();

            try {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            } catch (Exception e) {
                log.error("Error: " + e.getMessage());
            }

            if (userService.findByUsername(username) == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }

            String token = jwtTokenProvider.createToken(username);

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);
            log.info("Login successful");
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping("register")
    public ResponseEntity register(@RequestBody RegistrationRequestDto requestDto) {
        User user = new User();
        user.setUsername(requestDto.getUsername());
        user.setPassword(requestDto.getPassword());
        user.setRoles(
                Arrays
                        .stream(requestDto.getRoles())
                        .map(roleRepository.&findByName)
                        .collect(Collectors.toList())
                        as List<Role>
        );
        userService.register(user, requestDto.getRoles());
        return ResponseEntity.ok("User registered");
    }
}