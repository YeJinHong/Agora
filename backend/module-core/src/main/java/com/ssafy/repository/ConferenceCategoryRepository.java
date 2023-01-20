package com.ssafy.repository;

import com.ssafy.entity.ConferenceCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 회의 카테고리 모델 관련 디비 쿼리 생성을 위한 JPA Query Method 인터페이스 정의.
 */
@Repository
public interface ConferenceCategoryRepository extends JpaRepository<ConferenceCategory, Long> {

}