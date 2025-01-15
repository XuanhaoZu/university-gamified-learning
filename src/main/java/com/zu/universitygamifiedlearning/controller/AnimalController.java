package com.zu.universitygamifiedlearning.controller;

import com.zu.universitygamifiedlearning.model.UserAnimal;
import com.zu.universitygamifiedlearning.service.AnimalService;
import com.zu.universitygamifiedlearning.dto.LeaderboardDTO;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/animal")
public class AnimalController {

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    // 获取用户动物状态
    @GetMapping("/{userId}")
    public UserAnimal getUserAnimal(@PathVariable Long userId) {
        return animalService.getUserAnimal(userId);
    }

    // 增加经验值
    @PostMapping("/{userId}/add-experience")
    public String addExperience(@PathVariable Long userId, @RequestParam int experience) {
        animalService.addExperience(userId, experience);
        return "Experience added!";
    }

    @GetMapping("/leaderboard/top10")
    public List<LeaderboardDTO> getTop10AnimalLeaderboard() {
        return animalService.getTop10AnimalLeaderboard();
    }


    // 获取当前用户的排名
    @GetMapping("/leaderboard/rank/{userId}")
    public int getUserRank(@PathVariable Long userId) {
        return Math.toIntExact(animalService.getUserRank(userId));
    }
}

