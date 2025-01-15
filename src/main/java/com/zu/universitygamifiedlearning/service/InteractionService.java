package com.zu.universitygamifiedlearning.service;

import com.zu.universitygamifiedlearning.dto.CommentDTO;
import com.zu.universitygamifiedlearning.model.LearningContent;
import com.zu.universitygamifiedlearning.model.User;
import com.zu.universitygamifiedlearning.model.UserInteraction;
import com.zu.universitygamifiedlearning.repository.LearningContentRepository;
import com.zu.universitygamifiedlearning.repository.UserInteractionRepository;
import com.zu.universitygamifiedlearning.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InteractionService {
    private final UserInteractionRepository interactionRepository;
    private final UserRepository userRepository;
    private final LearningContentRepository contentRepository;
    public InteractionService(UserInteractionRepository userInteractionRepository,
                              UserRepository userRepository,
                              LearningContentRepository learningContentRepository) {
        this.interactionRepository = userInteractionRepository;
        this.userRepository = userRepository;
        this.contentRepository = learningContentRepository;
    }

    public UserInteraction addComment(Long userId, Long contentId, String comment) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        LearningContent learningContent = contentRepository.findById(contentId).orElseThrow(() -> new RuntimeException("content not found"));
        UserInteraction interaction = new UserInteraction();
        interaction.setUser(user);
        interaction.setLearningContent(learningContent);
        interaction.setComment(comment);
        interaction.setInteractionType(UserInteraction.InteractionType.COMMENT);

        return interactionRepository.save(interaction);
    }

    public void addLike(Long userId, Long contentId) {
        // 检查是否已经点赞
        Optional<UserInteraction> existingLike = interactionRepository.findByUserIdAndLearningContentIdAndInteractionType(
                userId, contentId, UserInteraction.InteractionType.LIKE
        );

        if (existingLike.isPresent() && existingLike.get().isLiked()) {
            throw new RuntimeException("You have already liked this content.");
        }

        // 创建或更新点赞记录
        UserInteraction interaction = existingLike.orElse(new UserInteraction());
        interaction.setUser(userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found")));
        LearningContent content = contentRepository.findById(contentId)
                .orElseThrow(() -> new RuntimeException("Content not found"));

        interaction.setLearningContent(content);
        interaction.setInteractionType(UserInteraction.InteractionType.LIKE);
        interaction.setLiked(true);

        // 更新课程的点赞数
        content.setLikesCount(content.getLikesCount()+1);
        contentRepository.save(content);

        interactionRepository.save(interaction);
    }


    public List<CommentDTO> getCommentsByContentId(Long contentId) {
        List<UserInteraction> interactions = interactionRepository.findByLearningContentId(contentId);

        return interactions.stream()
                .filter(interaction -> interaction.getInteractionType() == UserInteraction.InteractionType.COMMENT)
                .map(interaction -> new CommentDTO(
                        interaction.getId(),
                        interaction.getUser().getUsername(),
                        interaction.getComment(),
                        interaction.getCreatedAt()
                ))
                .collect(Collectors.toList());
    }




    public void recordCourseCompletion(Long userId, Long contentId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        LearningContent content = contentRepository.findById(contentId)
                .orElseThrow(() -> new RuntimeException("Content not found"));

        UserInteraction interaction = new UserInteraction();
        interaction.setUser(user);
        interaction.setLearningContent(content);
        interaction.setInteractionType(UserInteraction.InteractionType.COURSE_COMPLETION);

        interactionRepository.save(interaction);
    }


    // 获取某个课程的总互动数
    public int countInteractionsByContentId(Long contentId) {
        return interactionRepository.countInteractionsByContentId(contentId);
    }

    // 获取用户完成的课程数
    public int countCompletedCoursesByUserId(Long userId) {
        return interactionRepository.countCompletedCoursesByUserId(userId);
    }


}
