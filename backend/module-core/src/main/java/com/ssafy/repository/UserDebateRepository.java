package com.ssafy.repository;

import com.ssafy.entity.rdbms.UserDebate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDebateRepository extends JpaRepository<UserDebate, Long> {
    List<UserDebate> findAllByUserId(Long userId);
}
