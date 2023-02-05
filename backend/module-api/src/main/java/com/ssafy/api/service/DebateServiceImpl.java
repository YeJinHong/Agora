package com.ssafy.api.service;

import com.ssafy.api.request.DebateGetDebatesGetReq;
import com.ssafy.api.request.DebateRegisterPostReq;
import com.ssafy.api.request.PerspectiveBase;
import com.ssafy.api.service.converter.DebateConverter;
import com.ssafy.entity.rdbms.Debate;
import com.ssafy.entity.rdbms.Perspective;
import com.ssafy.entity.rdbms.User;
import com.ssafy.repository.DebateRepository;
import com.ssafy.repository.DebateResultRepository;
import com.ssafy.repository.PerspectiveRepository;
import com.ssafy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * 토론 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("debateService")
@RequiredArgsConstructor
public class DebateServiceImpl implements DebateService {

    private final UserRepository userRepository;

    private final DebateRepository debateRepository;

    private final DebateResultRepository debateResultRepository;

    private final PerspectiveRepository perspectiveRepository;

    @Override
    @Transactional
    public Debate createDebate(DebateRegisterPostReq debateRegisterPostReq) {
        User owner = userRepository.findById(debateRegisterPostReq.getOwnerId())
                .orElseThrow(NoSuchElementException::new);
        Debate debate = makeDebate(debateRegisterPostReq, owner);
        Debate savedDebate = debateRepository.save(debate);

        List<Perspective> perspectives = debateRegisterPostReq.getPerspectiveNames()
                .stream()
                .map(perspectiveName -> {
                    Perspective perspective = new Perspective();
                    perspective.setDebate(savedDebate);
                    perspective.setName(perspectiveName);
                    return perspective;
                })
                .collect(Collectors.toList());
        perspectiveRepository.saveAll(perspectives);
        return savedDebate;
    }

    @Override
    public List<Debate> getDebates(DebateGetDebatesGetReq debateReq) {
//        debateRepository.getUser
        return null;
    }

    private Debate makeDebate(DebateRegisterPostReq debateRegisterPostReq, User owner) {
        Debate debate = new Debate();
        debate.setOwner(owner);
        debate.setCategory(debateRegisterPostReq.getCategory());
        debate.setTitle(debateRegisterPostReq.getTitle());
        debate.setDescription(debateRegisterPostReq.getDescription());
        debate.setModeratorOnOff(debateRegisterPostReq.getModeratorOnOff());
        debate.setDebateMode(debate.getDebateMode());
        debate.setThumbnailUrl(debateRegisterPostReq.getThumbnailUrl());
        debate.setState(debate.getState());
        debate.setInsertedTime(debateRegisterPostReq.getInsertedTime());
        debate.setCallStartTime(debateRegisterPostReq.getCallStartTime());
        debate.setCallEndTime(debateRegisterPostReq.getCallEndTime());
        debate.setDebateModeOption(debateRegisterPostReq.getDebateModeOption());
        return debate;
    }

    @Override
    public void deleteDebate(Long id) {
        Debate debate = debateRepository.findById(id).orElseThrow(NoSuchElementException::new);
        debateRepository.delete(debate);
    }

}
