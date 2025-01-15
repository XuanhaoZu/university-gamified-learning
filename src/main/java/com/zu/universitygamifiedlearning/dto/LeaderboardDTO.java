package com.zu.universitygamifiedlearning.dto;

public class LeaderboardDTO {
    private Long userId;
    private String username;
    private int currentExperience;

    public LeaderboardDTO(Long userId, String username, int currentExperience) {
        this.userId = userId;
        this.username = username;
        this.currentExperience = currentExperience;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getCurrentExperience() {
        return currentExperience;
    }

    public void setCurrentExperience(int currentExperience) {
        this.currentExperience = currentExperience;
    }
}
