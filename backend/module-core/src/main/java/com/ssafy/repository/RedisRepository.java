package com.ssafy.repository;


import com.ssafy.entity.redis.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RedisRepository extends CrudRepository<RefreshToken,String> {

}
