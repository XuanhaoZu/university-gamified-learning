package com.zu.universitygamifiedlearning.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class NewsScheduler {
    private final NewsService newsService;

    public NewsScheduler(NewsService newsService) {
        this.newsService = newsService;
    }

    @Scheduled(fixedRate = 3600000) // 每小时执行一次
    public void updateNews() {
        newsService.fetchAndStoreNews();
    }
}
