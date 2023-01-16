package com.ssafy.api.service;

import com.ssafy.api.request.ConferenceRegisterPostReq;
import com.ssafy.api.request.UserModifyPatchReq;
import com.ssafy.api.request.UserRegisterPostReq;
import com.ssafy.db.entity.Conference;
import com.ssafy.db.entity.User;

/**
 *	회의 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface ConferenceService {
	Conference createConference(ConferenceRegisterPostReq conferenceRegisterInfo);

	void deleteConference(Long id);
}
