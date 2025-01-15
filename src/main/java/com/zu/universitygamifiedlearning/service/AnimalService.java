package com.zu.universitygamifiedlearning.service;


import com.zu.universitygamifiedlearning.dto.LeaderboardDTO;
import com.zu.universitygamifiedlearning.model.AnimalType;
import com.zu.universitygamifiedlearning.model.UserAnimal;
import com.zu.universitygamifiedlearning.repository.AnimalTypeRepository;
import com.zu.universitygamifiedlearning.repository.UserAnimalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {
    private final UserAnimalRepository userAnimalRepository;
    private final AnimalTypeRepository animalTypeRepository;

    public AnimalService(UserAnimalRepository userAnimalRepository, AnimalTypeRepository animalTypeRepository) {
        this.userAnimalRepository = userAnimalRepository;
        this.animalTypeRepository = animalTypeRepository;
    }

    // 为用户增加经验值
    public void addExperience(Long userId, int experience) {
        // 获取用户动物状态
        UserAnimal userAnimal = userAnimalRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User animal not found"));

        // 增加经验值
        userAnimal.setCurrentExperience(userAnimal.getCurrentExperience() + experience);

        // 查询下一级别动物
        AnimalType currentType = userAnimal.getAnimalType();
        Optional<AnimalType> nextLevel = animalTypeRepository.findFirstByLevelGreaterThanOrderByLevelAsc(currentType.getLevel());

        // 如果有下一级，且经验值足够，升级
        if (nextLevel.isPresent() && userAnimal.getCurrentExperience() >= nextLevel.get().getRequiredExperience()) {
            userAnimal.setAnimalType(nextLevel.get());
        }

        // 保存用户动物状态
        userAnimalRepository.save(userAnimal);
    }

    // 获取用户当前动物状态
    public UserAnimal getUserAnimal(Long userId) {
        return userAnimalRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User animal not found"));
    }

    public List<LeaderboardDTO> getTop10AnimalLeaderboard() {
        return userAnimalRepository.findTop10ByOrderByExperienceDesc()
                .stream()
                .map(ua -> new LeaderboardDTO(
                        ua.getUser().getId(),
                        ua.getUser().getUsername(),
                        ua.getCurrentExperience()
                ))
                .toList();
    }

    // 获取当前用户的排名
    public int getUserRank(Long userId) {
        UserAnimal userAnimal = userAnimalRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User animal not found"));

        int userExperience = userAnimal.getCurrentExperience();
        return userAnimalRepository.findUserRankByExperience(userExperience);
    }
}

