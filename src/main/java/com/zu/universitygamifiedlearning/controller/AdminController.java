package com.zu.universitygamifiedlearning.controller;

import com.zu.universitygamifiedlearning.model.LearningContent;
import com.zu.universitygamifiedlearning.service.AnimalService;
import com.zu.universitygamifiedlearning.service.InteractionService;
import com.zu.universitygamifiedlearning.service.LearningContentService;
import com.zu.universitygamifiedlearning.service.NewsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final LearningContentService service;
    private final AnimalService animalService;
    private final InteractionService interactionService;

    private final NewsService newsService;

    public AdminController(LearningContentService service, AnimalService animalService, InteractionService interactionService, NewsService newsService) {
        this.service = service;
        this.animalService = animalService;
        this.interactionService = interactionService;
        this.newsService = newsService;
    }

    @GetMapping("/dashboard")
    public String adminDashboard() {
        return "Welcome to the Admin Dashboard!";
    }

    @PostMapping("/learning-content")
    public LearningContent createContent(@RequestBody LearningContent content) {
        return service.createLearningContent(content);
    }

    @DeleteMapping("/learning-content/{id}")
    public String deleteContent(@PathVariable Long id) {
        service.deleteLearningContent(id);
        return "Content deleted successfully!";
    }

}
