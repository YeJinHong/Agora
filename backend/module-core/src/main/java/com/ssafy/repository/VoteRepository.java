package com.ssafy.repository;

import com.ssafy.entity.rdbms.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * 청중 투표 모델 관련 디비 쿼리 생성을 위한 JPA Query Method 인터페이스 정의.
 */
public interface VoteRepository extends JpaRepository<Vote, Long> {
    List<Vote> findByDebateId(Long debateId);
}
