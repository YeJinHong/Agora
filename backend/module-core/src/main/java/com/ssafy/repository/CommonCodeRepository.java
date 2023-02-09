package com.ssafy.repository;

import com.ssafy.entity.rdbms.CommonCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommonCodeRepository extends JpaRepository<CommonCode, Long> {

    List<CommonCode> findByParentId(Long parentId);

}
