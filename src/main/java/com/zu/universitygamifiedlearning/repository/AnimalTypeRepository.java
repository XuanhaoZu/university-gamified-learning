package com.zu.universitygamifiedlearning.repository;

import com.zu.universitygamifiedlearning.model.AnimalType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnimalTypeRepository extends JpaRepository<AnimalType, Long> {

    // 查询下一个动物等级（按等级升序）
    Optional<AnimalType> findFirstByLevelGreaterThanOrderByLevelAsc(int currentLevel);
    Optional<AnimalType> findByNameAndLevel(String name, int level);

}
