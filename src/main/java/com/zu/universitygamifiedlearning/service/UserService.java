package com.zu.universitygamifiedlearning.service;

import com.zu.universitygamifiedlearning.model.AnimalType;
import com.zu.universitygamifiedlearning.model.User;
import com.zu.universitygamifiedlearning.model.UserAnimal;
import com.zu.universitygamifiedlearning.repository.AnimalTypeRepository;
import com.zu.universitygamifiedlearning.repository.UserAnimalRepository;
import com.zu.universitygamifiedlearning.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserAnimalRepository userAnimalRepository;
    private final AnimalTypeRepository animalTypeRepository;
    public UserService(UserRepository userRepository,
                       UserAnimalRepository userAnimalRepository,
                       AnimalTypeRepository animalTypeRepository) {
        this.userRepository = userRepository;
        this.userAnimalRepository = userAnimalRepository;
        this.animalTypeRepository = animalTypeRepository;
    }

    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        String[] roles = user.getRoles() != null
                ? user.getRoles().stream()
                .map(role -> role.getName().name().replace("ROLE_", ""))
                .toArray(String[]::new)
                : new String[0];
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName().name()))  // 保留 ROLE_ 前缀
                        .toList())  // 转换为 List

                .build();
    }

    public User registerUser(User user) {
        // 保存用户
        User savedUser = userRepository.save(user);

        // 查询初始动物种类（假设等级 1 是初始等级）
        AnimalType initialAnimalType = animalTypeRepository.findByNameAndLevel("cat", 1)
                .orElseThrow(() -> new RuntimeException("Initial animal type not found"));

        // 创建用户的初始动物
        UserAnimal userAnimal = new UserAnimal();
        userAnimal.setUser(savedUser);
        userAnimal.setAnimalType(initialAnimalType);
        userAnimal.setCurrentExperience(0);

        // 保存用户动物
        userAnimalRepository.save(userAnimal);

        return savedUser;
    }
}
