package com.zu.universitygamifiedlearning.controller;

import com.zu.universitygamifiedlearning.dto.CommentDTO;
import com.zu.universitygamifiedlearning.model.LearningContent;
import com.zu.universitygamifiedlearning.model.UserInteraction;
import com.zu.universitygamifiedlearning.service.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/learning-content")
public class LearningContentController {
    private final LearningContentService service;
    private final AnimalService animalService;
    private final InteractionService interactionService;

    private final NewsService newsService;

    private final YouTubeService youtubeService;
    public LearningContentController(LearningContentService service, AnimalService animalService, InteractionService interactionService, NewsService newsService, YouTubeService youtubeService) {
        this.service = service;
        this.animalService = animalService;
        this.interactionService = interactionService;
        this.newsService = newsService;
        this.youtubeService = youtubeService;
    }



    @GetMapping
    public List<LearningContent> getAllContents() {
        return service.getAllLearningContents();
    }


    @GetMapping("/news")
    public String fetchNews() {
        newsService.fetchAndStoreNews(); // 调用 NewsService
        return "News fetched and stored!";
    }

    @GetMapping("/youtube")
    public String fetchVideos() {
        youtubeService.fetchAndStoreVideos();
        return "YouTube videos fetched and stored!";
    }

    @GetMapping("/{id}/likes")
    public int getLikesCount(@PathVariable Long id) {
        return service.getLikesCount(id);
    }

    // 获取课程的所有评论
    @GetMapping("/{contentId}/comments")
    public List<CommentDTO> getComments(@PathVariable Long contentId) {
        return interactionService.getCommentsByContentId(contentId);
    }

    @PostMapping("/{contentId}/complete/{userId}")
    public String completeCourse(@PathVariable Long contentId, @PathVariable Long userId) {
        // 增加经验值
        animalService.addExperience(userId, 100); // 课程奖励 100 经验值
        interactionService.recordCourseCompletion(userId, contentId);

        return "Course completed! Experience added.";
    }
}
