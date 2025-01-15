package com.zu.universitygamifiedlearning.service;

import com.zu.universitygamifiedlearning.dto.NewsApiResponse;
import com.zu.universitygamifiedlearning.model.LearningContent;
import com.zu.universitygamifiedlearning.repository.LearningContentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class NewsService {
    private final RestTemplate restTemplate;
    private final LearningContentRepository contentRepository;
    private final String apiKey = "385aafffb0d74093bb661fbd34bfc025"; // 替换为你的 NewsAPI Key
    private final String newsApiUrl = "https://newsapi.org/v2/everything?q=education&apiKey=" + apiKey;

    public NewsService(RestTemplate restTemplate, LearningContentRepository contentRepository) {
        this.restTemplate = restTemplate;
        this.contentRepository = contentRepository;
    }

    public void fetchAndStoreNews() {
        // 调用 NewsAPI
        NewsApiResponse response = restTemplate.getForObject(newsApiUrl, NewsApiResponse.class);

        if (response != null && response.getArticles() != null) {
            // 将新闻数据存储为课程内容
            Arrays.stream(response.getArticles()).forEach(article -> {
                if (!contentRepository.existsBySourceUrl(article.getUrl())) {
                    LearningContent content = new LearningContent();
                    content.setTitle(article.getTitle());
                    content.setDescription(article.getDescription());
                    content.setContentType("NEWS");
                    content.setSourceUrl(article.getUrl());
                    contentRepository.save(content);
                }
            });
        }
    }
}
