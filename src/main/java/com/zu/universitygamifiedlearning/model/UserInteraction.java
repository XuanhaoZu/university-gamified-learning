package com.zu.universitygamifiedlearning.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "user_interactions")
public class UserInteraction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "learning_content_id", nullable = true)
    private LearningContent learningContent;

    @Enumerated(EnumType.STRING)
    private InteractionType interactionType;

    @Column(length = 1000, nullable = true)
    private String comment;

    private boolean liked; // 是否点赞

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();

    public enum InteractionType {
        COMMENT, LIKE, COURSE_COMPLETION
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LearningContent getLearningContent() {
        return learningContent;
    }

    public void setLearningContent(LearningContent learningContent) {
        this.learningContent = learningContent;
    }

    public InteractionType getInteractionType() {
        return interactionType;
    }

    public void setInteractionType(InteractionType interactionType) {
        this.interactionType = interactionType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
