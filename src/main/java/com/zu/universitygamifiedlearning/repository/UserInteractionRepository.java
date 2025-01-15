package com.zu.universitygamifiedlearning.repository;

import com.zu.universitygamifiedlearning.model.UserInteraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserInteractionRepository extends JpaRepository<UserInteraction, Long> {
    List<UserInteraction> findByLearningContentId(Long contentId);
    Optional<UserInteraction> findByUserIdAndLearningContentIdAndInteractionType(
            Long userId, Long contentId, UserInteraction.InteractionType interactionType
    );

    @Query("SELECT COUNT(ui) FROM UserInteraction ui WHERE ui.learningContent.id = :contentId")
    int countInteractionsByContentId(@Param("contentId") Long contentId);

    // 统计用户完成的课程数
    @Query("SELECT COUNT(ui) FROM UserInteraction ui WHERE ui.user.id = :userId AND ui.interactionType = 'COURSE_COMPLETION'")
    int countCompletedCoursesByUserId(@Param("userId") Long userId);
}


