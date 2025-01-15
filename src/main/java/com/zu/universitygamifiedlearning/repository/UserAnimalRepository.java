package com.zu.universitygamifiedlearning.repository;

import com.zu.universitygamifiedlearning.model.UserAnimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserAnimalRepository extends JpaRepository<UserAnimal, Long> {

    // 根据用户 ID 查询用户的动物信息
    Optional<UserAnimal> findByUserId(Long userId);

    @Query("SELECT ua FROM UserAnimal ua ORDER BY ua.currentExperience DESC")
    List<UserAnimal> findTop10ByOrderByExperienceDesc();

    @Query("SELECT COUNT(ua) + 1 FROM UserAnimal ua WHERE ua.currentExperience > :experience")
    int findUserRankByExperience(@Param("experience") int experience);


}

