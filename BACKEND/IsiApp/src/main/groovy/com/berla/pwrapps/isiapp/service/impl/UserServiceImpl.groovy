package com.berla.pwrapps.isiapp.service.impl

import com.berla.pwrapps.isiapp.model.Role
import com.berla.pwrapps.isiapp.model.Status
import com.berla.pwrapps.isiapp.model.User
import com.berla.pwrapps.isiapp.repository.RoleRepository
import com.berla.pwrapps.isiapp.repository.UserRepository
import com.berla.pwrapps.isiapp.service.UserService
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final RoleRepository roleRepository;
    @Autowired
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public User register(User user, String[] roleNames) {
        List<Role> roles = new LinkedList<>();
        for(String roleName : roleNames) {
            Role newRole = roleRepository.findByName(roleName);
            if(newRole != null) {
                roles.add(newRole);
            } else {
                log.error("In UserServiceImpl: role with name not found");
            }
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(roles);
        user.setStatus(Status.ACTIVE);

        User registeredUser = userRepository.save(user);

        log.info("IN register - user: {} successfully registered", registeredUser);

        return registeredUser;
    }

    @Override
    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        log.info("IN getAll - {} users found", result.size());
        return result;
    }

    @Override
    public User findByUsername(String username) {
        User result = userRepository.findByUsername(username);
        log.info("IN findByUsername - user: {} found by username: {}", result, username);
        return result;
    }

    @Override
    public User findById(Long id) {
        User result = userRepository.findById(id).orElse(null);

        if (result == null) {
            log.warn("IN findById - no user found by id: {}", id);
            return null;
        }

        log.info("IN findById - user: {} found by id: {}", result);
        return result;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
        log.info("IN delete - user with id: {} successfully deleted");
    }
}