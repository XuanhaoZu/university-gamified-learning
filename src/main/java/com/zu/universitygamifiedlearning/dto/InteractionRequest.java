package com.zu.universitygamifiedlearning.dto;



public class InteractionRequest {
    private Long userId; // 用户ID
    private Long contentId; // 学习内容ID
    private String comment; // 评论内容
    private String interactionType; // 互动类型: COMMENT, LIKE

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getInteractionType() {
        return interactionType;
    }

    public void setInteractionType(String interactionType) {
        this.interactionType = interactionType;
    }
}

