package com.ssafy.repository;

import com.ssafy.entity.rdbms.UserDebate;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDebateRepository extends JpaRepository<UserDebate, Long> {

    @Query("select ud from UserDebate ud left join fetch ud.debate where ud.user.id = :userId")
    List<UserDebate> findAllByUserId(@Param(value = "userId") Long userId);
=======
import org.springframework.stereotype.Repository;

@Repository
public interface UserDebateRepository extends JpaRepository<UserDebate, Long> {
>>>>>>> 42754e0880166f042d20dbf61c552cf03bdd1b3e
}
