package com.ssafy.repository;

import com.ssafy.entity.rdbms.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
}
