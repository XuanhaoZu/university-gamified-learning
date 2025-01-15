package com.zu.universitygamifiedlearning.dto;

import java.util.Date;

public class CommentDTO {
    private Long id;          // 评论的唯一标识
    private String username;  // 发布评论的用户名
    private String comment;   // 评论内容
    private Date createdAt;   // 评论的创建时间

    // Constructor
    public CommentDTO(Long id, String username, String comment, Date createdAt) {
        this.id = id;
        this.username = username;
        this.comment = comment;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
