package com.ssafy.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.entity.QUser;
import com.ssafy.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 유저 모델 관련 디비 쿼리 생성을 위한 구현 정의.
 */
@Repository
public class UserRepositorySupport {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QUser qUser = QUser.user;

    public Optional<User> findUserByUserId(String userEmail) {
        User user = jpaQueryFactory.select(qUser).from(qUser)
                .where(qUser.userEmail.eq(userEmail)).fetchOne();
        if(user == null) return Optional.empty();
        return Optional.ofNullable(user);
    }
}
