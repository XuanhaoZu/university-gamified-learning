package com.zu.universitygamifiedlearning.controller;

import com.zu.universitygamifiedlearning.model.Role;
import com.zu.universitygamifiedlearning.model.User;
import com.zu.universitygamifiedlearning.repository.AnimalTypeRepository;
import com.zu.universitygamifiedlearning.repository.RoleRepository;
import com.zu.universitygamifiedlearning.repository.UserAnimalRepository;
import com.zu.universitygamifiedlearning.repository.UserRepository;
import com.zu.universitygamifiedlearning.util.JwtUtil;
import com.zu.universitygamifiedlearning.model.AnimalType;
import com.zu.universitygamifiedlearning.model.UserAnimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public String login(@RequestBody Map<String,String> loginRequest) {
        String username = loginRequest.get("username");
        String password = loginRequest.get("password");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
        return jwtUtil.generateToken(authentication);
    }


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AnimalTypeRepository animalTypeRepository;
    @Autowired
    private UserAnimalRepository userAnimalRepository;
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByName(Role.RoleName.ROLE_USER)
                        .orElseThrow(()-> new RuntimeException("Default role not found"));
        user.setRoles(Set.of(userRole));

        User savedUser = userRepository.save(user);

        // 初始化用户动物
        AnimalType initialAnimalType = animalTypeRepository.findByNameAndLevel("cat",1)
                .orElseThrow(() -> new RuntimeException("Initial animal type not found"));

        UserAnimal userAnimal = new UserAnimal();
        userAnimal.setUser(savedUser);
        userAnimal.setAnimalType(initialAnimalType);
        userAnimal.setCurrentExperience(0);

        // 保存用户动物到数据库
        userAnimalRepository.save(userAnimal);

        return "User registered successfully with initial animal!";
    }
}
