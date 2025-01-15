package com.zu.universitygamifiedlearning.service;

import com.zu.universitygamifiedlearning.model.LearningContent;
import com.zu.universitygamifiedlearning.repository.LearningContentRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LearningContentService {
    private final LearningContentRepository repository;

    public LearningContentService(LearningContentRepository repository) {
        this.repository = repository;
    }

    public LearningContent createLearningContent(LearningContent content) {
        return repository.save(content);
    }
    @Cacheable("learningContents")
    public List<LearningContent> getAllLearningContents() {
        return repository.findAll();
    }
    public void deleteLearningContent(Long id) {
        repository.deleteById(id);
    }

    public int getLikesCount(Long contentId) {
        LearningContent content = repository.findById(contentId)
                .orElseThrow(() -> new RuntimeException("Content not found"));
        return content.getLikesCount();
    }
}
