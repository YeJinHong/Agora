package com.ssafy.api.service;

import com.ssafy.api.request.UserModifyPasswordReq;
import com.ssafy.api.request.UserModifyPatchReq;
import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.api.request.UserReissuePostReq;
import com.ssafy.common.auth.TokenInfo;
import com.ssafy.entity.rdbms.User;
import org.springframework.http.ResponseEntity;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface UserService {
	User createUser(UserRegisterPostReq userRegisterInfo);
	User getUserByUserEmail(String userEmail);
	void updateUser(String userEmail, UserModifyPatchReq req);
	void modifyUserPassword(String userEmail, UserModifyPasswordReq req);

	void modifyUserPasswordByEmail(String userEmail, String password);
	void deleteUser(String userEmail);
	boolean checkExist(String userEmail);
	ResponseEntity<?> reissue(UserReissuePostReq userReissuePostReq);
	TokenInfo login(User user);

}
