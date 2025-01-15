package com.zu.universitygamifiedlearning.repository;

import com.zu.universitygamifiedlearning.model.LearningContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface LearningContentRepository extends JpaRepository<LearningContent, Long> {
    boolean existsBySourceUrl(String sourceUrl);
    int deleteByCreatedAtBefore(Date date);
}
