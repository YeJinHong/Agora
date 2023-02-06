package com.ssafy.repository;

import com.ssafy.entity.rdbms.Perspective;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 청중 투표(관점) 관련 디비 쿼리 생성을 위한 JPA Query Method 인터페이스 정의.
 */
public interface PerspectiveRepository extends JpaRepository<Perspective, Long> {
}
