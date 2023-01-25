package com.ssafy.api.service;

import com.ssafy.api.request.DebateRegisterPostReq;
import com.ssafy.entity.Debate;

/**
 *	토론 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface DebateService {
	Debate createDebate(DebateRegisterPostReq debateRegisterPostReq);

	void deleteDebate(Long id);
}
