package com.ssafy.api.service;

import com.ssafy.api.request.DebateRegisterPostReq;
import com.ssafy.entity.Debate;
import com.ssafy.repository.DebateResultRepository;
import com.ssafy.repository.DebateRepository;
import com.ssafy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

/**
 *	토론 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("debateService")
@RequiredArgsConstructor
public class DebateServiceImpl implements DebateService {

	private final UserRepository userRepository;

	private final DebateRepository debateRepository;

	private final DebateResultRepository debateResultRepository;

	@Override
	public Debate createDebate(DebateRegisterPostReq debateRegisterPostReq) {
		Debate debate = new Debate();
		debate.setOwner(userRepository.findById(debateRegisterPostReq.getOwnerId()).orElseThrow(NoSuchElementException::new));
		debate.setCallStartTime(debateRegisterPostReq.getCallStartTime());
		debate.setCallEndTime(debateRegisterPostReq.getCallEndTime());
		debate.setTitle(debateRegisterPostReq.getTitle());
		debate.setThumbnailUrl(debateRegisterPostReq.getThumbnailUrl());
		debate.setDescription(debateRegisterPostReq.getDescription());
		return debateRepository.save(debate);
	}

	@Override
	public void deleteDebate(Long id) {
		Debate debate = debateRepository.findById(id).orElseThrow(NoSuchElementException::new);
		debateRepository.delete(debate);
	}

}
