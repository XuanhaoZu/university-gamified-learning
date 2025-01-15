package com.zu.universitygamifiedlearning.service;

import com.zu.universitygamifiedlearning.dto.YouTubeApiResponse;
import com.zu.universitygamifiedlearning.model.LearningContent;
import com.zu.universitygamifiedlearning.repository.LearningContentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class YouTubeService {
    private final RestTemplate restTemplate;
    private final LearningContentRepository contentRepository;
    private final String apiKey = "AIzaSyD5l3PVUkaOjkLN5vyykbUIPidt_TgITP4"; // 替换为你的 YouTube API Key
    private final String youtubeApiUrl = "https://www.googleapis.com/youtube/v3/search?part=snippet&q=education&type=video&key=" + apiKey;

    public YouTubeService(RestTemplate restTemplate, LearningContentRepository contentRepository) {
        this.restTemplate = restTemplate;
        this.contentRepository = contentRepository;
    }

    public void fetchAndStoreVideos() {
        YouTubeApiResponse response = restTemplate.getForObject(youtubeApiUrl, YouTubeApiResponse.class);

        if (response != null && response.getItems() != null) {
            // 使用 Arrays.stream 流式处理
            Arrays.stream(response.getItems())
                    .map(item -> {
                        String videoUrl = "https://www.youtube.com/watch?v=" + item.getId().getVideoId();

                        // 检查是否已存在
                        if (!contentRepository.existsBySourceUrl(videoUrl)) {
                            LearningContent content = new LearningContent();
                            content.setTitle(item.getSnippet().getTitle());
                            content.setDescription(item.getSnippet().getDescription());
                            content.setContentType("VIDEO");
                            content.setSourceUrl(videoUrl);
                            return content;
                        }
                        return null; // 对于已存在的内容返回 null
                    })
                    .filter(content -> content != null) // 过滤掉 null 值
                    .forEach(contentRepository::save); // 保存新的内容
        }
    }

}
