package com.ssafy.repository;

import org.springframework.data.repository.CrudRepository;

public interface RedisRepository extends CrudRepository<RefreshToken,String> {

}
