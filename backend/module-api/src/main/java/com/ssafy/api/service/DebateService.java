package com.ssafy.api.service;

import com.ssafy.api.request.DebateRegisterPostReq;
import com.ssafy.api.request.PerspectiveBase;
import com.ssafy.entity.rdbms.Debate;
import com.ssafy.entity.rdbms.Perspective;

import java.util.List;

/**
 *	토론 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface DebateService {
	Debate createDebate(DebateRegisterPostReq debateRegisterPostReq);

	Perspective createPerspective(List<PerspectiveBase> perspectiveBaseList);

	void deleteDebate(Long id);
}
