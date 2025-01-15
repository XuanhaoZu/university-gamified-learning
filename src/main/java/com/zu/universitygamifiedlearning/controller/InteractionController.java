package com.zu.universitygamifiedlearning.controller;

import com.zu.universitygamifiedlearning.dto.InteractionRequest;
import com.zu.universitygamifiedlearning.model.UserInteraction;
import com.zu.universitygamifiedlearning.service.AnimalService;
import com.zu.universitygamifiedlearning.service.InteractionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interactions")
public class InteractionController {

    private final InteractionService interactionService;
    private final AnimalService animalService;

    public InteractionController(InteractionService interactionService, AnimalService animalService) {
        this.interactionService = interactionService;
        this.animalService = animalService;
    }

    // 通用互动接口：支持评论、点赞等
    @PostMapping("/interact")
    public String interact(@RequestBody InteractionRequest interactionRequest) {
        Long userId = interactionRequest.getUserId();
        Long contentId = interactionRequest.getContentId();
        String interactionType = interactionRequest.getInteractionType();

        if ("COMMENT".equalsIgnoreCase(interactionType)) {
            interactionService.addComment(userId, contentId, interactionRequest.getComment());
            animalService.addExperience(userId, 20); // 评论奖励 20 经验值
            return "Comment added! Experience added.";
        } else if ("LIKE".equalsIgnoreCase(interactionType)) {
            interactionService.addLike(userId, contentId);
            animalService.addExperience(userId, 10); // 点赞奖励 10 经验值
            return "Like added! Experience added.";
        } else {
            throw new IllegalArgumentException("Invalid interaction type");
        }
    }


}
