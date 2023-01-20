package com.ssafy.common.util;

import com.ssafy.common.auth.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RedisRepository extends CrudRepository<RefreshToken,String> {

}
