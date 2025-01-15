package com.zu.universitygamifiedlearning.service;


import com.zu.universitygamifiedlearning.repository.LearningContentRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Component
public class ContentCleanupScheduler {

    private final LearningContentRepository contentRepository;

    public ContentCleanupScheduler(LearningContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    // 每天凌晨 2 点清理 30 天之前的内容
    @Scheduled(cron = "0 0 2 * * ?") // CRON 表达式: 秒 分 时 日 月 周
    public void cleanOldContent() {
        // 计算 30 天前的日期
        Date thirtyDaysAgo = Date.from(LocalDate.now().minusDays(30).atStartOfDay(ZoneId.systemDefault()).toInstant());

        // 删除数据库中创建日期早于 30 天前的内容
        int deletedCount = contentRepository.deleteByCreatedAtBefore(thirtyDaysAgo);

        // 打印日志（可选）
        System.out.println("Cleaned up " + deletedCount + " old content entries.");
    }
}

