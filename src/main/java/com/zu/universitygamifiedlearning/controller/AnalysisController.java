package com.zu.universitygamifiedlearning.controller;

import com.zu.universitygamifiedlearning.service.InteractionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/analysis")
public class AnalysisController {
    private final InteractionService interactionService;

    public AnalysisController(InteractionService interactionService) {
        this.interactionService = interactionService;
    }

    @GetMapping("/user/{userId}/completed-courses")
    public int getCompletedCoursesByUser(@PathVariable Long userId) {
        return interactionService.countCompletedCoursesByUserId(userId);
    }

    @GetMapping("/content/{contentId}/interactions")
    public int getTotalInteractionsForContent(@PathVariable Long contentId) {
        return interactionService.countInteractionsByContentId(contentId);
    }
}
